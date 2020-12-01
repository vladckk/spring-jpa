package gomel.iba.by;

public enum Position {
    DIRECTOR("director"), ACTOR("actor");

    private String name;

    private Position(String name) {
        this.name = name;
    }
}
