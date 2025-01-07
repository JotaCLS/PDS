import java.util.ArrayList;

public class SocialSecurity {
    
    private static ArrayList<Person> SocialSecurityList = new ArrayList<Person>();

    public static void regist(Person p) {
        if (!SocialSecurityList.contains(p)) {
            SocialSecurityList.add(p);
            System.out.println("Person sucessfully registered on Social Security System");
        } else {
            System.out.println("Person already registered on Social Security System");
        }
    }
}
