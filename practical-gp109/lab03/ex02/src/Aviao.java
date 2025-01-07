
public class Aviao {
    private int[][] executiva;
    private int[][] turistica;

    public Aviao(int[][] executiva, int[][] turistica) {
        this.executiva = executiva;
        this.turistica = turistica;
    }

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

    private int contarLugaresOcupados(int[][] classe) {
        int ocupados = 0;
        
        for (int[] linha : classe) {
            for (int lugar : linha) {
                if (lugar != 0) {
                    ocupados++;
                }
            }
        }

        return ocupados;
    }

    public int getCapacidadeTuristica() {
        return turistica.length * turistica[0].length;
    }

    public int getCapacidadeExecutiva() {
        return executiva.length * (executiva.length > 0 ? executiva[0].length : 0);
    }

    public int getLugaresOcupadosTuristica() {
        return contarLugaresOcupados(turistica);
    }

    public int getLugaresOcupadosExecutiva() {
        return contarLugaresOcupados(executiva);
    }

    public String toString() {
        return "Aviao: " + getCapacidadeTuristica() + " turistica, " + getCapacidadeExecutiva() + " executiva";
    }
}
