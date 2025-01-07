public class TeamMember extends EmployeeDecorator {
    private EmployeeInterface employee;

    public TeamMember(EmployeeInterface employee){
        super(employee);
    }

    @Override
    public void work() {
        super.work();
        colaborate(this.employee);
    }

    private void colaborate(EmployeeInterface employee){
        System.out.println("Employee is part of the team");
    }
}
