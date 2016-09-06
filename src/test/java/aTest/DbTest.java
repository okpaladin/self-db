package aTest;

import com.puhui.bi.selfDb.common.InitDataSource;
import com.puhui.bi.selfDb.model.Record;
import com.puhui.bi.selfDb.model.RecordBuilder;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Puhui on 2016/9/6.
 */
public class DbTest {

    @Test
    public void test1() throws SQLException{

        InitDataSource ids = new InitDataSource("classpath*:config.properties") ;

        Connection conn = ids.getDefaultConnection() ;

        String sql = "select * from bi_fore_cast";

        ResultSet rs = conn.prepareStatement(sql).executeQuery();

        List<Record>  records = RecordBuilder.build(rs);

        for(Record rd : records) {
            System.out.println(rd.getColumns().toString());
        }

    }



}
