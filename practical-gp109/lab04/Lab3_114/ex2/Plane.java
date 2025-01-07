public class Plane {
    private int num_seats_executive, num_rows_executive, num_columns_executive;
    private int num_seats_tourist, num_rows_tourist, num_columns_tourist;

    public Plane(String expression_executive, String expression_tourist) {
        this.num_columns_executive = Integer.parseInt(expression_executive.split("x")[0]);
        this.num_rows_executive = Integer.parseInt(expression_executive.split("x")[1]);
        this.num_columns_tourist = Integer.parseInt(expression_tourist.split("x")[0]);
        this.num_rows_tourist = Integer.parseInt(expression_tourist.split("x")[1]);

        this.num_seats_tourist = this.num_columns_tourist * this.num_rows_tourist;
        this.num_seats_executive = this.num_columns_executive * this.num_rows_executive;
    }

    public String[][] Display_plane() {
        int maxRows = Math.max(num_rows_executive, num_rows_tourist);
        int totalColumns = num_columns_executive + num_columns_tourist;

        String[][] all_seats = new String[maxRows][totalColumns];

        for (int i = 0; i < maxRows; i++) {
            for (int j = 0; j < totalColumns; j++) {
                if (i >= num_rows_executive && j < num_columns_executive) {
                    all_seats[i][j] = " ";
                } else {
                    all_seats[i][j] = "0";
                }
            }
        }

        return all_seats;
    }

    public int getNumSeatsExecutive() {
        return num_seats_executive;
    }

    public void setNumSeatsExecutive(int num_seats_executive) {
        this.num_seats_executive = num_seats_executive;
    }

    public int getNumRowsExecutive() {
        return num_rows_executive;
    }

    public void setNumRowsExecutive(int num_rows_executive) {
        this.num_rows_executive = num_rows_executive;
    }

    public int getNumColumnsExecutive() {
        return num_columns_executive;
    }

    public void setNumColumnsExecutive(int num_columns_executive) {
        this.num_columns_executive = num_columns_executive;
    }

    public int getNumSeatsTourist() {
        return num_seats_tourist;
    }

    public void setNumSeatsTourist(int num_seats_tourist) {
        this.num_seats_tourist = num_seats_tourist;
    }

    public int getNumRowsTourist() {
        return num_rows_tourist;
    }

    public void setNumRowsTourist(int num_rows_tourist) {
        this.num_rows_tourist = num_rows_tourist;
    }

    public int getNumColumnsTourist() {
        return num_columns_tourist;
    }

    public void setNumColumnsTourist(int num_columns_tourist) {
        this.num_columns_tourist = num_columns_tourist;
    }
}