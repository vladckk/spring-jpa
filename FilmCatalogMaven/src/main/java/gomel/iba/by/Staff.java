package gomel.iba.by;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="fullname")
    private String fullName;

    private String position;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "actorList")
    private List<Movie> movieList;

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    protected Staff() {

    }

    public Staff(String fullName, String position) {
        this.fullName = fullName;
        this.position = position;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return String.format("Staff[id=%d, fullName=%s, position=%s]", id, fullName, position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return id == staff.id &&
                fullName.equals(staff.fullName) &&
                position.equals(staff.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, position);
    }
}
