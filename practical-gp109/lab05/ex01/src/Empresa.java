
import java.util.ArrayList;
import java.util.List;



    
    public class Empresa{
        protected String nome;
        protected int codigoPostal;
        protected String email;
        protected List<Veiculo> conjuntoViaturas = new ArrayList<>(); 


        Empresa(String nome, int codigoPostal,String email){
            this.nome = nome;
            this.codigoPostal = codigoPostal;
            this.email = email;
        }


        public void adicionarVeiculo(Veiculo newVeiculo){

            conjuntoViaturas.add(newVeiculo);

        }

        public String toString(){
            return "Nome: " + nome + "\n" + "Codigo Postal: " + codigoPostal + "\n" + "Email: " + email + "\n" + "Numero de veiculos: " + conjuntoViaturas.size() + "\n"; 
        }






    }
