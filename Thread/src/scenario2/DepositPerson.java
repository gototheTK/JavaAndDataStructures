package scenario2;

public class DepositPerson {
    private MoneySafeKey safe;
    private String name;

    public DepositPerson(String name, MoneySafeKey safe) {
        this.name = name;
        this.safe = safe;
    }

    public void deposit(int money) {
        safe.deposit(money, name);
    }
}