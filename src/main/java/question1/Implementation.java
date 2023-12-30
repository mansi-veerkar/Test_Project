package question1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Implementation implements Service{
    static Connection conn;
    static {
        String url = "jdbc:mysql://localhost:3306/testdb";
        String username = "root";
        String password = "tiger";
        try {
            conn = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            System.err.println("CONNECTION UNSUCCESSFUL !! ");
            System.exit(1);
        }
    }

    @Override
    public int addMovie(Film newFilm) {
        int n =0;
        String addQuery = "insert into film_table(film_name, film_year, film_lang, film_rating) values(?,?,?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(addQuery);
            pstmt.setString(1,newFilm.getFilmName());
            pstmt.setInt(2,newFilm.getFilmYear());
            pstmt.setString(3,newFilm.getFilmLang());
            pstmt.setDouble(4,newFilm.getFilmRating());
            n=pstmt.executeUpdate() ;
        } catch (SQLException e) {
            System.err.println("INVALID FILM DATA !!");
        }
        return n;
    }

    @Override
    public int updateMovie(String filmName, int filmYear) {
        int n=0;
//        String updateQuery="update film_table set film_year=? where film_name=? ";
//        String query = "delete from student where rollno="+rollNo+" ";
        String updateQuery="update film_table set film_year="+filmYear+" where film_name='"+filmName+"'";
        try {
            Statement stmt = conn.createStatement();
            n=stmt.executeUpdate(updateQuery);
//            PreparedStatement pstmt= conn.prepareStatement(updateQuery);
//            pstmt.setString(1,question);
//            pstmt.setInt(2,questionID);
//            n=pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Invalid Input !!");
        }
        return n;
    }

    @Override
    public List<Film> displayRating() {
        String displayRatingQuery = "SELECT * FROM film_table WHERE film_rating >(SELECT AVG(film_rating) FROM film_table)";
        List<Film> filmList= new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(displayRatingQuery);
            while (rs.next()){
                int filmId=rs.getInt(1);
                String filmName=rs.getString(2);
                int filmYear=rs.getInt(3);
                String filmLang=rs.getString(4);
                double filmRating=rs.getDouble(5);
                Film film = new Film(filmId,filmName,filmYear,filmLang,filmRating);
                filmList.add(film);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return filmList;
    }

    @Override
    public List<Film> displayOscar() {
        String displayRatingQuery = "SELECT film_name, film_rating FROM film_table ORDER BY film_rating DESC LIMIT 5";
        List<Film> filmList= new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(displayRatingQuery);
            while (rs.next()){
                int filmId=rs.getInt(1);
                String filmName=rs.getString(2);
                int filmYear=rs.getInt(3);
                String filmLang=rs.getString(4);
                double filmRating=rs.getDouble(5);
                Film film = new Film(filmId,filmName,filmYear,filmLang,filmRating);
                filmList.add(film);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return filmList;
    }
}
