package generationcode;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.SneakyThrows;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class GenerationCode {
    @SneakyThrows
    @Test
    public void test01() {
        //创建freeMake的配置类
        Configuration cfg = new Configuration();
        //指定模板加载器，将模板放入缓存
        //文件路径加载器
        FileTemplateLoader ftl = new FileTemplateLoader(new File("src/main/resources/templates"));
        cfg.setTemplateLoader(ftl);
        //获取模板
        Template template = cfg.getTemplate("template01.ftl");
        //构造数据模型
        Map<String, Object> map = new HashMap<>();
        map.put("name","mosheng");
        //输出到文件
        //template.process(map,new FileWriter("src/main/resources/templates/testfreemaker.txt"));
        //输出到控制台
        template.process(map,new PrintWriter(System.out));

    }
}
