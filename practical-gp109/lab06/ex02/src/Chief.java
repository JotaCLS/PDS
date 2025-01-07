import java.util.List;

public class Chief {
    
    private MovieBuilder movieBuilder;

    public void setMovieBuilder(MovieBuilder movieBuilder) {
        this.movieBuilder = movieBuilder;
    }

    public void construct(
        String title,
        int year,
        Person director,
        Person writer,
        String series,
        List<Person> cast,
        List<Place> locations,
        List<String> languages,
        List<String> genres,
        boolean isTelevision,
        boolean isNetflix,
        boolean isIndependent
    ) {
        movieBuilder.setTitle(title);
        movieBuilder.setYear(year);
        movieBuilder.setDirector(director);
        movieBuilder.setWriter(writer);
        movieBuilder.setSeries(series);
        movieBuilder.setCast(cast);
        movieBuilder.setLocations(locations);
        movieBuilder.setLanguages(languages);
        movieBuilder.setGenres(genres);
        movieBuilder.setIsTelevision(isTelevision);
        movieBuilder.setIsNetflix(isNetflix);
        movieBuilder.setIsIndependent(isIndependent);
    }

    public Movie getMovie() {
        return movieBuilder.build();
    }
}
