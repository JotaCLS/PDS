import java.util.ArrayList;
import java.util.List;

public class AirTrafficControl implements MediatorInterface{

    private List<Airplane> airplanes = new ArrayList<Airplane>();


    public AirTrafficControl() {
        this.airplanes = airplanes;
    }

    @Override
    public void addAirplane(Airplane airplane) {
        airplanes.add(airplane);
    }

    @Override
    public void sendMessage(String message, Airplane airplane) {
        airplane.receiveMessage(message);
    }
    
}
