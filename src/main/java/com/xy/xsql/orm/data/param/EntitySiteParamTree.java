package com.xy.xsql.orm.data.param;

import java.util.ArrayList;
import java.util.List;

/**
 * 实体参数树
 * Created by xiaoyao9184 on 2016/4/11.
 */
@SuppressWarnings("WeakerAccess")
public class EntitySiteParamTree extends EntitySiteParam {

    protected List<EntitySiteParamTree> child;

    /**
     * EntitySiteParam append NOTUSE flag
     * @param entityClass
     */
    public EntitySiteParamTree(Class entityClass){
        super();
        this.withLinkClass(entityClass);
        this.child = new ArrayList<>();
    }

    /**
     * EntitySiteParam append USE flag and entity args
     * @param entityArgs entity args
     */
    public EntitySiteParamTree(Object... entityArgs){
        super();
        this.withArgs(entityArgs);
        this.child = new ArrayList<>();
    }

    /**
     * EntitySiteParam append USE flag, entity class and entity args
     * @param entityClass entity class
     * @param entityArgs entity args
     */
    public EntitySiteParamTree(Class entityClass, Object... entityArgs){
        super();
        this.withLinkClass(entityClass);
        this.withArgs(entityArgs);
        this.child = new ArrayList<>();
    }

    /**
     * EntitySiteParam append USE flag, entity column name and entity args
     * @param bindColumnName entity column name
     * @param entityArgs entity args
     */
    public EntitySiteParamTree(String bindColumnName, Object... entityArgs){
        super();
        this.withLinkColumnName(bindColumnName);
        this.withArgs(entityArgs);
        this.child = new ArrayList<>();
    }

    //TODO
    public EntitySiteParamTree turnOn(Class entityClass, Object... entityArgs) {
        EntitySiteParamTree entityParamTree = new EntitySiteParamTree(entityClass,entityArgs);
        this.child.add(entityParamTree);
        return entityParamTree;
    }

    /**
     * 添加子级
     * @param entityClass
     * @param entityArgs
     * @return
     */
    public EntitySiteParamTree add(Class entityClass, Object... entityArgs) {
        EntitySiteParamTree entityParamTree = new EntitySiteParamTree(entityClass,entityArgs);
        this.child.add(entityParamTree);
        return entityParamTree;
    }

    /**
     * 添加子级
     * @param bindColumnName
     * @param entityArgs
     * @return
     */
    public EntitySiteParamTree add(String bindColumnName, Object... entityArgs) {
        EntitySiteParamTree entityParamTree = new EntitySiteParamTree(bindColumnName,entityArgs);
        this.child.add(entityParamTree);
        return entityParamTree;
    }

    public List<EntitySiteParamTree> getChild() {
        return child;
    }

    public void setChild(List<EntitySiteParamTree> child) {
        this.child = child;
    }
}
