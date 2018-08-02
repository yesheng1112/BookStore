package com.ys.bookstore;

import com.ys.bookstore.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

public class TestJDBCUtils {

    @Test
    public void test(){
        Connection conn = JDBCUtils.getConn();
        System.out.println(conn);
        JDBCUtils.releaseConn(conn);
    }
}
