package com.xy.xsql.orm.core.entity.template;

import com.xy.xsql.orm.core.BaseBuilder;
import com.xy.xsql.orm.data.entity.EntityColumn;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/12/9.
 */
public class EntityColumnFilter implements BaseBuilder<List<EntityColumn>,List<EntityColumn>> {

    protected static final Log log = LogFactory.getLog(EntityColumnFilter.class);
    private String[] names;
    private String[] types;


    /**
     * Set name to filter
     * @param names Name Array
     * @return This
     */
    public EntityColumnFilter withName(String... names) {
        this.names = names;
        return this;
    }

    /**
     * Set type to filter
     * @param types Type Array
     * @return This
     */
    public EntityColumnFilter withType(String... types) {
        this.types = types;
        return this;
    }

    @Override
    public List<EntityColumn> build(List<EntityColumn> columnList) {
        List<EntityColumn> result = new ArrayList<>();
        for (EntityColumn column: columnList) {
            if(!passByName(column)){
                result.add(column);
            }else if(!passByType(column)){
                result.add(column);
            }
        }

        return result;
    }


    /**
     * Pass By Name
     * @param column EntityColumn
     * @return True/False
     */
    private boolean passByName(EntityColumn column) {
        if(this.names != null){
            for (String name: this.names) {
                if(column.getName().equalsIgnoreCase(name) ||
                        column.getName().contains(name)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Pass By Type
     * @param column EntityColumn
     * @return True/False
     */
    private boolean passByType(EntityColumn column) {
        if(this.types != null){
            for (String type: this.types) {
                if(column.getType().equalsIgnoreCase(type) ||
                        column.getType().contains(type)){
                    return false;
                }
            }
        }
        return true;
    }
}
