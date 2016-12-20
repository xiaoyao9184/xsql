package com.xy.xsql.orm.util;


import com.xy.xsql.orm.core.BaseBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/11/12.
 */
public class ListBuilder<ListType> implements BaseBuilder<Void,List<ListType>> {

    private List<ListType> list;

    public ListBuilder(){
        this.list = new ArrayList<>();
    }

    public ListBuilder<ListType> withItem(ListType listType){
        if(listType == null){
            return this;
        }
        list.add(listType);
        return this;
    }

    @Override
    public List<ListType> build(Void aVoid) {
        return list;
    }
}
