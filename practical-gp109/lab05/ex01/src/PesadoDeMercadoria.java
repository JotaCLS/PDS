




    public class PesadoDeMercadoria extends Veiculo implements KmPercorridoInterface{
        String numeroQuadro;
        double peso;
        double cargaMax;


        PesadoDeMercadoria(String matricula, String marca,String modelo,int potencia,String numeroQuadro, double peso,double cargaMax){
            Matricula = matricula;
            Marca = marca;
            Modelo = modelo;
            Potencia = potencia;
            this.numeroQuadro = numeroQuadro;
            this.peso = peso;
            this.cargaMax = cargaMax;
           

        }
        public String toString(){
            return "Matricula: " + Matricula + "\n" + "Marca: " + Marca + "\n" + "Modelo: " + Modelo + "\n" + "Potencia: " + Potencia + "\n" + "N° Quadro:: " + numeroQuadro + "\n" + "Peso: " + peso + "\n" + "CargaMax: " + cargaMax + "\n"; 
        }
    }


