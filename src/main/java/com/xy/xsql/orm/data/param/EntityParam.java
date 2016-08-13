package com.xy.xsql.orm.data.param;

import com.xy.xsql.orm.util.CheckUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 实体参数对
 * Created by xiaoyao9184 on 2016/4/11.
 */
@SuppressWarnings("WeakerAccess")
public class EntityParam {

    protected Class clazz;
    protected String bindColumnName;
    protected boolean use;
    protected boolean onlyUseForEverParam;
    protected Object[] args;

    /**
     * EntityParam with NOTUSE flag
     * @param entityClass entity class
     */
    public EntityParam(Class entityClass){
        this.use = false;
        this.onlyUseForEverParam = false;
        this.clazz = entityClass;
    }

    /**
     * EntityParam with USE flag and entity args
     * @param entityArgs entity args
     */
    public EntityParam(Object... entityArgs){
        this.use = true;
        this.onlyUseForEverParam = false;
        this.args = entityArgs;
    }

    /**
     * TODO 冲突
     * @see EntityParam#EntityParam(Object...)
     * EntityParam with USE flag, entity class and entity args
     * @param entityClass entity class
     * @param entityArgs entity args
     */
    @Deprecated
    public EntityParam(Class entityClass, Object... entityArgs){
        this.use = true;
        this.onlyUseForEverParam = false;
        this.clazz = entityClass;
        this.args = entityArgs;
    }

    /**
     * TODO 冲突
     * @see EntityParam#EntityParam(Object...)
     * EntityParam with USE flag, entity column name and entity args
     * @param bindColumnName entity column name
     * @param entityArgs entity args
     */
    @Deprecated
    public EntityParam(String bindColumnName, Object... entityArgs){
        this.use = true;
        this.onlyUseForEverParam = false;
        this.bindColumnName = bindColumnName;
        this.args = entityArgs;
    }


    /**
     * 实体
     */
    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    /**
     * 实体对象在主实体中的字段名
     */
    public String getBindColumnName() {
        return bindColumnName;
    }

    public void setBindColumnName(String bindColumnName) {
        this.bindColumnName = bindColumnName;
    }

    /**
     * 是否使用
     */
    public boolean isUse() {
        return use;
    }

    public void setUse(boolean use) {
        this.use = use;
    }

    /**
     * 参数
     */
    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    /**
     * 只使用永久参数
     */
    public boolean isOnlyUseForEverParam() {
        return onlyUseForEverParam;
    }

    public void setOnlyUseForEverParam(boolean onlyUseForEverParam) {
        this.onlyUseForEverParam = onlyUseForEverParam;
    }

    /**
     * 是否使用了Class(Class非空)
     * @return 是/否
     */
    public boolean isUseClass(){
        return !CheckUtil.isNull(this.clazz);
    }

    /**
     * 是否使用了Column(Column非空)
     * @return 是/否
     */
    public boolean isUseColumn(){
        return !CheckUtil.isNull(this.bindColumnName);
    }

    /**
     * 是否使用了参数(参数非空)
     * @return 是/否
     */
    public boolean isUseArgs(){
        if(!CheckUtil.isNull(this.args)){
            for (Object obj: this.args) {
                if(obj != null){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 是否是主实体
     * @param clazz Class
     * @return 是/否
     */
    public boolean isCoreBean(Class clazz){
        return (
            this.clazz == clazz ||
                (this.clazz == null &&
                        CheckUtil.isNullOrEmpty(this.bindColumnName)
                )
        );
    }

    /**
     * 参数转为非空集合
     * @return Object List
     */
    public List<Object> toNoNullArgsList(){
        List<Object> list = new ArrayList<>();
        if(!CheckUtil.isNull(this.args)){
            for (Object obj: this.args) {
                if(obj != null){
                    list.add(obj);
                }
            }
        }
        return list;
    }

    /**
     * 参数转为集合
     * @return  Object List
     */
    public List<Object> toArgsList() {
        List<Object> list = new ArrayList<>();
        if(!CheckUtil.isNull(this.args)){
            for (Object obj: this.args) {
                list.add(obj);
            }
        }
        return list;
    }

    /**
     * 添加参数
     * @param args 参数数组
     */
    public void addArgs(Object... args) {
        if(this.args == null){
            this.args = args;
            return;
        }
        Object[] result = Arrays.copyOf(this.args, this.args.length + args.length);
        System.arraycopy(args, 0, result, this.args.length, args.length);
        this.args = result;
    }

}
