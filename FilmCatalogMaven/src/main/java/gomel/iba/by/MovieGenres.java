package gomel.iba.by;

import javax.persistence.*;

@Entity
@Table(name = "moviegenres")
public class MovieGenres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Genres_id")
    private int idGenres;

    @Column(name = "Movies_id")
    private int idMovies;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdGenres(int idGenres) {
        this.idGenres = idGenres;
    }

    public void setIdMovies(int idMovies) {
        this.idMovies = idMovies;
    }

    protected MovieGenres(){

    }

    public MovieGenres(int idMovies, int idGenres) {
        this.idGenres = idGenres;
        this.idMovies = idMovies;
    }

    public int getIdGenres() {
        return idGenres;
    }

    public int getIdMovies() {
        return idMovies;
    }
    public String toString() {
        return String.format("id = %d, genresId = %d, moviesId = %d", id, idGenres, idMovies);
    }
}
