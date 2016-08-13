package com.xy.xsql.orm.data.page;

import com.xy.xsql.orm.annotation.EntitySql;
import com.xy.xsql.orm.data.param.EntityParam;
import com.xy.xsql.orm.model.BaseRowNumberList;

import java.util.ArrayList;
import java.util.List;

/**
 * 参数分页对象
 * Created by xiaoyao9184 on 2016/1/11.
 */
public class ParamPage extends Page {

    /**
     * 标识自动填充参数
     */
    private boolean autoFill = true;
    /**
     * 实体参数
     */
    private List<EntityParam> beanArgs = new ArrayList<>();
    /**
     * VO类型
     */
    private Class voClass = null;
    /**
     * 已经映射转换完毕
     */
    private boolean  maped = false;


    public ParamPage(int pageNo, int pageSize){
        super.setPageSize(pageSize);
        super.setPageNumber(pageNo);
    }

    public boolean isAutoFill() {
        return autoFill;
    }

    public void setAutoFill(boolean autoFill) {
        this.autoFill = autoFill;
    }

    public List<EntityParam> getBeanArgs() {
        return beanArgs;
    }

    public void setBeanArgs(List<EntityParam> beanArgs) {
        this.beanArgs = beanArgs;
    }

    public Class getVoClass() {
        return voClass;
    }

    public void setVoClass(Class voClass) {
        this.voClass = voClass;
    }

    public boolean isMaped() {
        return maped;
    }

    public void setMaped(boolean maped) {
        this.maped = maped;
    }

    /**
     * 添加主实体参数
     * @param args
     */
    public void addArg(Object...args){
        beanArgs.add(new EntityParam(args));
    }

    /**
     * 添加参数
     * @param beanClass 相关实体
     * @param args 参数
     */
    public void addBeanArg(Class beanClass, Object...args){
        beanArgs.add(new EntityParam(beanClass,args));
    }

    /**
     * 添加参数
     * @param classColumn 实体相关的(主实体)数据库字段
     * @param args 参数
     */
    public void addBeanArg(String classColumn, Object...args){
        beanArgs.add(new EntityParam(classColumn,args));
    }

    /**
     * 复位参数
     */
    public void restArg(){
        this.beanArgs = new ArrayList<EntityParam>();
    }

    /**
     * 是否是基本VO
     * @return
     */
    public boolean isListVo(){
        return this.voClass != null && BaseRowNumberList.class.isAssignableFrom(this.voClass);
    }

    /**
     * 是否是Sql Vo
     * @return
     */
    public boolean isListSqlVo() {
        return this.voClass != null && this.voClass.getAnnotation(EntitySql.class) != null;
    }
}
