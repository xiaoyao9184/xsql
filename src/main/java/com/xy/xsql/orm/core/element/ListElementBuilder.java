package com.xy.xsql.orm.core.element;


import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.UnknownString;
import com.xy.xsql.orm.util.ListBuilder;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/11/12.
 */
public class ListElementBuilder extends ListBuilder<Element> {

    /**
     * Add Element
     * @param element Element
     * @return This
     */
    public ListElementBuilder append(Element element) {
        if(element != null){
            super.withItem(element);
        }
        return this;
    }

    /**
     * Add Element List with Separator OtherEnum
     * @param elementList Element List
     * @param delimiter Separator OtherEnum
     * @return This
     */
    public ListElementBuilder append(List<Element> elementList, OtherEnum delimiter) {
        int i = 0;
        for (Element element: elementList) {
            if(i==0){
                super.withItem(element);
            }else{
                super.withItem(delimiter).withItem(element);
            }
            i++;
        }
        return this;
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

    /**
     * Build
     * @return Element List
     */
    public List<Element> build() {
        return super.build(null);
    }

}
