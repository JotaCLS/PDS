


    public abstract class Veiculo {

        public static String Matricula;
        public static String Marca;
        public static String Modelo;
        public static int Potencia;
        int trajeto = 0;
        int total = 0;

        public void trajeto(int quilometros){

            this.trajeto = quilometros;
            total = total + this.trajeto;
            

        }
        public int ultimoTrajeto(){

            return(trajeto);


        }
        public int distanciaTotal(){

            
            return(total);

        }

        public String getMatricula(){
            return Matricula;
        }

        public String getMarca(){
            return Marca;
        }

        public String getModelo(){
            return Modelo;
        }


      
        
    }


    

