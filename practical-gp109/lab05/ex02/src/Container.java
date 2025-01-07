import java.util.Random;

public interface Container {
    Portion getPortion();

    public static Container create(Portion portion){
        Random random = new Random();
        int two_choices = random.nextInt(2);
        if (portion instanceof Tuna){
            if (two_choices == 0){
                return new Tupperware(portion);
            }else{
                return new PlasticBag(portion);
            }
        }

        if (portion instanceof Pork){
            return new Tupperware(portion);
        }

        if (portion instanceof FruitJuice){
            if (two_choices == 0){
                return new TermicBottle(portion);
            }else{
                return new PlasticBottle(portion);
            }
        }

        if (portion instanceof Milk){
            return new TermicBottle(portion);
        }

        throw new IllegalArgumentException("Invalid Portion");
    }
}
