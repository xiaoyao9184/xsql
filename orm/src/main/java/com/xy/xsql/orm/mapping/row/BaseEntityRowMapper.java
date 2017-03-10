package com.xy.xsql.orm.mapping.row;

import com.xy.xsql.orm.util.CheckUtil;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基本 映射
 * Created by xiaoyao9184 on 2016/4/22.
 */
public class BaseEntityRowMapper<T> implements RowMapper<T>, FieldRowNameHandler {

    private Class<T> clazz;
    private String rowNumberName;
    private String pageRowNumberName;
    private List<FieldRowNameHandler> fieldRowNameHandlerList;
    private boolean useDefaultRowNameHandler;
    private boolean ignoreCase;
    private boolean buildFlag;

    private Map<String,Field> cacheRowNameFieldMap;

    public BaseEntityRowMapper(Class<T> clazz) {
        this.fieldRowNameHandlerList = new ArrayList<>();

        this.clazz = clazz;
        // Default
        this.ignoreCase = true;
        this.useDefaultRowNameHandler = true;
    }

    /**
     * Set Row Number Name
     * @param rowNumberName Row Number Name
     * @return This
     */
    public BaseEntityRowMapper<T> withRowNumberName(String rowNumberName) {
        this.rowNumberName = rowNumberName;
        return this;
    }

    /**
     * Set Page Row Number Name
     * @param pageRowNumberName Page Row Number Name
     * @return This
     */
    public BaseEntityRowMapper<T> withPageRowNumberName(String pageRowNumberName) {
        this.pageRowNumberName = pageRowNumberName;
        return this;
    }

    /**
     * Set Row Name Handler
     * @param fieldRowNameHandler Row Name Handler
     * @return This
     */
    public BaseEntityRowMapper<T> withRowNameHandler(FieldRowNameHandler fieldRowNameHandler){
        if(this.fieldRowNameHandlerList == null){
            this.fieldRowNameHandlerList = new ArrayList<>();
        }
        this.fieldRowNameHandlerList.add(fieldRowNameHandler);
        return this;
    }

    /**
     * Set use default Row Name Handler
     * @param useDefaultRowNameHandler True:add default/false
     * @return This
     */
    public BaseEntityRowMapper<T> withDefaultRowNameHandler(boolean useDefaultRowNameHandler) {
        this.useDefaultRowNameHandler = useDefaultRowNameHandler;
        return this;
    }

    /**
     * init RowName-Field Map cache
     */
    private void initCacheRowNameMap(){
        if(this.useDefaultRowNameHandler &&
                this.fieldRowNameHandlerList.size() == 0){
            this.fieldRowNameHandlerList.add(this);
        }
        this.cacheRowNameFieldMap = new HashMap<>();
        Field[] fields = clazz.getDeclaredFields();
        for (FieldRowNameHandler fieldRowNameHandler :
                this.fieldRowNameHandlerList) {
            for (Field field : fields) {
                String rowName = fieldRowNameHandler.handlerField(field);
                if(!CheckUtil.isNullOrEmpty(rowName)){
                    this.cacheRowNameFieldMap.put(rowName,field);
                    if(ignoreCase){
                        this.cacheRowNameFieldMap.put(rowName.toUpperCase(),field);
                        this.cacheRowNameFieldMap.put(rowName.toLowerCase(),field);
                    }
                }
            }
        }
    }

    /**
     * init cache
     * @return This
     */
    public BaseEntityRowMapper<T> buildCache(){
        this.initCacheRowNameMap();
        this.buildFlag = true;
        return this;
    }


    /**
     * RowMapper
     * @param rs ResultSet
     * @param rowNum Row Nummber
     * @return T
     */
    @Override
    public T mapRow(ResultSet rs, int rowNum) throws SQLException {
        if(!this.buildFlag){
            this.buildCache();
        }

        if(Map.class.isAssignableFrom(this.clazz)){
            //noinspection unchecked
            return (T) BaseMapper.build(rs, rowNum, this.pageRowNumberName);
        }
        try {
            return BaseRomNumberListMapRowMapper.buildObjectByTFieldNameMap(rs, this.cacheRowNameFieldMap, this.clazz,null);
        } catch (Exception ex){
            throw new SQLException(ex);
        }
    }

    /**
     * Base Field Name handler
     * @param field Field
     * @return Field Name
     */
    @Override
    public String handlerField(Field field) {
        return field.getName();
    }
}
