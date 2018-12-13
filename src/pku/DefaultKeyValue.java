package pku;

import java.util.HashMap;
import java.util.Set;

/**
 * KeyValue的实现
 * 目前来看该实现基本是以HashMap为核心实现函数
 */

public class DefaultKeyValue implements KeyValue {

    // final与static是类成员变化修饰词。final表示被修饰成员一旦被赋值就无法被复写、覆盖与继承
    private final HashMap<String, Object> kvs = new HashMap<>();
    private final HashMap<String, String> kv = new HashMap<>();
    private String tmp;
    private int length = 0;

    public DefaultKeyValue(){}

    public Object getObj(String key){
        return kvs.get(key);
    }

    public HashMap<String, Object> getMap(){
        return kvs;
    }
    public HashMap<String, String> getMap2(){
        return kv;
    }

    public DefaultKeyValue put(String key, int value){
        tmp = String.valueOf(value);
        length = length+tmp.length();
        kv.put(key, tmp);
        return this;
    }

    public DefaultKeyValue put(String key, double value){
        tmp = String.valueOf(value);
        length = length+tmp.length();
        kv.put(key, tmp);
        return this;
    }

    public DefaultKeyValue put(String key, long value){
        tmp = String.valueOf(value);
        length = length+tmp.length();
        kv.put(key, tmp);
        return this;
    }

    public DefaultKeyValue put(String key, String value){
        tmp = String.valueOf(value);
        length = length+tmp.length();
        kv.put(key, tmp);
        return this;
    }

    public int getInt(String key){
        return Integer.parseInt(kv.get(key));
    }

    public double getDouble(String key){
        return Double.parseDouble(kv.get(key));
    }

    public long getLong(String key){
        return Long.parseLong(kv.get(key));
    }

    public String getString(String key){
        return kv.get(key);
    }

    public Set<String> keySet(){
        return kv.keySet();
    }

    public boolean containsKey(String key){
        return kv.containsKey(key);
    }

    public int getLength() {
        return length;
    }
    /*
    public DefaultKeyValue put(String key, int value){
        kvs.put(key, value);
        return this;
    }

    public DefaultKeyValue put(String key, double value){
        kvs.put(key, value);
        return this;
    }

    public DefaultKeyValue put(String key, long value){
        kvs.put(key, value);
        return this;
    }

    public DefaultKeyValue put(String key, String value){
        kvs.put(key, value);
        return this;
    }
    */

    /*

    public int getInt(String key){
        return (Integer) kvs.getOrDefault(key, 0);
    }

    public double getDouble(String key){
        return (Double) kvs.getOrDefault(key, 0.0d);
    }

    public long getLong(String key){
        return (Long) kvs.getOrDefault(key, 0L);
    }

    public String getString(String key){
        return (String) kvs.getOrDefault(key, null);
    }

    public Set<String> keySet(){
        return kvs.keySet();
    }

    public boolean containsKey(String key){
        return kvs.containsKey(key);
    }

    */

}
