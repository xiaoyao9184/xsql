package com.xy.xsql.block.core.converter;

import com.xy.xsql.block.core.BlockManager;
import com.xy.xsql.block.model.MetaBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xiaoyao9184 on 2017/9/7.
 */
public class ClassMetaBlockConverter
        implements BlockConverter<Class,MetaBlock> {

    private Logger logger;

    public ClassMetaBlockConverter() {
        logger = LoggerFactory.getLogger(ClassMetaBlockConverter.class);
    }

    @Override
    public MetaBlock convert(Class aClass) {
        BlockConverter converter = BlockManager.byConverter()
                .getMapper(aClass);
        if(converter instanceof ModelMetaBlockConverter){
            ModelMetaBlockConverter modelMetaBlockConverter = (ModelMetaBlockConverter) converter;
            MetaBlock block = new MetaBlock();
            block.setMeta(modelMetaBlockConverter.meta());
            return block;
        }
        logger.warn("cant get BlockMeta from " + aClass.getName());
        return null;
    }

}
