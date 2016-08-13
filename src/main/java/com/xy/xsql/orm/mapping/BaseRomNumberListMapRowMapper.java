package com.xy.xsql.orm.mapping;

import com.xy.xsql.orm.model.BaseRowNumberList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * BaseRowNumberList 映射
 * Created by xiaoyao9184 on 2016/4/13.
 */
public class BaseRomNumberListMapRowMapper
        extends BaseMapper
        implements RowMapper<BaseRowNumberList> {

    /**
     * VO bean
     */
    private Class<BaseRowNumberList> clazz;

    public BaseRomNumberListMapRowMapper(Class<BaseRowNumberList> clazz) {
        this.clazz = clazz;
    }

    @Override
    public BaseRowNumberList mapRow(ResultSet resultSet, int i) throws SQLException {
        try {
            if(BaseRowNumberList.class.isAssignableFrom(this.clazz)){
                return build(resultSet,this.clazz);
            }
            return (BaseRowNumberList) buildObjectByClass(resultSet,this.clazz);
        } catch (Exception ex){
            throw new SQLException(ex);
        }
    }

}
