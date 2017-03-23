package com.xy.xsql.core.builder;

/**
 * CodeBuilder
 * use code build Target
 * Created by xiaoyao9184 on 2016/12/28.
 * @param <Target> build target
 */
public abstract class CodeBuilder<Target>
        implements BaseBuilder<Void, Target> {

    protected Target target;

    public CodeBuilder(Target target){
        this.target = target;
    }

    /**
     * @see #build(Object)
     * @param aVoid
     * @return
     */
    @Override
    public Target build(Void aVoid) {
        return target;
    }

    /**
     * @see #build(Void)
     * @return
     */
    public Target build() {
        return target;
    }

    /**
     * @see #build(Void)
     * @return
     */
    public Target done(){
        return target;
    }

}
