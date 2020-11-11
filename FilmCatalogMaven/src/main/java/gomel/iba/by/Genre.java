package gomel.iba.by;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "genreSet")
    private Set<Movie> movieSet;

    public Set<Movie> getMovieSet() {
        return movieSet;
    }

    public void setMovieSet(Set<Movie> movieSet) {
        this.movieSet = movieSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected Genre(){

    }

    public Genre(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Genre[id=%d, name='%s']", id, name);
    }

    public String getName() {
        return name;
    }
}
