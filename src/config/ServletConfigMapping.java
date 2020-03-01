package config;

import java.util.ArrayList;
import java.util.List;

public class ServletConfigMapping {

    private static List<ServletConfig> configs = new ArrayList<>();//模拟初始化配置文件，使用ArrayList存放

    static {
        configs.add(new ServletConfig("servlet","/test","servlet.Servlet"));
    }

    public static List<ServletConfig> getConfigs(){
        return configs;
    }//使外部类获取到配置文件


}
