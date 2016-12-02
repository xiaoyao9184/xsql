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
public class EntitySiteParam {

    protected String linkColumnName;
    protected Class linkEntityClass;

    protected boolean useLink;

    protected boolean useArgs;
    protected Object[] args;


    /**
     * EntitySiteParam with NOTUSE flag
     */
    public EntitySiteParam(){
    }


    /**
     * 实体对象在主实体中的字段名
     */
    public String getLinkColumnName() {
        return linkColumnName;
    }

    public void setLinkColumnName(String linkColumnName) {
        this.linkColumnName = linkColumnName;
    }

    /**
     * 实体
     */
    public Class getLinkEntityClass() {
        return linkEntityClass;
    }

    public void setLinkEntityClass(Class linkEntityClass) {
        this.linkEntityClass = linkEntityClass;
    }

    /**
     * 是否使用
     */
    public boolean isUseLink() {
        return useLink;
    }

    public void setUseLink(boolean useLink) {
        this.useLink = useLink;
    }

    /**
     *
     * @return
     */
    public boolean isUseArgs() {
        return useArgs && this.args != null;
    }

    public void setUseArgs(boolean useArgs) {
        this.useArgs = useArgs;
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
     * 是否使用了Class(Class非空)
     * @return 是/否
     */
    public boolean isUseClass(){
        return !CheckUtil.isNull(this.linkEntityClass);
    }

    /**
     * 是否使用了Column(Column非空)
     * @return 是/否
     */
    public boolean isUseColumn(){
        return !CheckUtil.isNull(this.linkColumnName);
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



    public EntitySiteParam withLinkColumnName(String linkColumnName) {
        this.linkColumnName = linkColumnName;
        return this;
    }

    public EntitySiteParam withLinkClass(Class linkEntityClass){
        this.linkEntityClass = linkEntityClass;
        return this;
    }

    public EntitySiteParam withUseLink(boolean useLink){
        this.useLink = useLink;
        return this;
    }

    public EntitySiteParam withUseArgs(boolean useArgs){
        this.useArgs = useArgs;
        return this;
    }


    /**
     * 添加参数
     * @param args 参数数组
     */
    public EntitySiteParam withArgs(Object... args) {
        if(this.args == null){
            this.args = args;
            this.useArgs = true;
            return this;
        }
        if(args == null || args.length == 0){
            this.args = null;
            this.useArgs = false;
            return this;
        }
        Object[] result = Arrays.copyOf(this.args, this.args.length + args.length);
        System.arraycopy(args, 0, result, this.args.length, args.length);
        this.args = result;
        this.useArgs = true;
        return this;
    }

}
