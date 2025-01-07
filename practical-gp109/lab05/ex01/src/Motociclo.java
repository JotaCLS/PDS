


    public class Motociclo extends Veiculo implements KmPercorridoInterface{

        String tipo;
        
        Motociclo(String matricula, String marca,String modelo,int potencia,String tipo){
            Matricula = matricula;
            Marca = marca;
            Modelo = modelo;
            Potencia = potencia;
            this.tipo = tipo;

        }

        public String toString(){
            return "Matricula: " + Matricula + "\n" + "Marca: " + Marca + "\n" + "Modelo: " + Modelo + "\n" + "Potencia: " + Potencia + "\n" + "Tipo: " + tipo + "\n"; 
        }
    }

