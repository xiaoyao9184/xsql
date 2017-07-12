package com.xy.xsql.block.core;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/7/12.
 */
public class IndexClassMapper<INDEX,TARGET> {

    private Map<INDEX,TARGET> indexObjectMap;
    private Map<Type,TARGET> typeObjectMap;

    public IndexClassMapper() {
        indexObjectMap = new HashMap<>();
        typeObjectMap = new HashMap<>();
    }


    public void map(INDEX index, TARGET target){
        indexObjectMap.put(index, target);
        typeObjectMap.put(target.getClass(), target);
    }

    public Map<INDEX,TARGET> map(){
        return indexObjectMap;
    }

    public TARGET get(INDEX index) {
        return indexObjectMap.get(index);
    }

    public TARGET get(Class clazz) {
        return typeObjectMap.get(clazz);
    }

    public boolean check(INDEX index) {
        return indexObjectMap.containsKey(index);
    }

    public boolean check(Class clazz) {
        return typeObjectMap.containsKey(clazz);
    }

}
