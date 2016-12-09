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


    /**
     * Set name to filter
     * @param names Name Array
     * @return This
     */
    public EntityColumnFilter withName(String... names) {
        this.names = names;
        return this;
    }

    @Override
    public List<EntityColumn> build(List<EntityColumn> columnList) {
        List<EntityColumn> result = new ArrayList<>();
        for (EntityColumn column: columnList) {
            if(!passByName(column)){
                result.add(column);
            }
        }

        return result;
    }

    /**
     * Process pass name
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
}
