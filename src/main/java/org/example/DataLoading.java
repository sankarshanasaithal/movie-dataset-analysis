package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class DataLoading {
    public static Map<Integer, Directors> loadDirectors(String filePath) {
        Map<Integer, Directors> directors = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int directorId = Integer.parseInt(data[0].trim());
                String name = data[1].trim();
                String dob = data[2].trim();
                String nationality = data[3].trim();
                directors.put(directorId, new Directors(directorId, name, dob, nationality));
            }
        } catch (IOException e) {
            System.out.println("Error loading directors: " + e.getMessage());
        }
        return directors;
    }

    public static Map<Integer, Actors> loadActors(String filePath) {
        Map<Integer, Actors> actors = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int actorId = Integer.parseInt(data[0].trim());
                String name = data[1].trim();
                String dob = data[2].trim();
                String nationality = data[3].trim();
                actors.put(actorId, new Actors(actorId, name, dob, nationality));
            }
        } catch (IOException e) {
            System.out.println("Error loading actors: " + e.getMessage());
        }
        return actors;
    }

    public static Map<Integer, Movies> loadMovies(String filePath) {
        Map<Integer, Movies> movies = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int movieId = Integer.parseInt(data[0].trim());
                String title = data[1].trim();
                int releaseYear = Integer.parseInt(data[2].trim());
                String genre = data[3].trim();
                float rating = Float.parseFloat(data[4].trim());
                int duration = Integer.parseInt(data[5].trim());
                int directorId = Integer.parseInt(data[6].trim());
                String[] actorIdsStr = data[7].replace("\"", "").split(",");
                int[] actorIds = Arrays.stream(actorIdsStr)
                        .mapToInt(id -> Integer.parseInt(id.trim()))
                        .toArray();

                movies.put(movieId, new Movies(movieId, directorId, releaseYear, duration, rating, actorIds, title, genre));
            }
        } catch (IOException e) {
            System.out.println("Error loading movies: " + e.getMessage());
        }
        return movies;
    }
}
