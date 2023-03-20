import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;




public class ThreadPool {

    




    public static void main(String[] args) throws ExecutionException{

        ExecutorService threadPool = Executors.newFixedThreadPool(4);

        threadPool.execute(() -> {
            int sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            System.out.println(sum);
        });
        try {
            Future<Long> future1 = threadPool.submit(() -> {
                long sum = 0;
                for (long i = 0; i< 500000000L; i++) {
                    sum += i;
                }
                return sum;
            });

            Future<Long> future2 = threadPool.submit(() -> {
                long sum = 0;
                for (long i = 500000000L; i< 1000000000L; i++){
                    sum += i;
                }
                return sum;
            });
    
            System.out.println(future1.get() + future2.get());
            System.out.println("Main Thread Terminated");

         
        

    //////

    // 운용하는 Thread 갯수가 고정되어있는 Thread Pool
    ExecutorService threadPool1 = Executors.newFixedThreadPool(4);


    // 운용하는 Thread 갯수가 1개로 고정되어있는 Thread Pool
    ExecutorService threadPool2 = Executors.newSingleThreadExecutor();

    // 일정시간 주기적으로 실행해야 하는 작업이 있는 경우 사용하는 Thread Pool
    ScheduledExecutorService threadPool3 = Executors.newScheduledThreadPool(4);


    // 운용하는 Thread의 갯수를 정하지 않고 상황에 따라서 생성 및 해제하는 Thread Pool
    ExecutorService threadPool4 = Executors.newCachedThreadPool();

    threadPool3.schedule(() -> {
        System.out.println("10초 후 실행됨");
    }, 10, TimeUnit.SECONDS);

    threadPool3.schedule(() -> {
        System.out.println("1분 후 실행됨");
    }, 1, TimeUnit.MINUTES);

    threadPool3.schedule(() -> {
        System.out.println("1.2초 후 실행됨");
    }, 1200, TimeUnit.MILLISECONDS);



    ScheduledFuture<Integer> future = threadPool3.schedule(() -> {
        System.out.println("1초 후 실행됨");
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        return sum;
    }, 1, TimeUnit.SECONDS);

    int result = future.get();

} catch (InterruptedException e) {
       // TODO: handle exception
       e.printStackTrace();
       System.out.println("Thread Error");
   }

    }

}