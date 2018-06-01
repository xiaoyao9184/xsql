package com.xy.xsql.tsql.model.functions.rowset;

import com.xy.xsql.tsql.model.datatypes.DataType;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class OpenJson
        implements RowsetFunction, Function.InternalFunction {

    private Expression jsonExpression;
    private String path;
    private List<Item> with;

    public Expression getJsonExpression() {
        return jsonExpression;
    }

    public void setJsonExpression(Expression jsonExpression) {
        this.jsonExpression = jsonExpression;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Item> getWith() {
        return with;
    }

    public void setWith(List<Item> with) {
        this.with = with;
    }

    public static class Item {
        private ColumnName colName;
        private DataType type;
        private String columnPath ;
        private boolean useAsJson;

        public ColumnName getColName() {
            return colName;
        }

        public void setColName(ColumnName colName) {
            this.colName = colName;
        }

        public DataType getType() {
            return type;
        }

        public void setType(DataType type) {
            this.type = type;
        }

        public String getColumnPath() {
            return columnPath;
        }

        public void setColumnPath(String columnPath) {
            this.columnPath = columnPath;
        }

        public boolean isUseAsJson() {
            return useAsJson;
        }

        public void setUseAsJson(boolean useAsJson) {
            this.useAsJson = useAsJson;
        }
    }
}
