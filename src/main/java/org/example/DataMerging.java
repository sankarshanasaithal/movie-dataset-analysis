package org.example;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataMerging {
    public static void mergeAndDisplay(Map<Integer, Movies> movies, Map<Integer, Directors> directors, Map<Integer, Actors> actors) {
        for (Movies movie : movies.values()) {
            Directors director = directors.get(movie.getDirectorId());
            List<Actors> movieActors = getActorsForMovie(movie.getActorIds(), actors);

            System.out.println("=== Movie Details ===");
            System.out.println(movie);
            System.out.println("Director: " + (director != null ? director.getName() : "Not Found"));

            System.out.println("Actors:");
            for (Actors actor : movieActors) {
                System.out.println(actor != null ? actor : "Not Found");
            }
            System.out.println("\n");
        }
    }

    private static List<Actors> getActorsForMovie(int[] actorIds, Map<Integer, Actors> actors) {
        return java.util.Arrays.stream(actorIds)
                .mapToObj(actors::get)
                .collect(Collectors.toList());
    }
}