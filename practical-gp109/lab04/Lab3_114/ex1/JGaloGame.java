public class JGaloGame implements JGaloInterface {
    private char[][] jogo;
    private char jogador;
    private int contMovimentos;

    public JGaloGame() {
        jogo = new char[3][3];
        jogador = 'X';
        contMovimentos = 0;
    }

    public JGaloGame(char charAt) {
        jogo = new char[3][3];
        jogador = charAt;
        contMovimentos = 0;
    }

    public void setJogador(char jogador) {
        this.jogador = jogador;
    }

    public int getcontMovimentos() {
        return contMovimentos;
    }

    public void setcontMovimentos(int contMovimentos) {
        this.contMovimentos = contMovimentos;
    }

    public char[][] getJogo() {
        return jogo;
    }

    public void setJogo(char[][] jogo) {
        this.jogo = jogo;
    }

    @Override
    public char getActualPlayer() {
        return jogador;
    }

    @Override
    public boolean setJogada(int lin, int col) {
        if (lin < 1 || lin > jogo.length || col < 1 || col > jogo[0].length) {
            return false;
        }

        if (jogador == 'X' || jogador == 'O') {
            return verificarJogada(lin, col);
        }
        return false;
    }

    private boolean verificarJogada(int lin, int col) {
        if (jogo[lin - 1][col - 1] == 0) {
            jogo[lin - 1][col - 1] = jogador;
            contMovimentos++;
            jogador = (jogador == 'X') ? 'O' : 'X';
            return true;
        }
        return false;
    }

    @Override
    public boolean isFinished() {
        if (contMovimentos == 9)
            return true;
        return checkResult() != ' ';
    }

    @Override
    public char checkResult() {
        for (int i = 0; i < 3; i++) {
            if (verificarLinha(i)) return jogo[i][0];
            if (verificarColuna(i)) return jogo[0][i];
        }
        if(verificarDiagonais()) return jogo[1][1];
        return ' ';
    }

    private boolean verificarDiagonais() {
        return (jogo[0][0] == jogo[1][1] && jogo[1][1] == jogo[2][2] && jogo[0][0] != 0) || 
               (jogo[0][2] == jogo[1][1] && jogo[1][1] == jogo[2][0] && jogo[0][2] != 0);
    }

    private boolean verificarColuna(int i) {
        return jogo[0][i] == jogo[1][i] && jogo[1][i] == jogo[2][i] && jogo[0][i] != 0;
    }

    private boolean verificarLinha(int i) {
        return jogo[i][0] == jogo[i][1] && jogo[i][1] == jogo[i][2] && jogo[i][0] != 0;
    }

}
