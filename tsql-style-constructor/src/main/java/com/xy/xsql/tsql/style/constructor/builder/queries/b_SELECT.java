package com.xy.xsql.tsql.style.constructor.builder.queries;

import com.xy.xsql.tsql.builder.chain.queries.SelectBuilder;
import com.xy.xsql.tsql.model.elements.operators.Set;
import com.xy.xsql.tsql.model.queries.Select;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public class b_SELECT extends SelectBuilder {

    public static class b$query_expression extends SelectBuilder.QueryExpressionBuilder<b$query_expression> {

        public b$query_expression() {
            this.in(this);
        }


        /*
        Item
         */

        public b$query_expression UNION(b$query_specification query_specification){
            return withUnitItem()
                    .withOperatorSet(Set.UNION)
                    .withQuerySpecification(query_specification.build())
                    .and()
                    .and();
        }

        public b$query_expression UNION_ALL(b$query_specification query_specification){
            return withUnitItem()
                    .withOperatorSet(Set.UNION_ALL)
                    .withQuerySpecification(query_specification.build())
                    .and()
                    .and();
        }

        public b$query_expression EXCEPT(b$query_specification query_specification){
            return withUnitItem()
                    .withOperatorSet(Set.EXCEPT)
                    .withQuerySpecification(query_specification.build())
                    .and()
                    .and();
        }

        public b$query_expression INTERSECT(b$query_specification query_specification){
            return withUnitItem()
                    .withOperatorSet(Set.INTERSECT)
                    .withQuerySpecification(query_specification.build())
                    .and()
                    .and();
        }

    }

    public static class b$query_specification extends SelectBuilder.QuerySpecificationBuilder<b$query_specification> {

        public b$query_specification() {
            super(new Select.QuerySpecification());
        }


        /*
        Item
         */

        /**
         * same as
         * @see b$query_expression
         */

        public b$query_expression UNION(b$query_specification query_specification){
            return new b$query_expression(){
                {
                    withUnitItem()
                            .withOperatorSet(Set.UNION)
                            .withQuerySpecification(query_specification.build());
                }
            };
        }

        public b$query_expression UNION_ALL(b$query_specification query_specification){
            return new b$query_expression(){
                {
                    withUnitItem()
                            .withOperatorSet(Set.UNION_ALL)
                            .withQuerySpecification(query_specification.build());
                }
            };
        }

        public b$query_expression EXCEPT(b$query_specification query_specification){
            return new b$query_expression(){
                {
                    withUnitItem()
                            .withOperatorSet(Set.EXCEPT)
                            .withQuerySpecification(query_specification.build());
                }
            };
        }

        public b$query_expression INTERSECT(b$query_specification query_specification){
            return new b$query_expression(){
                {
                    withUnitItem()
                            .withOperatorSet(Set.INTERSECT)
                            .withQuerySpecification(query_specification.build());
                }
            };
        }


        //TODO may be not need

        public b$query_expression UNION_(b$query_specification query_specification){
            return new b$query_expression(){
                {
                    withUnitItem()
                            .withOperatorSet(Set.UNION)
                            .withQuerySpecification(query_specification.build());
                }
            };
        }

        public b$query_expression UNION_ALL_(b$query_specification query_specification){
            return new b$query_expression(){
                {
                    withUnitItem()
                            .withOperatorSet(Set.UNION_ALL)
                            .withQuerySpecification(query_specification.build());
                }
            };
        }

        public b$query_expression EXCEPT_(b$query_specification query_specification){
            return new b$query_expression(){
                {
                    withUnitItem()
                            .withOperatorSet(Set.EXCEPT)
                            .withQuerySpecification(query_specification.build());
                }
            };
        }

        public b$query_expression INTERSECT_(b$query_specification query_specification){
            return new b$query_expression(){
                {
                    withUnitItem()
                            .withOperatorSet(Set.INTERSECT)
                            .withQuerySpecification(query_specification.build());
                }
            };
        }

    }

}
