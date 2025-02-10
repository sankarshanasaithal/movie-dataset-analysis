package org.example;

import java.util.Arrays;

public class Movies {
    private int movieId, directorId, releaseYear, duration;
    private float rating;
    private int[] actorIds;
    private String title, genre;

    public Movies(int movieId, int directorId, int releaseYear, int duration, float rating, int[] actorIds,
                  String title, String genre) {
        this.movieId = movieId;
        this.directorId = directorId;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.rating = rating;
        this.actorIds = actorIds;
        this.title = title;
        this.genre = genre;
    }

    public int getMovieId() {
        return movieId;
    }

    public int getDirectorId() {
        return directorId;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public int getDuration() {
        return duration;
    }

    public float getRating() {
        return rating;
    }

    public int[] getActorIds() {
        return actorIds;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Movie ID: " + movieId +
                ", Title: " + title +
                ", Year: " + releaseYear +
                ", Genre: " + genre +
                ", Rating: " + rating +
                ", Duration: " + duration +
                ", Director ID: " + directorId +
                ", Actor IDs: " + Arrays.toString(actorIds);
    }
}