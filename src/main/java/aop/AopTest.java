package aop;

import org.testng.annotations.Test;

public class AopTest {
    @MyAnnotation(methodName = "myannotation")
    public void aoptest() {
        System.out.println("aoptest");
    }

    @Test
    public void test() {
        aoptest();
    }
}
