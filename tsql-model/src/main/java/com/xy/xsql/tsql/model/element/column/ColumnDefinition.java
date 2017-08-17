package com.xy.xsql.tsql.model.element.column;


import com.xy.xsql.tsql.model.datatype.DataType;
import com.xy.xsql.tsql.model.datatype.StringConstant;
import com.xy.xsql.tsql.model.datatype.TableTypeDefinition;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.collation.Collate;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.statement.ddl.create.CreateTable;

import java.util.List;

/**
 * https://docs.microsoft.com/en-us/sql/t-sql/data-types/table-transact-sql
 * https://docs.microsoft.com/en-us/sql/t-sql/statements/create-table-transact-sql
 *
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class ColumnDefinition
        extends ColumnName
        implements TableTypeDefinition.Item, CreateTable.DiskBasedColumn, CreateTable.MemoryBasedColumn {

    //<data_type>
    //scalar_data_type
    private DataType dataType;

    //[ FILESTREAM ]
    private boolean useFilestream;
    //[ COLLATE collation_name ]
    //[ COLLATE <collation_definition> ]
    private Collate collationName;
    //[ SPARSE ]
    private boolean useSparse;
    //[ MASKED WITH ( FUNCTION = ' mask_function ') ]
    private StringConstant maskFunction;
    //[ CONSTRAINT constraint_name ] DEFAULT constant_expression ]
    private String constraintName;
    //DEFAULT constant_expression
    private Expression constantExpression;
    //[ IDENTITY [ ( seed,increment ) ]
    private Integer seed;
    private Integer increment;

    //[ NOT FOR REPLICATION ]
    private boolean useNotForReplication;
    //[ GENERATED ALWAYS AS ROW { START | END } [ HIDDEN ] ]
    private boolean useGeneratedAlwaysStart;
    private boolean useGeneratedAlwaysEnd;
    private boolean useHidden;

    //[ NULL | NOT NULL ]
    private boolean useNull;
    private boolean useNotNull;
    //[ ROWGUIDCOL ]
    private boolean useRowGuidCol;
//    [ ENCRYPTED WITH
//            ( COLUMN_ENCRYPTION_KEY = key_name ,
//              ENCRYPTION_TYPE = { DETERMINISTIC | RANDOMIZED } ,
//    ALGORITHM = 'AEAD_AES_256_CBC_HMAC_SHA_256'
//            ) ]
    private String columnEncryotedKey;
    private boolean randomizedEncryotedType;
    private StringConstant algorithm;
    //[ column_constraint ] [ ...n ]
    private List<ColumnConstraint> columnConstraint;
    //[ <column_index> ]
    private ColumnIndex columnIndex;

    public ColumnDefinition(){
        super("");
    }

    public ColumnDefinition(String name){
        super(name);
    }

    @Deprecated
    public ColumnDefinition(String name,String type){
        super(name);
    }

    @Deprecated
    public ColumnDefinition(String name, String type, Integer len) {
        super(name);
    }


    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public boolean isUseFilestream() {
        return useFilestream;
    }

    public void setUseFilestream(boolean useFilestream) {
        this.useFilestream = useFilestream;
    }

    public Collate getCollationName() {
        return collationName;
    }

    public void setCollationName(Collate collationName) {
        this.collationName = collationName;
    }

    public boolean isUseSparse() {
        return useSparse;
    }

    public void setUseSparse(boolean useSparse) {
        this.useSparse = useSparse;
    }

    public StringConstant getMaskFunction() {
        return maskFunction;
    }

    public void setMaskFunction(StringConstant maskFunction) {
        this.maskFunction = maskFunction;
    }

    public String getConstraintName() {
        return constraintName;
    }

    public void setConstraintName(String constraintName) {
        this.constraintName = constraintName;
    }

    public Expression getConstantExpression() {
        return constantExpression;
    }

    public void setConstantExpression(Expression constantExpression) {
        this.constantExpression = constantExpression;
    }

    public Integer getSeed() {
        return seed;
    }

    public void setSeed(Integer seed) {
        this.seed = seed;
    }

    public Integer getIncrement() {
        return increment;
    }

    public void setIncrement(Integer increment) {
        this.increment = increment;
    }

    public boolean isUseNotForReplication() {
        return useNotForReplication;
    }

    public void setUseNotForReplication(boolean useNotForReplication) {
        this.useNotForReplication = useNotForReplication;
    }

    public boolean isUseGeneratedAlwaysStart() {
        return useGeneratedAlwaysStart;
    }

    public void setUseGeneratedAlwaysStart(boolean useGeneratedAlwaysStart) {
        this.useGeneratedAlwaysStart = useGeneratedAlwaysStart;
    }

    public boolean isUseGeneratedAlwaysEnd() {
        return useGeneratedAlwaysEnd;
    }

    public void setUseGeneratedAlwaysEnd(boolean useGeneratedAlwaysEnd) {
        this.useGeneratedAlwaysEnd = useGeneratedAlwaysEnd;
    }

    public boolean isUseHidden() {
        return useHidden;
    }

    public void setUseHidden(boolean useHidden) {
        this.useHidden = useHidden;
    }

    public boolean isUseNull() {
        return useNull;
    }

    public void setUseNull(boolean useNull) {
        this.useNull = useNull;
    }

    public boolean isUseNotNull() {
        return useNotNull;
    }

    public void setUseNotNull(boolean useNotNull) {
        this.useNotNull = useNotNull;
    }

    public boolean isUseRowGuidCol() {
        return useRowGuidCol;
    }

    public void setUseRowGuidCol(boolean useRowGuidCol) {
        this.useRowGuidCol = useRowGuidCol;
    }

    public String getColumnEncryotedKey() {
        return columnEncryotedKey;
    }

    public void setColumnEncryotedKey(String columnEncryotedKey) {
        this.columnEncryotedKey = columnEncryotedKey;
    }

    public boolean isRandomizedEncryotedType() {
        return randomizedEncryotedType;
    }

    public void setRandomizedEncryotedType(boolean randomizedEncryotedType) {
        this.randomizedEncryotedType = randomizedEncryotedType;
    }

    public StringConstant getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(StringConstant algorithm) {
        this.algorithm = algorithm;
    }

    public List<ColumnConstraint> getColumnConstraint() {
        return columnConstraint;
    }

    public void setColumnConstraint(List<ColumnConstraint> columnConstraint) {
        this.columnConstraint = columnConstraint;
    }

    public ColumnIndex getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(ColumnIndex columnIndex) {
        this.columnIndex = columnIndex;
    }
}

