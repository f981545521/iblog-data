package cn.acyou.iblogdata.generator;

import com.google.common.base.CaseFormat;
import org.springframework.util.StringUtils;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 *  使用MySQL，根据数据库表名，生成实体类
 * @author youfang
 * @version [1.0.0, 2018-06-11 下午 02:52]
 **/
public class Generator4Oracle {

    private static final String DRIVER = "oracle.jdbc.OracleDriver";
    private static final String USER = "sfgz";
    private static final String PWD = "sfgz";

    private static final String URL = "jdbc:oracle:thin:@192.168.1.188:1521:orcl";
    private static final String TABLE_NAME = "SFGZ_MBGL_TEMPLATE_CONTENT";

    private static Connection connection = null;

    public static void main(String[] args) {
        generateMapper(TABLE_NAME);
    }


    private static void generateMapper(String tableName) {
        connection = getConnections();
        String sql = "select wm_concat(column_name) from user_tab_columns where table_name = '" + tableName + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                String result = resultSet.getString(1);
                String[] resultArray = result.split(",");
                for (String s: resultArray){
                    System.out.println(s);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    // 创建数据库连接
    private static Connection getConnections() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PWD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}
