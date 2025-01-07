package ex1;
public class ProxyClass implements BankAccount{

    private BankAccountImpl bankAccountImpl;
    
    public ProxyClass(String bank, double initialDeposit) {
        bankAccountImpl = new BankAccountImpl(bank, initialDeposit);
    }

    public void deposit(double amount) {
        bankAccountImpl.deposit(amount);
    }

    @Override
    public boolean withdraw(double amount) {
        return true;
    }

    @Override
    public double balance() {
        return 0.0;
    }
    
    public String getBank() {
        return bankAccountImpl.getBank();
    }
    
}
