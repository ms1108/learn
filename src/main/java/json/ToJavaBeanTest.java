package json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.Test;

import java.util.Map;

public class ToJavaBeanTest {
    @Test
    public void test() {
        JSONObject jsonObject = JSON.parseObject("{\"age\":\"\",\"sex\":{\"testInClass\":2}}");
        //空字符串会转成null
        User user2 = JSON.toJavaObject(jsonObject, User.class);
        System.out.println(user2);

        User user = new User().myUser();
        Map<String,Object> jsonObject1 = JSON.parseObject(JSON.toJSONString(user));
        System.out.println(jsonObject1);
        jsonObject1.putAll(jsonObject);
        System.out.println(jsonObject1);
        User user1 = JSONObject.parseObject(JSON.toJSONString(jsonObject1), User.class);
        System.out.println(user1);
    }
}


