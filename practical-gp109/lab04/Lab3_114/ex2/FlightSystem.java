import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlightSystem {
    public static void main(String[] args) {
        List<Flight> listOfFlights = new ArrayList<>();

        if (args.length > 0) {
            processCommandsFromFile(args[0], listOfFlights);
        } else {
            processCommandsFromUser(listOfFlights);
        }
    }

    private static void processCommandsFromFile(String filename, List<Flight> listOfFlights) {
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            while (fileScanner.hasNextLine()) {
                String command = fileScanner.nextLine();
                processCommand(command, listOfFlights);
            }
        } catch (Exception e) {
            System.out.println("Error reading or processing the file: " + e.getMessage());
        }

    }

    private static void processCommandsFromUser(List<Flight> listOfFlights) {
        Scanner sc = new Scanner(System.in);
        String input;
        boolean carry_on = true;

        while (carry_on) {
            System.out.println("\nSelect an option: (H for help)");
            input = sc.nextLine();
            processCommand(input, listOfFlights);
        }

        sc.close();
    }

    private static void processCommand(String command, List<Flight> listOfFlights) {
        String[] options = command.trim().split(" ");

        switch (options[0].toUpperCase()) {
            case "H":
                helpMessage();
                break;
            case "I":
                caseI(options, listOfFlights);
                break;
            case "M":
                caseM(options, listOfFlights);
                break;
            case "F":
                caseF(options, listOfFlights);
                break;
            case "R":
                caseR(options, listOfFlights);
                break;
            case "C":
                caseC(options, listOfFlights);
                break;
            case "Q":
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }

    private static boolean checkArguments(String[] options, int expectedArgs) {
        System.out.println("Checking arguments: " + options.length + " vs " + expectedArgs);
        if (options.length == expectedArgs) {
            return true;
        } else {
            System.out.println("Wrong arguments!\n");
            helpMessage();
            return false;
        }
    }
    
    private static void caseC(String[] options, List<Flight> listOfFlights) {
        if (checkArguments(options, 2)) {
            cancelThisReservation(options, listOfFlights);
        }
    }
    
    private static void caseR(String[] options, List<Flight> listOfFlights) {
        if (checkArguments(options, 4)) {
            addReservation(options, listOfFlights);
        }
    }
    
    private static void caseF(String[] options, List<Flight> listOfFlights) {
        if (checkArguments(options, 3) || checkArguments(options, 4)) {
            addFlight(options, listOfFlights);
        }
    }
    
    private static void caseM(String[] options, List<Flight> listOfFlights) {
        if (checkArguments(options, 2)) {
            displayPlane(options, listOfFlights);
        }
    }
    
    private static void caseI(String[] options, List<Flight> listOfFlights) {
        if (checkArguments(options, 2)) {
            readInputFile(options, listOfFlights);
        }
    }
    

    public static void helpMessage() {
        System.out.println("H - Help");
        System.out.println("I <nome_do_arquivo> - Importar informações do voo a partir do arquivo");
        System.out.println("M <codigo_do_voo> - Exibir informações do voo");
        System.out.println("F <codigo_do_voo> <executivo> <turista> - Criar um novo voo");
        System.out.println("R <codigo_do_voo> <classe> <n> - Reservar n assentos na classe especificada");
        System.out.println(
                "C <codigo_da_reserva> - Cancelar reserva. codigo_da_reserva = codigo_do_voo:numero_da_reserva");
        System.out.println("Q - Sair");
    }

    public static void readInputFile(String[] options, List<Flight> listOfFlights) {
        File f = new File(options[1]);

        try (Scanner sc2 = new Scanner(f)) {
            String firstLine = sc2.nextLine().trim();
            if (firstLine.startsWith(">")) {
                processFlightInfo(firstLine, listOfFlights, sc2);

            } else {
                System.out.println("Invalid format for flight information.");
            }
        } catch (Exception e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    private static void processFlightInfo(String flightInfoLine, List<Flight> listOfFlights, Scanner sc2) {
        String[] flightInfo = flightInfoLine.substring(1).split("\\s+");
        String flightCode = flightInfo[0];
        String executiveInfo = (flightInfo.length == 2) ? "0x0" : flightInfo[1];
        String touristInfo = (flightInfo.length == 2) ? flightInfo[1] : flightInfo[2];

        Plane plane = new Plane(executiveInfo, touristInfo);

        printFlightInformation(flightCode, plane);

        Flight flight = new Flight(flightCode, plane);
        listOfFlights.add(flight);

        processReservations(sc2, flight);
    }

    private static void printFlightInformation(String flightCode, Plane plane) {
        System.out.print("Código de voo " + flightCode + ".");
        if (plane.getNumSeatsExecutive() == 0 && plane.getNumSeatsTourist() == 0) {
            System.out.print(" Sem lugares disponíveis!");
        } else if (plane.getNumSeatsExecutive() == 0) {
            System.out.print(" Lugares disponíveis: " + plane.getNumSeatsTourist()
                    + " lugares em classe Turística.");
            System.out.print(" Classe executiva não disponível neste voo.");
        } else {
            System.out.print(" Lugares disponíveis: " + plane.getNumSeatsExecutive()
                    + " lugares em classe Executiva; " + plane.getNumSeatsTourist()
                    + " lugares em classe Turística.");
        }

        System.out.println();
    }

    private static void processReservations(Scanner sc2, Flight flight) {
        while (sc2.hasNextLine()) {
            String[] reservationInfo = sc2.nextLine().trim().split("\\s+");
            if (reservationInfo.length == 2) {
                String classType = reservationInfo[0];
                int numSeats = Integer.parseInt(reservationInfo[1]);

                flight.reservation(classType, numSeats);
            } else {
                System.out.println("Invalid format for reservation information.");
            }
        }
    }

    public static void displayPlane(String[] options, List<Flight> listOfFlights) {
        String flightCodeToDisplay = options[1];
        boolean foundFlight = false;

        for (Flight flight : listOfFlights) {
            if (flight.getFlightCode().equals(flightCodeToDisplay)) {
                flight.Disp();
                foundFlight = true;
                break;
            }
        }

        if (!foundFlight) {
            System.out.println("Flight with code '" + flightCodeToDisplay + "' not found.");
        }
    }

    public static void addFlight(String[] options, List<Flight> listOfFlights) {
        String executiveInfo = "";
        String touristInfo = "";
        String flightCode = options[1];

        if (options.length == 3) {
            executiveInfo = "0x0";
            touristInfo = options[2];
        } else if (options.length == 4) {
            executiveInfo = options[2];
            touristInfo = options[3];
        } else {
            System.out.println("Wrong arguments!\n");
            helpMessage();
        }

        Plane plane = new Plane(executiveInfo, touristInfo);
        Flight flight = new Flight(flightCode, plane);
        listOfFlights.add(flight);
    }

    public static void addReservation(String[] options, List<Flight> listOfFlights) {
        String targetFlightCode = options[1];
        String seatClass = options[2];
        int numberOfSeats = Integer.parseInt(options[3]);

        for (Flight f : listOfFlights) {
            if (f.getFlightCode().equals(targetFlightCode)) {
                if (f.reservation(seatClass, numberOfSeats)) {
                    System.out.print(f.getFlightCode() + ":" + (f.n_reservas - 1) + " = ");
                    f.printLastAssignedSeats(numberOfSeats);
                }
                break;
            }
        }
    }

    public static void cancelThisReservation(String[] options, List<Flight> listOfFlights) {
        String reservationCode = options[1];
        String[] reservationInfo = reservationCode.split(":");

        if (reservationInfo.length == 2) {
            String targetFlightCode = reservationInfo[0];
            
            if (listOfFlights.stream().noneMatch(f -> f.getFlightCode().equals(targetFlightCode))) {
                System.out.println("Flight with code '" + targetFlightCode + "' not found.");
                return;
            }
            try {
                Integer.parseInt(reservationInfo[1]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid reservation code format.\n");
                helpMessage();
                return;
            }
            int sequentialReservationNumber = Integer.parseInt(reservationInfo[1]);
            for (Flight f : listOfFlights) {
                if (f.getFlightCode().equals(targetFlightCode)) {
                    System.out
                            .println("deleting reservation " + sequentialReservationNumber + " from flight "
                                    + targetFlightCode);
                    f.cancelReservation(sequentialReservationNumber);
                    break;
                }
            }
        } else {
            System.out.println("Invalid reservation code format.\n");
            helpMessage();
        }
    }

}