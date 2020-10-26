package gomel.iba.by;

import javax.persistence.*;

@Entity
@Table(name = "moviestaff")
public class MovieStaff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Movies_id")
    private int idMovies;

    @Column(name = "Staff_id")
    private int idStaff;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdMovies(int idMovies) {
        this.idMovies = idMovies;
    }

    public void setIdStaff(int idStaff) {
        this.idStaff = idStaff;
    }

    protected MovieStaff(){

    }

    public MovieStaff(int idMovies, int idStaff) {
        this.idMovies = idMovies;
        this.idStaff = idStaff;
    }

    public int getIdMovies() {
        return idMovies;
    }

    public int getIdStaff() {
        return idStaff;
    }

    @Override
    public String toString() {
        return String.format("id = {}, Movies_id = {}, Staff_id = {}", id, idMovies, idStaff);
    }
}
