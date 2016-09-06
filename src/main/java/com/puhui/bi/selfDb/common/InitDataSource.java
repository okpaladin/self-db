package com.puhui.bi.selfDb.common;


import com.alibaba.druid.pool.DruidDataSource;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * 功能描述：获取数据源连接
 * User: NanJiang
 * Date: 2016/9/2
 */
public class InitDataSource {

    final protected Logger log = Logger.getLogger(this.getClass());
    private static Properties p = new Properties();

    private DataSource ds;
    private Connection conn;
    private DruidDataSource  dds;
    /**
     * 禁用无参构造
     */
    public InitDataSource() {
        throw new RuntimeException("需要传入一个dataSource!");
    }

    /**
     * 有参构造
     * @param fileConfig the file config
     */
    public InitDataSource(String fileConfig) {

        initProperties(fileConfig);
        ds = initSource();
    }

    /**
     * 初始化数据源（DruidDataSource）
     */
    private DruidDataSource initSource() {
        DruidDataSource dds = new DruidDataSource() ;

        dds.setUrl(p.getProperty("dbSelf.bi.jdbc.url"));
        dds.setUsername(p.getProperty("dbSelf.bi.jdbc.user"));
        dds.setPassword(p.getProperty("dbSelf.bi.jdbc.password"));
        dds.setInitialSize(5);
        dds.setMinIdle(5);
        dds.setMaxActive(200);
        dds.setMaxWait(60000);

        this.dds = dds;
        return dds;
    }

    /**
     * 关闭数据源（DruidDataSource）
     */
    private void close()  {
        dds.close();
    }

    /**
     * 单例获取数据库连接
     * @return the default connection
     */
    public Connection getDefaultConnection() {
        try {
            if(conn==null)
                return ds.getConnection();
        } catch (SQLException e) {
            log.error("数据源初始化错误：", e);
        }
        return conn;
    }

    /**
     * 根据配置文件初始化Properties
     */
    private void initProperties(String fileConfig) {
        String[] fileConfigs = fileConfig.split(",");
        try {
            for(String file:fileConfigs) {
                if(file.startsWith("classpath*:")) {
                    p.load(InitDataSource.class.getClassLoader().getResourceAsStream(file.substring(file.indexOf("classpath*:"))));
                } else if(file.startsWith("file:")) {
                    InputStream in = new FileInputStream(file);
                    p.load(in);
                }
            }
        } catch (IOException e) {
            log.error("初始化配置文件错误:",e);
        }
    }

    /**
     * Gets ds.
     *
     * @return the ds
     */
    public DataSource getDs() {
        return ds;
    }

    /**
     * Sets ds.
     *
     * @param ds the ds
     */
    public void setDs(DataSource ds) {
        this.ds = ds;
    }
}
