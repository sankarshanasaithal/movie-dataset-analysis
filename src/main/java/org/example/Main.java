package org.example;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Integer, Directors> directors = DataLoading.loadDirectors("datasets/directors_large.csv");
        Map<Integer, Actors> actors = DataLoading.loadActors("datasets/actors_large.csv");
        Map<Integer, Movies> movies = DataLoading.loadMovies("datasets/movies_large.csv");

        System.out.println("Directors:");
        directors.values().forEach(System.out::println);

        System.out.println("\nActors:");
        actors.values().forEach(System.out::println);

        System.out.println("\nMovies:");
        movies.values().forEach(System.out::println);
    }
}
