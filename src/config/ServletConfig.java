package config;

public class ServletConfig {

    private String name;//模拟servlet-name
    private String urlMapping;//模拟url-pattern
    private String myclass;//模拟servlet-class

    public ServletConfig(String name, String urlMapping, String myclass) {
        this.name = name;
        this.urlMapping = urlMapping;
        this.myclass = myclass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlMapping() {
        return urlMapping;
    }

    public void setUrlMapping(String urlMapping) {
        this.urlMapping = urlMapping;
    }

    public String getMyclass() {
        return myclass;
    }

    public void setMyclass(String clas) {
        this.myclass = myclass;
    }
}
