package gomel.iba.by;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "year")
    private int year;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "movie_actor", joinColumns = @JoinColumn(name = "idMovie"),
        inverseJoinColumns = @JoinColumn(name = "idActor"))
    private List<Staff> actorList;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "movie_director", joinColumns = @JoinColumn(name = "idMovie"),
        inverseJoinColumns = @JoinColumn(name = "idDirector"))
    private Staff director;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Genre> genreSet;

    public List<Staff> getActorList() {
        return actorList;
    }

    public void setActorList(List<Staff> actorList) {
        this.actorList = actorList;
    }

    public Staff getDirector() {
        return director;
    }

    public void setDirector(Staff director) {
        this.director = director;
    }

    public Set<Genre> getGenreSet() {
        return genreSet;
    }

    public void setGenreSet(Set<Genre> genreSet) {
        this.genreSet = genreSet;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    protected Movie() {

    }

    public Movie(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return id + " " + title + " " + year;
    }

}
