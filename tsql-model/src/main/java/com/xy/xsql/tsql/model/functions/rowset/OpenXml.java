package com.xy.xsql.tsql.model.functions.rowset;

import com.xy.xsql.tsql.model.datatypes.DataType;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class OpenXml
        implements RowsetFunction, Function.InternalFunction {

    private Expression idoc;
    private String rowPattern;
    private Flags flags;
    private SchemaDeclaration schemaDeclaration;
    private TableName tableName;

    public Expression getIdoc() {
        return idoc;
    }

    public void setIdoc(Expression idoc) {
        this.idoc = idoc;
    }

    public String getRowPattern() {
        return rowPattern;
    }

    public void setRowPattern(String rowPattern) {
        this.rowPattern = rowPattern;
    }

    public Flags getFlags() {
        return flags;
    }

    public void setFlags(Flags flags) {
        this.flags = flags;
    }

    public SchemaDeclaration getSchemaDeclaration() {
        return schemaDeclaration;
    }

    public void setSchemaDeclaration(SchemaDeclaration schemaDeclaration) {
        this.schemaDeclaration = schemaDeclaration;
    }

    public TableName getTableName() {
        return tableName;
    }

    public void setTableName(TableName tableName) {
        this.tableName = tableName;
    }

    public enum Flags {
        _0,
        _1,
        _2,
        _8;

        private String string;

        Flags(){
            this.string = this.name().replace("_","");
        }

        @Override
        public String toString(){
            return string;
        }

    }

    //ColName ColType [ColPattern | MetaProperty] [,ColName ColType [ColPattern | MetaProperty]...]
    public static class SchemaDeclaration {
        private List<SchemaDeclarationItem> items;

        public List<SchemaDeclarationItem> getItems() {
            return items;
        }

        public void setItems(List<SchemaDeclarationItem> items) {
            this.items = items;
        }
    }

    public static class SchemaDeclarationItem {
        private ColumnName colName;
        private DataType colType;
        private StringConstant colPattern;
        private String metaProperty;

        public ColumnName getColName() {
            return colName;
        }

        public void setColName(ColumnName colName) {
            this.colName = colName;
        }

        public DataType getColType() {
            return colType;
        }

        public void setColType(DataType colType) {
            this.colType = colType;
        }

        public StringConstant getColPattern() {
            return colPattern;
        }

        public void setColPattern(StringConstant colPattern) {
            this.colPattern = colPattern;
        }

        public String getMetaProperty() {
            return metaProperty;
        }

        public void setMetaProperty(String metaProperty) {
            this.metaProperty = metaProperty;
        }
    }
}
