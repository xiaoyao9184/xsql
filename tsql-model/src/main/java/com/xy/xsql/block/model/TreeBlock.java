package com.xy.xsql.block.model;

import com.xy.xsql.tsql.model.Block;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/3/14.
 */
public class TreeBlock implements Block {

    private boolean hasChild;
    private List<Block> blockList;

    public TreeBlock(){
        blockList = new ArrayList<>();
    }


    public TreeBlock withChild(List<Block> blockList){
        this.hasChild = true;
        this.blockList = blockList;
        return this;
    }

    public TreeBlock withChild(Block block){
        this.hasChild = true;
        this.blockList.add(block);
        return this;
    }


    @Override
    public boolean hasChild() {
        return hasChild;
    }

    @Override
    public List<Block> toBlockList() {
        return blockList;
    }
}
