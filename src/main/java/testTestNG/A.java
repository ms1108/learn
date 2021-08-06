package testTestNG;

import com.alibaba.fastjson.JSONObject;
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

    @Test
    public void test1(){
        String s = "{\n" +
                "   \"lotto\":{\n" +
                "      \"lottoId\":5,\n" +
                "      \"winning-numbers\":[2,45,34,23,7,5,3],\n" +
                "      \"winners\":[\n" +
                "         {\n" +
                "            \"winnerId\":23,\n" +
                "            \"numbers\":[2,45,34,23,3,5]\n" +
                "         },\n" +
                "         {\n" +
                "            \"winnerId\":54,\n" +
                "            \"numbers\":[52,3,12,11,18,22]\n" +
                "         }\n" +
                "      ]\n" +
                "   }\n" +
                "}";
        TestGosnFormat test = JSONObject.parseObject(s, TestGosnFormat.class);
        test.lotto.winners.add(new TestGosnFormat.Lotto.Winners());
        System.out.println(test.toString());
        System.out.println(JSONObject.toJSON(test));
    }
}
