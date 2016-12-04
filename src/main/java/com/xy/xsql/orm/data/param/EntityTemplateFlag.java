package com.xy.xsql.orm.data.param;

import java.util.Arrays;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/12/1.
 */
public class EntityTemplateFlag {
    protected Class clazz;
    protected List<EntityTemplateFlag> subs;
    protected Object[] args;
    protected boolean linkFlag;
    protected boolean paramFlag;
    protected boolean orderFlag;


    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public List<EntityTemplateFlag> getSubs() {
        return subs;
    }

    public void setSubs(List<EntityTemplateFlag> subs) {
        this.subs = subs;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public boolean isLinkFlag() {
        return linkFlag;
    }

    public void setLinkFlag(boolean linkFlag) {
        this.linkFlag = linkFlag;
    }

    public boolean isParamFlag() {
        return paramFlag;
    }

    public void setParamFlag(boolean paramFlag) {
        this.paramFlag = paramFlag;
    }

    public boolean isOrderFlag() {
        return orderFlag;
    }

    public void setOrderFlag(boolean orderFlag) {
        this.orderFlag = orderFlag;
    }


    /**
     * Set Entity Class
     * @param clazz Entity Class
     * @return This
     */
    public EntityTemplateFlag withClass(Class clazz) {
        this.clazz = clazz;
        return this;
    }

    /**
     * Set Sub EntityTemplateTreeArg
     * @param sub Sub EntityTemplateTreeArg
     * @return This
     */
    public EntityTemplateFlag withSub(EntityTemplateFlag... sub) {
        this.subs = Arrays.asList(sub);
        return this;
    }

    /**
     * Set Args
     * @param args Args
     * @return This
     */
    public EntityTemplateFlag withArgs(Object... args) {
        this.args = args;
        return this;
    }

    /**
     * Set Link Flag
     * @param linkFlag Link Flag
     * @return This
     */
    public EntityTemplateFlag withLinkFlag(boolean linkFlag) {
        this.linkFlag = linkFlag;
        return this;
    }

    /**
     * Set Param Flag
     * @param paramFlag Param Flag
     * @return This
     */
    public EntityTemplateFlag withParamFlag(boolean paramFlag) {
        this.paramFlag = paramFlag;
        return this;
    }

    /**
     * Set Order Flag
     * @param orderFlag Order Flag
     * @return This
     */
    public EntityTemplateFlag withOrderFlag(boolean orderFlag) {
        this.orderFlag = orderFlag;
        return this;
    }


    /**
     * Get Sub Tree
     * @param index Index
     * @param clazz Target Entity Class
     * @return EntityTemplateTreeArg
     */
    public EntityTemplateFlag getSubTree(int index, Class clazz) {
        if(this.subs != null){
            EntityTemplateFlag entityTemplateTreeArg = this.subs.get(index);
            if(entityTemplateTreeArg.getClazz().equals(clazz) ||
                    entityTemplateTreeArg.getClazz() == null){
                return entityTemplateTreeArg;
            }
        }
        return new EntityTemplateFlag()
                .withClass(clazz);
    }

}
