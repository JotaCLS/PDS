import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class Ex02Main {
        //Hash map para guardar os voos
        private static HashMap<String, Flight> voos = new HashMap<>();

        // Função para apresentar as opções
        private static String menu(){
            return "I filename:\tImportar voos a partir de um ficheiro de texto"
            + "\nM flight_code:\tMostrar mapa de reservas de um voo" +
            "\nF flight_code num_seats_executive num_seats_tourist:\t Criar um novo voo com o código flight_code e com os números de lugares especificados" +
            "\nR flight_code class number_seats:\tReservar number_seats na classe class (E/T) no voo flight_code" +
            "\nC reservation_code:\tCancelar a reserva com o código reservation_code no formato 'flight_code:sequential_reservation_number'." +
            "\nQ:\tSair do programa\n";
        }
        //função para descobrir os comandos

        private static void processCommand(String[] args) {
            String command = args[0].toUpperCase();
            switch (command) {
                case "H":
                    if (args.length > 1) {
                        System.out.println("Usage: H");
                        System.out.println();
                    } else {
                        System.out.println(menu());
                        break;
                    }
                case "I":
                    if (args.length != 2) {
                        System.err.println("Usage: I <filename>");
                        System.out.println();
                        return;
                    }
                    // Verifica se os inputs estao corretos e se existe espaco suficiente para a reserva
                    readFile(args[1]);
                    break;
                case "M":
                    if (args.length != 2) {
                        System.err.println("Usage: M <flight_code>");
                        System.out.println();
                        return;
                    }
        
                    // Imprime mapa da reserva
                    String codigoVooM = args[1];
                    if (voos.containsKey(codigoVooM)) {
                        voos.get(codigoVooM).mapaAvião();
                    } else {
                        System.out.println("Voo não existe");
                        System.out.println();
                        return;
                    }
                    break;
                case "F":
                    if (args.length != 4 && args.length != 3) {
                        System.err.println("Usage: F <flight_code> (opcional <num_seats_executive>) <num_seats_tourist>");
                        System.out.println();
                        return;
                    }
                    // Cria novo voo
                    String codigoVoo = args[1];
                    int numFilasExecutiva = 0;
                    int numBancosExecutiva = 0;
                    int numFilasTuristica = 0;
                    int numBancosTuristica = 0;
                    // Verifica se o voo já existe
                    if (voos.containsKey(codigoVoo)) {
                        System.out.println("Voo já existe");
                        System.out.println();
                        return;
                    }
                    //verifica se existe classe executiva
                    if (args.length == 4) {
                        String[] lugaresExecutiva = args[2].split("x");
                        numFilasExecutiva = Integer.parseInt(lugaresExecutiva[0]);
                        numBancosExecutiva = Integer.parseInt(lugaresExecutiva[1]);
        
                        String[] lugaresTuristica = args[3].split("x");
                        numFilasTuristica = Integer.parseInt(lugaresTuristica[0]);
                        numBancosTuristica = Integer.parseInt(lugaresTuristica[1]);
                    } else {
                        String[] lugaresTuristica = args[2].split("x");
                        numFilasTuristica = Integer.parseInt(lugaresTuristica[0]);
                        numBancosTuristica = Integer.parseInt(lugaresTuristica[1]);
                    }
                    //cria o avião e o voo
                    int[][] executiva = new int[numFilasExecutiva][numBancosExecutiva];
                    int[][] turistica = new int[numFilasTuristica][numBancosTuristica];
                    Aviao aviao = new Aviao(executiva, turistica);
                    Flight novoVoo = new Flight(codigoVoo, aviao);
                    voos.put(codigoVoo, novoVoo);
                    break;
                case "R":
                    if (args.length != 4) {
                        System.err.println("Usage: R <flight_code> <class (E/T)> <number_seats>");
                        System.out.println();
                        return;
                    }
                    // Processa os argumentos e atribui os respetivos valores
                    String codigoVooR = args[1];
                    String classeR = args[2].toUpperCase();
                    Classe classe;
                    int numLugares = Integer.parseInt(args[3]);
                    switch (classeR) {
                        case "E":
                            classe = Classe.EXECUTIVA;
                            break;
                        case "T":
                            classe = Classe.TURISTICA;
                            break;
                        default:
                            System.out.println("Classe inválida");
                            System.out.println();
                            return;
                    }
                    // Verifica se o voo existe
                    if (voos.containsKey(codigoVooR)) {
                        Flight vooR = voos.get(codigoVooR);
                        // Verifica se existe espaco suficiente para a reserva
                        if (vooR.reserve(classe, numLugares)) {
                            // Imprime o codigo da reserva
                            Aviao aviaoR = vooR.getAviao();
                            int reserva = vooR.getReservas();
                            int[][] executivaR = aviaoR.getExecutiva();
                            int[][] turisticaR = aviaoR.getTuristica();
        
                            System.out.print(codigoVooR + ":" + reserva + " = ");
        
                            int numFilasE = executivaR.length;
                            int numFilasT = turisticaR.length;
                            int numFilas = numFilasE + numFilasT;
                            int numBancosFilaE = 0;
                            int numBancosFilaT = 0;
                            int numBancosFila = 0;
        
                            if (numFilasE == 0) {
                                numBancosFilaT = turisticaR[0].length;
                                numBancosFila = numBancosFilaT;
                            } else {
                                numBancosFilaT = turisticaR[0].length;
                                numBancosFilaE = executivaR[0].length;
                                numBancosFila = Math.max(numBancosFilaE, numBancosFilaT);
                            }
                            // Imprime os lugares reservados
                            int prints = 0;
                            for (int fila = 0; fila < numFilas; fila++) {
                                char letra = 'A';
                                for (int banco = 0; banco < numBancosFila; banco++) {
                                    if (fila < numFilasE) {
                                        if (banco < numBancosFilaE) {
                                            if (executivaR[fila][banco] == reserva) {
                                                prints++;
                                                if (prints < numLugares) {
                                                    System.out.print("" + (fila + 1) + letra + " | ");
                                                } else {
                                                    System.out.print("" + (fila + 1) + letra);
                                                }
                                            }
                                        }
                                    } else {
                                        if (numFilasE == 0) {
                                            if (turisticaR[fila][banco] == reserva) {
                                                prints++;
                                                if (prints < numLugares) {
                                                    System.out.print("" + (fila + 1) + letra + " | ");
                                                } else {
                                                    System.out.print("" + (fila + 1) + letra);
                                                }
                                            }
                                        } else {
                                            if (turisticaR[fila - numFilasE][banco] == reserva) {
                                                prints++;
                                                if (prints < numLugares) {
                                                    System.out.print("" + (fila + 1) + letra + " | ");
                                                } else {
                                                    System.out.print("" + (fila + 1) + letra);
                                                }
                                            }
                                        }
                                    }
                                    letra++;
                                }
                            }
                            System.out.println();
                            System.out.println();
        
                        } else {
                            System.out.println("Não foi possível efetuar a reserva");
                            System.out.println();
                            return;
                        }
                    } else {
                        System.out.println("Voo não existe");
                        System.out.println();
                        return;
                    }
        
                    break;
        
                case "C":
                    if (args.length != 2) {
                        System.err.println("Usage: C <reservation_code>");
                        System.out.println();
                        return;
                    }
        
                    // Cancela a reserva de um determinado voo
                    String[] codigoReserva = args[1].split(":");
                    String codigoVooC = codigoReserva[0];
                    int numReserva = Integer.parseInt(codigoReserva[1]);
        
                    // Verifica se o voo existe
                    if (voos.containsKey(codigoVooC)) {
                        // Verifica se a reserva teve sucesso
                        if (!voos.get(codigoVooC).cancelarReserva(numReserva)) {
                            System.out.println("Reserva não existe");
                            return;
                        } else {
                            System.out.println("Reserva cancelada");
                        }
                    } else {
                        System.out.println("Voo não existe");
                    }
                    System.out.println();
        
                    break;
        
                case "Q":
                    if (args.length > 1) {
                        System.err.println("Usage: Q");
                        System.out.println();
                        return;
                    }
                    System.exit(0);
                    break;
        
                default:
                    System.out.println("Opção inválida");
                    System.out.println();
                    break;
        
            }
        }
        
    //função para ler o ficheiro
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
                    // Verifica a validade do ficheiro
                    if (words[0].charAt(0) != '>' || 2 > words.length || words.length > 3) {
                        System.out.println("Formato de ficheiro inválido");
                        System.exit(1);
                    }

                    codigovoo = words[0].substring(1);
                    // Verifica se tem classe executiva
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
                    Flight voo = new Flight(codigovoo, plane);
                    voos.put(codigovoo, voo);

                } else {
                    // verificar se existe espaço para fazer a reserva e, se nao for possivel,
                    // guarda a reserva que deu erro
                    Flight voo = voos.get(codigovoo);
                    if (words[0].startsWith("E")) {
                        if (voo.getCapacidadeExecutiva() == 0) {
                            mareserva += " " + words[0] + words[1];
                        } else {
                            if (!voo.reserve(Classe.EXECUTIVA, Integer.parseInt(words[1]))) {
                                mareserva += " " + words[0] + words[1];
                            }
                        }
                    } else if (words[0].startsWith("T")) {
                        if (voo.getCapacidadeTuristica() == 0) {
                            mareserva += " " + words[0] + words[1];
                        } else {
                            if (!voo.reserve(Classe.TURISTICA, Integer.parseInt(words[1]))) {
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
                processCommand(inputArgs);
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
                    processCommand(words);
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
