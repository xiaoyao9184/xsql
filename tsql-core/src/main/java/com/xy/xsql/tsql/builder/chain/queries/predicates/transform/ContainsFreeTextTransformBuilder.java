package com.xy.xsql.tsql.builder.chain.queries.predicates.transform;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.builder.chain.queries.predicates.ContainsPredicateBuilder;
import com.xy.xsql.tsql.builder.chain.queries.predicates.FreeTextPredicateBuilder;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.queries.predicates.Contains;
import com.xy.xsql.tsql.model.queries.predicates.FreeText;
import com.xy.xsql.tsql.model.queries.predicates.Predicate;

/**
 * Abstract Predicate Builder
 *
 * Created by xiaoyao9184 on 2017/3/16.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class ContainsFreeTextTransformBuilder<ParentBuilder>
        extends CodeTreeBuilder<ContainsFreeTextTransformBuilder<ParentBuilder>,ParentBuilder,Predicate> {

    public ContainsFreeTextTransformBuilder(Predicate predicate) {
        super(predicate);
    }

    public ContainsFreeTextTransformBuilder() {
        super(null);
    }

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
        return new ContainsPredicateBuilder<ParentBuilder>()
                .enter(out(),p -> {
                    target = p;
                    back();
                });
    }

    /**
     * in
     * @return FreeTextPredicateBuilder
     */
    public FreeTextPredicateBuilder<ParentBuilder> _FreeText() {
        return new FreeTextPredicateBuilder<ParentBuilder>()
                .enter(out(),p -> {
                    target = p;
                    back();
                });
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
                        .back();
            }else{
                return _Contains()
                        .withContainsSearchCondition(containsSearchCondition_freetextString)
                        .withColumnName(column)
                        .back();
            }
        }else if(FreeText.class.equals(predicateClass)){
            if(column == null){
                return _FreeText()
                        .withFreeText(containsSearchCondition_freetextString)
                        .withAllColumn()
                        .back();
            }else{
                return _FreeText()
                        .withFreeText(containsSearchCondition_freetextString)
                        .withColumnName(column)
                        .back();
            }
        }
        return and();
    }

}
