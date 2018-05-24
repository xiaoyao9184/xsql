package com.xy.xsql.tsql.builder.chain.statements.alter.table;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.table.TableStretchOptionsBuilder;
import com.xy.xsql.tsql.model.datatypes.table.table.TableStretchOptions;
import com.xy.xsql.tsql.model.statements.alter.table.StretchConfiguration;

import java.util.List;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * StretchConfigurationBuilder
 * Created by xiaoyao9184 on 2017/9/16.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class StretchConfigurationBuilder<ParentBuilder>
        extends CodeTreeBuilder<StretchConfigurationBuilder<ParentBuilder>,ParentBuilder,StretchConfiguration> {

    public StretchConfigurationBuilder(StretchConfiguration target) {
        super(target);
    }

    public StretchConfigurationBuilder<ParentBuilder> withUseOn(Boolean useOn){
        target.setUseOn(useOn);
        return this;
    }

    /**
     * set
     * @param tableStretchOptions TableStretchOptions
     * @return THIS
     */
    public StretchConfigurationBuilder<ParentBuilder> withTableStretchOptions(TableStretchOptions tableStretchOptions){
        target.setTableStretchOptions(tableStretchOptions);
        return this;
    }

    /**
     * set
     * @param tableStretchOptionsList TableStretchOptions
     * @return THIS
     */
    public StretchConfigurationBuilder<ParentBuilder> withTableStretchOptionsList(List<TableStretchOptions> tableStretchOptionsList){
        target.setTableStretchOptionsList(tableStretchOptionsList);
        return this;
    }




    /*
    Quick
     */

    /**
     * Quick in
     * @return TableStretchOptionsBuilder
     */
    public TableStretchOptionsBuilder<StretchConfigurationBuilder<ParentBuilder>> $On(){
        withUseOn(true);
        return new TableStretchOptionsBuilder<StretchConfigurationBuilder<ParentBuilder>>
                (initSet(TableStretchOptions::new,
                        target::getTableStretchOptions,
                        target::setTableStretchOptions))
                .in(this);
    }

    /**
     * Quick set
     * @return THIS
     */
    public StretchConfigurationBuilder<ParentBuilder> $OffWithoutDataRecovery$MigrationState$Paused(){
        return withUseOn(false);
    }

    /**
     * Quick in
     * @return TableStretchOptionsBuilder
     */
    public TableStretchOptionsBuilder<StretchConfigurationBuilder<ParentBuilder>> $(){
        return new TableStretchOptionsBuilder<StretchConfigurationBuilder<ParentBuilder>>
                (initNew(TableStretchOptions::new,
                        target::getTableStretchOptionsList,
                        target::setTableStretchOptionsList))
                .in(this);
    }

}
