import java.util.ArrayList;

public class Parking {
    
    public static ArrayList<Person> ParkingList = new ArrayList<Person>();
    
    public static void regist(Person p) {
        if (!ParkingList.contains(p)) {
            ParkingList.add(p);
            System.out.println("Person sucessfully registered on Parking System");
        } else {
            System.out.println("Person already registered on Parking System");
        }
    }
}
