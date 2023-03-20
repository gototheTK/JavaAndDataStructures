package scenario2;

public class WithdrawPerson {
    private MoneySafe safe;
    private String name;

    public WithdrawPerson(String name, MoneySafe safe) {
        this.name = name;
        this.safe = safe;
    }

    public void withDraw(int money) {
        safe.withDraw(money, name);
    }
    
}