package stream;

import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
 * 1、创建stream
 * 2、中间操作
 * 3、总端操作
 * */
public class StreamLearn {
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

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99, Employee.Status.FREE),
            new Employee("李四", 59, 5555.55, Employee.Status.BUSY),
            new Employee("王五", 26, 3333.33, Employee.Status.VOCATION),
            new Employee("赵六", 36, 6666.66, Employee.Status.FREE),
            new Employee("赵六", 36, 6666.66, Employee.Status.FREE),
            new Employee("田七", 12, 8888.88, Employee.Status.BUSY)
    );

    //中间操作
    //筛选与切片
    // filter，返回条件成立的
    @Test
    public void filterTest() {
        //中间操作
        Stream<Employee> employeeStream = employees.stream().filter(e -> e.getAge() > 35);
        //终止操作，要执行了终止操作，中间操作才真正的执行
        employeeStream.forEach(System.out::println);
    }

    //limit,只返回几个
    @Test
    public void limitTest() {
        employees.stream()
                .filter(e -> e.getSalary() > 1000)
                .limit(1).forEach(System.out::println);
    }

    //skip，跳过前几个
    @Test
    public void skipTest() {
        employees.stream().filter(e -> e.getSalary() > 1000)
                .filter(e -> e.getSalary() > 1000)
                .skip(1)
                .forEach(System.out::println);
    }

    //distinct,去重,根据hashcode和equals去重，实体类需要重写这两个返回
    @Test
    public void distinctTest() {
        employees.stream()
                .distinct().forEach(System.out::println);
    }

    //映射,map传入函数式接口，传入一个值返回一个值，map将每个元素应用到函数上。
    @Test
    public void mapTest() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc");
        list.stream()
                .map(s -> s.toUpperCase())
                .forEach(System.out::println);

        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
    }

    //flatMap
    @Test
    public void flatMapTest() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc");
        //返回的流中存了流
        Stream<Stream<Character>> streamStream = list.stream()
                .map(StreamLearn::filterCharacter);//{{a,a,a},{b,b,b}}流中流
        //streamStream.forEach(sm -> sm.forEach(System.out::println));

        //应用flatMap，把所有流合并成一个流
        list.stream()
                .flatMap(StreamLearn::filterCharacter)//{a,a,a,b,b,b}把多个流整合成一个
                .forEach(System.out::println);

    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }

    //排序
    //sorted(),自然排序(Comparable)
    //sorted(Comparator com) 定制排序
    @Test
    public void sortedTest() {
        List<String> list = Arrays.asList("ddd", "aaa", "bbb", "ccc");
        list.stream().sorted().forEach(System.out::println);

        employees.stream().sorted(
                (e1, e2) -> {
                    if (e1.getAge().equals(e2.getAge())) {
                        return e1.getName().compareTo(e2.getName());
                    } else {
                        //加个负号倒过来排
                        return -e1.getAge().compareTo(e2.getAge());
                    }
                }).forEach(System.out::println);
    }

    //终止操作
    //查找与匹配
    //allMatch,检查是不是匹配所有元素
    //anyMatch,至少匹配一个元素
    //noneMatch,检查是否没有匹配元素
    //finFirst,返回第一个元素
    //findAny,返回流中任意元素
    @Test
    public void matchTest() {
        System.out.println(employees.stream()
                .allMatch(e -> e.getStatus().equals(Employee.Status.BUSY)));

        System.out.println(employees.stream()
                .anyMatch(e -> e.getStatus().equals(Employee.Status.BUSY)));

        System.out.println(employees.stream()
                .noneMatch(e -> e.getStatus().equals(Employee.Status.BUSY)));

        Optional<Employee> first = employees.stream()
                .sorted((e1, e2) -> -Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();
        //first.orElse();如果为空则返回这个
        System.out.println(first.get());//取出对象

        System.out.println(employees.stream().parallel()
                .filter(e -> e.getStatus().equals(Employee.Status.FREE))
                .findAny().get());
    }

    //count
    //max
    @Test
    public void countTest() {
        System.out.println(employees.stream().count());

        System.out.println(employees.stream().max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())).get());

        System.out.println(employees.stream().map(Employee::getSalary).min(Double::compareTo).get());

    }

    //归约
    //reduce,0是初始值
    @Test
    public void reduceTest() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        System.out.println(list.stream().reduce(0, (x, y) -> x + y));

        //有可能返回为空时就会放在option中，上边有初始值所以不会为空
        System.out.println(employees.stream().map(Employee::getSalary).reduce(Double::sum).get());

    }

    //收集
    //collect
    @Test
    public void collectTest() {
        System.out.println(employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList()));

        System.out.println(employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet()));

        employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new))
                .forEach(System.out::println);

        System.out.println(employees.stream().collect(Collectors.counting()));

        System.out.println(employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary)));

        System.out.println(employees.stream()
                .collect(Collectors.summingDouble(Employee::getSalary)));

        System.out.println(employees.stream()
                .collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))).get());

        System.out.println(employees.stream()
                .map(Employee::getSalary).collect(Collectors.minBy(Double::compareTo)).get());
    }

    //分组
    //多级分组
    //分区，根据boolean分
    @Test
    public void group() {
        //返回map，key为组名，value为每个对象
        System.out.println(employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus)));

        Map<Employee.Status, Map<String, List<Employee>>> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(e -> {
                    if (e.getAge() <= 35) {
                        return "青年";
                    } else if (e.getAge() <= 50) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                })));

        System.out.println(map);

        Map<Boolean, List<Employee>> collect = employees.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 8000));
        System.out.println(collect);

        DoubleSummaryStatistics collect1 = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(collect1.getMax());
        System.out.println(collect1.getAverage());
        System.out.println(collect1.getCount());

        System.out.println(employees.stream().map(Employee::getName)
                .collect(Collectors.joining(",","=","==")));
    }


}
