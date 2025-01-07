import java.util.ArrayList;
import java.util.ArrayList;


public class Rental {
    private String name;
    private String code;
    private String email;
    private ArrayList<Veiculo> stock;
   
    
    

    public Rental(String name, String code, String email){
        this.name = name;
        this.code = code;
        this.email = email;
        this.stock = new ArrayList<Veiculo>();
    }

    public String getName(){
        return name;
    }

    public String getCode(){
        return code;
    }

    public String getEmail(){
        return email;
    }

    public void addVeiculo(Veiculo v){
        stock.add(v);
    }

    public ArrayList<Veiculo> getStock(){
        return stock;
    }

    
}
