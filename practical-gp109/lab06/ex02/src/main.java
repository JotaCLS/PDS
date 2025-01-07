import java.util.List;

public class main {
    public static void main(String[] args){
        MovieBuilder movieBuilder = new MovieBuilder();

        Chief chief = new Chief();

        chief.setMovieBuilder(movieBuilder);

        chief.construct(
            "The Godfather",
            1972,
            new Person("Francis Ford Coppola", 1939),
            new Person("Mario Puzo", 1920),
            "The Godfather",
            List.of(
                new Person("Marlon Brando", 1924),
                new Person("Al Pacino", 1940),
                new Person("James Caan", 1940),
                new Person("Robert Duvall", 1931),
                new Person("Diane Keaton", 1946)
            ),
            List.of(
                new Place("Paramount Studios", "5555 Melrose Avenue", "Los Angeles", "USA"),
                new Place("St. Patrick's Old Cathedral", "263 Mulberry Street", "New York City", "USA"),
                new Place("Louis Restaurant", "649 Union Street", "Brooklyn", "USA"),
                new Place("Toll Gate Inn", "690 Palisade Avenue", "Cliffside Park", "USA"),
                new Place("Long Beach", "Long Beach", "California", "USA"),
                new Place("Las Vegas", "Las Vegas", "Nevada", "USA"),
                new Place("Lake Tahoe", "Lake Tahoe", "California", "USA"),
                new Place("Sicily", "Sicily", "Sicily", "Italy")
            ),
            List.of("English", "Italian", "Latin"),
            List.of("Crime", "Drama"),
            false,
            false,
            false
        );

        Movie movie = chief.getMovie();

        System.out.println(movie);
    }
}
