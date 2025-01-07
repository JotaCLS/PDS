
public class main {
    public static void main(String[] args) {
        Strategy creditCard = new CreditCard();
        Strategy paypal = new Paypal();
        Strategy bitcoin = new Crypto();

        StrategyDirector strategyDirector = new StrategyDirector(paypal);

        strategyDirector.execute();

        strategyDirector.setStrategy(creditCard);

        strategyDirector.execute();

        strategyDirector.setStrategy(bitcoin);

        strategyDirector.execute();

        
    }
}
