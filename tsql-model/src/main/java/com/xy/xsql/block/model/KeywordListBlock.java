package com.xy.xsql.block.model;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xiaoyao9184 on 2017/6/6.
 */
public class KeywordListBlock implements Block {

    private List<String> keywordList;

    public KeywordListBlock(List<String> keywordList){
        this.keywordList = keywordList;
    }

    public List<String> getKeywordList() {
        return keywordList;
    }

    public void setKeywordList(List<String> keywordList) {
        this.keywordList = keywordList;
    }

    @Override
    public String toString() {
        return keywordList
                .stream()
                .collect(Collectors.joining(" "));
    }

}
