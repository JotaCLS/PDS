package ex1;

import java.util.ArrayList;
import java.util.List;

import EmployeeOperations;
import Empregado;
import Registos;

public class EmployeeAdapter implements EmployeeOperations {
    private Database database;
    private Registos registos;

    public EmployeeAdapter(Database database, Registos registos) {
        this.database = database;
        this.registos = registos;
    }

    @Override
    public void addEmployee(Empregado emp) {
        // Adicionar empregado ao Database
        // Converter Empregado para Employee
        Employee employee = new Employee(emp.nome(), emp.codigo(), emp.salario());
        database.addEmployee(employee);

        // Adicionar empregado ao Registos
        registos.insere(emp);
    }

    @Override
    public void removeEmployee(long emp_num) {
        // Remover empregado do Database
        // Converter int para long
        database.deleteEmployee(emp_num);

        // Remover empregado do Registos
        registos.remove((int) emp_num);
        
    }

    @Override
    public boolean isEmployee(long emp_num) {
        // Verificar se empregado existe no Database
        // Converter int para long
        for (Employee e : database.getAllEmployees()) {
            if (e.getEmpNum() == emp_num) {
                if(registos.isEmpregado((int) emp_num)){
                    return true;
                }

            }
        }

        return false;
    }

    @Override
    public List<Employee> listEmployees() {
        // Obter todos os empregados do Database
        // Converter Employee para Empregado
        List<Employee> employees = new ArrayList<>();
        for (Empregado e : registos.listaDeEmpregados()) {
            Employee employee = new Employee(e.nome(), e.codigo(), e.salario());
            employees.add(employee);
        }

        

        return employees;
        
        
    }
}
