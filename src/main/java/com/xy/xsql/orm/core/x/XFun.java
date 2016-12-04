package com.xy.xsql.orm.core.x;

/**
 * XCase Fun
 * Created by xiaoyao9184 on 2016/11/12.
 */
public interface XFun<THIS> {

    THIS funCount();

    THIS funSum();

    THIS funAvg();

    THIS funFirst();

    THIS funLast();

    THIS funMax();

    THIS funMin();


    THIS funLcase();

    THIS funUcase();

    THIS funLen();

    THIS funMid();

    THIS funFormat();

    THIS funRound();

    THIS funNow();


}
