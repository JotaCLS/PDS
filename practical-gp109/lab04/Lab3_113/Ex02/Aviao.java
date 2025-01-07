
public class Aviao {

    // 0 se lugar está livre, 1 para ocupado
    private int[][] executiva;
    private int[][] turistica;

    //construtor
    
    public Aviao(int[][] executiva, int [][] turistica){
        this.executiva = executiva;
        this.turistica = turistica;

    }
    // getters e setters
    public int[][] getExecutiva() {
        return executiva;
    }

    public int[][] getTuristica() {
        return turistica;
    }

    public void setExecutiva(int[][] executiva) {
        this.executiva = executiva;
    }

    public void setTuristica(int[][] turistica) {
        this.turistica = turistica;
    }

    // capacidade de cada classe
    public int capacidadeExecutiva(){
        int capacidade = 0;
        for (int i = 0; i < executiva.length; i++){
            for (int j = 0; j < executiva[i].length; j++){
                capacidade += executiva[i][j];
            }
        }
        return capacidade;
    }

    public int capacidadeTuristica(){
        int capacidade = 0;
        for (int i = 0; i < turistica.length; i++){
            for (int j = 0; j < turistica[i].length; j++){
                capacidade += turistica[i][j];
            }
        }
        return capacidade;
    }

    // lugares ocupados de cada classe
    public int lugaresOcupadosExecutiva(){
        int lugaresOcupados = 0;
        for (int i = 0; i < executiva.length; i++){
            for (int j = 0; j < executiva[i].length; j++){
                if (executiva[i][j] == 1){
                    lugaresOcupados++;
                }
            }
        }
        return lugaresOcupados;
    }

    public int lugaresOcupadosTuristica(){
        int lugaresOcupados = 0;
        for (int i = 0; i < turistica.length; i++){
            for (int j = 0; j < turistica[i].length; j++){
                if (turistica[i][j] == 1){
                    lugaresOcupados++;
                }
            }
        }
        return lugaresOcupados;
    }

    // toString
    @Override
    public String toString() {
        return "Avião: " + capacidadeTuristica() + " turistica, " + capacidadeExecutiva() + " executiva";   }



}
