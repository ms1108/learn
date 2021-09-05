package yamltest;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class YamlTest {
    @Test
    public void test(){
        // 第一种  spring boot解析yml
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        //yaml.setResources(new FileSystemResource("config.yml"));//File引入
        yaml.setResources(new ClassPathResource("/application-dev.yml"));//classPath引入
        System.out.println(yaml.getObject().get("spring.data.mongodb.uri"));



        //第二种  YamlPropertySourceLoader
        Resource resource = new ClassPathResource("/application-dev.yml");
        //List<Map<String, Object>> loaded = new OriginTrackedYamlLoader(resource).load();
        try {
            List<PropertySource<?>> propertySourceList = new YamlPropertySourceLoader().load("/application.yml",resource);
            System.out.println(propertySourceList.get(0).getProperty("spring.data.mongodb.uri"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
