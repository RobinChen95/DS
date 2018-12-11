package pku;

import java.util.HashMap;
import java.util.Set;


/**
 *
 * KeyValue接口
 * Key为String类型，Value为int long double String
 *
 * 接口的不同：
 * 1.除非实现接口的类是抽象类，否则该类要定义接口中的所有方法。
 * 2.接口无法被实例化，但是可以被实现。
 *
 */

public interface KeyValue {

    public Object getObj(String key);

    public HashMap<String, Object> getMap();

    public KeyValue put(String key, int value);

    public KeyValue put(String key, double value);

    public KeyValue put(String key, long value);

    public KeyValue put(String key, String value);

    public int getInt(String key);

    public double getDouble(String key);

    public long getLong(String key);

    public String getString(String key);

    public Set<String> keySet();

    public boolean containsKey(String key);

}

