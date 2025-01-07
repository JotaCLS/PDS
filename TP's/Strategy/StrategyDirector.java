
public class StrategyDirector {
    private Strategy strategy;

    public StrategyDirector(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute() {
        strategy.execute();
    }
    
}
