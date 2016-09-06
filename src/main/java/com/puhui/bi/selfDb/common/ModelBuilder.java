package com.puhui.bi.selfDb.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;

/**
 * 功能描述：
 * User: NanJiang
 * Date: 2016/9/5
 */
public class ModelBuilder {

    public static byte[] handleBlob(Blob blob) throws SQLException {
        if(blob == null) {
            return null;
        } else {
            InputStream is = null;

            byte[] e1;
            try {
                is = blob.getBinaryStream();
                if(is == null) {
                    Object e2 = null;
                    return (byte[])e2;
                }

                byte[] e = new byte[(int)blob.length()];
                if(e.length == 0) {
                    Object e3 = null;
                    return (byte[])e3;
                }

                is.read(e);
                e1 = e;
            } catch (IOException var14) {
                throw new RuntimeException(var14);
            } finally {
                if(is != null) {
                    try {
                        is.close();
                    } catch (IOException var13) {
                        throw new RuntimeException(var13);
                    }
                }
            }

            return e1;
        }
    }

    public static String handleClob(Clob clob) throws SQLException {
        if(clob == null) {
            return null;
        } else {
            Reader reader = null;

            Object e;
            try {
                reader = clob.getCharacterStream();
                if(reader != null) {
                    char[] e2 = new char[(int)clob.length()];
                    String e1;
                    if(e2.length == 0) {
                        e1 = null;
                        return e1;
                    }

                    reader.read(e2);
                    e1 = new String(e2);
                    return e1;
                }

                e = null;
            } catch (IOException var14) {
                throw new RuntimeException(var14);
            } finally {
                if(reader != null) {
                    try {
                        reader.close();
                    } catch (IOException var13) {
                        throw new RuntimeException(var13);
                    }
                }

            }

            return (String)e;
        }
    }
}
