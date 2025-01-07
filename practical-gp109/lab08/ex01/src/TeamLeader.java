public class TeamLeader extends EmployeeDecorator {
    private EmployeeInterface employee;

    public TeamLeader(EmployeeInterface employee){
        super(employee);
    }

    @Override
    public void work() {
        super.work();
        plan(this.employee);
    }

    private void plan(EmployeeInterface employee){
        System.out.println("Employee is planning");
}
}
