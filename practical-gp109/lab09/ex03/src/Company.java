
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;



class Company {

public static User user;
private List<Employee> emps = new ArrayList<>();

	public void admitEmployee(Person person, int salary) {
		Employee e = new Employee(person, salary);
		emps.add(e);

		SocialSecurity.regist(person);
		Insurance.regist(person);
		EmployeeCard.regist(person);
		if (e.getSalary() > getAvgSalary()) {
			Parking.regist(person);
		}else{
			System.out.println("Employee not registered on Parking System (salary below average)");
		}
	}
	
	public void paySalaries(int month) {
		for (Employee e : emps) {
			BankAccount ba = e.getPerson().getBankAccount();
			ba.deposit(e.getSalary());
		}
	}
	
	public List<Employee> employees() {
		return Collections.unmodifiableList(emps);
	}

	public double getAvgSalary() {
		double sum = 0;
		for (Employee e : emps) {
			sum += e.getSalary();
		}
		return sum / emps.size();
	}
}