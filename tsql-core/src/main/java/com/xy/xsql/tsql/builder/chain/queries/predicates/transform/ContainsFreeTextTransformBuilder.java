package com.xy.xsql.tsql.builder.chain.queries.predicates.transform;

import com.xy.xsql.core.builder.parent.ParentHoldLazyConfigBuilder;
import com.xy.xsql.tsql.builder.chain.queries.predicates.ContainsPredicateBuilder;
import com.xy.xsql.tsql.builder.chain.queries.predicates.FreeTextPredicateBuilder;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.queries.predicates.Contains;
import com.xy.xsql.tsql.model.queries.predicates.FreeText;
import com.xy.xsql.tsql.model.queries.predicates.Predicate;

import static com.xy.xsql.core.handler.object.SupplierObjectHandler.object;

/**
 * Abstract ContainsFreeText Predicate Builder
 * Created by xiaoyao9184 on 2017/3/16.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class ContainsFreeTextTransformBuilder<ParentBuilder>
        extends ParentHoldLazyConfigBuilder<ContainsFreeTextTransformBuilder<ParentBuilder>,ParentBuilder,Predicate> {

    public ContainsFreeTextTransformBuilder() {}

    private ColumnName column;
    private Class<? extends Predicate> predicateClass;


    /**
     * set transform
     * @param column column
     * @return THIS
     */
    public ContainsFreeTextTransformBuilder<ParentBuilder> withColumn(ColumnName column) {
        this.column = column;
        return this;
    }

    /**
     * set transform
     * @param predicateClass predicate
     * @return THIS
     */
    public ContainsFreeTextTransformBuilder<ParentBuilder> withPredicate(Class<? extends Predicate> predicateClass) {
        this.predicateClass = predicateClass;
        return this;
    }


    /**
     * in
     * @return ContainsPredicateBuilder
     */
    public ContainsPredicateBuilder<ParentBuilder> _Contains() {
        return new ContainsPredicateBuilder<ParentBuilder>
                (object(Contains::new).set(this::init))
                .in(this.and());
    }

    /**
     * in
     * @return FreeTextPredicateBuilder
     */
    public FreeTextPredicateBuilder<ParentBuilder> _FreeText() {
        return new FreeTextPredicateBuilder<ParentBuilder>
                (object(FreeText::new).set(this::init))
                .in(this.and());
    }

    /*
    Quick Transform CONTAINS FREETEXT Predicate
     */

    /**
     * Quick transform
     * Contains or FreeText Predicate
     * @param containsSearchCondition_freetextString expression
     * @return PARENT
     */
    public ParentBuilder $(String containsSearchCondition_freetextString) {
        if(Contains.class.equals(predicateClass)){
            if(column == null){
                return _Contains()
                        .withContainsSearchCondition(containsSearchCondition_freetextString)
                        .withAllColumn()
                        .and();
            }else{
                return _Contains()
                        .withContainsSearchCondition(containsSearchCondition_freetextString)
                        .withColumnName(column)
                        .and();
            }
        }else if(FreeText.class.equals(predicateClass)){
            if(column == null){
                return _FreeText()
                        .withFreeText(containsSearchCondition_freetextString)
                        .withAllColumn()
                        .and();
            }else{
                return _FreeText()
                        .withFreeText(containsSearchCondition_freetextString)
                        .withColumnName(column)
                        .and();
            }
        }
        return and();
    }

}
