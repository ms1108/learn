package copyProperties;

import org.springframework.beans.BeanUtils;
import org.testng.annotations.Test;

public class CopyPropertiesTest {
    @Test
    public void test(){
        User user = new User();
        user.setAge(100);
        User user2 = new User();
        BeanUtils.copyProperties(user2,user);
        System.out.println(user);
    }
}
