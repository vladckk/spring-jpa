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
        String genresString = null;
        String actorsString = null;
        String director;
        List<ViewMovie> viewMovieList = new ArrayList<>();
        while (iterable.hasNext()) {
            Movie mov = iterable.next();
            Set<Genre> genreSet = mov.getGenreSet();
            if (!genreSet.isEmpty()) {
                StringBuilder sb = new StringBuilder("");
                for (Genre genre : genreSet) {
                    sb.append(genre.getName()).append(", ");
                }
                genresString = sb.substring(0, sb.length() - 2);
            }
            List<Staff> actors = mov.getActorList();
            if (!actors.isEmpty()) {
                StringBuilder sb = new StringBuilder("");
                for (Staff actor: actors) {
                    sb.append(actor.getFullName()).append(", ");
                }
                actorsString = sb.substring(0, sb.length() - 2);
            }
            Staff dir = mov.getDirector();
            if (dir != null) {
                director = dir.getFullName();
            } else {
                director = "Not Found";
            }
            viewMovieList.add(new ViewMovie(mov, genresString, actorsString, director));
        }
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
            Movie movie = movieOptional.get();
            Optional<Staff> staffOptional = staffRepository.findFirstByFullName(director);
            if (staffOptional.isPresent() && movie.getDirector().equals(staffOptional.get())) {
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
        movieRepository.save(movie);
        Example<Movie> movieExample = Example.of(movie);
        Movie movie1 = movieRepository.findOne(movieExample).get();
        movie1.setActorList(new ArrayList<>());
        Staff dir;
        List<Staff> actorList = movie1.getActorList();
        if (staffRepository.existsByFullNameAndPosition(director, "director")) {
            dir = staffRepository.findFirstByFullNameAndPosition(director, "director").get();
        } else {
            Staff staffDirector = new Staff(director, "director");
            staffRepository.save(staffDirector);
            dir = staffDirector;
        }
        movie1.setDirector(dir);
        String[] arrayActors = actors.split(",");
        for (String actor : arrayActors) {
            actor = actor.trim();
            if (staffRepository.existsByFullNameAndPosition(actor, "actor")) {
                actorList.add(staffRepository.findFirstByFullName(actor).get());
            } else {
                Staff staffActor = new Staff(actor, "actor");
                staffRepository.save(staffActor);
                actorList.add(staffActor);
            }
        }
        String[] arrayGenres = genres.split(",");
        movie1.setGenreSet(new HashSet<Genre>());
        Set<Genre> genreSet = movie1.getGenreSet();
        for (String genre : arrayGenres) {
            genre = genre.trim();
            if (genreRepository.existsByName(genre)) {
                genreSet.add(genreRepository.findFirstByName(genre).get());
            } else {
                Genre genre1 = new Genre(genre);
                genreRepository.save(genre1);
                genreSet.add(genreRepository.findOne(Example.of(genre1)).get());
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
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie == null) {
            throw new FilmNotFoundException();
        }
        LOGGER.info("{}", movie);
        model.addAttribute("movie", movie);
        Set<Genre> genres = movie.getGenreSet();
        StringBuilder sb = new StringBuilder();
        if (!genres.isEmpty()) {
            for (Genre genre : genres) {
                sb.append(genre.getName());
                sb.append(", ");
            }
        }
        model.addAttribute("genres", sb.substring(0, sb.length() - 2));
        sb = new StringBuilder();
        List<Staff> actors = movie.getActorList();
        if (!actors.isEmpty()) {
            for (Staff actor : actors) {
                sb.append(actor.getFullName());
                sb.append(", ");
            }
        }
        model.addAttribute("actors", sb.substring(0, sb.length() - 2));
        LOGGER.info("{}", actors);
        Staff dir = movie.getDirector();
        String director;
        if (dir == null) {
            director = "";
        } else {
            director = dir.getFullName();
        }
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
        String[] genreNames = genres.split(",");
        String[] actorNames = actors.split(",");
        List<Staff> actorList = movie.getActorList();
        Set<Genre> genreSet = movie.getGenreSet();
        List<Genre> listGenres = Arrays.stream(genreNames).map(genreName -> genreRepository.findFirstByName(genreName.trim()).orElse(null))
                .collect(Collectors.toList());
        List<Staff> listActors = Arrays.stream(actorNames).map(staffName -> staffRepository.findFirstByFullNameAndPosition(staffName.trim(), "actor").orElse(null))
                .collect(Collectors.toList());
        List<String> listNewGenres = Arrays.stream(genreNames).filter(genreName -> !genreRepository.existsByName(genreName.trim()))
                .collect(Collectors.toList());
        List<String> listNewActors = Arrays.stream(actorNames).filter(actorName -> !staffRepository.existsByFullNameAndPosition(actorName.trim(), "actor"))
                .collect(Collectors.toList());

        movieRepository.save(movie);
        actorList.clear();
        genreSet.clear();
        if (staffRepository.findFirstByFullNameAndPosition(director, "director").isEmpty()) {
            Staff staff = new Staff(director, "director");
            staffRepository.save(staff);
            movie.setDirector(staff);
        } else {
            movie.setDirector(staffRepository.findFirstByFullNameAndPosition(director, "director").get());
        }

        listNewGenres.forEach(genreName -> {
            Genre genre = new Genre(genreName);
            genreRepository.save(genre);
            genreSet.add(genre);
        });
        listNewActors.forEach(staffName -> {
            Staff staff = new Staff(staffName, "actor");
            staffRepository.save(staff);
            actorList.add(staff);
        });
        listGenres.forEach(genre -> {
            if (genre != null) {
                genreSet.add(genre);
            }
        });
        listActors.forEach(staff -> {
            if (staff != null) {
                actorList.add(staff);
            }
        });
        return "redirect:/";
    }

}
