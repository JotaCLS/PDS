import java.util.ArrayList;

public class Insurance {

    private static ArrayList<Person> InsuranceList = new ArrayList<Person>();
    
    public static void regist(Person p) {
        if (!InsuranceList.contains(p)) {
            InsuranceList.add(p);
            System.out.println("Person sucessfully registered on Insurance System");
        } else {
            System.out.println("Person already registered on Insurance System");
        }
    }
}
