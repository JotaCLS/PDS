

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;



// Code made by: Joao Santos, Angela Ribeiro
//
//  Lab 1 de PDS
//  2023/2024
//  Class para resolver uma sopa de letras
//
//


public class WordSearchSolver {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Usage: java WordSearchSolver <input file>");
            return;
        }

        String inputFile = args[0];

        try {
            List<String> wordList = new ArrayList<>();
            List<String> puzzle = new ArrayList<>();

            Scanner scanner = new Scanner(new File(inputFile));

            while (scanner.hasNextLine()) {
                String line  = scanner.nextLine();
                if (!line.isEmpty()) {
                    if (Character.isLowerCase(line.charAt(0))) {
                        for (String word : line.split("[,;\\s]+")){
                            wordList.add(word.toUpperCase());
                        }
                    } else {
                        puzzle.add(line);
                    }
                } else {
                    System.out.println("Empty line found in input file.");
                    return;
                }
            }
            scanner.close();

            solvePuzzle(puzzle, wordList);

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + inputFile);
        }

    }

    public static void solvePuzzle(List<String> puzzle, List<String> wordList) {
        int size = puzzle.size();
        if (size > 40) {
            System.out.println("Puzzle size is too large. Maximum size is 40x40.");
            return;
        }
        for (String line : puzzle) {
            if (puzzle.size() != line.length()) {
                System.out.println("Puzzle is not a square.");
                return;
            }
        }
        
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
        char[][] grid = new char[size][size];
        char[][] grid2 = new char[size][size];
       

        for (int i = 0; i < size; i++) {
            grid[i] = puzzle.get(i).toCharArray();
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid2[i][j] = '.';
            }
        }


        

        Iterator<String> iterator = wordList.iterator();
        while (iterator.hasNext()) {
            String word = iterator.next();
            if (!word.chars().allMatch(Character::isLetter)) {
                System.out.println("Invalid word: " + word);
                iterator.remove();
            
            }   
            Iterator<String> iterator2 = wordList.iterator();
            while (iterator2.hasNext()) {
                String word2 = iterator2.next();
                if (!word.equals(word2) && word2.contains(word)) {
            iterator.remove();
                break;
                }
            }
            boolean found = false;

            for (int row = 0; row < size; row++) {
                for (int column = 0; column < size; column++) {
                    if (grid[row][column] == word.charAt(0)) {
                        for (int[] direction : directions) {
                            if (findWord(grid, word, row, column, direction)) {
                                System.out.println(word + " " + word.length() + " " + (row + 1) + "," + (column + 1) + " " + verifyDirection(direction));
                                replaceFoundWord(grid2, word, row, column, direction);
                                found = true;
                                break;
                            }
                        }
                    }
                    if (found) {
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
        } 
        System.out.println("\n");
        for (int i = 0; i < size; i++) {
            System.out.println(grid2[i]);
        }
        
    }

    public static boolean findWord(char[][] grid, String word, int row, int column, int[] direction) {
        int size = grid.length;
        int wordLength = word.length();


        for (int i = 1; i< wordLength; i++) {
            int newRow = row + direction[0] * i;
            int newColumn = column + direction[1] * i;

            if (newRow < 0 || newRow >= size || newColumn < 0 || newColumn >= size) {
                return false;
            }

            if (grid[newRow][newColumn] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static String verifyDirection(int[] direction) {
        if (direction[0] == 1 && direction[1] == 0) {
            return "Down";
        } else if (direction[0] == 0 && direction[1] == 1) {
            return "Right";
        } else if (direction[0] == -1 && direction[1] == 0) {
            return "Up";
        } else if (direction[0] == 0 && direction[1] == -1) {
            return "Left";
        } else if (direction[0] == 1 && direction[1] == 1) {
            return "DownRight";
        } else if (direction[0] == -1 && direction[1] == 1) {
            return "UpRight";
        } else if (direction[0] == 1 && direction[1] == -1) {
            return "DownLeft";
        } else if (direction[0] == -1 && direction[1] == -1) {
            return "UpLeft";
        }
        return "";
    }

    public static void replaceFoundWord(char[][] grid, String word, int row, int column, int[] direction) {
        int wordLength = word.length();

        for (int i = 0; i < wordLength; i++) {
            grid[row][column] = word.charAt(i);
            row += direction[0];
            column += direction[1];
        }
    }


    
    
}

