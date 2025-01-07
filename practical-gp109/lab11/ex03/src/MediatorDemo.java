public class MediatorDemo {
    public static void main(String[] args) {

        AirTrafficControl atc = new AirTrafficControl();

        Airplane airplane1 = new Airplane("Airplane 1", atc);
        Airplane airplane2 = new Airplane("Airplane 2", atc);
        Airplane airplane3 = new Airplane("Airplane 3", atc);
        Airplane airplane4 = new Airplane("Airplane 4", atc);

        atc.addAirplane(airplane1);
        atc.addAirplane(airplane2);
        atc.addAirplane(airplane3);
        atc.addAirplane(airplane4);

        airplane1.sendMessage("Hello from Airplane 1");
        airplane2.sendMessage("Hello from Airplane 2");
        airplane3.sendMessage("Hello from Airplane 3");
        airplane4.sendMessage("Hello from Airplane 4");
    }
    
}
