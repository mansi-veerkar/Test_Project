package question1;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    private static Scanner sc1=new Scanner(System.in);
    static Service service =  new Implementation();

    public static void main(String[] args) throws SQLException {
        System.out.println("SELECT ANY OPERATION");
        System.out.println("1. ADD ANY MOVIES");
        System.out.println("2. UPDATE ANY MOVIE");
        System.out.println("3. DISPLAY MOVIES WITH GOOD RATINGS");
        System.out.println("4. DISPLAY MOVIES FOR OSCAR AWARD");
        System.out.println("5. EXIT");
        int choice = sc1.nextInt();
        switch (choice){
            case 1:
                addMovie();
                break;

            case 2:
                updateMovie();
                break;

            case 3:
                displayMovieRatings();
                break;

            case 4:
                displayOscar();
                break;

        }
        main(args);
    }

    public static void addMovie(){
        System.out.println("ENTER YOUR FILM NAME");
        sc1.nextLine();
        String name = sc1.nextLine();
        System.out.println("ENTER FILM YEAR");
        int year = sc1.nextInt();
        System.out.println("ENTER FILM LANGUAGE");
        String lang = sc1.next();
        System.out.println("ENTER FILM RATING");
        double rating = sc1.nextDouble();
        Film film = new Film(name,year,lang,rating);
        int n = service.addMovie(film);
        System.out.println(n + " FILM ADDED!");
    }

    public static void updateMovie(){
        System.out.println("ENTER FILM NAME");
        sc1.nextLine();
        String name = sc1.nextLine();
        System.out.println("ENTER FILM YEAR YOU WANT TO UPDATE");
        int year =sc1.nextInt();
        int n = service.updateMovie(name,year);
        System.out.println(n + " Year Updated ");
    }

    public static void displayMovieRatings(){
        List<Film> filmList = service.displayRating();
        System.out.println("===================================================");
        System.out.println("FID \t FNAME \t FYEAR \t FLANG \t FRATING");
        System.out.println("===================================================");
        for (Film f : filmList) {
            System.out.println(f.getFilmId() + "\t\t" + f.getFilmName() + "\t\t" + f.getFilmYear()+ "\t\t" + f.getFilmLang() +"\t\t" + f.getFilmRating());
        }
    }

    public static void displayOscar(){
        List<Film> filmList = service.displayRating();
        System.out.println("============================");
        System.out.println("FNAME \t FRATING");
        System.out.println("============================");
        for (Film f : filmList) {
            System.out.println(f.getFilmName() + "\t\t" + f.getFilmRating());
        }
        System.out.println("============================");
    }
}
