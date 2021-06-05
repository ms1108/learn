package expendThisTest;

import lombok.SneakyThrows;

public class Person {
    public String aaa = "aaa";
    @SneakyThrows
    public Person(){
        System.out.println(this.getClass().getField("name"));
    }
}
