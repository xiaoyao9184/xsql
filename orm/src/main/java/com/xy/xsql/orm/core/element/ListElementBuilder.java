package com.xy.xsql.orm.core.element;


import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.NumberElement;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.UnknownString;
import com.xy.xsql.core.ListBuilder;

import java.util.Collection;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/11/12.
 */
public class ListElementBuilder extends ListBuilder<Element> {

    private Element delimiter;

    public ListElementBuilder withDelimiter(Element delimiter){
        this.delimiter = delimiter;
        return this;
    }

    /**
     * Add Element
     * @param element Element
     * @return This
     */
    public ListElementBuilder append(Element element) {
        if(element != null){
            if(this.delimiter != null){
                super.withItem(this.delimiter);
            }
            super.withItem(element);
        }
        return this;
    }

    /**
     * Add Element List with Separator OtherEnum
     * @param elementCollection Element List
     * @param delimiter Separator OtherEnum
     * @return This
     */
    public <T extends Element> ListElementBuilder append(Collection<T> elementCollection, OtherEnum delimiter) {
        Element listDelimiter = delimiter;
        if(listDelimiter == null){
            listDelimiter = this.delimiter;
        }
        int i = 0;
        for (Element element: elementCollection) {
            if(i==0){
                super.withItem(this.delimiter)
                        .withItem(element);
            }else{
                super.withItem(listDelimiter)
                        .withItem(element);
            }
            i++;
        }
        return this;
    }

    public <T extends Element> ListElementBuilder append(List<T> elementList) {
        return append(elementList,null);
    }

    /**
     * Add UnknownString
     * @param s String
     * @return This
     */
    public ListElementBuilder append(String s) {
        if(s != null){
            super.withItem(new UnknownString(s));
        }
        return this;
    }

    public ListElementBuilder append(Integer integerConstant) {
        if(integerConstant != null){
            super.withItem(new NumberElement(integerConstant));
        }
        return this;
    }



    public <T extends Expression> ListElementBuilder appendExpression(List<T> expressionList, OtherEnum delimiter) {
        int i = 0;
        for (T expression: expressionList) {
            if(i==0){
                this.append(expression.toElementList(),null);
            }else{
                this.append(expression.toElementList(),null)
                        .append(delimiter);
            }
            i++;
        }
        return this;
    }


    /**
     * Build
     * @return Element List
     */
    public List<Element> build() {
        return super.build(null);
    }

}
