public class VehicleFactory {

    public static Veiculo createMotociclo(String matricula, String marca, String modelo, int potencia, String tipo){
        return new Motociclo(matricula, marca, modelo, potencia, tipo);
    }

    public static Veiculo createAutomovelLigeiro(String matricula, String marca, String modelo, int potencia, String numeroQuadro, int capacidade){
        return new AutomovelLigeiro(matricula, marca, modelo, potencia, numeroQuadro, capacidade);
    }

    public static Veiculo createTaxi(String matricula, String marca, String modelo, int potencia, String numeroQuadro, int capacidade, String licenca){
        return new Taxi(matricula, marca, modelo, potencia, numeroQuadro, capacidade, licenca);
    }

    public static Veiculo createPPEletrico(String matricula, String marca, String modelo, int potencia, int autonomia, String numeroQuadro,
            int porcentagem, int peso, int maxPassageiros) {
        return new PesadoDePassageirosElet(matricula, marca, modelo, potencia, numeroQuadro, peso, maxPassageiros);
    }

    public static Veiculo createALEletrico(String matricula, String marca, String modelo, int potencia, String numQuadro, int autonomia,
            int peso, int l) {
        return new AutomovelLigeiroElet(matricula, marca, modelo, potencia, numQuadro, peso);
    }

    public static Veiculo createPesadoMercadorias(String matricula, String marca, String modelo, int potencia, String numQuadro,
            int peso, int cargaMax) {
        return new PesadoDeMercadoria(matricula, marca, modelo, potencia, numQuadro, peso, cargaMax);
    }

    public static Veiculo createPesadoPassageiros(String matricula, String marca, String modelo, int potencia, int peso,
            String numQuadro, int maxPassageiros) {
        return new PesadoDePassageiros(matricula, marca, modelo, potencia, numQuadro, peso, maxPassageiros);
    }



    
}
