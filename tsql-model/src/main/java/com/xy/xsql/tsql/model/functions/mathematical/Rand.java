package com.xy.xsql.tsql.model.functions.mathematical;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Rand
        implements MathematicalFunction, Function.InternalFunction {

    private Expression seed;

    public Expression getSeed() {
        return seed;
    }

    public void setSeed(Expression seed) {
        this.seed = seed;
    }
}
