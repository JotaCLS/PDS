

class Employee {

private double salary;
private Person person;

	public Employee(Person person, double s) {
		salary = s;
		this.person = person;
	}

	public double getSalary() {
		return salary;
	}

	public Person getPerson() {
		return person;
	}
	
}