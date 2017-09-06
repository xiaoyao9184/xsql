package com.xy.xsql.core.mapper;

import java.util.*;

/**
 * Created by xiaoyao9184 on 2017/7/12.
 */
public class SourceTargetMapper<LEFT, RIGHT> {

    private Map<LEFT, RIGHT> left2right;
    private Map<RIGHT, LEFT> right2left;

    public SourceTargetMapper() {
        left2right = new LinkedHashMap<>();
        right2left = new LinkedHashMap<>();
    }


    public void map(LEFT left, RIGHT right){
        left2right.put(left, right);
        right2left.put(right, left);
    }

    public RIGHT getByLeft(LEFT left) {
        return left2right.get(left);
    }

    public LEFT getByRight(RIGHT right) {
        return right2left.get(right);
    }

    public boolean checkLeft(LEFT left) {
        return left2right.containsKey(left);
    }

    public boolean checkRight(RIGHT right) {
        return right2left.containsKey(right);
    }

    public Set<LEFT> getLeft() {
        return left2right.keySet();
    }

    public Set<RIGHT> getRight() {
        return right2left.keySet();
    }
}
