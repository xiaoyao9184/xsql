package com.xy.xsql.tsql.builder.chain.statements.alter.table;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.index.PartitionBuilder;
import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.other.OnOff;
import com.xy.xsql.tsql.model.datatypes.table.index.Partition;
import com.xy.xsql.tsql.model.statements.alter.table.DropClusteredConstraintOption;

import static com.xy.xsql.core.handler.object.GetterSetterObjectHandler.object;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_unsigned_integer;

/**
 * DropClusteredConstraintOptionBuilder
 * Created by xiaoyao9184 on 2017/9/16.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class DropClusteredConstraintOptionBuilder<ParentBuilder>
        extends ParentHoldBuilder<DropClusteredConstraintOptionBuilder<ParentBuilder>,ParentBuilder,DropClusteredConstraintOption> {

    public DropClusteredConstraintOptionBuilder() {
        super(new DropClusteredConstraintOption());
    }

    public DropClusteredConstraintOptionBuilder(DropClusteredConstraintOption target) {
        super(target);
    }

    /**
     * set
     * @param maxDegreeOfParallelism max degree of parallelism
     * @return THIS
     */
    public DropClusteredConstraintOptionBuilder<ParentBuilder> withMaxDegreeOfParallelism(NumberConstant maxDegreeOfParallelism){
        target.setMaxDegreeOfParallelism(maxDegreeOfParallelism);
        return this;
    }

    /**
     * set
     * @param online OnOff
     * @return THIS
     */
    public DropClusteredConstraintOptionBuilder<ParentBuilder> withOnline(OnOff online){
        target.setOnline(online);
        return this;
    }

    /**
     * set
     * @param moveTo Partition
     * @return THIS
     */
    public DropClusteredConstraintOptionBuilder<ParentBuilder> withMoveTo(Partition moveTo){
        target.setMoveTo(moveTo);
        return this;
    }




    /*
    Quick
     */

    /**
     * Quick set
     * @param maxDegreeOfParallelism max degree of parallelism
     * @return PARENT
     */
    public ParentBuilder $Maxdop(Integer maxDegreeOfParallelism){
        return withMaxDegreeOfParallelism(
                c_unsigned_integer(maxDegreeOfParallelism))
                .and();
    }

    /**
     * Quick set
     * @return PARENT
     */
    public ParentBuilder $OnlineOn(){
        return withOnline(OnOff.ON)
                .and();
    }

    /**
     * Quick set
     * @return PARENT
     */
    public ParentBuilder $OnlineOff(){
        return withOnline(OnOff.OFF)
                .and();
    }

    /**
     * Quick set
     * @return PARENT
     */
    public PartitionBuilder<ParentBuilder> $MoveTo(){
        return new PartitionBuilder<ParentBuilder>
                (object(target::getMoveTo, target::setMoveTo)
                        .init(Partition::new))
                .in(this.and());
    }

}
