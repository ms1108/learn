package testTestNG;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Son extends Father {
    @BeforeClass
    public void beforeClass() {
        System.out.println("son.beforeClass");
    }

    @Test
    public void test2() {
        System.out.println("son.test");
    }
}
