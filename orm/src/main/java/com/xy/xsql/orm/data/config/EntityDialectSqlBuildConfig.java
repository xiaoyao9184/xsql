package com.xy.xsql.orm.data.config;

import com.xy.xsql.orm.core.ConfigInOut;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class EntityDialectSqlBuildConfig<Father> implements
        ConfigInOut<EntityDialectSqlBuildConfig,Father>,
        Cloneable {

    private boolean useSentenceBuilder;
    private boolean onlySelectUseStatus;
    private Map<Class,Class> dialectMap;

    //
    private Father father;

    public boolean isUseSentenceBuilder() {
        return useSentenceBuilder;
    }

    public boolean isOnlySelectUseStatus() {
        return onlySelectUseStatus;
    }

    public Map<Class,Class> getDialectMap() {
        return dialectMap;
    }


    /**
     * Set UseSentenceBuilder
     * @param useSentenceBuilder UseSentenceBuilder
     * @return This
     */
    @Deprecated
    public EntityDialectSqlBuildConfig<Father> withUseSentenceBuilder(boolean useSentenceBuilder) {
        this.useSentenceBuilder = useSentenceBuilder;
        return this;
    }

    /**
     * Set OnlySelectUseStatus
     * @param onlySelectUseStatus OnlySelectUseStatus
     * @return This
     */
    @Deprecated
    public EntityDialectSqlBuildConfig<Father> withOnlySelectUseStatus(boolean onlySelectUseStatus) {
        this.onlySelectUseStatus = onlySelectUseStatus;
        return this;
    }

    /**
     * Set Agreement-Dialect Map
     * @param dialectMap Agreement-Dialect Map
     * @return This
     */
    private EntityDialectSqlBuildConfig<Father> withDialectMap(Map<Class, Class> dialectMap) {
        this.dialectMap = dialectMap;
        return this;
    }

    /**
     * Set Agreement Class and Dialect Class
     * @param agreementClass Agreement Class
     * @param dialectClass Dialect Class
     * @return This
     */
    public EntityDialectSqlBuildConfig<Father> withDialectMap(Class agreementClass, Class dialectClass){
        if(this.dialectMap == null){
            this.dialectMap = new HashMap<>();
        }
        if(this.dialectMap.containsKey(agreementClass)){
            this.dialectMap.remove(agreementClass);
        }
        this.dialectMap.put(agreementClass,dialectClass);
        return this;
    }

    /**
     * Set AllInOne Dialect Class
     * @param dialectClass Dialect Class
     * @return This
     */
    public EntityDialectSqlBuildConfig<Father> withAllInThisDialectClass(Class dialectClass){
        this.dialectMap = new HashMap<>();
        this.dialectMap.put(null,dialectClass);
        return this;
    }

    /**
     * Check AllInOne
     * @return True/False
     */
    public boolean isAllInOne(){
        return this.dialectMap.size() == 1 &&
                this.dialectMap.containsKey(null);
    }

    /**
     * Get AllInOne Dialect Class
     * @return Dialect Class
     */
    public Class getAllInOne(){
        return this.dialectMap.get(null);
    }



    @Override
    public EntityDialectSqlBuildConfig<Father> in(Father father) {
        this.father = father;
        return this;
    }

    @Override
    public Father out() {
        return this.father;
    }


    @SuppressWarnings("CloneDoesntCallSuperClone")
    @Override
    public EntityDialectSqlBuildConfig<Father> clone(){
        return new EntityDialectSqlBuildConfig<Father>()
                .withOnlySelectUseStatus(this.onlySelectUseStatus)
                .withUseSentenceBuilder(this.useSentenceBuilder)
                .withDialectMap(this.dialectMap);
    }


}
