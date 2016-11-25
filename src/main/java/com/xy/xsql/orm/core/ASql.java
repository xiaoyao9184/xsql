package com.xy.xsql.orm.core;

import com.xy.xsql.orm.annotation.*;
import com.xy.xsql.orm.data.config.ASqlConfig;
import com.xy.xsql.orm.data.entity.*;
import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.orm.data.sql.element.info.Name;
import com.xy.xsql.orm.util.CheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 标注SQL
 * Created by xiaoyao9184 on 2016/1/13.
 */
@SuppressWarnings({"StringBufferReplaceableByString", "JavaDoc", "unused", "StatementWithEmptyBody", "WeakerAccess"})
public class ASql {

/*
 * Fields
 */

    protected Logger log = null;

    /**
     * 实体
     */
    protected Class clazz;

    /**
     * 配置
     */
    protected ASqlConfig config;

    /**
     * 表名
     */
    protected SqlTable tableName = null;

    /**
     * 特殊字段：主键
     */
    protected List<SqlKey> tableKey = null;

    /**
     * 特殊字段：状态
     */
    protected SqlStatus tableStatus = null;

    /**
     * 字段集合
     */
    protected List<Field> tableField = null;

    /**
     * 字段集合(标注为TableColumn)
     */
    protected List<SqlColumn> tableColumn = null;

    /**
     * 相关实体集合(标注为TableEntity)
     */
    protected List<SqlEntity> tableEntity = null;

    /**
     * 查询条件集合(标注为TableParam)
     */
    protected List<SqlParam> tableParam = null;

    /**
     * 查询排序集合(标注为TableOrder)
     */
    protected List<SqlOrder> tableOrder = null;

/*
 *
 */

    /**
     *
     * @param clazz 实体类
     */
    public ASql(Class<?> clazz){
        this.log = LoggerFactory.getLogger(this.getClass());
        this.init(new ASqlConfig(clazz));
    }

    /**
     *
     * @param config 配置
     */
    public ASql(ASqlConfig config){
        this.log = LoggerFactory.getLogger(this.getClass());
        this.init(config);
    }

/*
 * Getter&Setter
 */

    public Name getSqlTableName() {
        return this.tableName;
    }

    public void setConfig(ASqlConfig config){
        this.config = config;
    }

/*
 * Init
 */

    /**
     * 初始化
     * @param config 配置
     */
    private void init(ASqlConfig config) {
        this.config = config;
        this.clazz = config.getClazz();

        this.tableName = this.initTable();
        this.tableColumn = this.initColumn();
        this.tableStatus = this.initColumnStatus();
        this.tableKey = this.initColumnKey();
        //TODO 迁移到 A2Sql
        this.tableEntity = this.initBind();
        this.tableParam = this.initColumnParam();
        this.tableOrder = this.initColumnOrder();
    }

