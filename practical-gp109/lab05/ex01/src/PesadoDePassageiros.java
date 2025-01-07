





    public class PesadoDePassageiros extends Veiculo implements KmPercorridoInterface{
        String numeroQuadro;
        double peso;
        double maxPassageiros;

        PesadoDePassageiros(String matricula, String marca,String modelo,int potencia,String numeroQuadro, double peso,double maxPassageiros){
            Matricula = matricula;
            Marca = marca;
            Modelo = modelo;
            Potencia = potencia;
            this.numeroQuadro = numeroQuadro;
            this.peso = peso;
            this.maxPassageiros = maxPassageiros;
           

        }
        public String toString(){
            return "Matricula: " + Matricula + "\n" + "Marca: " + Marca + "\n" + "Modelo: " + Modelo + "\n" + "Potencia: " + Potencia + "\n" + "N° Quadro:: " + numeroQuadro + "\n" + "Peso: " + peso + "n" + "Passageiros: " + maxPassageiros + "\n"; 
        }
    }


