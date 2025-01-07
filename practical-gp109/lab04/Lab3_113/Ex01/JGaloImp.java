package lab03.Ex01;

import javax.swing.JOptionPane;

public class JGaloImp implements JGaloInterface{
    // Variáveis de instância
    char playerX;
    char playerO;
    char[][] board;
    char currentPlayer;
    int numPlays;

    // Construtor caso não seja passado nenhum jogador
    public JGaloImp(){
        this.playerX = 'X';
        this.playerO = 'O';
        this.board = new char[3][3];
        this.currentPlayer = playerX;
        this.numPlays = 0;
    }

    // Construtor caso seja passado um jogador
    public JGaloImp (char startingPlayer){
        this.playerX = 'X';
        this.playerO = 'O';
        this.board = new char[3][3];
        startingPlayer = Character.toUpperCase(startingPlayer);
        // Verifica se o jogador passado é válido
        if (startingPlayer == this.playerX || startingPlayer == this.playerO){
            this.currentPlayer = startingPlayer;
        } else {
            this.currentPlayer = playerX;
        }
        this.numPlays = 0;
    }

    @Override
    // Função para devolver o jogador que tem a vez
    public char getActualPlayer(){
        return this.currentPlayer;
    }

    @Override
    // Função para guardar a jogada do jogador
    public boolean setJogada(int row, int column){
        // Subtrair 1 pois os argumentos passados variam entre 1 e 3
        row--;
        column--;

        // Verifica se a posição está dentro do tabuleiro e se está vazia
        if (row < 0 || row > 2 || column < 0 || column > 2 || this.board[row][column] == this.playerX || this.board[row][column] == this.playerO){
            JOptionPane.showMessageDialog(null, "ERROR: Invalid position");
            return false;
        } 

        // Guardar a jogada e passar a vez ao outro jogador
        this.board[row][column] = this.currentPlayer;
        this.numPlays++;
        this.currentPlayer = (this.currentPlayer == this.playerX) ? this.playerO : this.playerX;
        return true;
    }

    @Override
    // Função para verificar se o jogo terminou
    public boolean isFinished(){
        // Verifica se alguém ganhou ou se o jogo empatou
        if ((this.numPlays > 4 && (checkResult() == playerX || checkResult() == playerO)) || (this.numPlays == 9)){
            return true;
        }
        return false;
    }

    @Override
    // Função para verificar se alguém ganhou
    public char checkResult() {
        if (findFirstCharacter('X')){
            return playerX;
        } else if (findFirstCharacter('O')){
            return playerO;
        }
        return ' ';
    }

    // função que irá procurar a primeira ocorrência de uma letra no tabuleiro
    public boolean findFirstCharacter(char characterToFind) {
        // Definir a palavra a procurar com base no jogador
        String word = characterToFind == 'X' ? "XXX" : "OOO";
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (this.board[i][j] == characterToFind){
                    // Ao encontrar uma ocorrência da primeira letra, chama que procurará a palavra
                    return searchWord(word, i, j);
                }
            }
        }
        return false;
    }

    // Função que irá procurar a palavra no tabuleiro
    public boolean searchWord(String word, int row, int column) {
        // Definir as direções possíveis para procurar a palavra
        int[] rowDirection = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] columnDirection = {-1, 0, 1, -1, 1, -1, 0, 1};
        int wordLength = 3;

        for (int direction = 0; direction < 8; direction++) {
            // Percorrer todas as direções possíveis
            int rowDirectionIncrement = rowDirection[direction];
            int columnDirectionIncrement = columnDirection[direction];
            int rowPosition = row + rowDirectionIncrement;
            int columnPosition = column + columnDirectionIncrement;

            int i;
            for (i = 1; i < wordLength; i++) {
                // Verificar se a posição está dentro do tabuleiro
                if (rowPosition < 0 || rowPosition >= 3 || columnPosition < 0 || columnPosition >= 3) {
                    break;
                }
                // Verificar se a letra é a igual à que está a ser procurada
                if (Character.toUpperCase(word.charAt(i)) != this.board[rowPosition][columnPosition]) {
                    break;
                }
                // Incrementar a posição para verificar a próxima letra
                rowPosition += rowDirectionIncrement;
                columnPosition += columnDirectionIncrement;
            }

            if (i == wordLength) {
                // Se a palavra foi encontrada, devolver true
                return true;
            }
        }
        // Caso contrário, devolver false
        return false;
    }
}