package com.xy.xsql.orm.data.sql.element.info;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.ElementList;
import com.xy.xsql.orm.data.sql.element.OtherEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/1/9.
 */
public class GroupList<T extends ElementList>
        implements ElementList {

    private List<List<T>> dataGroup;

    private Element groupStartElement = OtherEnum.GROUP_START;
    private Element groupDelimiterElement = OtherEnum.DELIMITER;
    private Element groupEndElement = OtherEnum.GROUP_END;


    public GroupList(){
        this.dataGroup = new ArrayList<>();
    }

    public GroupList(Class<List> listClass) throws IllegalAccessException, InstantiationException {
        this.dataGroup = listClass.newInstance();
    }


    @Override
    public List<Element> toElementList() {
        ListElementBuilder b = new ListElementBuilder();

        int i = 0;
        for (List<T> dataList: this.dataGroup) {
            if(dataList != null &&
                    !dataList.isEmpty()){
                if(i != 0){
                    b.append(groupDelimiterElement);
                }

                b.append(groupStartElement);
                for (T data: dataList) {
                    b.append(data.toElementList(),OtherEnum.DELIMITER);
                }
                b.append(groupEndElement);
            }
            i++;
        }

        return b.build();
    }

    public boolean add(List<T> dataList) {
        return this.dataGroup.add(dataList);
    }
}
