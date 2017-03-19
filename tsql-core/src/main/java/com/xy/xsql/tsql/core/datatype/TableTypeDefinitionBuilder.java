package com.xy.xsql.tsql.core.datatype;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.datatype.ColumnDefinition;
import com.xy.xsql.tsql.model.datatype.TableTypeDefinition;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.expression.Expression;

import java.util.Arrays;

import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * Created by xiaoyao9184 on 2017/3/17.
 */
public class TableTypeDefinitionBuilder<ParentBuilder>
        extends CodeTreeBuilder<TableTypeDefinitionBuilder<ParentBuilder>,ParentBuilder,TableTypeDefinition> {

    public TableTypeDefinitionBuilder(TableTypeDefinition tar) {
        super(tar);
    }

    public TableTypeDefinitionBuilder() {
        super(new TableTypeDefinition());
    }

    public ColumnDefinitionBuilder<TableTypeDefinitionBuilder<ParentBuilder>> withColumnDefinition(){
        return new ColumnDefinitionBuilder<TableTypeDefinitionBuilder<ParentBuilder>>
                ((ColumnDefinition)
                        initNew(ColumnDefinition::new,
                                tar::getList,
                                tar::setList))
                .in(this);
    }

    public TableConstraintBuilder<TableTypeDefinitionBuilder<ParentBuilder>> withTableConstraint(){
        return new TableConstraintBuilder<TableTypeDefinitionBuilder<ParentBuilder>>
                ((TableTypeDefinition.TableConstraint)
                        initNew(TableTypeDefinition.TableConstraint::new,
                                tar::getList,
                                tar::setList))
                .in(this);
    }

    /**
     * Quick set
     * @param columnDefinitions
     * @return
     */
    public TableTypeDefinitionBuilder<ParentBuilder> withColumnDefinition(ColumnDefinition... columnDefinitions){
        initAdd(Arrays.asList(columnDefinitions),
                tar::getList,
                tar::setList);
        return this;
    }


    /**
     * Quick set
     * @param columnDefinitions
     * @return
     */
    public TableTypeDefinitionBuilder<ParentBuilder> $_(ColumnDefinition... columnDefinitions){
        initAdd(Arrays.asList(columnDefinitions),
                tar::getList,
                tar::setList);
        return this;
    }

    /**
     * Quick inout set TableConstraintBuilder' PrimaryKey and ColumnName
     * @param columnNames
     * @return
     */
    public TableTypeDefinitionBuilder<ParentBuilder> $_PrimaryKey(ColumnName... columnNames){
        return withTableConstraint()
                .withPrimaryKey()
                .withColumnName(columnNames)
                .and();
    }

    /**
     * Quick inout set TableConstraintBuilder' Unique and ColumnName
     * @param columnNames
     * @return
     */
    public TableTypeDefinitionBuilder<ParentBuilder> $_Unique(ColumnName... columnNames){
        return withTableConstraint()
                .withUnique()
                .withColumnName(columnNames)
                .and();
    }

    /**
     * Quick inout set TableConstraintBuilder' Unique and ColumnName
     * @param logicalExpression
     * @return
     */
    public TableTypeDefinitionBuilder<ParentBuilder> $_Check(Expression logicalExpression){
        return withTableConstraint()
                .withLogicalExpression(logicalExpression)
                .and();
    }



    /**
     *
     * @param <ParentBuilder>
     */
    public class TableConstraintBuilder<ParentBuilder>
            extends CodeTreeBuilder<TableConstraintBuilder<ParentBuilder>,ParentBuilder,TableTypeDefinition.TableConstraint> {

        public TableConstraintBuilder(TableTypeDefinition.TableConstraint tar) {
            super(tar);
        }


        public TableConstraintBuilder<ParentBuilder> withPrimaryKey(){
            tar.setUsePrimaryKey(true);
            return this;
        }

        public TableConstraintBuilder<ParentBuilder> withUnique(){
            tar.setUseUnique(true);
            return this;
        }

        public TableConstraintBuilder<ParentBuilder> withColumnName(ColumnName... columnNames){
            initAdd(Arrays.asList(columnNames),
                    tar::getColumnName,
                    tar::setColumnName);
            return this;
        }

        public TableConstraintBuilder<ParentBuilder> withLogicalExpression(Expression logicalExpression){
            tar.setLogicalExpression(logicalExpression);
            return this;
        }


        /**
         * Quick inout set PrimaryKey and ColumnName
         * @param columnNames
         * @return
         */
        public ParentBuilder _PrimaryKey$(ColumnName... columnNames){
            tar.setUsePrimaryKey(true);
            initAdd(Arrays.asList(columnNames),
                    tar::getColumnName,
                    tar::setColumnName);
            return this.and();
        }

        /**
         * Quick inout set Unique and ColumnName
         * @param columnNames
         * @return
         */
        public ParentBuilder _Unique$(ColumnName... columnNames){
            tar.setUseUnique(true);
            initAdd(Arrays.asList(columnNames),
                    tar::getColumnName,
                    tar::setColumnName);
            return this.and();
        }

        /**
         * Quick inout set Unique and ColumnName
         * @param logicalExpression
         * @return
         */
        public ParentBuilder _Check$(Expression logicalExpression){
            tar.setLogicalExpression(logicalExpression);
            return this.and();
        }
    }

}
