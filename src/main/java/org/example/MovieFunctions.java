package org.example;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class MovieFunctions {
    private Map<Integer, Movies> movies;
    private Map<Integer, Directors> directors;
    private Map<Integer, Actors> actors;
    private int nextMovieId;

    public MovieFunctions(String moviesFile, String directorsFile, String actorsFile) {
        movies = DataLoading.loadMovies(moviesFile);
        directors = DataLoading.loadDirectors(directorsFile);
        actors = DataLoading.loadActors(actorsFile);
        nextMovieId = movies.keySet().stream().max(Integer::compareTo).orElse(0) + 1;
    }

    public void getMovieInformation(String identifier) {
        movies.values().stream()
                .filter(movie ->
                        movie.getMovieId() == (identifier.matches("\\d+") ? Integer.parseInt(identifier) : -1) ||
                                movie.getTitle().equalsIgnoreCase(identifier))
                .findFirst()
                .ifPresentOrElse(movie -> {
                    System.out.println("=== Movie Details ===");
                    System.out.println(movie);

                    // Get and display director
                    Directors director = directors.get(movie.getDirectorId());
                    System.out.println("Director: " + (director != null ? director.getName() : "Unknown"));

                    // Get and display actors
                    System.out.println("Actors:");
                    for (int actorId : movie.getActorIds()) {
                        Actors actor = actors.get(actorId);
                        System.out.println(actor != null ? actor : "Actor Not Found");
                    }
                }, () -> System.out.println("Movie not found."));
    }

    public void getTopRatedMovies() {
        System.out.println("=== Top 10 Rated Movies ===");
        movies.values().stream()
                .sorted(Comparator.comparing(Movies::getRating).reversed())
                .limit(10)
                .forEach(System.out::println);
    }

    public void getMoviesByGenre(String genre) {
        System.out.println("=== Movies in " + genre + " Genre ===");
        movies.values().stream()
                .filter(movie -> movie.getGenre().equalsIgnoreCase(genre))
                .forEach(System.out::println);
    }

    public void getMoviesByDirector(String directorName) {
        System.out.println("=== Movies by Director: " + directorName + " ===");
        int[] directorIds = directors.values().stream()
                .filter(d -> d.getName().equalsIgnoreCase(directorName))
                .mapToInt(Directors::getDirectorId)
                .toArray();

        movies.values().stream()
                .filter(movie -> Arrays.stream(directorIds).anyMatch(id -> id == movie.getDirectorId()))
                .forEach(System.out::println);
    }

    public void getMoviesByReleaseYear(int year) {
        System.out.println("=== Movies Released in " + year + " ===");
        movies.values().stream()
                .filter(movie -> movie.getReleaseYear() == year)
                .forEach(System.out::println);
    }

    public void getMoviesByReleaseYearRange(int startYear, int endYear) {
        System.out.println("=== Movies Released between " + startYear + " and " + endYear + " ===");
        movies.values().stream()
                .filter(movie -> movie.getReleaseYear() >= startYear && movie.getReleaseYear() <= endYear)
                .forEach(System.out::println);
    }

    public void addNewMovie(Movies newMovie) {
        newMovie = new Movies(
                nextMovieId++,
                newMovie.getDirectorId(),
                newMovie.getReleaseYear(),
                newMovie.getDuration(),
                newMovie.getRating(),
                newMovie.getActorIds(),
                newMovie.getTitle(),
                newMovie.getGenre()
        );
        movies.put(newMovie.getMovieId(), newMovie);
        System.out.println("Movie added successfully with ID: " + newMovie.getMovieId());
    }

    public void updateMovieRating(int movieId, float newRating) {
        Movies movie = movies.get(movieId);
        if (movie != null) {
            movies.put(movieId, new Movies(
                    movie.getMovieId(),
                    movie.getDirectorId(),
                    movie.getReleaseYear(),
                    movie.getDuration(),
                    newRating,
                    movie.getActorIds(),
                    movie.getTitle(),
                    movie.getGenre()
            ));
            System.out.println("Movie rating updated successfully.");
        } else {
            System.out.println("Movie not found.");
        }
    }

    public void deleteMovie(int movieId) {
        if (movies.remove(movieId) != null) {
            System.out.println("Movie deleted successfully.");
        } else {
            System.out.println("Movie not found.");
        }
    }

    public void sortMoviesByReleaseYear() {
        System.out.println("=== Top 15 Movies Sorted by Release Year ===");
        movies.values().stream()
                .sorted(Comparator.comparing(Movies::getReleaseYear))
                .limit(15)
                .forEach(System.out::println);
    }

    public void getTopDirectorsWithMostMovies() {
        System.out.println("=== Top 5 Directors with Most Movies ===");
        Map<Integer, Long> directorMovieCounts = movies.values().stream()
                .collect(Collectors.groupingBy(Movies::getDirectorId, Collectors.counting()));

        directorMovieCounts.entrySet().stream()
                .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
                .limit(5)
                .forEach(entry -> {
                    Directors director = directors.get(entry.getKey());
                    System.out.println(director.getName() + ": " + entry.getValue() + " movies");
                });
    }

    public void getActorsInMostMovies() {
        System.out.println("=== Actors in Most Movies ===");
        Map<Integer, Long> actorMovieCounts = movies.values().stream()
                .flatMap(movie -> Arrays.stream(movie.getActorIds()).boxed())
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));

        actorMovieCounts.entrySet().stream()
                .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
                .limit(5)
                .forEach(entry -> {
                    Actors actor = actors.get(entry.getKey());
                    System.out.println(actor.getName() + ": " + entry.getValue() + " movies");
                });
    }

    public void getMoviesOfYoungestActor() {
        LocalDate referenceDate = LocalDate.of(2025, 2, 10);
        Actors youngestActor = actors.values().stream()
                .min(Comparator.comparing(actor -> {
                    String[] dobParts = actor.getDob().split("-");
                    LocalDate dob = LocalDate.of(
                            Integer.parseInt(dobParts[0]),
                            Integer.parseInt(dobParts[1]),
                            Integer.parseInt(dobParts[2])
                    );
                    return Period.between(dob, referenceDate).getYears();
                }))
                .orElse(null);

        if (youngestActor != null) {
            System.out.println("Youngest Actor: " + youngestActor);
            int age = Period.between(
                    LocalDate.parse(youngestActor.getDob()),
                    referenceDate
            ).getYears();
            System.out.println("Age: " + age + " years");

            System.out.println("Movies:");
            movies.values().stream()
                    .filter(movie -> Arrays.stream(movie.getActorIds())
                            .anyMatch(id -> id == youngestActor.getActorId()))
                    .forEach(System.out::println);
        }
    }
}