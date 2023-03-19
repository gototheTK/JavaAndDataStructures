package scenario1;

public class Person {
    
    private Safe safe;
    private String name;

    public Person(String name, Safe safe) {
        this.name = name;
        this.safe = safe;
    }

    public void deposit(int money) {
        safe.deposit(money);
        System.out.printf("%s �� %d�� �� �� �� �� �� ��.\n", name, money);
    }

    public void withDraw(int money) {
        int withDrawMoney = safe.withDraw(money);
        System.out.printf("%s �� %d�� �� �� �� �� �� ��.\n", name, money);
    }

}