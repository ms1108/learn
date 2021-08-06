package json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import static io.restassured.path.json.JsonPath.from;

public class MapDuplicate {
    @Test
    public void testDuplicate(){
        Map<String,Object> map1 = JSON.parseObject("{\"sex\":{\"testInClass\":1},\"name\":\"mosn\",\"age\":\"1\"}");
        Map<String,Object> map2 = JSON.parseObject("{\"sex\":{\"testInClass\":1,\"inner\":\"1\"},\"name\":\"mosn\",\"age\":\"\"}");
        System.out.println(map1.keySet());
        System.out.println(from("{\"sex\":{\"testInClass\":1},\"name\":\"mosn\",\"age\":\"1\"}"));
        //Map<Number, String> map1 = new HashMap<Number, String>();
        //Map<Number, String> map2 = new HashMap<Number, String>();
        //
        //map1.put(1, "a");
        //map1.put(2, "b");
        //map1.put(3, "c");
        //map1.put(4, "d");
        //map1.put(5, "e");
        //
        //map2.put(1, "g");
        //map2.put(2, "f");
        //map2.put(3, "c");
        //map2.put(4, "t");
        //map2.put(5, "e");

        boolean isEquals = false;

        for(Object o : map1.keySet()){
            isEquals = map2.containsKey(o);
            if(isEquals){
                isEquals = map1.get(o).equals(map2.get(o));
            }
            if(isEquals){
                map2.remove(o);
            }
        }
        System.out.println(map2);
        for(Object o : map2.keySet()){
            System.out.println(o.toString() + ":" + map2.get(o));
        }
    }

}
