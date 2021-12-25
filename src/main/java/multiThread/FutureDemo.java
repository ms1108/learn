package multiThread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class FutureDemo {
    public static void main(String[] args) throws Exception {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 执行");
        });
        System.out.println("11111111111");
        completableFuture.get();


        CompletableFuture<Long> completableFuture2 = CompletableFuture.supplyAsync(()->{
            int a=10/0;
            return 1024L;
        });

        Long result = completableFuture2.whenComplete((t,u)->{
            System.out.println("t:"+t);
            System.out.println("u:"+u);
        }).exceptionally((e)->{
            System.out.println(e.getMessage());
            return 1023L;
        }).get();
        System.out.println(result);
    }
}
