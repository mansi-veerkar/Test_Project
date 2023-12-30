package question1;

import java.util.List;

public interface Service {

    int addMovie(Film newFilm);
    int updateMovie(String filmName, int filmYear);
    List<Film> displayRating();
    List<Film> displayOscar();


}
