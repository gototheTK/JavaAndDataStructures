public class Main {

    public static void userThread() {
        try {

            long startTime = System.currentTimeMillis();
            Person person1 = new Person(0, 500000000);
            Thread threadA = new Thread(person1);
            Person person2 = new Person(500000001, 1000000000);
            Thread threadB = new Thread(person2);

            // Thread 시작
            threadA.start();
            threadB.start();

            // Main Thread Main
            threadA.join();
            threadB.join();

            System.out.println(person1sum + person2.sum);
            long endTime = System.currentTimeMillis();
            System.out.println("걸린시간:" + (endTime - startTime));

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

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