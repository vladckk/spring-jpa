package gomel.iba.by;

import javax.persistence.*;

@Entity
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

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
