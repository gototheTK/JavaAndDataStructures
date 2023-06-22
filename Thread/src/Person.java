public class Person implements Runnable {
    public long sum = 0;
    private final long from;
    private final long to;

    public Persion(long from, long to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        for (long i = from; i <= to; i++) {
            if (0 == i % 2) {
                sum += i;
            }
        }

    }

}