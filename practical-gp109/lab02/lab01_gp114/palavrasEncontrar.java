public class palavrasEncontrar {
    private String word;
    private int length;
    private int[] coordinates;
    private String direction;

    public palavrasEncontrar(String word, int length, int[] coordinates, String direction) {
        this.word = word;
        this.length = length;
        this.coordinates = coordinates;
        this.direction = direction;
    }

    // Getter methods
    public String getWord() {
        return word;
    }

    public int getLength() {
        return length;
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public String getDirection() {
        return direction;
    }

    // Setter methods
    public void setWord(String word) {
        this.word = word;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setCoordinates(int[] coordinates) {
        this.coordinates = coordinates;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return String.format("%-15s %-8d %-8s %-5s", word, length, coordinates[0] + "," + coordinates[1],
                direction);
    }
}