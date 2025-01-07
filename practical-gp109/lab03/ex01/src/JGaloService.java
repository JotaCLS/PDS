public class JGaloService implements JGaloInterface{
    private char player1;
    private char player2;
    private char actualplayer;
    private int jogadas=0;
    private char[][] jogo = new char[3][3];
    private char vencedor;


// Clean code check !



// construtor com argumento
    JGaloService(char player){
        if(player == 'X'){
            player1 = 'X';
            player2 = 'O';
        }
        else{
            player1 = 'O';
            player2 = 'X';
        }
    }

// construtor por omissao
    JGaloService(){
        player1 = 'X';
        player2 = 'O';
    }

//Getters
    public char getPlayer1(){
        return player1;
    }

    public char getPlayer2(){
        return player2;
    }

//Funcao para obter o player atual
    public char getActualPlayer(){
        if(jogadas%2==0){
            actualplayer = getPlayer1();
        }
        else{
            actualplayer = getPlayer2();
        }
        jogadas++;
        return actualplayer;
    }

//Funcao para verificar se é possivel fazer mais alguma jogada
    public boolean setJogada(int lin, int col){
        if(lin<1 || lin>3 || col<1 || col>3){
            return false;
        }
        else{
            jogo[lin-1][col-1] = actualplayer;
            return true;
        }
    }

//Funcao para verificar se o jogo terminou
    public boolean isFinished(){
        // Se ja forem preenchidas todas as tiles do jogo
        if (jogadas == 9){
            return true;
        }
        // Se ja puder haver um vencedor 
        if (jogadas >=5){
            if (checkResult() != ' '){
                return true;
            }
        }
        return false;
    }

//Funcao para verificar se ha algum vencedor

    public char checkResult(){
        verificarHorizontal();
        verificarVertical();
        verificarDiagonal();
        if (vencedor == 'X' || vencedor == 'O'){
            return vencedor;
        }
        else{
            return ' ';
        }
    }
    
    public void verificarHorizontal(){
        int i;
        for(i=0; i<3; i++){
            if(jogo[i][0] == jogo[i][1] && jogo[i][1] == jogo[i][2]){
                vencedor = jogo[i][0];
                return;
            }
        }
    }

    public void verificarVertical(){
        int i;
        for(i=0; i<3; i++){
            if(jogo[0][i] == jogo[1][i] && jogo[1][i] == jogo[2][i]){
                vencedor = jogo[0][i];
                return;
            }
        }
    }

    public void verificarDiagonal(){
        if(jogo[0][0] == jogo[1][1] && jogo[1][1] == jogo[2][2]){
            vencedor = jogo[0][0];
            return;
        }
        if(jogo[0][2] == jogo[1][1] && jogo[1][1] == jogo[2][0]){
            vencedor = jogo[0][2];
            return;
        }
    }
}