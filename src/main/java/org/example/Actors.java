package org.example;

public class Actors {
    private int actorId;
    private String name, dob, nationality;

    public Actors(int actorId, String name, String dob, String nationality){
        this.actorId= actorId;
        this.name = name;
        this.dob = dob;
        this.nationality = nationality;
    }

    public int getActorId() {
        return actorId;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getNationality() {
        return nationality;
    }

    @Override
    public String toString() {
        return "Actor ID: " + actorId +
                ", Name: " + name +
                ", DOB: " + dob +
                ", Nationality: " + nationality;
    }
}
