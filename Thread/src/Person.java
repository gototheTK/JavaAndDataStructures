public class Person implements Runnable{

    private final Sink sink;

    public Person(Sink sink) {
        this.sink = sink;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            sink.washingDishes();
        }
    }
    
}