public class Flight {
    String flightCode;
    Plane plane;
    String[][] emptyArray;

    int n_reservas = 1;
    String nreserva = "";

    public Flight(String flightCode, Plane plane) {
        this.flightCode = flightCode;
        this.plane = plane;
        this.emptyArray = plane.Display_plane();
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public String getNumberReservation() {
        return nreserva;
    }

    public void setNumberReservation(String nreserva) {
        this.nreserva = nreserva;
    }

    public void Disp() {
        int columns = emptyArray[0].length;

        System.out.printf(" ");
        for (int i = 1; i <= columns; i++) {
            System.out.printf("%3d", i);
        }
        System.out.printf("\n");

        int rows = emptyArray.length;
        for (int i = 0; i < rows; i++) {
            printRow(i);
        }
    }

    private void printRow(int i) {
        System.out.printf("%c", (char) (65 + i));
        for (int j = 0; j < emptyArray[0].length; j++) {
            System.out.printf("%3s", emptyArray[i][j]);
        }
        System.out.printf("\n");
    }

    public boolean reservation(String tipo, int n) {
        if (tipo.equals("E")) {
            return reservatedInClass(tipo, n);
        } else if (tipo.equals("T")) {
            return reservatedInClass(tipo, n);
        } else {
            System.out.println("Invalid seat class type!");
            return false;
        }
    }

    
    public boolean reservatedInClass(String classType, int n) {
        String className = classType.equals("E") ? "Executive" : "Tourist";
        int numColumns = classType.equals("E") ? plane.getNumColumnsExecutive() : plane.getNumColumnsTourist();
        int numRows = classType.equals("E") ? plane.getNumRowsExecutive() : plane.getNumRowsTourist();
        int numSeats = classType.equals("E") ? plane.getNumSeatsExecutive() : plane.getNumSeatsTourist();
    
        if (numSeats < n) {
            System.out.println("Não foi possível obter lugares para a reserva: " + className + " " + n);
            return false;
        }
    
        for (int i = 0; i < numColumns; i++) {
            for (int j = 0; j < numRows; j++) {
                int columnIndex = classType.equals("E") ? i : i + plane.getNumColumnsExecutive();
                if (emptyArray[j][columnIndex].equals("0")) {
                    emptyArray[j][columnIndex] = String.format("%d", n_reservas);
                    n--;
                    if (classType.equals("E")) {
                        plane.setNumSeatsExecutive(numSeats - 1);
                    } else {
                        plane.setNumSeatsTourist(numSeats - 1);
                    }
                    String a = String.format("%d", columnIndex + 1) + String.format("%c", (char) (65 + j));
                    this.setNumberReservation(this.getNumberReservation() + a + "|");
                    if (n == 0) {
                        n_reservas++;
                        return true;
                    }
                }
            }
        }
    
        System.out.println("Não foi possível obter lugares suficientes para a reserva: " + className + " " + n);
        return false;
    }
    
    public void printLastAssignedSeats(int numberOfSeats) {
        String[] reservations = nreserva.split("\\|");
        int startIdx = Math.max(0, reservations.length - numberOfSeats);

        for (int i = startIdx; i < reservations.length; i++) {
            String seat = reservations[i];
            char rowLetter = seat.charAt(seat.length() - 1);
            int colNumber = Integer.parseInt(seat.substring(0, seat.length() - 1));
            printSeat(i, reservations.length, rowLetter, colNumber);
        }

        System.out.println("\n");

    }

    private void printSeat(int i, int length, char rowLetter, int colNumber) {
        if (i == length - 1) {
            System.out.printf("%c%d", rowLetter, colNumber);
        } else {
            System.out.printf("%c%d | ", rowLetter, colNumber);
        }
    }

    public void cancelReservation(int sequentialReservationNumber) {
        nreserva = nreserva.replace(sequentialReservationNumber + "|", "");
    
        for (int i = 0; i < emptyArray.length; i++) {
            performCancelationOnColumns(sequentialReservationNumber, i);
        }
    }

    private void performCancelationOnColumns(int sequentialReservationNumber, int i) {
        for (int j = 0; j < emptyArray[i].length; j++) {
            checkAndPerformCancelation(sequentialReservationNumber, i, j);
        }
    }

    private void checkAndPerformCancelation(int sequentialReservationNumber, int i, int j) {
        if (emptyArray[i][j].equals(String.valueOf(sequentialReservationNumber))) { 
            emptyArray[i][j] = "0";
            checkAndPerformCancelationOnSeatsClass(j);
        }
    }

    private void checkAndPerformCancelationOnSeatsClass(int j) {
        if (j < plane.getNumColumnsExecutive()) { 
            plane.setNumSeatsExecutive(plane.getNumSeatsExecutive() + 1);
        } else {
            plane.setNumSeatsTourist(plane.getNumSeatsTourist() + 1);
        }
    }
    
}