package scenario2;

public class Scenario2{

    public static void run() {
        MoneySafe safe = new MoneySafe(0);

        WithdrawPerson personA = new WithdrawPerson("출금자", safe);
        DepositPerson personB = new DepositPerson("입금자", safe);

        Thread threadA = new Thread(() -> {
            personA.withDraw(1000);
            personA.withDraw(500);
            personA.withDraw(1000);
            personA.withDraw(2000);
        });
        threadA.start();

        Thread threadB = new Thread(() -> {
            personB.deposit(1000);
            personB.deposit(1000);
            personB.deposit(2000);
            personB.deposit(3000);
            personB.deposit(5000);
        });
        threadB.start();

        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        safe.printBalance();
    }


}