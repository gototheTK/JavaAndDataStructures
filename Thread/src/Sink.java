public class Sink {

    private int washDaishes = 100000;

    public synchronized void washingDishes() {
        --washDaishes;
    }

    public int getWashDishes() {
        return washDaishes;
    }
    
}