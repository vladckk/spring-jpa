package gomel.iba.by;

public class ViewMovie {
    private Movie movie;
    private String genres;
    private String actors;
    private String director;

    public ViewMovie(Movie movie, String genres, String actors, String director) {
        this.movie = movie;
        this.genres = genres;
        this.actors = actors;
        this.director = director;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return movie + " " + genres + " " + actors + " " + director;
    }
}
