package multiThread;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import java.util.concurrent.*;

public class ExecutorInnerTest {
    private static ExecutorService executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() - 1,
            4 * Runtime.getRuntime().availableProcessors(),
            30L, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(4096),
            new BasicThreadFactory.Builder().namingPattern("mock-common-pool-%d").build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Test
    public void test() {

//        executor.execute(()->{
//            System.out.println("sub.execute");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        System.out.println("main");
        Future future = null;
        for (int i = 0; i < 2; i++) {
            future = executor.submit(new Callable() {
                @Override
                public Long call() throws Exception {
                    System.out.println(Thread.currentThread().getName());
                    long sum = 0;
                    for (long i = 0; i <= 10000000l; i++) {
                        sum += i;
                    }
                    return sum;
                }
            });
        }

        Long result = null;
        try {
            result = (Long) future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println(result);
    }
}
