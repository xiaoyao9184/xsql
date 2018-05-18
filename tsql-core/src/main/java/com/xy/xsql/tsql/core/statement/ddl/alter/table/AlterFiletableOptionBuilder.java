package com.xy.xsql.tsql.core.statement.ddl.alter.table;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.statements.alter.table.AlterFiletableOption;

/**
 * Created by xiaoyao9184 on 2017/9/16.
 */
public class AlterFiletableOptionBuilder<ParentBuilder>
        extends CodeTreeBuilder<AlterFiletableOptionBuilder<ParentBuilder>,ParentBuilder,AlterFiletableOption> {

    public AlterFiletableOptionBuilder(AlterFiletableOption target) {
        super(target);
    }

    public AlterFiletableOptionBuilder<ParentBuilder> withUseEnableFileTableNamespace(Boolean useEnableFileTableNamespace){
        target.setUseEnableFileTableNamespace(useEnableFileTableNamespace);
        return this;
    }

    public AlterFiletableOptionBuilder<ParentBuilder> withDirectoryName(String directoryName){
        target.setDirectoryName(directoryName);
        return this;
    }

    /*
    Quick
     */

    public AlterFiletableOptionBuilder<ParentBuilder> $ENABLE_$FILETABLE_NAMESPACE(){
        return withUseEnableFileTableNamespace(true);
    }

    public AlterFiletableOptionBuilder<ParentBuilder> $DISABLE_$FILETABLE_NAMESPACE(){
        return withUseEnableFileTableNamespace(false);
    }

    public AlterFiletableOptionBuilder<ParentBuilder> $SET_$FILETABLE_DIRECTORY(String directoryName){
        return withDirectoryName(directoryName);
    }

}
