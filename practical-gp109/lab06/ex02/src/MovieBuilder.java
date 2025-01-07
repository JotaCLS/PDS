import java.util.List;
import java.util.ArrayList;

public class MovieBuilder {
    
    public String title;
    public int year;
    public Person director;
    public Person writer;
    public String series;
    public List<Person> cast;
    public List<Place> locations;
    public List<String> languages;
    public List<String> genres;
    public boolean isTelevision;
    public boolean isNetflix;
    public boolean isIndependent;

    public MovieBuilder() {
        
    }

    public MovieBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public MovieBuilder setYear(int year) {
        this.year = year;
        return this;
    }

    public MovieBuilder setDirector(Person director) {
        this.director = director;
        return this;
    }

    public MovieBuilder setWriter(Person writer) {
        this.writer = writer;
        return this;
    }

    public MovieBuilder setSeries(String series) {
        this.series = series;
        return this;
    }

    public MovieBuilder setCast(List<Person> cast) {
        this.cast = cast;
        return this;
    }

    public MovieBuilder setLocations(List<Place> locations) {
        this.locations = locations;
        return this;
    }

    public MovieBuilder setLanguages(List<String> languages) {
        this.languages = languages;
        return this;
    }

    public MovieBuilder setGenres(List<String> genres) {
        this.genres = genres;
        return this;
    }

    public MovieBuilder setIsTelevision(boolean isTelevision) {
        this.isTelevision = isTelevision;
        return this;
    }

    public MovieBuilder setIsNetflix(boolean isNetflix) {
        this.isNetflix = isNetflix;
        return this;
    }

    public MovieBuilder setIsIndependent(boolean isIndependent) {
        this.isIndependent = isIndependent;
        return this;
    }

    public Movie build() {
        return new Movie(this);
    }

    

}
