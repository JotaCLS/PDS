

class Person {
	
private String name;
private BankAccount bankAccount;
private ProxyClass proxyClass;

	public Person(String n) {
		name = n;
		bankAccount = new BankAccountImpl("PeDeMeia", 0);
		proxyClass = new ProxyClass("PeDeMeia", 0);
	}

	public String getName() {
		return name;
	}
	
	public BankAccount getBankAccount() {
		if ( Company.user == User.OWNER ) 
			return bankAccount;
		else
			return proxyClass;
	}
}