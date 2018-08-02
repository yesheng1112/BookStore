package com.ys.bookstore.dao;

import com.ys.bookstore.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/*
* 封装对数据库的基本操作
* 以后所有表的增删查改基本操作都是调用这里面的方法
* */
public class BaseDao<T> {

    /*
    * 数据库操作的工具类dbutils提供
    * */
    private QueryRunner runner = new QueryRunner();

    /*
    * 泛型的类型
    * */
    private Class<T> type;

    /*
    * @param sql 批处理要执行的sql语句
    * @param params 第一维：sql语句需要执行的次数，第二维，每次sql执行时需要的参数
    * */
    public void batchUpdate(String sql ,Object[][] params){
        Connection conn = JDBCUtils.getConn();
        try {
            runner.batch(conn,sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            //JDBCUtils.releaseConn(conn);
        }
    }

    /*
    * BaseDao 是提供给其他的具体的dao继承的，不会直接创建它的对象
    * UserDao extends BaseDao<User>
    * BaseDao 构造器以后只会被子类对象调用
    * */
    public BaseDao(){
        //this代表子类对象
        //获取子类的类型UserDao
        Class<? extends BaseDao> cla = this.getClass();
        //获取父类的类型 获取带参数的父类的类型 BaseDao<User>
        ParameterizedType pt = (ParameterizedType) cla.getGenericSuperclass();
        //获取类的泛型列表
        Type [] types = pt.getActualTypeArguments();
        //第一个位置就是需要的泛型
        type = (Class<T>) types[0];
    }

    /*
    * 对数据库表增删改操作
    * */
    public int update(String sql ,Object...params){
        Connection conn = JDBCUtils.getConn();
        int update = 0;
        try {
            //返回影响了几条数据
            update = runner.update(conn,sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            //JDBCUtils.releaseConn(conn);
        }
        return update;
    }

    /*
    * 查询一条记录并封装为对象的方法
    * */
    public T getBean(String sql,Object...params){
        Connection conn = JDBCUtils.getConn();
        T t = null;
        try {
            t = runner.query(conn,sql,new BeanHandler<>(type),params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            //关闭资源
            //JDBCUtils.releaseConn(conn);
        }
        return t;
    }

    /*
    * 查询多条记录并封装为对象集合的方法
    * */
    public List<T> getBeanList(String sql,Object...params){
        List<T> list = null;
        Connection conn = JDBCUtils.getConn();
        try {
            list = runner.query(conn,sql, new BeanListHandler<>(type),params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            //关闭资源
            //JDBCUtils.releaseConn(conn);
        }
        return list;
    }

    /*
    * 查询记录总条数方法
    * */
    public long getCount(String sql,Object...params){
        Connection conn = JDBCUtils.getConn();
        long query = 0;
        //ScalarHandler:默认将查询到的数据的第一行第一列的数据封装为对象返回
        try {
            query = (long) runner.query(conn,sql,new ScalarHandler(),params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            //关闭资源
            //JDBCUtils.releaseConn(conn);
        }
        return query;
    }
}
