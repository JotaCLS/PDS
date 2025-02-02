import java.util.Date;

public abstract class EmployeeDecorator implements EmployeeInterface {
    
    protected EmployeeInterface employee;

    public EmployeeDecorator(EmployeeInterface employee){
        this.employee = employee;
    }

    @Override
    public void work() {
        employee.work();
    }

    @Override
    public void start(Date date) {
        employee.start(date);
        
    }

    @Override
    public void terminate(Date date) {
        employee.start(date);
    }


}
