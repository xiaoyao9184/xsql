package com.xy.xsql.tsql.core;

import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.util.CheckUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/5/13.
 */
public class SimpleBlockRenderer
        implements BlockRenderer<String> {

    private String blockSeparator = "\n";
    private String blockListSeparator = " ";

    private int deepLevel = 0;

    public SimpleBlockRenderer(){}

    @Override
    public String render(Block block) {
        if(CheckUtil.isNull(block)){
            throw new NullPointerException("Null Block cant render!");
        }

        StringBuilder sb = new StringBuilder();
        List<Block> list = block.toBlockList();
        if(CheckUtil.isNull(list)){
            sb.append(StringUtils.repeat(' ', deepLevel));
            sb.append(block.toString());
            if(!(block instanceof Other)){
                sb.append(blockSeparator);
            }
        }else{
            this.deepLevel++;
            list.forEach(b -> {
                sb.append(this.render(b));
//                sb.append(blockListSeparator);
            });
            this.deepLevel--;
        }

        return sb.toString();
    }

}
