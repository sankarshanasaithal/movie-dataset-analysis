package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String moviesFile = "datasets/movies_large.csv";
        String directorsFile = "datasets/directors_large.csv";
        String actorsFile = "datasets/actors_large.csv";

        MovieFunctions cms = new MovieFunctions(moviesFile, directorsFile, actorsFile);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Movie Dataset Insights ===");
            System.out.println("1. Get Movie Information");
            System.out.println("2. Get Top 10 Rated Movies");
            System.out.println("3. Get Movies by Genre");
            System.out.println("4. Get Movies by Director");
            System.out.println("5. Get Movies by Release Year");
            System.out.println("6. Get Movies by Release Year Range");
            System.out.println("7. Add New Movie");
            System.out.println("8. Update Movie Rating");
            System.out.println("9. Delete a Movie");
            System.out.println("10. Sort Movies by Release Year");
            System.out.println("11. Get Top Directors with Most Movies");
            System.out.println("12. Get Actors in Most Movies");
            System.out.println("13. Get Movies of Youngest Actor");
            System.out.println("14. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter Movie ID or Title: ");
                        cms.getMovieInformation(scanner.nextLine());
                        break;
                    case 2:
                        cms.getTopRatedMovies();
                        break;
                    case 3:
                        System.out.print("Enter Genre: ");
                        cms.getMoviesByGenre(scanner.nextLine());
                        break;
                    case 4:
                        System.out.print("Enter Director Name: ");
                        cms.getMoviesByDirector(scanner.nextLine());
                        break;
                    case 5:
                        System.out.print("Enter Release Year: ");
                        cms.getMoviesByReleaseYear(scanner.nextInt());
                        break;
                    case 6:
                        System.out.print("Enter Start Year: ");
                        int startYear = scanner.nextInt();
                        System.out.print("Enter End Year: ");
                        int endYear = scanner.nextInt();
                        cms.getMoviesByReleaseYearRange(startYear, endYear);
                        break;
                    case 7:
                        System.out.print("Enter Movie Title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter Release Year: ");
                        int releaseYear = scanner.nextInt();
                        System.out.print("Enter Genre: ");
                        scanner.nextLine();
                        String genre = scanner.nextLine();
                        System.out.print("Enter Rating: ");
                        float rating = scanner.nextFloat();
                        System.out.print("Enter Duration: ");
                        int duration = scanner.nextInt();
                        System.out.print("Enter Director ID: ");
                        int directorId = scanner.nextInt();
                        System.out.print("Enter Actor IDs (comma-separated): ");
                        scanner.nextLine();
                        String[] actorIdsStr = scanner.nextLine().split(",");
                        int[] actorIds = new int[actorIdsStr.length];
                        for (int i = 0; i < actorIdsStr.length; i++) {
                            actorIds[i] = Integer.parseInt(actorIdsStr[i].trim());
                        }
                        Movies newMovie = new Movies(0, directorId, releaseYear, duration, rating, actorIds,
                                title, genre);
                        cms.addNewMovie(newMovie);
                        break;
                    case 8:
                        System.out.print("Enter Movie ID: ");
                        int movieId = scanner.nextInt();
                        System.out.print("Enter New Rating: ");
                        float newRating = scanner.nextFloat();
                        cms.updateMovieRating(movieId, newRating);
                        break;
                    case 9:
                        System.out.print("Enter Movie ID to Delete: ");
                        cms.deleteMovie(scanner.nextInt());
                        break;
                    case 10:
                        cms.sortMoviesByReleaseYear();
                        break;
                    case 11:
                        cms.getTopDirectorsWithMostMovies();
                        break;
                    case 12:
                        cms.getActorsInMostMovies();
                        break;
                    case 13:
                        cms.getMoviesOfYoungestActor();
                        break;
                    case 14:
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }catch (InputMismatchException e) {
                System.out.println("Invalid datatype for input utilized. Please try again.");
                scanner.nextLine();
            }
            catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }
}