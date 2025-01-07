package ex1;

import javax.xml.crypto.Data;

import Registos;

public class main {
    
    public static void main(String[] args) {
        Registos r = new Registos();
        r.insere(new Empregado("João", "Silva", 1, 1000));
        r.insere(new Empregado("Maria", "Silva", 2, 1200));
        r.insere(new Empregado("Carlos", "Silva", 3, 1300));
        r.insere(new Empregado("Ana", "Silva", 4, 1400));
        r.insere(new Empregado("Rita", "Silva", 5, 1500));
        r.insere(new Empregado("Pedro", "Silva", 6, 1600));
        r.insere(new Empregado("Marta", "Silva", 7, 1700));
        r.insere(new Empregado("Rui", "Silva", 8, 1800));
        r.insere(new Empregado("Sara", "Silva", 9, 1900));
        r.insere(new Empregado("Vasco", "Silva", 10, 2000));
        r.remove(5);
        r.remove(10);
        r.remove(1);
        r.remove(7);
        r.remove(3);
        r.remove(8);
        r.remove(4);
        r.remove(6);
        r.remove(2);
        r.remove(9);
        System.out.println(r.isEmpregado(1));
        System.out.println(r.isEmpregado(2));
        System.out.println(r.isEmpregado(3));
        System.out.println(r.isEmpregado(4));
        System.out.println(r.isEmpregado(5));
        System.out.println(r.isEmpregado(6));
        System.out.println(r.isEmpregado(7));
        System.out.println(r.isEmpregado(8));
        System.out.println(r.isEmpregado(9));
        System.out.println(r.isEmpregado(10));
        Database d = new Database();
        d.addEmployee(new Employee("João", 1, 1000));
        d.addEmployee(new Employee("Maria", 2, 1200));
        d.addEmployee(new Employee("Carlos", 3, 1300));
        d.addEmployee(new Employee("Ana", 4, 1400));
        d.addEmployee(new Employee("Rita", 5, 1500));
        d.addEmployee(new Employee("Pedro", 6, 1600));
        d.addEmployee(new Employee("Marta", 7, 1700));
        d.addEmployee(new Employee("Rui", 8, 1800));
        d.addEmployee(new Employee("Sara", 9, 1900));
        d.addEmployee(new Employee("Vasco", 10, 2000));
        d.deleteEmployee(5);
        d.deleteEmployee(10);
        d.deleteEmployee(1);
        d.deleteEmployee(7);
        d.deleteEmployee(3);
        d.getAllEmployees();
    }

}
