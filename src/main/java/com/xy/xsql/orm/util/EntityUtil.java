package com.xy.xsql.orm.util;

import com.xy.xsql.orm.data.entity.EntityColumn;
import com.xy.xsql.orm.data.entity.EntityTemplateData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2016/11/25.
 */
public class EntityUtil {

    /**
     * Comparison EntityTemplateData with table name, remove if one of them not exist
     * @param srcList EntityTemplateData List 1
     * @param src2List EntityTemplateData List 2
     * @return EntityTemplateData Map
     */
    public static Map<EntityTemplateData,EntityTemplateData> entityDataAllHaveMap(List<EntityTemplateData> srcList, List<EntityTemplateData> src2List){
        Map<EntityTemplateData,EntityTemplateData> alterMap = new HashMap<>();
        for (EntityTemplateData EntityTemplateData: srcList) {
            for (EntityTemplateData entityData1: src2List) {
                if(EntityTemplateData.getTable().getName().equals(
                        entityData1.getTable().getName()
                )){
                    alterMap.put(EntityTemplateData,entityData1);
                    break;
                }
            }
        }

        return alterMap;
    }

    /**
     * Comparison EntityTemplateData with table name, keep rather List if target List not exist
     * @param tarList EntityTemplateData target List
     * @param ratherList EntityTemplateData rather List
     * @return EntityTemplateData List
     */
    public static List<EntityTemplateData> entityDataLostList(List<EntityTemplateData> tarList, List<EntityTemplateData> ratherList) {
        List<EntityTemplateData> lostList = new ArrayList<>();
        boolean haved = false;
        for (EntityTemplateData entityTemplateData: ratherList) {
            for (EntityTemplateData entityTemplateData1: tarList) {
                if(entityTemplateData.getTable().getName().equals(
                        entityTemplateData1.getTable().getName()
                )){
                    haved = true;
                    break;
                }
            }

            if(!haved){
                lostList.add(entityTemplateData);
            }

            haved = false;
        }
        return lostList;
    }

    /**
     * Comparison EntityColumn with column name, remove if one of them not exist
     * @param srcList EntityColumn List 1
     * @param src2List EntityColumn List 2
     * @return EntityColumn Map
     */
    public static Map<EntityColumn,EntityColumn> allHaveMap(List<EntityColumn> srcList, List<EntityColumn> src2List){
        Map<EntityColumn,EntityColumn> alterMap = new HashMap<>();
        for (EntityColumn entityColumn: srcList) {
            for (EntityColumn entityColumn1: src2List) {
                if(entityColumn.getName().equals(
                        entityColumn1.getName()
                )){
                    alterMap.put(entityColumn,entityColumn1);
                    break;
                }
            }
        }

        return alterMap;
    }

    /**
     * Comparison EntityColumn with table name, keep rather List if target List not exist
     * @param tarList EntityColumn target List
     * @param ratherList EntityColumn rather List
     * @return EntityColumn List
     */
    public static List<EntityColumn> lostList(List<EntityColumn> tarList, List<EntityColumn> ratherList) {
        List<EntityColumn> lostList = new ArrayList<>();
        boolean haved = false;
        for (EntityColumn ratherEntityColumn: ratherList) {
            for (EntityColumn tarEntityColumn: tarList) {
                if(ratherEntityColumn.getName().equals(
                        tarEntityColumn.getName()
                )){
                    haved = true;
                    break;
                }
            }

            if(!haved){
                lostList.add(ratherEntityColumn);
            }

            haved = false;
        }
        return lostList;
    }

}
