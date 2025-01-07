import java.util.Random;

public abstract class BaseHandler implements Handler {
    protected Handler next_handler;
    protected String speciality;

    @Override
    public void setNext(Handler handler) {
        this.next_handler = handler;
    }

    @Override
    public void cook(String plate) {
        String chef_name = this.getClass().getSimpleName();
        if (!plate.toLowerCase().contains(speciality.toLowerCase())) {
            System.out.println(chef_name + ": I can't cook that.");
            if (this.next_handler != null) {
                this.next_handler.cook(plate);
            } else {
                System.out.println("We're sorry but that request can't be satisfied by our service!");
            }
        } else {
            int minutes_left = getRandomMinutes();
            System.out.println(chef_name + ": Starting to cook " + plate + ". Out in " + minutes_left + " minutes!");
        }
    }

    public static int getRandomMinutes() {
        int minMinutes = 1;
        int maxMinutes = 59;
        Random random = new Random();
        return random.nextInt((maxMinutes - minMinutes) + 1) + minMinutes;
    }
}
