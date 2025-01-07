package lab01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WSGenerator {
    public static void main(String[] args) throws IOException {

        if (args.length != 6 || !args[0].equals("-i") || !args[2].equals("-s") || !args[4].equals("-o")) {
            System.out.println("Usage: java WSGenerator -i <input_file> -s <puzzle_size> -o <output_file>");
            System.exit(1);
        }

        String inputFile = args[1];
        int puzzleSize = Integer.parseInt(args[3]);
        String outputFile = args[5];

        // tamanho da sopa
        if (puzzleSize <= 0 || puzzleSize > 40) {
            System.out.println("Error: Puzzle size must be between 1 and 40");
            System.exit(1);
        }
        ArrayList<String> wordsList = readWordList(inputFile, puzzleSize);
        char[][] puzzleTable = generatePuzzle(wordsList, puzzleSize);


        for (String word : wordsList) {
            placeWord(puzzleTable, word);
        }
        //print to file
        savePuzzleToFile(puzzleTable, wordsList, outputFile);
        
    }
    private static ArrayList<String> readWordList(String inputFile, int size) {
        ArrayList<String> words = new ArrayList<>();
    
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineWords = line.split("[, ;]");
                for (int i = 0; i < lineWords.length; i++) {
                    String word = lineWords[i].toLowerCase();
                    if (word.length() < 3) {
                        System.out.println("Warning: Word '" + word + "' is too short");
                        System.exit(1);
                    }
                    if (word.length() > size) {
                        System.out.println("Warning: Word '" + word + "' is too long for the soup size");
                        System.exit(1);
                    }
                    boolean validWord = true;
                    for (int charIndex = 0; charIndex < word.length(); charIndex++) {
                        if (!Character.isAlphabetic(word.charAt(charIndex))) {
                            System.out.printf("Warning: Word '%s' will be ignored for containing non-alphabetic characters \n", word);
                            validWord = false;
                            break;
                        }
                    }
                    if (validWord) {
                        words.add(word);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return words;
    }

    private static char[][] generatePuzzle(List<String> words, int puzzleSize) {
        char[][] puzzle = new char[puzzleSize][puzzleSize];

        
        Random random = new Random(); 
        for (int i = 0; i < puzzleSize; i++) {
            for (int j = 0; j < puzzleSize; j++) {
                puzzle[i][j] = (char) (random.nextInt(26) + 'A'); 
            }
        }
        return puzzle;
    }
    private static void placeWord(char[][] puzzle, String word) {
        Random random = new Random();
    
        int length = word.length();
        boolean placed = false;
        int attempts = 0;
    
        while (!placed && attempts < 2000) {
            int direction = random.nextInt(8);
    
            int row = random.nextInt(puzzle.length);
            int col = random.nextInt(puzzle.length);
    
            int endRow = row + getRowDelta(direction, (length - 1)); //valida a posição da última letra tendo em conta a direção
            int endCol = col + getColDelta(direction, (length - 1));
            
            if (isValidPlacement(puzzle, row, col, endRow, endCol)) {
                if(placeWord(puzzle, word, row, col, direction)) {
                    placed = true;
                    System.out.println("Placed word: " + word);
                }
            }
    
            attempts++;
        }
    
        if (!placed) {
            System.out.println("Unable to place word: " + word);
        }
    }
    private static int getRowDelta(int direction, int length) {
        int[] delta = {-1, -1, -1, 0, 0, 1, 1, 1};
        return delta[direction] * length;
    }

    private static int getColDelta(int direction, int length) {
        int[] delta = {-1, 0, 1, -1, 1, -1, 0, 1};
        return delta[direction] * length;
    }
    private static boolean isValidPlacement(char[][] puzzle, int startRow, int startCol, int endRow, int endCol) {
        return startRow >= 0 && startRow < puzzle.length &&
               startCol >= 0 && startCol < puzzle.length &&
               endRow >= 0 && endRow < puzzle.length &&
               endCol >= 0 && endCol < puzzle.length &&
               (startRow == endRow || startCol == endCol || Math.abs(startRow - endRow) == Math.abs(startCol - endCol));
    }
    private static boolean placeWord(char[][] puzzle, String word, int startRow, int startCol, int direction) {
        int length = word.length();
    
        for (int i = 0; i < length; i++) {
            int currentRow = startRow + getRowDelta(direction, i);
            int currentCol = startCol + getColDelta(direction, i);
   
            puzzle[currentRow][currentCol] = Character.toUpperCase(word.charAt(i));
        }
    
        return true;
    }
    private static void savePuzzleToFile(char[][] puzzle, ArrayList<String> wordList, String outputFile) {
        try (FileWriter fw = new FileWriter(outputFile)) {
            for (char[] row : puzzle) {
                fw.write(row);
                fw.write('\n');
            }
            for (String word : wordList) {
                fw.write(word);
                fw.write(' ');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
}
