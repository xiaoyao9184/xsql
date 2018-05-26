package com.xy.xsql.tsql.builder.chain;

import com.xy.xsql.core.holder.ParentHolder;

import java.lang.reflect.Constructor;

/**
 * Created by xiaoyao9184 on 2017/3/17.
 */
public class MockParentBuilder<ChildBuilder extends ParentHolder,Child> {


    private MockParent _this;
    private Class<? extends ChildBuilder> childBuilderClass;
    private Class<Child> childClass;

    @SuppressWarnings("unchecked")
    public MockParentBuilder(Class<?> childBuilderClass, Class<Child> childClass){
        this.childBuilderClass = (Class<? extends ChildBuilder>) childBuilderClass;
        this.childClass = childClass;
        this._this = new MockParent();
    }

    /**
     * mock ParentBuilder
     * @return
     */
    @SuppressWarnings("unchecked")
    public ChildBuilder $child(){
        try {
            Child child = childClass.newInstance();
            _this.setBuidData(child);
            Constructor constructor = childBuilderClass.getConstructor(childClass);
            ParentHolder parentHolder = (ChildBuilder) constructor.newInstance(child);
            parentHolder.in(this._this);
            return (ChildBuilder) parentHolder;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
