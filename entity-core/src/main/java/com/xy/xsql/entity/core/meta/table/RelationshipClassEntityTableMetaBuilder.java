package com.xy.xsql.entity.core.meta.table;

import com.xy.xsql.entity.api.meta.BaseMeta;
import com.xy.xsql.entity.api.meta.ColumnMeta;
import com.xy.xsql.entity.api.meta.TableMeta;
import com.xy.xsql.entity.core.meta.SimpleColumnMetaHolder;
import com.xy.xsql.entity.core.meta.SimpleTableMetaManager;
import com.xy.xsql.entity.core.meta.column.ColumnCollectionIndexer;
import com.xy.xsql.entity.model.definition.NoEntityDefFoundError;
import com.xy.xsql.entity.model.definition.RelationshipClass;
import com.xy.xsql.entity.model.entity.EntityColumnMeta;
import com.xy.xsql.entity.model.lambda.MethodEntityColumnMeta;
import com.xy.xsql.entity.model.lambda.PropertyDescriptorEntityColumnMeta;
import com.xy.xsql.entity.model.lambda.RelationshipEntityTableMeta;
import net.sf.cglib.beans.BeanCopier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * RelationshipClassEntityTableMetaBuilder
 * RelationshipClass is also given class description
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class RelationshipClassEntityTableMetaBuilder
        implements EntityTableMetaBuilder<RelationshipClassEntityTableMetaBuilder,RelationshipClassEntityTableMetaBuilder.RelationshipClassEntityTableMetaBuilderConfig,RelationshipClass,RelationshipEntityTableMeta> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private RelationshipClassEntityTableMetaBuilderConfig config;

    /**
     * Both class of RelationshipClass must have any field
     * @param source RelationshipClass
     * @return support/not support
     */
    public static boolean checkSupport(RelationshipClass source) {
        return source.getLeftClass().getDeclaredFields().length != 0 &&
                source.getRightClass().getDeclaredFields().length != 0;
    }

    @Override
    public RelationshipClassEntityTableMetaBuilder config(RelationshipClassEntityTableMetaBuilderConfig config) {
        if(config == null){
            this.config = new RelationshipClassEntityTableMetaBuilderConfig() {
                @Override
                public TableClassIndexer<TableMeta> tableClassIndexer() {
                    return SimpleTableMetaManager.INSTANCE;
                }

                @Override
                public ColumnCollectionIndexer<ColumnMeta> columnCollectionIndexer(Class<?> tableClass) {
                    return tableClassIndexer().table(tableClass)
                            .map(t -> new SimpleColumnMetaHolder(t))
                            .orElseThrow(NoEntityDefFoundError::new);
                }
            };
        }else{
            this.config = config;
        }
        return this;
    }

    @Override
    public RelationshipEntityTableMeta build(RelationshipClass aClass) {
        RelationshipEntityTableMeta meta = new RelationshipEntityTableMeta();
        meta.setEntity(aClass);
        buildTable(meta)
                .buildColumn(meta);
        return meta;
    }

    private RelationshipClassEntityTableMetaBuilder buildTable(RelationshipEntityTableMeta meta){
        String leftName = config.tableClassIndexer().table(meta.getEntity().getLeftClass())
                .map(BaseMeta::getName)
                .orElse("");
        String rightName = config.tableClassIndexer().table(meta.getEntity().getLeftClass())
                .map(BaseMeta::getName)
                .orElse("");

        String name = leftName + "_" + rightName;
        name = toDbName(name);
        logger.debug("Table name is {}", name);
        meta.setName(name);
        return this;
    }

    private RelationshipClassEntityTableMetaBuilder buildColumn(RelationshipEntityTableMeta meta){
        //simple support left entity id and right entity id
        List<MethodEntityColumnMeta> list = Stream.concat(
                buildColumnKey(meta.getEntity().getLeftClass()),
                buildColumnKey(meta.getEntity().getRightClass())
        )
                .filter(c -> c instanceof PropertyDescriptorEntityColumnMeta)
                .map(c -> (PropertyDescriptorEntityColumnMeta)c)
                .flatMap(RelationshipClassEntityTableMetaBuilder::flatPropertyDescriptorToMethodEntityColumnMeta)
                .collect(Collectors.toList());

        Map<String,MethodEntityColumnMeta> nameEntityColumnMetaMap = list
                .stream()
                .collect(Collectors.toMap(MethodEntityColumnMeta::getName, c -> c));
        meta.setNameEntityColumnMetaMap(nameEntityColumnMetaMap);

        Map<Method,MethodEntityColumnMeta> methodEntityColumnMetaMap = list
                .stream()
                .collect(Collectors.toMap(EntityColumnMeta::getEntity, c -> c));
        meta.setMethodEntityColumnMetaMap(methodEntityColumnMetaMap);

        return this;
    }

    /**
     * Get Column Key form entity class
     * @param entityClass entity class
     * @return Key Column
     */
    private Stream<EntityColumnMeta> buildColumnKey(Class entityClass){
        //get id columns form entity class
        //PropertyDescriptorEntityColumnMeta and
        return config.columnCollectionIndexer(entityClass)
                .column()
                .stream()
                .filter(o -> o instanceof EntityColumnMeta)
                .map(o -> (EntityColumnMeta)o)
                // Only EntityColumnMeta type can test whether the primary key
                .filter(EntityColumnMeta::isPrimary);
    }

    /**
     * PropertyDescriptorEntityColumnMeta change to MethodEntityColumnMeta
     * PropertyDescriptor contain Getter and Setter Method
     * @param propertyDescriptorEntityColumnMeta PropertyDescriptorEntityColumnMeta
     * @return MethodEntityColumnMeta Stream
     */
    public static Stream<MethodEntityColumnMeta> flatPropertyDescriptorToMethodEntityColumnMeta(
            PropertyDescriptorEntityColumnMeta propertyDescriptorEntityColumnMeta){
        MethodEntityColumnMeta getterMethodEntityColumnMeta = new MethodEntityColumnMeta();
        MethodEntityColumnMeta setterMethodEntityColumnMeta = new MethodEntityColumnMeta();

        BeanCopier copier = BeanCopier.create(EntityColumnMeta.class,MethodEntityColumnMeta.class,false);
        copier.copy(propertyDescriptorEntityColumnMeta,getterMethodEntityColumnMeta,null);
        copier.copy(propertyDescriptorEntityColumnMeta,setterMethodEntityColumnMeta,null);

        getterMethodEntityColumnMeta.setEntity(propertyDescriptorEntityColumnMeta.getEntity().getReadMethod());
        setterMethodEntityColumnMeta.setEntity(propertyDescriptorEntityColumnMeta.getEntity().getWriteMethod());

        return Stream.of(getterMethodEntityColumnMeta,setterMethodEntityColumnMeta);
    }

    public static String toDbName(String name) {
        return uncapitalize(toSnakeCase(banishGetterSetters(name)));
    }

    private static String banishGetterSetters(String name) {
        return name.replaceAll("^(get|set|is)", "");
    }

    public static String uncapitalize(String s) {
        return Character.toLowerCase(s.charAt(0)) + s.substring(1);
    }

    public static String toSnakeCase(String s) {
        return s.replaceAll("([a-z])([A-Z])","$1_$2").toLowerCase();
    }



    public static interface RelationshipClassEntityTableMetaBuilderConfig {
        TableClassIndexer<TableMeta> tableClassIndexer();

        ColumnCollectionIndexer<ColumnMeta> columnCollectionIndexer(Class<?> tableClass);
    }
}
