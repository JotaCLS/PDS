    package lab01;

    import java.util.*;
    import java.io.*;

    public class WSSolverProcessing {
        private static final int MAX_SIZE = 40;
        private int size = 0;
        private int linesCount = 0;
        private File wordSoupFile = null;
        private ArrayList<String> wordSoupFileLines;
        private LinkedHashSet<String> wordsToFind;
        private char[][] wordSoupCharacters;
        private boolean[][] markWordSoupCharacters;
        private LinkedHashMap <String, Object[]> wordSoupSolution; 

        public WSSolverProcessing(String wordSoupFileName, String wordSoupResultFileName) throws IOException {
            this.wordSoupFile = new File(wordSoupFileName);
            this.wordSoupFileLines = new ArrayList<>();
            this.wordSoupLinesToArray();
            if (!this.checkWordSoupConditions()) {
                System.out.println("ERROR: Word Soup conditions not met");
                System.exit(1);
            }
            this.wordSoupToCharArray();
            this.wordsToFind();
            this.wordSoupSolution = new LinkedHashMap<>();
            this.markWordSoupCharacters = new boolean[this.size][this.size];
            this.findFirstCharacter();
            this.checkIfAllWordsFound();
            this.printLines();
            this.saveSolutionToFile(wordSoupResultFileName);
        }

        public void wordSoupLinesToArray(){
            try {
                Scanner fileScanner = new Scanner(this.wordSoupFile);
                while (fileScanner.hasNextLine()) {
                    this.wordSoupFileLines.add(fileScanner.nextLine());
                }
                fileScanner.close();
            } catch (Exception e) {
                System.err.println("ERROR: " + e.getMessage());
                System.exit(1);
            }
            this.getSize();
            this.countLines();
        }

        public void getSize() {
            this.size = this.wordSoupFileLines.get(0).length();
        }

        public void countLines(){
            this.linesCount = this.wordSoupFileLines.size();
        }

        public boolean checkWordSoupConditions(){
            if (this.size > MAX_SIZE) {
                System.err.println("ERROR: Size of Word Soup is too large (max 40x40)");
                return false;
            }

            for (int i = 0; i < this.linesCount; i++) {
                if (this.wordSoupFileLines.get(i).length() == 0){
                    System.err.println("ERROR: Found an empty line");
                    return false;
                }
            }

            for (int i = 0; i < this.size; i++) {
                if (this.wordSoupFileLines.get(i).length() != this.size){
                    System.err.println("ERROR: Found a line with an invalid size");
                    return false;
                }
            }

            for (int i = 0; i < this.size; i++) {
                for (int j = 0; j < this.size; j++) {
                    if (!Character.isUpperCase(this.wordSoupFileLines.get(i).charAt(j))) {
                        System.err.println("ERROR: Word Soup is not a square of capital letters");
                        return false;
                    }
                }
            }

            // Verficar se existe uma linha com todas as letras maiúsculas depois do suposto tamanho da sopa de letras
            for (int i = this.size; i < this.linesCount; i++) {
                int[] lineAllCapitals = new int[this.wordSoupFileLines.get(i).length()];
                for (int j = 0; j < this.wordSoupFileLines.get(i).length(); j++) {
                    if (Character.isUpperCase(this.wordSoupFileLines.get(i).charAt(j))) {
                        lineAllCapitals[j] = 1;
                    } else {
                        lineAllCapitals[j] = 0;
                    }
                }
                if (Arrays.stream(lineAllCapitals).sum() == this.wordSoupFileLines.get(i).length()) {
                    System.err.println("ERROR: Word Soup is not a square.");
                    return false;
                }
            } 

            return true;
        }

        public void wordSoupToCharArray(){
            this.wordSoupCharacters = new char[this.size][this.size];
            for (int i = 0; i < this.size; i++) {
                for (int j = 0; j < this.size; j++) {
                    this.wordSoupCharacters[i][j] = this.wordSoupFileLines.get(i).charAt(j);
                }
            }
        }

        public void wordsToFind(){
            boolean validWord = true;

            this.wordsToFind = new LinkedHashSet<>();
            int i = this.size;
            while (i < this.linesCount) {
                String lineToProcess = this.wordSoupFileLines.get(i);
                String[] words = lineToProcess.split("[;,\\s]+");
                for (String word : words) {
                    if (word.length() < 3) {
                        System.out.printf("WARNING: Ignoring Word \"%s\" because is too short\n", word);
                        continue;
                    }
                    for (int j = 0; j < word.length(); j++) {
                        if (!Character.isAlphabetic(word.charAt(j))) {
                            System.out.printf("WARNING: Word \"%s\" will be ignored because it contains non-alphabetic characters.\n", word);
                            validWord = false;
                            break;
                        }
                    }
                    if (validWord) {
                        this.wordsToFind.add(word);
                    }
                    validWord = true;
                }
                i++;
            }

            if (this.wordsToFind.size() == 0) {
                System.err.println("ERROR: No valid words to find");
                System.exit(1);
            }

            // Remover palavras que estão contidas noutras palavras
            Iterator<String> wordsToFindIterator = wordsToFind.iterator();
            while (wordsToFindIterator.hasNext()) {
                String word1 = wordsToFindIterator.next();
                Iterator<String> wordsToFindInnerIterator = wordsToFind.iterator();
                while (wordsToFindInnerIterator.hasNext()) {
                    String word2 = wordsToFindInnerIterator.next();
                    if (!word1.equals(word2) && word2.contains(word1)) {
                wordsToFindIterator.remove();
                    break;
                    }
                }
            }
        }

        public void findFirstCharacter() {
            for (String word : this.wordsToFind) {
                char characterToFind = Character.toUpperCase(word.charAt(0));
                    for (int j = 0; j < this.size; j++) {
                        for (int k = 0; k < this.size; k++) {
                            if (characterToFind == this.wordSoupCharacters[j][k]){
                                searchWord(word, j, k);
                            }
                        }
                }
            }
        }

        public void searchWord(String word, int row, int column) {
            int[] rowDirection = {-1, -1, -1, 0, 0, 1, 1, 1};
            int[] columnDirection = {-1, 0, 1, -1, 1, -1, 0, 1};
            int wordLength = word.length();
            int endRow = row;
            int endColumn = column;

            for (int direction = 0; direction < 8; direction++) {
                int rowDirectionIncrement = rowDirection[direction];
                int columnDirectionIncrement = columnDirection[direction];
                int rowPosition = row + rowDirectionIncrement;
                int columnPosition = column + columnDirectionIncrement;
                int i;
                for (i = 1; i < wordLength; i++) {
                    if (rowPosition < 0 || rowPosition >= this.size || columnPosition < 0 || columnPosition >= this.size) {
                        break;
                    }
                    if (Character.toUpperCase(word.charAt(i)) != this.wordSoupCharacters[rowPosition][columnPosition]) {
                        break;
                    }
                    endRow = rowPosition;
                    endColumn = columnPosition;
                    rowPosition += rowDirectionIncrement;
                    columnPosition += columnDirectionIncrement;
                }

                if (i == wordLength && !this.wordSoupSolution.containsKey(word)) {
                    markCharacters(row, column, endRow, endColumn);
                    String directionString = getDirectionString(direction);
                    Object[] positionDirection = {row + 1, column + 1, directionString};
                    wordSoupSolution.put(word, positionDirection);
                    break;
                }
            }
        }

        public void markCharacters(int startRow, int startColumn, int endRow, int endColumn) {

            int rowDirection = (endRow - startRow) / Math.max(Math.abs(endRow - startRow), 1);
            int columnDirection = (endColumn - startColumn) / Math.max(Math.abs(endColumn - startColumn), 1);
    
            for (int row = startRow, column = startColumn; row != endRow + rowDirection || column != endColumn + columnDirection; row += rowDirection, column += columnDirection) {
            markWordSoupCharacters[row][column] = true;
            }
        }

        private String getDirectionString(int direction) {
            switch (direction) {
                case 0:
                    return "UpLeft";
                case 1:
                    return "Up";
                case 2:
                    return "UpRight";
                case 3:
                    return "Left";
                case 4:
                    return "Right";
                case 5:
                    return "DownLeft";
                case 6:
                    return "Down";
                case 7:
                    return "DownRight";
                default:
                    return "Unknown";
            }
        }

        public void checkIfAllWordsFound() {
            if (wordSoupSolution.size() != wordsToFind.size()) {
                System.err.println("ERROR: Not all words were found");
                System.exit(1);
            }
        }

        public void printLines() {

            for (Map.Entry<String, Object[]> entry : wordSoupSolution.entrySet()) {
                String word = entry.getKey();
                Object[] solution = entry.getValue();
                int row = (int) solution[0];
                int column = (int) solution[1];
                String direction = (String) solution[2];
                System.out.printf("%-15s %-5d %2d,%2d %12s\n", word, word.length(), row, column, direction);
            }

            System.out.println();

            for (int i = 0; i < this.size; i++) {
                for (int j = 0; j < this.size; j++) {
                    if (markWordSoupCharacters[i][j]) {
                        System.out.print(wordSoupCharacters[i][j] + " ");
                    } else {
                        System.out.print(". ");
                    }
                }
                System.out.println();
            }

        }

        public void saveSolutionToFile(String resultFileName) throws IOException {
            PrintWriter saveSolution = null;

            try {
                FileWriter wordSoupSolutionFile = new FileWriter(resultFileName);
                saveSolution = new PrintWriter(wordSoupSolutionFile);

                System.out.println("\nSaving to file: " + resultFileName);

                for (Map.Entry<String, Object[]> entry : wordSoupSolution.entrySet()) {
                    String word = entry.getKey();
                    Object[] solution = entry.getValue();
                    int row = (int) solution[0];
                    int column = (int) solution[1];
                    String direction = (String) solution[2];
                    saveSolution.printf("%-15s %-5d %2d,%2d %12s\n", word, word.length(), row, column, direction);
                }

                saveSolution.println();

                for (int i = 0; i < this.size; i++) {
                    for (int j = 0; j < this.size; j++) {
                        if (markWordSoupCharacters[i][j]) {
                            saveSolution.print(wordSoupCharacters[i][j] + " ");
                        } else {
                            saveSolution.print(". ");
                        }
                    }
                    saveSolution.println();
                }
                saveSolution.close();
                System.exit(0);
            } catch (IOException e) {
                System.err.println("ERROR: Failed to save solution to file: " + e.getMessage());
                System.exit(1);
            }
        }
    }
