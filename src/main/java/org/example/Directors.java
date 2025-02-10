package org.example;

public class Directors {
    private int directorId;
    private String name, dob, nationality;

    public Directors(int directorId, String name, String dob, String nationality){
        this.directorId= directorId;
        this.name = name;
        this.dob = dob;
        this.nationality = nationality;
    }

    public int getDirectorId() {
        return directorId;
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
        return "Director ID: " + directorId +
                ", Name: " + name +
                ", DOB: " + dob +
                ", Nationality: " + nationality;
    }
}