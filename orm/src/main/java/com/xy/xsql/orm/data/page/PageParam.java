package com.xy.xsql.orm.data.page;

import com.xy.xsql.entity.annotation.ESql;
import com.xy.xsql.orm.data.param.EntitySiteParam;
import com.xy.xsql.orm.data.row.BaseRowNumberList;

import java.util.ArrayList;
import java.util.List;

/**
 * Page with arg
 * Created by xiaoyao9184 on 2016/1/11.
 */
public class PageParam<SrcType,TarType> extends Page<SrcType> {

    /**
     * 标识自动填充参数
     */
    private boolean autoFill = true;
    /**
     * 实体参数
     */
    private List<EntitySiteParam> beanArgs = new ArrayList<>();
    /**
     * VO类型
     */
    private Class<TarType> voClass = null;
    /**
     * 已经映射转换完毕
     */
    private boolean maped = false;



    public boolean isAutoFill() {
        return autoFill;
    }

    public void setAutoFill(boolean autoFill) {
        this.autoFill = autoFill;
    }

    public List<EntitySiteParam> getBeanArgs() {
        return beanArgs;
    }

    public void setBeanArgs(List<EntitySiteParam> beanArgs) {
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
        beanArgs.add(new EntitySiteParam()
                .withArgs(args));
    }

    /**
     * 添加参数
     * @param beanClass 相关实体
     * @param args 参数
     */
    public void addBeanArg(Class beanClass, Object...args){
        beanArgs.add(new EntitySiteParam()
                .withLinkClass(beanClass)
                .withArgs(args));
    }

    /**
     * 添加参数
     * @param classColumn 实体相关的(主实体)数据库字段
     * @param args 参数
     */
    public void addBeanArg(String classColumn, Object...args){
        beanArgs.add(new EntitySiteParam()
                .withLinkColumnName(classColumn)
                .withArgs(args));
    }

    /**
     * 复位参数
     */
    public void restArg(){
        this.beanArgs = new ArrayList<EntitySiteParam>();
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
        return this.voClass != null && this.voClass.getAnnotation(ESql.class) != null;
    }
}
