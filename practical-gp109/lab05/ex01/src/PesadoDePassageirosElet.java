

public class PesadoDePassageirosElet extends PesadoDePassageiros implements veiculoEletrico {

    int autonomia = 200;
    int porcentagem = 100;

    PesadoDePassageirosElet(String matricula, String marca, String modelo, int potencia, String numeroQuadro,
            double peso, double maxPassageiros) {
        super(matricula, marca, modelo, potencia, numeroQuadro, peso, maxPassageiros);
        
    }

    
    public int autonomia() {

        return autonomia;

    }

    
    public void carregar(int porcentagem) {
        this.porcentagem = porcentagem;
        autonomia = porcentagem*2;

        
        
    }
    public void trajeto(int quilometros){

        this.trajeto = quilometros;
        total = total + this.trajeto;
        autonomia = autonomia - quilometros;
        porcentagem = (autonomia*100)/200;
        

    }
    public int ultimoTrajeto(){

        return(trajeto);


    }
    public int distanciaTotal(){

        
        return(total);

    }
    public String toString(){
        return "Matricula: " + Matricula + "\n" + "Marca: " + Marca + "\n" + "Modelo: " + Modelo + "\n" + "Potencia: " + Potencia + "\n" + "N° Quadro:: " + numeroQuadro +  "\n" +"Max Passageiros: " + maxPassageiros + "\n" +"Autonomia: " + autonomia +  "\n" +"Porcentagem: : " + porcentagem + "%" + "\n"; 
    }
    

    

    
}
