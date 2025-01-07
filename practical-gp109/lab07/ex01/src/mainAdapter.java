package ex1;

import java.util.List;

import Registos;

public class mainAdapter {
    public static void main(String[] args) {
        Database database = new Database();
        Registos registos = new Registos();
        EmployeeOperations employeeOps = new EmployeeAdapter(database, registos);

        // Adicionar empregado
        Empregado emp = new Empregado("João", "Silva", 1, 1000.0);
        employeeOps.addEmployee(emp);
        Empregado emp2 = new Empregado("Maria", "Santos", 2, 2000.0);
        employeeOps.addEmployee(emp2);
        Empregado emp3 = new Empregado("Manuel", "Fernandes", 3, 3000.0);
        employeeOps.addEmployee(emp3);

        // Remover empregado
        employeeOps.removeEmployee(1);

        // Verificar se empregado existe
        boolean exists = employeeOps.isEmployee(1);
        System.out.println("Empregado existe: " + exists);
        boolean exists2 = employeeOps.isEmployee(2);
        System.out.println("Empregado existe: " + exists2);
        boolean exists3 = employeeOps.isEmployee(3); 
        System.out.println("Empregado existe: " + exists3);

        // Listar empregados
        List<Employee> employees = employeeOps.listEmployees();
        for (Employee e : employees) {
            System.out.println(e.getName() + " " + e.getEmpNum() + " " + e.getSalary());
        }
    }
}
