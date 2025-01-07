import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class WSGenerator {
    private static final int LIMITE_TENTATIVAS = 100;

    public static void main(String[] args) throws IOException {
        // Verificar se foram fornecidos os parâmetros corretos
        if ((args.length != 4 && args.length != 6) || !args[0].equals("-i") || !args[2].equals("-s")) {
            System.err.println(
                    "Uso incorreto. Uso: java WSGenerator -i <arquivo_palavras> -s <dimensao> [-o <arquivo_saida>]");
            System.exit(1);
        }

        int tamanho = 0;

        // Verificar a existência do arquivo de entrada
        File inputFile = new File(args[1]);
        if (!inputFile.exists() || !inputFile.isFile()) {
            System.err.println("Erro: O arquivo de entrada especificado não existe ou não é um arquivo válido.");
            System.exit(1);
        }

        // Verificar se o argumento 'tamanho' é um número inteiro
        try {
            tamanho = Integer.parseInt(args[3]);
        } catch (NumberFormatException e) {
            System.err.println("Erro: A dimensão especificada não é um número inteiro válido.");
            System.exit(1);
        }

        // Extrair os parâmetros de entrada
        String ficheiroEntrada = args[1];

        // Ler palavras do arquivo de entrada
        BufferedReader br = new BufferedReader(new FileReader(ficheiroEntrada));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            // Verificar se a linha é vazia
            if (line.trim().isEmpty()) {
                System.err.println("Erro: O arquivo de entrada não pode conter linhas vazias.");
                System.exit(1);
            }
            sb.append(line).append("\n");
        }
        br.close();

        // Dividir palavras com base em espaço, vírgula e ponto e vírgula
        String[] palavras = sb.toString().split("[,;\\s]+");

        // se alguma palavra for repetida, o programa termina
        for (int i = 0; i < palavras.length; i++) {
            for (int j = i + 1; j < palavras.length; j++) {
                if (palavras[i].equalsIgnoreCase(palavras[j])) {
                    System.err.println("Erro: As palavras não podem ser repetidas.");
                    System.exit(1);
                }
            }
        }

        // Criar ArrayList para armazenar objetos palavrasEncontrar
        ArrayList<palavrasEncontrar> listaPalavras = new ArrayList<>();

        // Array de direções possíveis
        String[] direcoes = { "Right", "Left", "Down", "Up", "DownRight", "UpLeft", "DownLeft", "UpRight" };

        // Criar objetos palavrasEncontrar para cada palavra lida e adicioná-los ao
        // ArrayList
        Random rand = new Random();
        for (String palavra : palavras) {
            // Verificar se a palavra contém caracteres não alfabéticos
            if (!palavra.matches("[a-zA-Z]+")) {
                System.err.println("Erro: As palavras têm de ser compostas só por caracteres alfabéticos.");
                System.exit(1);
            }

            // Verificar se a palavra é maior que o tamanho dado
            if (palavra.length() > tamanho) {
                System.err.println("Erro: Não é possível inserir palavras maiores que as dimensões da sopa de letras.");
                System.exit(1);
            }

            // Criar objeto palavrasEncontrar com direção aleatória
            palavrasEncontrar palavraObj = new palavrasEncontrar(palavra, palavra.length(), new int[] { 0, 0 },
                    "");
            listaPalavras.add(palavraObj);
        }

        ArrayList<String> sopa = new ArrayList<>();
        boolean flag = true;
        int cont = 0;
        int tentativas1 = 0;

        while (flag && tentativas1 < LIMITE_TENTATIVAS) {
            // esvaziar a sopa de letras
            sopa.clear();
            cont = 0;
            tentativas1++;

            for (int i = 0; i < tamanho; i++) {
                for (int j = 0; j < tamanho; j++) {
                    sopa.add(".");
                }
            }

            // Colocar as palavras na sopa de letras
            for (palavrasEncontrar palavraObj : listaPalavras) {
                boolean inserido = false;
                int tentativas2 = 0;
                while (!inserido && tentativas2 < LIMITE_TENTATIVAS) {
                    tentativas2++;
                    int startX = rand.nextInt(tamanho);
                    int startY = rand.nextInt(tamanho);
                    String direcao = direcoes[rand.nextInt(direcoes.length)];
                    int[] coordenadas = { startX, startY };
                    palavraObj.setCoordinates(coordenadas);
                    palavraObj.setDirection(direcao);

                    if (isSafe(sopa, palavraObj.getWord(), startX, startY, direcao, tamanho)) {
                        placeWord(sopa, palavraObj.getWord(), startX, startY, direcao);
                        inserido = true;
                    }
                }
                if (!inserido) {
                    cont++;
                }

            }
            if (cont == 0) {
                flag = false;
            }
        }
        if (tentativas1 == LIMITE_TENTATIVAS) {
            System.err.println("Erro: Não foi possível colocar todas as palavras na sopa de letras.");
            System.exit(1);
        }
        boolean continuar = true; // para ter a certeza que nenhuma palavra está repetida
        int fim = 0; // para garantir que não haja loops infinitos (mesmo que sejam imensamente
                     // improváveis)

        // pode acontecer que ao adicionarmos letras aleatorias sejam repetidas palavras
        // na sopa de letras
        while (continuar && fim < 100) {
            // Escolher o resto das letras aleatóriamente
            for (int i = 0; i < tamanho; i++) {
                for (int j = 0; j < tamanho; j++) {
                    if (sopa.get(i * tamanho + j).equals(".")) {
                        sopa.set(i * tamanho + j, String.valueOf((char) (rand.nextInt(26) + 'A')));
                    }
                }
            }

            // vê se acidentalmente ao colocar letras aleatorias aconteceu de ficaram duas
            // vezes na sopa a mesma palavra, o que não deve acontecer
            if (procurarPalavras(sopa, listaPalavras)) {
                continuar = false;
            } else {
                fim++;
            }

        }

        // se foi fornecido ficheiro de saída escrever o output nele
        if (args.length == 6) {
            // Escrever a sopa de letras no arquivo de saída
            FileWriter fw = new FileWriter(args[5]);
            for (int i = 0; i < tamanho; i++) {
                for (int j = 0; j < tamanho; j++) {
                    fw.write(sopa.get(i * tamanho + j));
                }
                fw.write("\n");
            }

            // Escrever as palavras no arquivo de saída
            for (palavrasEncontrar palavraObj2 : listaPalavras) {
                fw.write(palavraObj2.getWord().toLowerCase() + ";");
            }
            fw.close();
        } else {
            // caso não tenha sido colocado um ficheiro de saída o outpur é escrito no
            // terminal

            // Escrever a sopa de letras no terminal
            for (int i = 0; i < tamanho; i++) {
                for (int j = 0; j < tamanho; j++) {
                    System.out.print(sopa.get(i * tamanho + j));
                }
                System.out.print("\n");
            }

            // Escrever as palavras no terminal
            for (palavrasEncontrar palavraObj2 : listaPalavras) {
                System.out.print(palavraObj2.getWord().toLowerCase() + ";");
            }

            System.out.println("\n");
        }

    }

    // Verificar se a palavra cabe na sopa de letras
    private static boolean isSafe(ArrayList<String> sopa, String palavra, int startX, int startY, String direcao,
            int tamanho) {
        // capitalizar a palavra
        palavra = palavra.toUpperCase();

        for (int i = 0; i < palavra.length(); i++) {
            if (startX < 0 || startY < 0 || startX >= tamanho || startY >= tamanho) {
                // A palavra não cabe na sopa de letras
                return false;
            }

            char currentCell = sopa.get(startX * tamanho + startY).charAt(0);
            if (currentCell != '.' && currentCell != palavra.charAt(i)) {
                // A palavra não cabe na sopa de letras
                return false;
            }

            if (direcao.equals("Right")) {
                startY++;
            } else if (direcao.equals("Left")) {
                startY--;
            } else if (direcao.equals("Down")) {
                startX++;
            } else if (direcao.equals("Up")) {
                startX--;
            } else if (direcao.equals("DownRight")) {
                startX++;
                startY++;
            } else if (direcao.equals("UpLeft")) {
                startX--;
                startY--;
            } else if (direcao.equals("DownLeft")) {
                startX++;
                startY--;
            } else if (direcao.equals("UpRight")) {
                startX--;
                startY++;
            }
        }
        return true;
    }

    // Colocar a palavra na posição dada com a direção dada
    private static void placeWord(ArrayList<String> sopa, String palavra, int startX, int startY, String direcao) {
        // capitalizar a palavra
        palavra = palavra.toUpperCase();

        for (int i = 0; i < palavra.length(); i++) {
            int tamanho = (int) Math.sqrt(sopa.size());
            sopa.set(startX * tamanho + startY, String.valueOf(palavra.charAt(i)));
            if (direcao.equals("Right")) {
                startY++;
            } else if (direcao.equals("Left")) {
                startY--;
            } else if (direcao.equals("Down")) {
                startX++;
            } else if (direcao.equals("Up")) {
                startX--;
            } else if (direcao.equals("DownRight")) {
                startX++;
                startY++;
            } else if (direcao.equals("UpLeft")) {
                startX--;
                startY--;
            } else if (direcao.equals("DownLeft")) {
                startX++;
                startY--;
            } else if (direcao.equals("UpRight")) {
                startX--;
                startY++;
            }
        }

    }

    public static boolean procurarPalavras(ArrayList<String> sopaLetras, ArrayList<palavrasEncontrar> palavras) {
        // Crie uma cópia da lista original de palavras
        ArrayList<palavrasEncontrar> palavrasOrdenadas = new ArrayList<>(palavras);

        // Ordena a lista de palavras por comprimento em ordem decrescente
        Collections.sort(palavrasOrdenadas, Comparator.comparingInt(palavrasEncontrar::getLength).reversed());

        // Lista para acompanhar as palavras já encontradas
        ArrayList<String> palavrasEncontradas = new ArrayList<>();

        // Lista para acompanhar as coordenadas das letras já percorridas
        Set<String> letrasPercorridas = new HashSet<>();

        for (palavrasEncontrar palavra : palavrasOrdenadas) {
            int occurrences = 0;
            // capitalizar as palavras:
            palavra.setWord(palavra.getWord().toUpperCase());

            for (int i = 0; i < sopaLetras.size(); i++) {
                for (int j = 0; j < sopaLetras.get(i).length(); j++) {
                    if (sopaLetras.get(i).charAt(j) == palavra.getWord().charAt(0)) {
                        // Procurar na horizontal, vertical e diagonais
                        for (int di = -1; di <= 1; di++) {
                            for (int dj = -1; dj <= 1; dj++) {
                                // Se ambos di e dj forem 0, é a direção de não movimento, ignoramos
                                if (di == 0 && dj == 0)
                                    continue;

                                int row = i, col = j;
                                int idx = 0;

                                // Verifica se a palavra já foi encontrada completamente em outra busca
                                if (letrasPercorridas.contains(row + "-" + col + "-" + di + "-" + dj)) {
                                    continue;
                                }

                                while (row >= 0 && row < sopaLetras.size() && col >= 0
                                        && col < sopaLetras.get(row).length()
                                        && idx < palavra.getLength()
                                        && sopaLetras.get(row).charAt(col) == palavra.getWord().charAt(idx)) {
                                    idx++;
                                    row += di;
                                    col += dj;
                                }

                                if (idx == palavra.getLength()) {
                                    // Se encontrarmos a palavra
                                    palavra.setCoordinates(new int[] { i + 1, j + 1 });
                                    if (di == -1 && dj == -1)
                                        palavra.setDirection("UpLeft");
                                    else if (di == -1 && dj == 0)
                                        palavra.setDirection("Up");
                                    else if (di == -1 && dj == 1)
                                        palavra.setDirection("UpRight");
                                    else if (di == 0 && dj == -1)
                                        palavra.setDirection("Left");
                                    else if (di == 0 && dj == 1)
                                        palavra.setDirection("Right");
                                    else if (di == 1 && dj == -1)
                                        palavra.setDirection("DownLeft");
                                    else if (di == 1 && dj == 0)
                                        palavra.setDirection("Down");
                                    else if (di == 1 && dj == 1)
                                        palavra.setDirection("DownRight");
                                    occurrences++;

                                    // Marca as letras como percorridas para evitar que uma palavra menor seja
                                    // detectada novamente
                                    int r = i, c = j;
                                    for (int k = 0; k < palavra.getLength(); k++) {
                                        letrasPercorridas.add(r + "-" + c + "-" + di + "-" + dj);
                                        r += di;
                                        c += dj;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (occurrences > 1) { // uma palavra ao colocar as letras aleatoria ficou repetida
                return false;
            } else {
                palavrasEncontradas.add(palavra.getWord());
            }
        }

        return true;
    }
}
