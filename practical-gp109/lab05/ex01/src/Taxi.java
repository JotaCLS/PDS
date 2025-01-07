







    public class Taxi extends AutomovelLigeiro{
        String licença;

        Taxi(String matricula, String marca, String modelo,int potencia, String numeroQuadro,int capacidade, String licença) {
            super(matricula, marca, modelo, potencia, numeroQuadro, capacidade);
            this.licença = licença;

            
            
        }



        

        

        public String toString(){
            return "Matricula: " + Matricula + "\n" + "Marca: " + Marca + "\n" + "Modelo: " + Modelo + "\n" + "Potencia: " + Potencia + "\n" + "N° Quadro:: " + numeroQuadro + "Capacidade: " + capacidade + "\n" + "Licença: " + licença + "\n"; 
        }

    }

