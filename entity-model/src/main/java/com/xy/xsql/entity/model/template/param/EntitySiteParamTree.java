package com.xy.xsql.entity.model.template.param;

import java.util.ArrayList;
import java.util.List;

/**
 * 实体参数树
 * Created by xiaoyao9184 on 2016/4/11.
 */
@SuppressWarnings("WeakerAccess")
@Deprecated
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
     * EntitySiteParam append USE flag and entities args
     * @param entityArgs entities args
     */
    public EntitySiteParamTree(Object... entityArgs){
        super();
        this.withArgs(entityArgs);
        this.child = new ArrayList<>();
    }

    /**
     * EntitySiteParam append USE flag, entities class and entities args
     * @param entityClass entities class
     * @param entityArgs entities args
     */
    public EntitySiteParamTree(Class entityClass, Object... entityArgs){
        super();
        this.withLinkClass(entityClass);
        this.withArgs(entityArgs);
        this.child = new ArrayList<>();
    }

    /**
     * EntitySiteParam append USE flag, entities column name and entities args
     * @param bindColumnName entities column name
     * @param entityArgs entities args
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
