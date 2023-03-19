package scenario1;

public class Scenario1 {

    public static void run() {

        Safe safe = new Safe(50000);

        Person personA = new Person("PersonA", safe);
        Person personB = new Person("PersonB", safe);

        Thread threadA = new Thread(() -> {
            personA.withDraw(1000);
            personA.withDraw(3000);
            personA.deposit(10000);
            personA.withDraw(2000);
            personA.withDraw(3000);
        });
        threadA.start();

        Thread threadB = new Thread(() -> {
            personB.withDraw(4000);
            personB.deposit(1000);
            personB.withDraw(1000);
            personB.withDraw(1000);
            personB.withDraw(1000);
        });
        threadB.start();

        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // TODO : 잔 고 는 45000어이 되어야 한다.
        safe.printBalance();

    }
    
}