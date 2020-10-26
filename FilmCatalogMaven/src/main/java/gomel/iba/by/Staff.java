package gomel.iba.by;

import javax.persistence.*;

@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="fullname")
    private String fullName;

    private String position;

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
}
