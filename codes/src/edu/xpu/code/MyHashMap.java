package edu.xpu.code;

import java.util.*;

class MyHashMap<K, V> extends HashMap<K,V> {
    private static final long serialVersionUID = -5894887960346129860L;
    // 重写HashMapSon类的toString()方法
    @Override
    public String toString() {
        Set<Map.Entry<K, V>> keyset = this.entrySet();
        Iterator<Map.Entry<K, V>> i = keyset.iterator();
        if (!i.hasNext())
            return "";
        StringBuffer buffer = new StringBuffer();
        buffer.append("{" +"\n");
        for (;;) {
            Map.Entry<K, V> me = i.next();
            K key = me.getKey();
            V value = me.getValue();
            buffer.append(" ");
            buffer.append(key.toString() + ":");
            buffer.append(value.toString() + "\n");
            if (!i.hasNext())
                return buffer.append("}").toString();
        }
    }
}