    /**
     * 初始化表：名称
     * @see ETable
     * @return 表名 对象（不允许空表名）
     */
    private SqlTable initTable() {
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof ETable) {
                ETable eTable = (ETable) annotation;
                return new SqlTable(eTable, config.getTablePrefix());
            }
        }
        throw new UnsupportedOperationException(clazz.getName() + " 未使用@" + ETable.class.getSimpleName() + "标注或未设置标注属性：name！");
    }

    /**
     * 初始化字段：数据库字段
     * @see EColumn
     */
    private List<SqlColumn> initColumn(){
        this.tableField = new ArrayList<>();
        List<SqlColumn> list = new ArrayList<>();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof EColumn) {
                    EColumn eColumn = (EColumn)annotation;
                    this.tableField.add(field);
                    list.add(new SqlColumn(eColumn,field,this.tableName));
                }
            }
        }
        if(CheckUtil.isNullOrEmpty(list)){
            throw new UnsupportedOperationException(clazz.getName() + " 未有任何字段使用@" + EColumn.class.getSimpleName() + "标注！");
        }
        return list;
    }

    /**
     * 初始化字段：假删除
     * @see EStatus
     * @return 注解
     */
    private SqlStatus initColumnStatus() {
        SqlStatus sqlStatus = null;
        for (Field field: this.tableField) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation: annotations) {
                if(annotation instanceof EStatus) {
                    EStatus eStatus = (EStatus)annotation;
                    EColumn eColumn = field.getAnnotation(EColumn.class);
                    sqlStatus = new SqlStatus(eStatus, eColumn, field, this.tableName);
                    break;
                }
            }
        }
        if(config.isOnlySelectUseStatus() &&
                CheckUtil.isNull(sqlStatus)){
            throw new UnsupportedOperationException(clazz.getName() + " 未有任何字段使用@" + EStatus.class.getSimpleName() + "标注！这与设置冲突！");
        }
        return sqlStatus;
    }

    /**
     * 初始化字段：主键
     * @see EKey
     * @return
     */
    private List<SqlKey> initColumnKey() {
        List<SqlKey> list = new ArrayList<>();
        for (Field field: this.tableField) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation: annotations) {
                if (annotation instanceof EKey) {
                    EKey eKey = (EKey)annotation;
                    EColumn eColumn = field.getAnnotation(EColumn.class);
                    if(eColumn == null){
                        throw new UnsupportedOperationException("不支持在非@" + EColumn.class.getSimpleName() + "标注的字段上使用@" + EKey.class.getSimpleName() + "标注！");
                    }
                    list.add(new SqlKey(eKey, eColumn, field, this.tableName));
                }
            }
        }
        if(list.size() == 0){
            throw new UnsupportedOperationException("实体没有主键");
        }
        return list;
    }

    /**
     * 初始化字段：相关实体
     * @see EBind
     * @return 相关实体 集合
     */
    private List<SqlEntity> initBind() {
        List<SqlEntity> list = new ArrayList<>();
        list.add(new SqlEntity(this.clazz));
        for (Field field: this.tableField) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation: annotations) {
                if(annotation instanceof EBind) {
                    EBind eBind = (EBind)annotation;
                    EColumn eColumn = field.getAnnotation(EColumn.class);
                    if(eColumn == null){
                        throw new UnsupportedOperationException("不支持在非@" + EColumn.class.getSimpleName() + "标注的字段上使用@" + EBind.class.getSimpleName() + "标注！");
                    }
                    SqlEntity sqlEntity = new SqlEntity(eBind, eColumn, field, this.tableName);
                    list.add(sqlEntity);
                }
            }
        }
        return list;
    }

    /**
     * 初始化字段：参数
     * @see EParam
     * @return 查询参数 集合
     */
    private List<SqlParam> initColumnParam(){
        List<SqlParam> list = new ArrayList<>();
        for (Field field: this.tableField) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation: annotations) {
                if(annotation instanceof EParam) {
                    EParam eParam = (EParam)annotation;
                    EColumn eColumn = field.getAnnotation(EColumn.class);
                    if(eColumn == null){
                        throw new UnsupportedOperationException("不支持在非@" + EColumn.class.getSimpleName() + "标注的字段上使用@" + EParam.class.getSimpleName() + "标注！");
                    }
                    SqlParam sqlParam = new SqlParam(eParam, eColumn, field, this.tableName);
                    list.add(sqlParam);
                }
            }
        }
        return list;
    }

    /**
     * 初始化字段：排序
     * @see EOrder
     * @return 查询排序 集合
     */
    private List<SqlOrder> initColumnOrder(){
        List<SqlOrder> list = new ArrayList<>();
        for (Field field: this.tableField) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation: annotations) {
                if(annotation instanceof EOrder) {
                    EOrder eOrder = (EOrder)annotation;
                    EColumn eColumn = field.getAnnotation(EColumn.class);
                    if(eColumn == null){
                        throw new UnsupportedOperationException("不支持在非@" + EColumn.class.getSimpleName() + "标注的字段上使用@" + EOrder.class.getSimpleName() + "标注！");
                    }
                    SqlOrder sqlOrder = new SqlOrder(eOrder, eColumn, field, this.tableName);
                    list.add(sqlOrder);
                }
            }
        }
        return list;
    }

