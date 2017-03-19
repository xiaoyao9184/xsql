package com.xy.xsql.core.builder;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public abstract class CodeBuilder<TarType>
        implements
        BaseBuilder<Void,TarType> {

    protected TarType tar;

    public CodeBuilder(TarType tar){
        this.tar = tar;
    }

    /**
     * @see #build(Object)
     * @param aVoid
     * @return
     */
    @Override
    public TarType build(Void aVoid) {
        return tar;
    }

    /**
     * @see #build(Void)
     * @return
     */
    public TarType build() {
        return tar;
    }

    /**
     * @see #build(Void)
     * @return
     */
    public TarType done(){
        return tar;
    }

}
