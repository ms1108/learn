package json;

import lombok.Data;

@Data
public class User {
    private String name = "mo";
    private Integer age;
    private test sex = new test();

    @Data
    public static class test {
        private String testInClass = "1";
    }
    public User myUser(){
        name = "mosn";
        age = 2;
        return this;
    }
}
