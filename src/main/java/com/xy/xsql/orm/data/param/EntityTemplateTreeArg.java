package com.xy.xsql.orm.data.param;

import java.util.Arrays;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/12/1.
 */
public class EntityTemplateTreeArg {

    private Class clazz;
    private Object[] args;
    private List<EntityTemplateTreeArg> sub;

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

    public List<EntityTemplateTreeArg> getSub() {
        return sub;
    }

    public void setSub(List<EntityTemplateTreeArg> sub) {
        this.sub = sub;
    }


    /**
     *
     * @param index
     * @param clazz
     * @return
     */
    public EntityTemplateTreeArg getSubTree(int index, Class clazz) {
        if(this.sub != null){
            EntityTemplateTreeArg entityTemplateTreeArg = this.sub.get(index);
            if(entityTemplateTreeArg.getClazz().equals(clazz) ||
                    entityTemplateTreeArg.getClazz() == null){
                return entityTemplateTreeArg;
            }
        }
        return new EntityTemplateTreeArg()
                .withClass(clazz);
    }

    public EntityTemplateTreeArg withClass(Class clazz) {
        this.clazz = clazz;
        return this;
    }

    public EntityTemplateTreeArg withArgs(Object... args) {
        this.args = args;
        return this;
    }

    public EntityTemplateTreeArg withSub(EntityTemplateTreeArg... sub) {
        this.sub = Arrays.asList(sub);
        return this;
    }

}
