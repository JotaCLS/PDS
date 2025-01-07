import java.util.Date;

public class TodosFazem {
    public static void main(String[] args) {
        // Creating a basic employee
        EmployeeInterface basicEmployee = new Employee("John Doe");
        basicEmployee.start(new Date());
        basicEmployee.work();
        basicEmployee.terminate(new Date());
        
        System.out.println("------------------------------------");
        
        // Creating a manager
        EmployeeInterface manager = new Manager(new Employee("Alice"));
        manager.start(new Date());
        manager.work();
        manager.terminate(new Date());
        
        System.out.println("------------------------------------");
        
        // Creating a team leader who is also a manager
        EmployeeInterface teamLeaderManager = new TeamLeader(new Manager(new Employee("Bob")));
        teamLeaderManager.start(new Date());
        teamLeaderManager.work();
        teamLeaderManager.terminate(new Date());
    }
}
