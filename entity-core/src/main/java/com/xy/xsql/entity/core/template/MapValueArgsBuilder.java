package com.xy.xsql.entity.core.template;

import com.xy.xsql.core.builder.BaseBuilder;
import com.xy.xsql.entity.model.template.EntityColumn;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2016/12/2.
 */
public class MapValueArgsBuilder implements BaseBuilder<Map,List<Object>> {
    private List<EntityColumn> columns;


    /**
     * Set EntityColumn List
     * @param entityColumnList EntityColumn
     * @return This
     */
    public MapValueArgsBuilder withColumn(List<EntityColumn> entityColumnList) {
        this.columns = entityColumnList;
        return this;
    }


    @Override
    public List<Object> build(Map map) {
        return getArgsListByNameMap(map);
    }

    /**
     * Get Value List form map
     * @param map Map
     * @return Value List
     */
    public List<Object> getArgsListByNameMap(Map map) {
        List<Object> params = new ArrayList<>();
        for (EntityColumn column: this.columns) {
            if(map.containsKey(column.getName())){
                params.add(map.get(column.getName()));
            }else if(map.containsKey(column.getAliasName())){
                params.add(map.get(column.getAliasName()));
            }else{
                params.add(null);
            }
        }
        return params;
    }
}
