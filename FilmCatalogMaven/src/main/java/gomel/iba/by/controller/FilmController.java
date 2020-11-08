package gomel.iba.by.controller;

import gomel.iba.by.*;
import gomel.iba.by.exceptions.AlreadyExistFilmException;
import gomel.iba.by.exceptions.FilmNotFoundException;
import gomel.iba.by.exceptions.IncorrectFilmException;
import gomel.iba.by.exceptions.ValidationException;
import gomel.iba.by.interfaces.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class works with http requests
 */
@Transactional
@Controller
public class FilmController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilmController.class);

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieGenresRepository movieGenresRepository;

    @Autowired
    private MovieStaffRepository movieStaffRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private GenreRepository genreRepository;

    /**
     * Loads home page with table full of movies
     */
    @GetMapping("/")
    public String getFilmPage(Model model) {
        List<Movie> movies = movieRepository.findAll();
        Iterator<Movie> iterable = movies.iterator();
        String genresString = "";
        String staffString = "";
        String director = "";
        List<ViewMovie> viewMovieList = new ArrayList<>();
        while (iterable.hasNext()) {
            Movie mov = iterable.next();
            int id = mov.getId();
            List<MovieGenres> list = movieGenresRepository.findByIdMovies(id);
            if (!list.isEmpty()) {
                StringBuilder sb = new StringBuilder("");
                for (MovieGenres movieGenres1 : list) {
                    Genre genre = genreRepository.findById(movieGenres1.getIdGenres()).get();
                    sb.append(genre.getName()).append(", ");
                }
                genresString = sb.toString();
                genresString = genresString.substring(0, genresString.length() - 2);
            }
            StringBuilder sb = new StringBuilder("");
            List<MovieStaff> list1 = movieStaffRepository.findByIdMovies(id);
            if (!list1.isEmpty()) {
                for (MovieStaff movieStaff1 : list1) {
                    Staff staff1 = staffRepository.findById(movieStaff1.getIdStaff()).get();
                    if (staff1.getPosition().equals("director")) {
                        director = staff1.getFullName();
                    } else {
                        sb.append(staff1.getFullName()).append(", ");
                    }
                }
                staffString = sb.toString();
                staffString = staffString.substring(0, staffString.length() - 2);
            }
            viewMovieList.add(new ViewMovie(mov, genresString, staffString, director));
        }
        LOGGER.info("Logging...");
        LOGGER.info("{}", viewMovieList);
        model.addAttribute("viewMovieList", viewMovieList);
        return "home";
    }

    /**
     * loads page for adding new movie to storage
     */
    @GetMapping("/add")
    public String addNewFilmPage(Model model) {
        return "add";
    }

    /**
     * Gets new data from form and adds it to the storage
     */
    @PostMapping("/add")
    public String addNewFilm(@RequestParam(required=false) String title, @RequestParam(required=false) Integer year,
                             @RequestParam(required=false) String director, @RequestParam(required=false) String genres,
                             @RequestParam(required=false) String actors) {
        if (title == null || year == null || director == null || genres == null || actors == null) {
            LOGGER.info("Validation");
            throw new ValidationException();
        }
        Optional<Movie> movieOptional = movieRepository.findFirstByTitleAndYear(title, year);
        if (movieOptional.isPresent()) {
           if (staffRepository.findFirstByMoviesIdDirector(movieOptional.get().getId()).isPresent()) {
               LOGGER.info("Film already exists");
               throw new AlreadyExistFilmException();
           }
        }
        int currentYear = LocalDate.now().getYear();
        if (year < 1850 || year > currentYear + 50) {
            LOGGER.info("Incorrect film");
            throw new IncorrectFilmException();
        }
        Movie movie = new Movie(title, year);
        LOGGER.info("{}", movie.toString());
        movieRepository.save(movie);
        Example<Movie> movieExample = Example.of(movie);
        Movie movie1 = movieRepository.findOne(movieExample).get();
        int idMovie = movie1.getId();
        LOGGER.info("{}", movie1);
        if (staffRepository.existsByFullNameAndPosition(director, "director")) {
            movieStaffRepository.save(new MovieStaff(idMovie, staffRepository.findFirstByFullName(director).get().getId()));
        } else {
            Staff staffDirector = new Staff(director, "director");
            staffRepository.save(staffDirector);
            movieStaffRepository.save(new MovieStaff(idMovie, staffDirector.getId()));
        }
        String[] arrayActors = actors.split(",");
        for (String actor : arrayActors) {
            actor = actor.trim();
            if (staffRepository.existsByFullNameAndPosition(actor, "actor")) {
                movieStaffRepository.save(new MovieStaff(idMovie, staffRepository.findFirstByFullName(actor).get().getId()));
            } else {
                Staff staffActor = new Staff(actor, "actor");
                staffRepository.save(staffActor);
                movieStaffRepository.save(new MovieStaff(idMovie, staffActor.getId()));
            }
        }
        String[] arrayGenres = genres.split(",");
        for (String genre : arrayGenres) {
            genre = genre.trim();
            if (genreRepository.existsByName(genre)) {
                movieGenresRepository.save(new MovieGenres(idMovie, genreRepository.findFirstByName(genre).get().getId()));
            } else {
                Genre genre1 = new Genre(genre);
                genreRepository.save(genre1);
                int genreId = genreRepository.findOne(Example.of(genre1)).get().getId();
                movieGenresRepository.save(new MovieGenres(idMovie, genreId));
            }
        }
        return "redirect:/";
    }

    /**
     * Deletes chosen movie from storage
     */
    @GetMapping("delete/{id}")
    public String deleteItem(@PathVariable Integer id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie == null) {
            throw new FilmNotFoundException();
        }
        movieRepository.delete(movie);
        return "redirect:/";
    }

    /**
     * Loads edit page with data of chosen movie
     * @param id film's id
     */
    @GetMapping("edit/{id}")
    public String editItem(@PathVariable Integer id, Model model) {
        Movie movie = movieRepository.findById(id).get();
        LOGGER.info("{}", movie);
        model.addAttribute("movie", movie);
        List<String> genres = genreRepository.getAllGenresByFilm(id);
        StringBuilder sb = new StringBuilder();
        for (String genre : genres) {
            sb.append(genre);
            sb.append(", ");
        }
        sb.deleteCharAt(sb.length() - 2);
        model.addAttribute("genres", sb.toString());
        sb = new StringBuilder();
        List<String> actors = staffRepository.findByMoviesIdActors(id);
        for (String actor : actors) {
            sb.append(actor);
            sb.append(", ");
        }
        sb.deleteCharAt(sb.length() - 2);
        model.addAttribute("actors", sb.toString());
        String director = staffRepository.findFirstByMoviesIdDirector(id).orElse("director");
        model.addAttribute("director", director);
        return "edit";
    }

    /**
     * Switching new data with old
     */
    @PostMapping("edit/{id}")
    public String loadEditedItem(@PathVariable Integer id, @RequestParam String title, @RequestParam Integer year,
                                 @RequestParam String director, @RequestParam String genres,
                                 @RequestParam String actors) {
        Movie movie = movieRepository.findById(id).get();
        movie.setTitle(title);
        movie.setYear(year);
        String[] genre = genres.split(",");
        String[] actor = actors.split(",");
        LOGGER.info("{}", new ArrayList<>(Arrays.asList(genre)));
        LOGGER.info("{}", new ArrayList<>(Arrays.asList(actor)));
        List<Genre> listGenres = Arrays.stream(genre).map(genreName -> genreRepository.findFirstByName(genreName.trim()).orElse(null))
                .collect(Collectors.toList());
        List<String> listNewGenres = Arrays.stream(genre).filter(genreName -> !genreRepository.existsByName(genreName.trim())).collect(Collectors.toList());
        List<Staff> listStaff = Arrays.stream(actor).map(actorName -> staffRepository.findFirstByFullName(actorName.trim()).orElse(null))
                .collect(Collectors.toList());
        List<String> listNewStaff = Arrays.stream(actor).filter(actorName -> !staffRepository.existsByFullNameAndPosition(actorName.trim(), "actor"))
                .collect(Collectors.toList());
        LOGGER.info("{}", listGenres);
        LOGGER.info("{}", listStaff);

        movieRepository.save(movie);
        movieGenresRepository.deleteAllByIdMovies(id);
        movieStaffRepository.deleteAllByIdMovies(id);
        if (staffRepository.findFirstByFullNameAndPosition(director, "director").isEmpty()) {
            staffRepository.save(new Staff(director, "director"));
        }
        movieStaffRepository.save(new MovieStaff(id, staffRepository.findFirstByFullNameAndPosition(director, "director")
                .get().getId()));
        listNewGenres.forEach(genreName -> {
            genreRepository.save(new Genre(genreName));
            movieGenresRepository.save(new MovieGenres(id, genreRepository.findFirstByName(genreName).get().getId()));
        });
        listNewStaff.forEach(staffName -> {
            staffRepository.save(new Staff(staffName, "actor"));
            movieStaffRepository.save(new MovieStaff(id, staffRepository.findFirstByFullNameAndPosition(staffName, "actor")
                    .get().getId()));
        });
        for (Genre genre1 : listGenres) {
            if (genre1 != null) {
                int genreId = genre1.getId();
                movieGenresRepository.save(new MovieGenres(id, genreId));
            }
        }
        for (Staff staff : listStaff) {
            if (staff != null) {
                int staffId = staff.getId();
                movieStaffRepository.save(new MovieStaff(id, staffId));
            }
        }
        return "redirect:/";
    }

}
