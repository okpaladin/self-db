package com.puhui.bi.selfDb.model;


import java.io.Serializable;
import java.util.*;

/**
 * 功能描述：
 * User: NanJiang
 * Date: 2016/9/5
 */
public class Record implements Serializable {
    private static final long serialVersionUID = 427684513600884082L;

    private  Map<String, Object> columns;


    void setColumnsMap(Map<String, Object> mapColumns) {
        this.columns = mapColumns;
    }

    public Map<String, Object> getColumns() {

        if(columns==null||columns.isEmpty())
            this.columns =  new LinkedHashMap<String, Object>();

        return this.columns;
    }

    public Record remove(String column) {
        this.getColumns().remove(column);
        return this;
    }

    public Record remove(String... columns) {
        if(columns != null) {
            String[] arr$ = columns;
            int len$ = columns.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                String c = arr$[i$];
                this.getColumns().remove(c);
            }
        }

        return this;
    }

    public Record removeNullValueColumns() {
        Iterator it = this.getColumns().entrySet().iterator();

        while(it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
            if(e.getValue() == null) {
                it.remove();
            }
        }

        return this;
    }

    public Record clear() {
        this.getColumns().clear();
        return this;
    }

    public Record set(String column, Object value) {
        this.getColumns().put(column, value);
        return this;
    }

    public Object get(String column) {
        return  this.getColumns().get(column);
    }

    public Object get(String column, Object defaultValue) {
        Object result = this.getColumns().get(column);
        return result != null?result:defaultValue;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(" {");
        boolean first = true;

        Map.Entry e;
        Object value;
        for(Iterator i$ = this.getColumns().entrySet().iterator(); i$.hasNext(); sb.append((String)e.getKey()).append(":").append(value)) {
            e = (Map.Entry)i$.next();
            if(first) {
                first = false;
            } else {
                sb.append(", ");
            }

            value = e.getValue();
            if(value != null) {
                value = value.toString();
            }
        }

        sb.append("}");
        return sb.toString();
    }


}
