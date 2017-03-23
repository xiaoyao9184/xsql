package com.xy.xsql.entity.core.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/11/13.
 */
public class ListUtil {

    /**
     * Comparison and find lost elements in Target List through Refer List
     * @param targetList Target List
     * @param referList Refer List
     * @param comparator Comparator with T
     * @param <T> T
     * @return lost element List
     */
    public static <T> List<T> lostElementList(List<T> targetList, List<T> referList, Comparator<? super T> comparator){
        List<T> addedList = new ArrayList<>();
        boolean findFlag = false;
        for (T refer : referList) {
            //find in targetList
            for (T target : targetList) {
                if(comparator.compare(refer,target) == 0){
                    findFlag = true;
                    break;
                }
            }
            //cant find
            if(!findFlag){
                addedList.add(refer);
            }

            findFlag = false;
        }
        return addedList;
    }

    /**
     * Comparison and find both included elements in Target List and Refer List
     * @param targetList Target List
     * @param referList Refer List
     * @param comparator Comparator with T
     * @param <T> T
     * @return both included element List
     */
    public static <T> List<T> bothIncludedElementList(List<T> targetList, List<T> referList, Comparator<? super T> comparator){
        List<T> addedList = new ArrayList<>();
        for (T refer: referList) {
            //find in targetList
            for (T target: targetList) {
                if(comparator.compare(refer,target) == 0){
                    addedList.add(target);
                    break;
                }
            }
        }
        return addedList;
    }

    public static <T> List<T> fill(List<T> list, int size, Class<T> fillClass) throws IllegalAccessException, InstantiationException {
        int sizeMax = list.size();
        if(size > sizeMax){
            for(int index = sizeMax; index < size; index++){
                list.add(index, fillClass.newInstance());
            }
        }
        return list;
    }
}
