import java.util.ArrayList;

public class EmployeeCard {
    
    public static ArrayList<Person> EmployeeList = new ArrayList<Person>();

    public static void regist(Person p) {
        if (!EmployeeList.contains(p)) {
            EmployeeList.add(p);
            System.out.println("Employee sucessfully registered on Employee Card System");
        } else {
            System.out.println("Employee already registered on Employee Card System");
        }
    }
}
