package com.xy.xsql.orm.util;

import com.xy.xsql.orm.data.entity.EntityColumn;
import com.xy.xsql.orm.data.entity.EntityTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2016/11/25.
 */
public class EntityUtil {

    /**
     * Comparison EntityTemplate with table name, remove if one of them not exist
     * @param srcList EntityTemplate List 1
     * @param src2List EntityTemplate List 2
     * @return EntityTemplate Map
     */
    public static Map<EntityTemplate,EntityTemplate> entityDataAllHaveMap(List<EntityTemplate> srcList, List<EntityTemplate> src2List){
        Map<EntityTemplate,EntityTemplate> alterMap = new HashMap<>();
        for (EntityTemplate EntityTemplate : srcList) {
            for (EntityTemplate entityData1: src2List) {
                if(EntityTemplate.getTable().getName().equals(
                        entityData1.getTable().getName()
                )){
                    alterMap.put(EntityTemplate,entityData1);
                    break;
                }
            }
        }

        return alterMap;
    }

    /**
     * Comparison EntityTemplate with table name, keep rather List if target List not exist
     * @param tarList EntityTemplate target List
     * @param ratherList EntityTemplate rather List
     * @return EntityTemplate List
     */
    public static List<EntityTemplate> entityDataLostList(List<EntityTemplate> tarList, List<EntityTemplate> ratherList) {
        List<EntityTemplate> lostList = new ArrayList<>();
        boolean haved = false;
        for (EntityTemplate entityTemplate : ratherList) {
            for (EntityTemplate entityTemplate1 : tarList) {
                if(entityTemplate.getTable().getName().equals(
                        entityTemplate1.getTable().getName()
                )){
                    haved = true;
                    break;
                }
            }

            if(!haved){
                lostList.add(entityTemplate);
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
