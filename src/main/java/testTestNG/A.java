package testTestNG;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class A {
    @BeforeClass
    public void beforeClass() {
        System.out.println("A.beforeClass");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("A.beforeMethod");
    }

    @Test
    public void test(){
        System.out.println("A.test");
    }
}
