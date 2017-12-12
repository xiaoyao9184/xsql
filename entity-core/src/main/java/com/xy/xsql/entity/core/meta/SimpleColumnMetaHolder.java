package com.xy.xsql.entity.core.meta;

import com.xy.xsql.entity.api.meta.ColumnMeta;
import com.xy.xsql.entity.api.meta.TableMeta;
import com.xy.xsql.entity.core.meta.column.ColumnCollectionIndexer;
import com.xy.xsql.entity.core.meta.column.ColumnMethodIndexer;
import com.xy.xsql.entity.core.meta.column.ColumnNameIndexer;
import com.xy.xsql.entity.core.meta.column.ColumnNumberIndexer;
import com.xy.xsql.entity.core.meta.table.*;
import com.xy.xsql.entity.model.definition.RelationshipClass;
import com.xy.xsql.entity.model.lambda.MethodEntityColumnMeta;
import com.xy.xsql.entity.model.lambda.PropertyDescriptorEntityColumnMeta;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by xiaoyao9184 on 2017/10/23.
 */
public class SimpleColumnMetaHolder
        implements ColumnCollectionIndexer<ColumnMeta>,
        ColumnNumberIndexer<ColumnMeta>,
        ColumnMethodIndexer<ColumnMeta>,
        ColumnNameIndexer<ColumnMeta> {

    private ClassTableMetaBuilderSelector classTableMetaBuilderSelector;
    private TableMeta<ColumnMeta> tableMeta;

    public SimpleColumnMetaHolder(TableMeta tableMeta) {
        this.tableMeta = tableMeta;
    }

    @Override
    public Optional<ColumnMeta> column(Integer index) {
        List<ColumnMeta> list = new ArrayList<>(tableMeta.getColumns());
        if(list.size() > index){
            return Optional.of(list.get(index));
        }
        return Optional.empty();
    }

    @Override
    public Optional<ColumnMeta> column(String name) {
        return tableMeta.getColumns().stream()
                .filter(c -> c.getName().equals(name))
                .findFirst();
    }

    @Override
    public Optional<ColumnMeta> column(Method method) {
        return tableMeta.getColumns().stream()
                .filter(c -> {
                    if(c instanceof MethodEntityColumnMeta){
                        MethodEntityColumnMeta methodEntityColumnMeta = (MethodEntityColumnMeta) c;
                        return methodEntityColumnMeta.getEntity().equals(method);
                    }else if(c instanceof PropertyDescriptorEntityColumnMeta){
                        PropertyDescriptorEntityColumnMeta propertyDescriptorEntityColumnMeta = (PropertyDescriptorEntityColumnMeta) c;
                        return propertyDescriptorEntityColumnMeta.getEntity().getReadMethod().equals(method) ||
                                propertyDescriptorEntityColumnMeta.getEntity().getWriteMethod().equals(method);
                    }
                    return false;
                })
                .findFirst();
    }

    @Override
    public Collection<ColumnMeta> column() {
        return tableMeta.getColumns();
    }

}
