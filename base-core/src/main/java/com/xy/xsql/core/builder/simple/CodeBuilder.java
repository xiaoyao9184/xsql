package com.xy.xsql.core.builder.simple;

import com.xy.xsql.core.builder.SimpleBuilder;
import com.xy.xsql.core.holder.ParentHolder;

/**
 * CodeBuilder
 * Use code build
 * Created by xiaoyao9184 on 2016/12/28.
 * @param <Target>
 */
public abstract class CodeBuilder<Target>
        implements
        SimpleBuilder<Target> {

    protected Target target;

    public CodeBuilder(Target target){
        this.target = target;
    }

    @Override
    public Target build() {
        return target;
    }

}
