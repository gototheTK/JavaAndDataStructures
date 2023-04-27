public class Main {

    public static void main(String[] args) throws InterruptedException {

        Sink sink = new Sink();
        Person personA = new Person(sink);
        Person personB = new Person(sink);

        Thread threadA = new Thread(personA);
        Thread threadB = new Thread(personB);

        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();

        System.out.println(sink.getWashDishes());

    }

}