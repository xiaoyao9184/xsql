package com.xy.xsql.tsql.core.statement.ddl.alter.table;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.core.element.index.PartitionBuilder;
import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatype.OnOff;
import com.xy.xsql.tsql.model.element.index.Partition;
import com.xy.xsql.tsql.model.statement.ddl.alter.table.DropClusteredConstraintOption;

import static com.xy.xsql.core.FiledBuilder.initSet;

/**
 * Created by xiaoyao9184 on 2017/9/16.
 */
public class DropClusteredConstraintOptionBuilder<ParentBuilder>
        extends CodeTreeBuilder<DropClusteredConstraintOptionBuilder<ParentBuilder>,ParentBuilder,DropClusteredConstraintOption> {

    public DropClusteredConstraintOptionBuilder(DropClusteredConstraintOption target) {
        super(target);
    }

    public DropClusteredConstraintOptionBuilder<ParentBuilder> withMaxDegreeOfParallelism(NumberConstant maxDegreeOfParallelism){
        target.setMaxDegreeOfParallelism(maxDegreeOfParallelism);
        return this;
    }

    public DropClusteredConstraintOptionBuilder<ParentBuilder> withOnline(OnOff online){
        target.setOnline(online);
        return this;
    }

    public DropClusteredConstraintOptionBuilder<ParentBuilder> withMoveTo(Partition moveTo){
        target.setMoveTo(moveTo);
        return this;
    }

    /*
    Quick
     */

    public ParentBuilder $MAXDOP(Integer maxDegreeOfParallelism){
        return withMaxDegreeOfParallelism(
                new NumberConstant(maxDegreeOfParallelism)
                        .withInteger()
                        .withUnsigned())
                .out();
    }

    public ParentBuilder $ONLINE_ON(){
        return withOnline(OnOff.ON)
                .out();
    }

    public ParentBuilder $ONLINE_OFF(){
        return withOnline(OnOff.OFF)
                .out();
    }

    public PartitionBuilder<ParentBuilder> $MOVE_TO(){
        return new PartitionBuilder<ParentBuilder>
                (initSet(Partition::new,
                        target::getMoveTo,
                        target::setMoveTo))
                .in(out());
    }

}
