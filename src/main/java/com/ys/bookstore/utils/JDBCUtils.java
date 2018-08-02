package com.ys.bookstore.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JDBCUtils {

    //数据库连接池对象
    private static DataSource source = new ComboPooledDataSource("webDataSource");
    //将数据库连接设置为单例模式【不能使用】
    //private static Connection conn;
    //我们可以为每一个线程分配一个数据库连接HashMap 是线程不安全的
    //使用线程安全的Map：ConcurrentHashMap
    private static Map<Thread,Connection> map = new ConcurrentHashMap<>();
    //ThreadLocal:此类中维护了一个静态的线程安全的map，默认的以当前线程为key，我们可以使用ThreadLocal绑定一个对象
    private static ThreadLocal<Connection> local = new ThreadLocal<>();

    /*
    * 从ThreadLocal中获取数据库连接
    * */
    public static Connection getConnection(){
        //1.获取Connection
        Connection conn = local.get();
        //2.判断
        if (conn == null){
            //第一次获取conn
            //创建connection
            try {
                conn = source.getConnection();
                //将数据库连接和当前线程绑定
                local.set(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    public static void closeConnection(){
        //获取当前线程的连接
        Connection conn = local.get();
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //移出当前线程绑定的数据库连接
            local.remove();
        }
    }

    /*
    * 获取数据库连接
    * */
    public static Connection getConn(){
        /*
         * Connection conn = null; try { conn = source.getConnection(); } catch
         * (SQLException e) { e.printStackTrace(); }
         */
        /*
         * if(conn==null){ try { conn = source.getConnection(); } catch
         * (SQLException e) { e.printStackTrace(); } }
         */
        //使用当前线程对象为键从map中获取数据库连接
        Connection conn = map.get(Thread.currentThread());
        //判断是否为空
        if (conn == null){
            //当前线程没有绑定数据库连接
            try {
                //获取连接
                conn = source.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //将当前线程和数据库连接绑定
            map.put(Thread.currentThread(),conn);
        }
        return conn;
    }

    /*
    * 释放当前线程绑定的数据库连接方法
    * */
    public static void closeConn(){
        //获取连接
        Connection conn = map.get(Thread.currentThread());
        //关闭连接
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //清理map中存的当前线程数据
        map.remove(Thread.currentThread());
    }

    /*
    * 释放数据库连接的方法
    * */
    public static void releaseConn(Connection conn){
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
