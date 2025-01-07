



public class AutomovelLigeiroElet extends AutomovelLigeiro implements veiculoEletrico  {

    int autonomia = 200;
    int porcentagem = 100;

    AutomovelLigeiroElet(String matricula, String marca, String modelo,int potencia, String numeroQuadro,int capacidade) {
        super(matricula, marca, modelo, potencia, numeroQuadro, capacidade);
    
        
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
        return "Matricula: " + Matricula + "\n" + "Marca: " + Marca + "\n" + "Modelo: " + Modelo + "\n" + "Potencia: " + Potencia + "\n" + "N° Quadro:: " + numeroQuadro +  "\n" +"Capacidade: " + capacidade + "\n" +"Autonomia: " + autonomia +  "\n" +"Porcentagem: : " + porcentagem + "%" + "\n"; 
    }
    
    
}
