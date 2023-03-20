package scenario2;

public class MoneySafe {
    private int money;
    private final MoneySafeKey key;

    public MoneySafe(int money) {
        this.money = money;
        this.key = new MoneySafeKey();
    }

    public void deposit(int money, String name) {
        synchronized (key) {

            this.money += money;
            System.out.printf("", name, money);

            key.notifyAll();
        }
    }

    public void withDraw(int money, String name){
        synchronized(key) {
            try {
                while (this.money < money) {
                    
                    key.wait();
                }
            } catch(InterruptedException e) {
            e.printStackTrace();
            }
            this.money -= money;
            System.out.printf("\n", name, money);
        }
    }

    public void printBalance() {
        // TODO : 
        synchronized(key) {
            System.out.printf("\n", this.money);
        }
    }

}