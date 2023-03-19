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

            //  변 경 구 간 만 동 기 화 하 지 않 은 이 유 는
            // this.money 변 수 를 읽 을 때
            // 누 군 가 가 key 를 이 용 하 여 deposit 메 서 드 를 호 출 하 면
            // 변 경 되 지 않 은 값 을 읽 은 상 로
            // 조 건 문 에 서 판 단 하 는 문 제 가 생 기 기 때 문 입 니 다

            if (this.money < money) {
                System.out.println("잔 고 가 부 족 하여 출 금 할 수 없 습 니 다.");
                return 0;
            }
            this.money -= money;
            return money;
        }
    }

    public void printBalance() {
        // TODO : 잔 고 확 인 메 서 드
        synchronized(key) {
            System.out.printf("현 재 잔 고 는 %d 입 니 다\n", this.money);
        }
    }

}