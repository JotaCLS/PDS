public class PortionFactory {
    public static Portion create(String type, Temperature temp){
        if (type.equalsIgnoreCase("Meat") && temp.equals(Temperature.WARM)){
            return new Pork();
        }

        if (type.equalsIgnoreCase("Meat") && temp.equals(Temperature.COLD)){
            return new Tuna();
        }

        if (type.equalsIgnoreCase("Beverage") && temp.equals(Temperature.WARM)){
            return new Milk();
        }

        if (type.equalsIgnoreCase("Beverage") && temp.equals(Temperature.COLD)){
            return new FruitJuice();
        }

        throw new IllegalArgumentException("Invalid type");
    }
}
