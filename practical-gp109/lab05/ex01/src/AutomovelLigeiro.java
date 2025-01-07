


    
    public class AutomovelLigeiro extends Veiculo implements KmPercorridoInterface{
        String numeroQuadro;
        int capacidade;

        AutomovelLigeiro(String matricula, String marca,String modelo,int potencia,String numeroQuadro, int capacidade){
            Matricula = matricula;
            Marca = marca;
            Modelo = modelo;
            Potencia = potencia;
            this.numeroQuadro = numeroQuadro;
            this.capacidade = capacidade;



        }

        public String toString(){
            return "Matricula: " + Matricula + "\n" + "Marca: " + Marca + "\n" + "Modelo: " + Modelo + "\n" + "Potencia: " + Potencia + "\n" + "N° Quadro:: " + numeroQuadro +  "\n" +"Capacidade: " + capacidade + "\n"; 
        }


    }
    

