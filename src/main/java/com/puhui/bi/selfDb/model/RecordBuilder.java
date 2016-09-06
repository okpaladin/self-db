package com.puhui.bi.selfDb.model;

import com.puhui.bi.selfDb.common.ModelBuilder;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 * 功能描述：
 * User: NanJiang
 * Date: 2016/9/5
 */
public class RecordBuilder {

    public RecordBuilder() {

    }

    public static final List<Record> build(ResultSet rs) throws SQLException{

        List<Record> result = new LinkedList<Record>();


        ResultSetMetaData  rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        String[] labelNames = new String[columnCount + 1];
        int[] types = new int[columnCount + 1];
        buildLabelNamesAndTypes(rsmd,labelNames,types);


        while(rs.next()) {
            Record record = new Record();
            Map columns = new LinkedHashMap<String, Object>();

            for(int i = 1; i <= columnCount; ++i) {
                Object value;
                if(types[i] < 2004) {
                    value = rs.getObject(i);
                } else if(types[i] == 2005) {
                    value = ModelBuilder.handleClob(rs.getClob(i));
                } else if(types[i] == 2011) {
                    value = ModelBuilder.handleClob(rs.getNClob(i));
                } else if(types[i] == 2004) {
                    value = ModelBuilder.handleBlob(rs.getBlob(i));
                } else {
                    value = rs.getObject(i);
                }
//                columns.put(labelNames[i], value);
                record.getColumns()
                        .put(labelNames[i], value);
//                System.out.printf(record.getColumns().toString());
            }
//            record.setColumnsMap(columns);

//            columns = record.getColumns();
            result.add(record);
        }

        return result;
    }

    private static final void buildLabelNamesAndTypes(ResultSetMetaData rsmd, String[] labelNames, int[] types) throws SQLException {
        for(int i = 1; i < labelNames.length; ++i) {
            labelNames[i] = rsmd.getColumnLabel(i);
            types[i] = rsmd.getColumnType(i);
        }
    }
}
