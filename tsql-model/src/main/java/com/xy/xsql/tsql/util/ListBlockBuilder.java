package com.xy.xsql.tsql.util;

import com.xy.xsql.core.ListBuilder;
import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.element.Unknown;
import com.xy.xsql.tsql.model.expression.Expression;

import java.util.Collection;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/3/14.
 */
public class ListBlockBuilder extends ListBuilder<Block> {

    private Block delimiter;

    public ListBlockBuilder withDelimiter(Block delimiter) {
        this.delimiter = delimiter;
        return this;
    }

    /**
     * Add Block
     *
     * @param element Block
     * @return This
     */
    public ListBlockBuilder append(Block element) {
        if (element != null) {
            if (this.delimiter != null) {
                super.withItem(this.delimiter);
            }
            super.withItem(element);
        }
        return this;
    }

    /**
     * Add Block List with Separator OtherEnum
     *
     * @param elementCollection Block List
     * @param delimiter         Separator OtherEnum
     * @return This
     */
    public <T extends Block> ListBlockBuilder append(Collection<T> elementCollection, Other delimiter) {
        Block listDelimiter = delimiter;
        if (listDelimiter == null) {
            listDelimiter = this.delimiter;
        }
        int i = 0;
        for (Block element : elementCollection) {
            if (i == 0) {
                super.withItem(this.delimiter)
                        .withItem(element);
            } else {
                super.withItem(listDelimiter)
                        .withItem(element);
            }
            i++;
        }
        return this;
    }

    public <T extends Block> ListBlockBuilder append(List<T> elementList) {
        return append(elementList, null);
    }

    /**
     * Add UnknownString
     *
     * @param s String
     * @return This
     */
    public ListBlockBuilder append(String s) {
        if (s != null) {
            super.withItem(new Unknown(s));
        }
        return this;
    }

    public ListBlockBuilder append(Integer integerConstant) {
        if (integerConstant != null) {
            super.withItem(new Unknown(integerConstant.toString()));
        }
        return this;
    }


    public <T extends Expression> ListBlockBuilder appendExpression(List<T> expressionList, Other delimiter) {
        int i = 0;
        for (T expression : expressionList) {
            if (i == 0) {
                this.append(expression.toBlockList(), null);
            } else {
                this.append(expression.toBlockList(), null)
                        .append(delimiter);
            }
            i++;
        }
        return this;
    }


    /**
     * Build
     *
     * @return Block List
     */
    public List<Block> build() {
        return super.build(null);
    }

    public ListBlockBuilder append(ListBlockBuilder b) {
        this.append(b.build());
        return this;
    }
}
