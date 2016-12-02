package com.xy.xsql.orm.data.param;

import java.util.Arrays;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/12/1.
 */
public class EntityTemplateDataArgTree {

    private Class clazz;
    private Object[] args;
    private List<EntityTemplateDataArgTree> sub;

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public List<EntityTemplateDataArgTree> getSub() {
        return sub;
    }

    public void setSub(List<EntityTemplateDataArgTree> sub) {
        this.sub = sub;
    }


    /**
     *
     * @param index
     * @param clazz
     * @return
     */
    public EntityTemplateDataArgTree getSubTree(int index, Class clazz) {
        if(this.sub != null){
            EntityTemplateDataArgTree entityTemplateDataArgTree = this.sub.get(index);
            if(entityTemplateDataArgTree.getClazz().equals(clazz) ||
                    entityTemplateDataArgTree.getClazz() == null){
                return entityTemplateDataArgTree;
            }
        }
        return new EntityTemplateDataArgTree()
                .withClass(clazz);
    }

    public EntityTemplateDataArgTree withClass(Class clazz) {
        this.clazz = clazz;
        return this;
    }

    public EntityTemplateDataArgTree withArgs(Object... args) {
        this.args = args;
        return this;
    }

    public EntityTemplateDataArgTree withSub(EntityTemplateDataArgTree... sub) {
        this.sub = Arrays.asList(sub);
        return this;
    }

}
