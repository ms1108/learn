package multiThread;

import org.testng.annotations.Test;

import java.util.concurrent.*;

public class FutureTest {
    @Test
    public void test(){
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("call");
                TimeUnit.SECONDS.sleep(1);
                return "str";
            }
        });
        // 手动阻塞调用get通过call方法获得返回值。
        try {
            System.out.println(future.get());
            System.out.println("main");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        // 需要手动关闭，不然线程池的线程会继续执行。
        executor.shutdown();
    }
}
