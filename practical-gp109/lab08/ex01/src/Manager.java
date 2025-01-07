public class Manager extends EmployeeDecorator {
    private EmployeeInterface employee;

    public Manager(EmployeeInterface employee){
        super(employee);
    }

    @Override
    public void work() {
        super.work();
        manage(this.employee);
    }

    private void manage(EmployeeInterface employee){
        System.out.println("Employee is managing");
    }
}
