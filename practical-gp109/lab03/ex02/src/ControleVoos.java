import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class ControleVoos {
    // HashMap to store flights
    static HashMap<String, Voo> voos = new HashMap<>();

    public static String printMenu() {
        return "I filename -> " +
                "Read text file containing information about a flight\n" +
                "M flight_code -> " +
                "Display reservation map of a flight\n" +
                "F flight_code num_seats_executive num_seats_tourist -> " +
                "Add new flight with code, seats in executive class\n" +
                "R flight_code class num_seats -> " +
                "Reserve seats in a class\n" +
                "C reservation_code -> " +
                "Cancel a reservation\n" +
                "Q -> " +
                "Quit the program\n\n";
    }

    public static void command(String[] args) {
        String option = args[0].toUpperCase();
        switch (option) {
            case "H":
                if (args.length != 1) {
                    System.err.println("Usage: H");
                    System.out.println();
                    return;
                }
                System.out.print(printMenu());
                break;
            case "I":
                if (args.length != 2) {
                    System.err.println("Usage: I <filename>");
                    System.out.println();
                    break;
                }
                readFile(args[1]);
                break;
            case "M":
                if (args.length != 2) {
                    System.err.println("Usage: M <flight_code>");
                    System.out.println();
                    return;
                }
                displayReservationMap(args[1]);
                break;
            case "F":
                if (args.length != 3 && args.length != 4) {
                    System.err.println("Usage: F <flight_code> (optional <num_seats_executive>) <num_seats_tourist>");
                    System.out.println();
                    return;
                }
                addNewFlight(args);
                break;
            case "R":
                if (args.length != 4) {
                    System.err.println("Usage: R <flight_code> <class> <num_seats>");
                    System.out.println();
                    return;
                }
                reserveSeats(args);
                break;
            case "C":
                if (args.length != 2) {
                    System.err.println("Usage: C <reservation_code>");
                    System.out.println();
                    return;
                }
                cancelReservation(args[1]);
                break;
            case "Q":
                break;
            default:
                System.out.println("Invalid option");
                System.out.println();
                break;
        }
    }

    public static void readFlightFile(String filename) {
        String flightCode = "";
        String failedReservations = "";
        int[][] touristSeats = null;
        int[][] executiveSeats = null;
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            int lineNumber = 1;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split(" ");

                if (lineNumber == 1) {
                    if (words[0].charAt(0) != '>' || 2 > words.length || words.length > 3) {
                        System.out.println("Invalid file format");
                        System.exit(1);
                    }

                    flightCode = words[0].substring(1);

                    if (words.length == 3) {
                        executiveSeats = new int[Integer.parseInt(words[1].substring(0, words[1].indexOf("x")))]
                                [Integer.parseInt(words[1].substring(words[1].indexOf("x") + 1))];
                        touristSeats = new int[Integer.parseInt(words[2].substring(0, words[2].indexOf("x")))]
                                [Integer.parseInt(words[2].substring(words[2].indexOf("x") + 1))];
                    } else {
                        executiveSeats = new int[0][0];
                        touristSeats = new int[Integer.parseInt(words[1].substring(0, words[1].indexOf("x")))]
                                [Integer.parseInt(words[1].substring(words[1].indexOf("x") + 1))];
                    }

                    Aviao plane = new Aviao(executiveSeats, touristSeats);
                    System.out.println(plane.toString());
                    Voo flight = new Voo(flightCode, plane);
                    voos.put(flightCode, flight);
                } else {
                    Voo flight = voos.get(flightCode);
                    if (words[0].startsWith("E")) {
                        if (flight.getCapacidadeExecutiva() == 0) {
                            failedReservations += " " + words[0] + words[1];
                        } else {
                            if (!flight.reservar(Classe.EXECUTIVA, Integer.parseInt(words[1]))) {
                                failedReservations += " " + words[0] + words[1];
                            }
                        }
                    } else if (words[0].startsWith("T")) {
                        if (flight.getCapacidadeTuristica() == 0) {
                            failedReservations += " " + words[0] + words[1];
                        } else {
                            if (!flight.reservar(Classe.TURISTICA, Integer.parseInt(words[1]))) {
                                failedReservations += " " + words[0] + words[1];
                            }
                        }
                    }
                }
                lineNumber++;
            }

            if (executiveSeats.length == 0) {
                System.out.println("Flight Code: " + flightCode + ". Available Seats: "
                        + touristSeats.length * touristSeats[0].length + " in Tourist class.");
                if (!failedReservations.isEmpty()) {
                    System.out.println("Failed to make reservation:" + failedReservations);
                }
            } else {
                System.out.println("Flight Code: " + flightCode + ". Available Seats: "
                        + executiveSeats.length * executiveSeats[0].length + " in Executive class; "
                        + touristSeats.length * touristSeats[0].length + " in Tourist class.");
                if (!failedReservations.isEmpty()) {
                    System.out.println("Failed to make reservation:" + failedReservations);
                }
            }
            System.out.println();

            scanner.close();
        } catch (Exception e) {
            System.out.println("Error opening file");
            return;
        }
    }

    public static void displayReservationMap(String flightCode) {
        if (voos.containsKey(flightCode)) {
            voos.get(flightCode).mapaReservas();
        } else {
            System.out.println("Flight does not exist");
            System.out.println();
        }
    }

    public static void addNewFlight(String[] args) {
        String flightCode = args[1];
        int numFilasExecutiva = 0;
        int numBancosExecutiva = 0;
        int numFilasTuristica = 0;
        int numBancosTuristica = 0;

        if (voos.containsKey(flightCode)) {
            System.out.println("Flight already exists");
            System.out.println();
            return;
        }

        if (args.length == 4) {
            String[] seatsExecutiva = args[2].split("x");
            numFilasExecutiva = Integer.parseInt(seatsExecutiva[0]);
            numBancosExecutiva = Integer.parseInt(seatsExecutiva[1]);

            String[] seatsTuristica = args[3].split("x");
            numFilasTuristica = Integer.parseInt(seatsTuristica[0]);
            numBancosTuristica = Integer.parseInt(seatsTuristica[1]);
        } else {
            String[] seatsTuristica = args[2].split("x");
            numFilasTuristica = Integer.parseInt(seatsTuristica[0]);
            numBancosTuristica = Integer.parseInt(seatsTuristica[1]);
        }

        int[][] executiveSeats = new int[numFilasExecutiva][numBancosExecutiva];
        int[][] touristSeats = new int[numFilasTuristica][numBancosTuristica];
        Aviao plane = new Aviao(executiveSeats, touristSeats);
        Voo flight = new Voo(flightCode, plane);
        voos.put(flightCode, flight);
        System.out.println();
    }

    public static void reserveSeats(String[] args) {
        String flightCode = args[1];
        String classType1 = args[2].toUpperCase();
        Classe classType = Classe.NONE;
        int numSeats = Integer.parseInt(args[3]);

        switch (classType1) {
            case "E":
                classType = Classe.EXECUTIVA;
                break;
            case "T":
                classType = Classe.TURISTICA;
                break;
            default:
                System.out.println("Invalid class");
                System.out.println();
                return;
        }

        if (voos.containsKey(flightCode)) {
            Voo flight = voos.get(flightCode);
            if (flight.reservar(classType, numSeats)) {
                Aviao airplane = flight.getAviao();
                int reservationCode = flight.getNumReservas();
                int[][] executivaSeats = airplane.getExecutiva();
                int[][] turisticaSeats = airplane.getTuristica();

                System.out.print(flightCode + ":" + reservationCode + " = ");

                int numRowsE = executivaSeats.length;
                int numSeatsE = executivaSeats[0].length;
                int numSeatsT = turisticaSeats[0].length;

                int prints = 0;
                for (int row = 0; row < numRowsE + turisticaSeats.length; row++) {
                    char letter = 'A';
                    for (int seat = 0; seat < Math.max(numSeatsE, numSeatsT); seat++) {
                        if (row < numRowsE) {
                            if (seat < numSeatsE) {
                                if (executivaSeats[row][seat] == reservationCode) {
                                    prints++;
                                    if (prints < numSeats) {
                                        System.out.print("" + (row + 1) + letter + " | ");
                                    } else {
                                        System.out.print("" + (row + 1) + letter);
                                    }
                                }
                            }
                        } else {
                            if (seat < numSeatsT) {
                                if (turisticaSeats[row - numRowsE][seat] == reservationCode) {
                                    prints++;
                                    if (prints < numSeats) {
                                        System.out.print("" + (row + 1) + letter + " | ");
                                    } else {
                                        System.out.print("" + (row + 1) + letter);
                                    }
                                }
                            }
                        }
                        letter++;
                    }
                }
                System.out.println();
                System.out.println();
            } else {
                System.out.println("Reservation failed");
                System.out.println();
                return;
            }
        } else {
            System.out.println("Flight does not exist");
            System.out.println();
            return;
        }
    }

    public static void cancelReservation(String reservationCode) {
        String[] code = reservationCode.split(":");
        String flightCode = code[0];
        int resCode = Integer.parseInt(code[1]);

        if (voos.containsKey(flightCode)) {
            if (!voos.get(flightCode).cancelarReserva(resCode)) {
                System.out.println("Reservation does not exist");
                return;
            } else {
                System.out.println("Reservation canceled");
            }
        } else {
            System.out.println("Flight does not exist");
        }
        System.out.println();
    }

    public static void readFile(String filename) {
        String codigovoo = "";
        String mareserva = "";
        int[][] lugaresturistica = null;
        int[][] lugaresexecutiva = null;
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            int numLine = 1;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split(" ");

                if (numLine == 1) {
                    // verifica se o ficheiro tem o formato correto
                    if (words[0].charAt(0) != '>' || 2 > words.length || words.length > 3) {
                        System.out.println("Formato de ficheiro inválido");
                        System.exit(1);
                    }

                    // voo
                    codigovoo = words[0].substring(1);

                    // classe executiva, se nao tiver, nao adiciona lugares
                    if (words.length == 3) {
                        lugaresexecutiva = new int[Integer.parseInt(words[1].substring(0, words[1].indexOf("x")))][Integer.parseInt(words[1].substring(words[1].indexOf("x") + 1))];
                        lugaresturistica = new int[Integer.parseInt(words[2].substring(0, words[2].indexOf("x")))][Integer.parseInt(words[2].substring(words[2].indexOf("x") + 1))];
                    } else {
                        lugaresexecutiva = new int[0][0];
                        lugaresturistica = new int[Integer.parseInt(words[1].substring(0, words[1].indexOf("x")))][Integer.parseInt(words[1].substring(words[1].indexOf("x") + 1))];
                    }
                    // criar classes
                    Aviao plane = new Aviao(lugaresexecutiva, lugaresturistica);
                    System.out.println(plane.toString());
                    Voo voo = new Voo(codigovoo, plane);
                    voos.put(codigovoo, voo);

                } else {
                    // verificar se existe espaço para fazer a reserva e, se nao for possivel,
                    // guarda a reserva que deu erro
                    Voo voo = voos.get(codigovoo);
                    if (words[0].startsWith("E")) {
                        if (voo.getCapacidadeExecutiva() == 0) {
                            mareserva += " " + words[0] + words[1];
                        } else {
                            if (!voo.reservar(Classe.EXECUTIVA, Integer.parseInt(words[1]))) {
                                mareserva += " " + words[0] + words[1];
                            }
                        }
                    } else if (words[0].startsWith("T")) {
                        if (voo.getCapacidadeTuristica() == 0) {
                            mareserva += " " + words[0] + words[1];
                        } else {
                            if (!voo.reservar(Classe.TURISTICA, Integer.parseInt(words[1]))) {
                                mareserva += " " + words[0] + words[1];
                            }
                        }
                    }
                }
                numLine++;
            }

            // imprimir lugares disponiveis e reservas que nao foram possiveis
            if (lugaresexecutiva.length == 0) {
                System.out.println("Codigo de voo: " + codigovoo + ". Lugares disponiveis: "
                        + lugaresturistica.length * lugaresturistica[0].length + " em classe Turistica.");
                if (!mareserva.isEmpty()) {
                    System.out.println("Não foi possível efetuar a reserva:" + mareserva);
                }
            } else{
                System.out.println("Codigo de voo: " + codigovoo + ". Lugares disponiveis: "
                        + lugaresexecutiva.length * lugaresexecutiva[0].length + " em classe Executiva; "
                        + lugaresturistica.length * lugaresturistica[0].length + " em classe Turistica.");
                if (!mareserva.isEmpty()) {
                    System.out.println("Não foi possível efetuar a reserva:" + mareserva);
                }
            }
            System.out.println();

            scanner.close();
        } catch (Exception e) {
            System.out.println("Erro ao abrir ficheiro");
            return;
        }
    }

    public static void main(String[] args) {
        // Se nao tiver argumentos, ler do teclado
        if (args.length == 0) {

            Scanner scanner = new Scanner(System.in);
            String[] inputArgs;
            do {
                System.out.print("Escolha uma opção: (H para ajuda) ");
                String input = scanner.nextLine();
                inputArgs = input.split(" ");
                command(inputArgs);
            } while (!inputArgs[0].toUpperCase().equals("Q"));
            scanner.close();

        // Se tiver um argumento, ler do ficheiro
        } else if (args.length == 1) {

            try {
                File file = new File(args[0]);
                Scanner scanner = new Scanner(file);

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    System.out.println(line);
                    String[] words = line.split(" ");
                    command(words);
                }

                scanner.close();
            } catch (Exception e) {
                System.out.println("Erro ao abrir ficheiro");
                System.exit(1);
            }

        // Se tiver mais que um argumento, erro
        } else {
            System.out.println("Usage: java lab03/Ex02/Ex02 (opcional lab03/Ex02/<filename>)");
            System.exit(1);
        }
    }
}
