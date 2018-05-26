package com.xy.xsql.tsql.builder.chain.statements.alter.table;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.model.statements.alter.table.AlterFiletableOption;

/**
 * AlterFiletableOptionBuilder
 * Created by xiaoyao9184 on 2017/9/16.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class AlterFiletableOptionBuilder<ParentBuilder>
        extends ParentHoldBuilder<AlterFiletableOptionBuilder<ParentBuilder>,ParentBuilder,AlterFiletableOption> {

    public AlterFiletableOptionBuilder() {
        super(new AlterFiletableOption());
    }

    public AlterFiletableOptionBuilder(AlterFiletableOption target) {
        super(target);
    }

    /**
     * set
     * @param useEnableFileTableNamespace enable file table namespace
     * @return THIS
     */
    public AlterFiletableOptionBuilder<ParentBuilder> withUseEnableFileTableNamespace(Boolean useEnableFileTableNamespace){
        target.setUseEnableFileTableNamespace(useEnableFileTableNamespace);
        return this;
    }

    /**
     * set
     * @param directoryName directory name
     * @return THIS
     */
    public AlterFiletableOptionBuilder<ParentBuilder> withDirectoryName(String directoryName){
        target.setDirectoryName(directoryName);
        return this;
    }




    /*
    Quick
     */

    /**
     * Quick set
     * @return THIS
     */
    public AlterFiletableOptionBuilder<ParentBuilder> $Enable$FiletableNamespace(){
        return withUseEnableFileTableNamespace(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public AlterFiletableOptionBuilder<ParentBuilder> $Disable$FiletableNamespace(){
        return withUseEnableFileTableNamespace(false);
    }

    /**
     * Quick set
     * @param directoryName directory name
     * @return THIS
     */
    public AlterFiletableOptionBuilder<ParentBuilder> $Set$FiletableDirectory(String directoryName){
        return withDirectoryName(directoryName);
    }

}
