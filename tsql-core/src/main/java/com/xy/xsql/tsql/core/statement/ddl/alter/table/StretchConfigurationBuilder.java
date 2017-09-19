package com.xy.xsql.tsql.core.statement.ddl.alter.table;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.core.element.table.TableStretchOptionsBuilder;
import com.xy.xsql.tsql.model.element.table.TableStretchOptions;
import com.xy.xsql.tsql.model.statement.ddl.alter.table.StretchConfiguration;

import java.util.List;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * Created by xiaoyao9184 on 2017/9/16.
 */
public class StretchConfigurationBuilder<ParentBuilder>
        extends CodeTreeBuilder<StretchConfigurationBuilder<ParentBuilder>,ParentBuilder,StretchConfiguration> {

    public StretchConfigurationBuilder(StretchConfiguration target) {
        super(target);
    }

    public StretchConfigurationBuilder<ParentBuilder> withUseOn(Boolean useOn){
        target.setUseOn(useOn);
        return this;
    }

    public StretchConfigurationBuilder<ParentBuilder> withTableStretchOptions(TableStretchOptions tableStretchOptions){
        target.setTableStretchOptions(tableStretchOptions);
        return this;
    }

    public StretchConfigurationBuilder<ParentBuilder> withTableStretchOptionsList(List<TableStretchOptions> tableStretchOptionsList){
        target.setTableStretchOptionsList(tableStretchOptionsList);
        return this;
    }

    /*
    Quick
     */

    public TableStretchOptionsBuilder<StretchConfigurationBuilder<ParentBuilder>> $ON(){
        withUseOn(true);
        return new TableStretchOptionsBuilder<StretchConfigurationBuilder<ParentBuilder>>
                (initSet(TableStretchOptions::new,
                        target::getTableStretchOptions,
                        target::setTableStretchOptions))
                .in(this);
    }

    public StretchConfigurationBuilder<ParentBuilder> $OFF_WITHOUT_DATA_RECOVERY_$MIGRATION_STATE_$PAUSED(){
        return withUseOn(false);
    }

    public TableStretchOptionsBuilder<StretchConfigurationBuilder<ParentBuilder>> $(){
        return new TableStretchOptionsBuilder<StretchConfigurationBuilder<ParentBuilder>>
                (initNew(TableStretchOptions::new,
                        target::getTableStretchOptionsList,
                        target::setTableStretchOptionsList))
                .in(this);
    }

}
