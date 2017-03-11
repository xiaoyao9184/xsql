package com.xy.xsql.orm.core;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public abstract class CodeTreeBuilder<This,Parent,TarType>
        extends
        TreeBuilder<This,Parent,Void,TarType> {

    protected TarType tar;

    public CodeTreeBuilder(TarType tar){
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
     * @see #out()
     * @return
     */
    public Parent and() {
        return out();
    }
}
