package testTestNG;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class B {
    @BeforeClass
    public void beforeClass() {
        A a = new A();
        a.test();
        System.out.println("B.beforeClass");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("B.beforeMethod");
    }

    @BeforeMethod(groups = {"1"})
    public String beforeMethod1() {
        System.out.println("B.beforeMethod1");
        return "1";
    }

    @Test
    private void test() {
        System.out.println(beforeMethod1());
        A a = new A();
        a.test();
        System.out.println("B.test");
    }

    @Test(groups = {"1"})
    private void test1() {
        A a = new A();
        a.test();
        System.out.println("B.test1");
    }
}