/*
 * Create SQL
 */

    /**
     * one table insert
     * @return SQL with (+)? , + is 1 or more , is columns count
     */
    public String createInsertSql(){
        List<String> list = new ArrayList<>();
        for (SqlColumn sqlColumn : this.tableColumn) {
            list.add(sqlColumn.getName());
        }

        XSql sql = new XSql()
                .insert(this.tableName.getName())
                .values(list);

        return sql.toSql();
    }

    /**
     * one table all columns update
     * @return SQL with (+)? , + is 1 or more , is columns+keys count
     */
    public String createUpdateSql(){
        List<String> list = new ArrayList<>();
        for (SqlColumn sqlColumn : this.tableColumn) {
            list.add(sqlColumn.getName());
        }

        XSql sql = new XSql()
                .update(this.tableName.getName())
                .set(list)
                .where(this.tableKey.get(0).getName(),"=");
        for(int i = 1; i < this.tableKey.size(); i++){
            sql.and(this.tableKey.get(i).getName(),"=");
        }

        return sql.toSql();
    }

    /**
     * one table select
     * @return SQL with (+)? , + is 1 or more , is keys count
     */
    public String createSelectSql(){
        List<Column> list = new ArrayList<>();
        list.addAll(this.tableColumn);

        XSql sql = new XSql()
                .select(list)
                .from(this.tableName.getName())
                .where(this.tableKey.get(0).getName(),"=");
        for(int i = 1; i < this.tableKey.size(); i++){
            sql.and(this.tableKey.get(i).getName(),"=");
        }

        return sql.toSql();
    }

    /**
     * one table delete
     * @return SQL with (+)? , + is 1 or more , is keys count
     */
    public String createDeleteSql(){
        XSql sql = new XSql()
                .delete()
                .from(this.tableName.getName())
                .where(this.tableKey.get(0).getName(),"=");
        for(int i = 1; i < this.tableKey.size(); i++){
            sql.and(this.tableKey.get(i).getName(),"=");
        }

        return sql.toSql();
    }

    /**
     * one table status column update
     * @return SQL with (+)? , + is 1 or more , is keys count
     */
    public String createUpdateStatusSql(){
        if(this.tableStatus == null){
            throw new UnsupportedOperationException("没有任何字段被标注为@" + EStatus.class.getSimpleName());
        }
        XSql sql = new XSql()
                .update(this.tableName.getName())
                .set(this.tableStatus.geteColumn().name())
                .where(this.tableKey.get(0).getName(),"=");
        for(int i = 1; i < this.tableKey.size(); i++){
            sql.and(this.tableKey.get(i).getName(),"=");
        }

        return sql.toSql();
    }

    /**
     * one table more row insert
     * @param count row count
     * @return SQL with (count)*(+)? , + is 1 or more , is columns count
     */
    public String createInsertAllSql(int count){
        List<String> list = new ArrayList<>();
        for (SqlColumn sqlColumn : this.tableColumn) {
            list.add(sqlColumn.getName());
        }

        XSql sql = new XSql()
                .insert(this.tableName.getName())
                .values(list,count);

        return sql.toSql();
    }

    /**
     * one table more row status column update
     * @param count row count
     * @return SQL with (count)?
     */
    public String createUpdateAllSql(int count){
        if(this.tableKey.size() > 1){
            throw new UnsupportedOperationException("联合主键不提供批量更新操作！");
        }
        if(count < 1){
            count = 1;
        }

        List<String> list = new ArrayList<>();
        for (SqlColumn sqlColumn : this.tableColumn) {
            list.add(sqlColumn.getName());
        }
        List<String> caseWhen = new ArrayList<>();
        for (SqlColumn sqlColumn : this.tableColumn) {
            XSql sql = new XSql()
                    .caseStart(this.tableKey.get(0).getName())
                    .whenThen(count)
                    .caseEnd();
            caseWhen.add(sql.toSql());
        }

        XSql sql = new XSql()
                .update(this.tableName.getName())
                .set(list,caseWhen)
                .where()
                .in(this.tableKey.get(0).getName(),count);

        return sql.toSql();
    }


    /**
     * one table more row select
     * @return SQL with (?)? , ? is 1 or 0 , if status column used and config it on , it will 1
     */
    public String createSelectAllSql(){
        List<Column> list = new ArrayList<>();
        list.addAll(this.tableColumn);

        XSql sql = new XSql()
                .select(list)
                .from(this.tableName.getName());
        if(config.isOnlySelectUseStatus()){
            sql.where(this.tableStatus.getName(),"=");
        }

        return sql.toSql();
    }

    /**
     * one table more row count select
     * @return SQL with (?)? , ? is 1 or 0 , if status column used and config it on , it will 1
     */
    public String createSelectAllCountSql(){
        XSql sql = new XSql()
                .select()
                .funCount()
                .from(this.tableName.getName());
        if(config.isOnlySelectUseStatus()){
            sql.where(this.tableStatus.getName(),"=");
        }

        return sql.toSql();
    }

    /**
     * one table more row count delete
     * @param count row count
     * @return SQL with (count)?
     */
    public String createDeleteAllSql(int count){
        if(this.tableKey.size() > 1){
            throw new UnsupportedOperationException("联合主键不提供批量删除操作！");
        }
        if(count < 1){
            count = 1;
        }

        XSql sql = new XSql()
                .delete()
                .from(this.tableName.getName())
                .where()
                .in(this.tableKey.get(0).getName(),count);

        return sql.toSql();
    }

    /**
     * one table more row status column update
     * @param count row count
     * @return SQL with (count)?
     */
    public String createUpdateAllStatusSql(int count){
        if(this.tableStatus == null){
            throw new UnsupportedOperationException("没有任何字段被标注为@" + EStatus.class.getSimpleName());
        }
        if(this.tableKey.size() > 1){
            throw new UnsupportedOperationException("联合主键不提供批量更新状态操作！");
        }
        if(count < 1){
            count = 1;
        }

        XSql sql = new XSql()
                .update(this.tableName.getName())
                .set(this.tableStatus.geteColumn().name())
                .where()
                .in(this.tableKey.get(0).getName(),count);

        return sql.toSql();
    }

}
