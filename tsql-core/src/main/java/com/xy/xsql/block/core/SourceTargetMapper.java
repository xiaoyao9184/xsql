package com.xy.xsql.block.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/7/12.
 */
public class SourceTargetMapper<LEFT, RIGHT> {

    private Map<LEFT, RIGHT> left2right;
    private Map<RIGHT, LEFT> right2left;

    public SourceTargetMapper() {
        left2right = new HashMap<>();
        right2left = new HashMap<>();
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

}
