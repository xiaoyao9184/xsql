package com.xy.xsql.tsql.model.datatype;


import com.xy.xsql.tsql.model.element.ColumnName;

/**
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class ColumnDefinition extends ColumnName {

    public ColumnDefinition(String name){
        super(name);
    }

    public ColumnDefinition(String name,String type){
        super(name);
        this.dataType = new DataType(type);
    }


    private DataType dataType;

    public ColumnDefinition(String name, String type, Integer len) {
        super(name);
        this.dataType = new DataType(type);
        this.dataType.setLen(len);
    }


    public static class DataType {

        private String type_schema_name;
        private String type_name;
        private Integer len;



        //[ ( precision [ , scale ] | max |
        //[ { CONTENT | DOCUMENT } ] xml_schema_collection ) ]

        public DataType(String type) {
            this.type_name = type;
        }

        public Integer getLen() {
            return len;
        }

        public void setLen(Integer len) {
            this.len = len;
        }
    }
}
