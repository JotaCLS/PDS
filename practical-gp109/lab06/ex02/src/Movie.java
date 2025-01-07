import java.util.List;
import java.util.ArrayList;

public class Movie {
    private final String title;
    private final int year;
    private final Person director;
    private final Person writer;
    private final String series;
    private final List<Person> cast;
    private final List<Place> locations;
    private final List<String> languages;
    private final List<String> genres;
    private final boolean isTelevision;
    private final boolean isNetflix;
    private final boolean isIndependent;

        public Movie(MovieBuilder movieBuilder) { 
            this.title = movieBuilder.title;
            this.year = movieBuilder.year;
            this.director = movieBuilder.director;
            this.writer = movieBuilder.writer;
            this.series = movieBuilder.series;
            this.cast = movieBuilder.cast;
            this.locations = movieBuilder.locations;
            this.languages = movieBuilder.languages;
            this.genres = movieBuilder.genres;
            this.isTelevision = movieBuilder.isTelevision;
            this.isNetflix = movieBuilder.isNetflix;
            this.isIndependent = movieBuilder.isIndependent;
        }

    public String toString() {
        return title + " (" + year + ") directed by " + director + " and written by " + writer + " in " + languages + " with genres " + genres + " filmed in " + locations + " with cast " + cast + " is a " + (isTelevision ? "television" : "movie") + " " + (isNetflix ? "Netflix" : "") + " " + (isIndependent ? "independent" : "") + " production";
    }
}
    