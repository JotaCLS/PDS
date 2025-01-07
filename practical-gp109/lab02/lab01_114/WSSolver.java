import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class WSSolver {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Erro: Número incorreto de argumentos. Utilização: java WSSolver <ficheiro>");
            System.exit(1);
        }
        File f = new File(args[0]);
        if (!f.exists()) {
            System.err.println("Erro: Ficheiro não encontrado.");
            System.exit(1);
        }

        // Extrair os últimos números do nome do arquivo de entrada (para depois colocar
        // no ficheiro de saída)
        String inputFileName = f.getName();
        String lastNumbers = inputFileName.replaceAll("[^0-9]", "");

        Scanner scan = new Scanner(f);
        ArrayList<String> sopaLetras = new ArrayList<String>();
        ArrayList<palavrasEncontrar> palavras = new ArrayList<palavrasEncontrar>();

        while (scan.hasNextLine()) {
            String linha = scan.nextLine();
            // Verifica se a linha contém letras minúsculas, exceto as linhas com palavras
            if (!linha.isEmpty() && !linha.contains(";") && !linha.contains(",") && !linha.contains(" ")
                    && containsLowerCase(linha)) {
                System.err.println("Erro: A sopa de letras não pode conter letras minúsculas.");
                System.out.println(linha);
                System.exit(1);
            }

            if (!linha.isEmpty()) {
                if (linha.contains(";") || linha.contains(",") || linha.contains(" ")) {
                    // Linha com palavras a procurar
                    String[] parts = linha.split("[;,\\s]+");
                    // print the parts
                    for (String part : parts) {
                        palavras.add(new palavrasEncontrar(part, part.length(), new int[] { 0, 0 }, "N/A"));
                    }

                } else {
                    // Linha da sopa de letras
                    sopaLetras.add(linha.toUpperCase()); // Converter para maiúsculas
                }

            }
        }
        scan.close();

        // Verifica o tamanho da sopa de letras
        int tamanho = sopaLetras.size();
        if (tamanho > 40 || !isSquare(sopaLetras)) {
            System.err.println("Erro: O puzzle não é válido. Deve ser um quadrado de no máximo 40x40.");
            System.exit(1);
        }

        // procurar as palavras na sopa de letras
        procurarPalavras(sopaLetras, palavras);

        // substituir as letras da sopa de letras que nao formam palavras por pontos
        substituirLetras(sopaLetras, palavras);

        // Criar o nome do arquivo de saída
        String outputFileName = "out" + lastNumbers + ".txt";
        File outputFile = new File(outputFileName);

        try {
            // Tentar excluir o arquivo de saída se ele existir
            if (outputFile.exists() && !outputFile.delete()) {
                throw new IOException("Falha ao excluir o arquivo existente: " + outputFileName);
            }

            // Criar um BufferedWriter para escrever no novo arquivo de saída
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                // Imprime as palavras a procurar no arquivo
                for (palavrasEncontrar palavra : palavras) {
                    writer.write(palavra.toString());
                    writer.newLine();
                }

                writer.newLine(); // Adiciona uma linha em branco entre as palavras e a sopa de letras

                // Imprime a sopa de letras no arquivo com espaços entre os caracteres
                for (String linha : sopaLetras) {
                    char[] caracteres = linha.toCharArray();
                    for (char c : caracteres) {
                        writer.write(c + " "); // Adiciona um espaço entre os caracteres
                    }
                    writer.newLine(); // Move para a próxima linha após imprimir a linha atual
                }
            }
        } catch (IOException e) {
            System.err.println("Erro durante a manipulação do arquivo: " + e.getMessage());
            System.exit(1);
        }

    }

    // Verifica se uma string contém letras minúsculas
    private static boolean containsLowerCase(String input) {
        for (char c : input.toCharArray()) {
            if (Character.isLowerCase(c)) {
                return true;
            }
        }
        return false;
    }

    // Verifica se a sopa de letras é um quadrado
    private static boolean isSquare(ArrayList<String> sopaLetras) {
        int tamanho = sopaLetras.size();
        for (String linha : sopaLetras) {
            if (linha.length() != tamanho) {
                return false;
            }
        }
        return true;
    }

    public static void procurarPalavras(ArrayList<String> sopaLetras, ArrayList<palavrasEncontrar> palavras) {
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

            // A palavra não foi encontrada ou está peresente mais do que uma vez -- erro
            if (occurrences != 1) {
                System.err.println("Todas as palavras da lista têm de estar no puzzle e apenas uma vez");
                System.exit(1);
            } else {
                palavrasEncontradas.add(palavra.getWord());
            }
        }
    }

    private static void substituirLetras(ArrayList<String> sopaLetras, ArrayList<palavrasEncontrar> palavras) {
        for (int i = 0; i < sopaLetras.size(); i++) {
            for (int j = 0; j < sopaLetras.get(i).length(); j++) {
                boolean found = false;
                for (palavrasEncontrar palavra : palavras) {
                    int[] coordinates = palavra.getCoordinates();
                    int row = coordinates[0] - 1;
                    int col = coordinates[1] - 1;
                    int length = palavra.getLength();
                    String direction = palavra.getDirection();

                    // Verifica se a posição atual está dentro da palavra, de acordo com a direção
                    switch (direction) {
                        case "Right":
                            if (i == row && j >= col && j < col + length) {
                                found = true;
                            }
                            break;
                        case "Left":
                            if (i == row && j <= col && j > col - length) {
                                found = true;
                            }
                            break;
                        case "Down":
                            if (j == col && i >= row && i < row + length) {
                                found = true;
                            }
                            break;
                        case "Up":
                            if (j == col && i <= row && i > row - length) {
                                found = true;
                            }
                            break;
                        case "DownRight":
                            if (i >= row && i < row + length && j >= col && j < col + length && i - row == j - col) {
                                found = true;
                            }
                            break;
                        case "UpLeft":
                            if (i <= row && i > row - length && j <= col && j > col - length && row - i == col - j) {
                                found = true;
                            }
                            break;
                        case "DownLeft":
                            if (i >= row && i < row + length && j <= col && j > col - length && i - row == col - j) {
                                found = true;
                            }
                            break;
                        case "UpRight":
                            if (i <= row && i > row - length && j >= col && j < col + length && row - i == j - col) {
                                found = true;
                            }
                            break;
                    }
                    if (found) {
                        break;
                    }
                }
                if (!found) {
                    // Se não fizer parte de nenhuma palavra, substitua a letra por um ponto
                    char[] linha = sopaLetras.get(i).toCharArray();
                    linha[j] = '.';
                    sopaLetras.set(i, new String(linha));
                }
            }
        }
    }

}