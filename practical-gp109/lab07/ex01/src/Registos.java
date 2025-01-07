package ex1;
import java.util.ArrayList;
import java.util.List;


class Registos {
    // Data elements
    private ArrayList<Empregado> empregados; // Stores the employees
    public Registos() {
        empregados = new ArrayList<>();
    }
    public void insere(Empregado emp) {
    // Code to insert employee
        Empregado e = new Empregado(emp.nome(), emp.apelido(), emp.codigo(), emp.salario());
        empregados.add(e);
    }
    public void remove(int codigo) {
    // Code to remove employee
        for (Empregado e : empregados) {
            if (e.codigo() == codigo) {
                empregados.remove(e);
                break;
            }
        }
    }
    public boolean isEmpregado(int codigo) {
    // Code to check if employee exists
        for (Empregado e : empregados) {
            if (e.codigo() == codigo) {
                return true;
            }
        }
        return false;
    }
    public List<Empregado> listaDeEmpregados() {
    // Code to retrieve collection
        return empregados;
    }
    }
