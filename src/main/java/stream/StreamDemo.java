package stream;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
 * 1、创建stream
 * 2、中间操作
 * 3、总端操作
 * */
public class StreamDemo {
    //创建Steam
    @Test
    public void creat() {
        //1、通过Collection系列集合提供的stream或parallelStream
        List<String> list = new ArrayList<String>();
        Stream<String> stream = list.stream();
        //2、通过Arrays中的stream获取数组流
        int[] ints = new int[10];
        IntStream stream1 = Arrays.stream(ints);
        //3、通过Stream类中的of方法获取流
        Stream<String> stream2 = Stream.of("a", "b", "c");
        //4、创建无限流
        //迭代,从0开始执行+2操作
        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2);
        //stream3.limit(10).forEach(System.out::println);//终止操作
        //生成
        Stream<Double> stream4 = Stream.generate(() -> Math.random());
        //stream4.limit(5).forEach(System.out::println);
    }

    //中间操作
    @Test
    public void midOps(){
        
    }
}
