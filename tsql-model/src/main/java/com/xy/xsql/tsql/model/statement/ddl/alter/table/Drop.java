package com.xy.xsql.tsql.model.statement.ddl.alter.table;

import java.util.List;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/9/17
 */
public class Drop implements Item {

    private List<DropItem> items;

    public List<DropItem> getItems() {
        return items;
    }

    public void setItems(List<DropItem> items) {
        this.items = items;
    }



    public interface DropItem {}

    public static class DropConstraint implements DropItem {

        private boolean useConstraint;
        private boolean useIfExists;
        private List<Map.Entry<String,List<DropClusteredConstraintOption>>> constraintNameWithOptionList;

        public boolean isUseConstraint() {
            return useConstraint;
        }

        public void setUseConstraint(boolean useConstraint) {
            this.useConstraint = useConstraint;
        }

        public boolean isUseIfExists() {
            return useIfExists;
        }

        public void setUseIfExists(boolean useIfExists) {
            this.useIfExists = useIfExists;
        }

        public List<Map.Entry<String,List<DropClusteredConstraintOption>>> getConstraintNameWithOptionList() {
            return constraintNameWithOptionList;
        }

        public void setConstraintNameWithOptionList(List<Map.Entry<String,List<DropClusteredConstraintOption>>> constraintNameWithOptionList) {
            this.constraintNameWithOptionList = constraintNameWithOptionList;
        }
    }

    public static class DropColumn implements DropItem {

        private boolean useIfExists;
        private List<String> columnNames;

        public boolean isUseIfExists() {
            return useIfExists;
        }

        public void setUseIfExists(boolean useIfExists) {
            this.useIfExists = useIfExists;
        }

        public List<String> getColumnNames() {
            return columnNames;
        }

        public void setColumnNames(List<String> columnNames) {
            this.columnNames = columnNames;
        }
    }

    public static class PeriodSystemTime implements DropItem {}

}
