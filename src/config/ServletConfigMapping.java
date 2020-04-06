package config;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟整个XML文件，可以放置多个servlet和url的mapping
 */
public class ServletConfigMapping {

    private static List<ServletConfig> configs = new ArrayList<>();//模拟初始化配置文件，使用ArrayList存放

    static {
        configs.add(new ServletConfig("servlet","/test","servlet.Servlet"));
    }

    public static List<ServletConfig> getConfigs(){
        return configs;
    }//使外部类获取到配置文件


}
