package com.xy.xsql.tsql.model.datatype;

import java.util.List;

/**
 * https://msdn.microsoft.com/en-us/library/ms175010.aspx
 *
 * table_type_definition ::=
 TABLE ( { <column_definition> | <table_constraint> } [ ,...n ] )

 *
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class TableTypeDefinition {

    //( { <column_definition> | <table_constraint> } [ ,...n ] )
    private List<Item> list;

    public List<Item> getList() {
        return list;
    }

    public void setList(List<Item> list) {
        this.list = list;
    }


    /**
     * <column_definition> | <table_constraint>
     */
    public interface Item {

    }

}
