package scenario1;


public class Safe {
    private int money;
    private final MasterKey key;

    public Safe(int money) {
        this.money = money;
        this.key = new MasterKey();
    }

    public void deposit(int money) {
        synchronized (key) {
            this.money += money;
        }
    }

    public int withDraw(int money) {
        synchronized(key) {

            //  �� �� �� �� �� �� �� ȭ �� �� �� �� �� �� ��
            // this.money �� �� �� �� �� ��
            // �� �� �� �� key �� �� �� �� �� deposit �� �� �� �� ȣ �� �� ��
            // �� �� �� �� �� �� �� �� �� �� �� ��
            // �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��

            if (this.money < money) {
                System.out.println("�� �� �� �� �� �Ͽ� �� �� �� �� �� �� �� ��.");
                return 0;
            }
            this.money -= money;
            return money;
        }
    }

    public void printBalance() {
        // TODO : �� �� Ȯ �� �� �� ��
        synchronized(key) {
            System.out.printf("�� �� �� �� �� %d �� �� ��\n", this.money);
        }
    }

}