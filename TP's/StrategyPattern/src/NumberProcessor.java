public class NumberProcessor {
    private Strategy strategy;

    public NumberProcessor(Strategy strategy){
        this.strategy = strategy;
    }

    public Strategy getStrategy() {
        return this.strategy;
    }

    public void setOperation(Strategy strategy) {
        this.strategy = strategy;
    }

    public int performOperation(int a, int b){
        return this.strategy.performOperation(a, b);
    }

}
