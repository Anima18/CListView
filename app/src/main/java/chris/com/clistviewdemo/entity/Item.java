package chris.com.clistviewdemo.entity;

/**
 * Created by jianjianhong on 2017/12/20.
 */

public class Item {
    private String name;
    private Class aClass;

    public Item(String name, Class aClass) {
        this.name = name;
        this.aClass = aClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }
}
