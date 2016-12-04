package com.xy.xsql.orm.data.param;

import java.util.Arrays;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/12/1.
 */
public class EntityTemplateTreeArg {
    protected Class clazz;
    protected List<EntityTemplateTreeArg> subs;
    protected Object[] args;


    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public List<EntityTemplateTreeArg> getSubs() {
        return subs;
    }

    public void setSubs(List<EntityTemplateTreeArg> subs) {
        this.subs = subs;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }


    /**
     * Set Entity Class
     * @param clazz Entity Class
     * @return This
     */
    public EntityTemplateTreeArg withClass(Class clazz) {
        this.clazz = clazz;
        return this;
    }

    /**
     * Set Sub EntityTemplateTreeArg
     * @param sub Sub EntityTemplateTreeArg
     * @return This
     */
    public EntityTemplateTreeArg withSub(EntityTemplateTreeArg... sub) {
        this.subs = Arrays.asList(sub);
        return this;
    }

    /**
     * Set Args
     * @param args Args
     * @return This
     */
    public EntityTemplateTreeArg withArgs(Object... args) {
        this.args = args;
        return this;
    }


    /**
     * Get Sub Tree
     * @param index Index
     * @param clazz Target Entity Class
     * @return EntityTemplateTreeArg
     */
    public EntityTemplateTreeArg getSubTree(int index, Class clazz) {
        if(this.subs != null){
            EntityTemplateTreeArg entityTemplateTreeArg = this.subs.get(index);
            if(entityTemplateTreeArg.getClazz().equals(clazz) ||
                    entityTemplateTreeArg.getClazz() == null){
                return entityTemplateTreeArg;
            }
        }
        return new EntityTemplateTreeArg()
                .withClass(clazz);
    }

}
