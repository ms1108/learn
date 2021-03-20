package stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {
    public String name;
    public Integer age;
    public Double salary;
    public Status status;

    public enum Status{
        FREE,
        BUSY,
        VOCATION;
    }
}
