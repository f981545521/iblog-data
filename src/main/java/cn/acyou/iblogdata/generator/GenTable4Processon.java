package cn.acyou.iblogdata.generator;

import org.apache.commons.lang3.StringUtils;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author youfang
 * @version [1.0.0, 2018-12-14 上午 09:47]
 **/
public class GenTable4Processon {

    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String pwd = "root123";
    /**
     * 正常情况下读取表注释时，是取不出来的。
     * 需要增加useInformationSchema=true配置
     */
    private static final String DB_NAME = "er";// 数据库名
    private static final String FILE_NAME = DB_NAME + ".txt";

    private static final String url = "jdbc:mysql://localhost:3306/"+DB_NAME+"?useInformationSchema=true&useUnicode=true&characterEncoding=UTF-8";
    private static Connection connection = getConnections();
    private static List<String> tablesList = new ArrayList<>();
    public static void main(String[] args) {
        try {
            generateTableDesc();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void generateTableDesc() throws SQLException, IOException {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        String path = fsv.getHomeDirectory().toString();//获取当前用户桌面路径
        PrintWriter pw = null;
        if (StringUtils.isNotEmpty(FILE_NAME)){
            File directory = new File(path + "\\" + FILE_NAME);
            FileWriter fw = new FileWriter(directory);
            pw = new PrintWriter(fw);
        }
        /**
         * - getDatabaseProductName：获取数据库的产品名称
         * - getDatabaseProductName：获取数据库的版本号
         * - getUserName：获取数据库的用户名
         * - getURL：获取数据库连接的URL
         * - getDriverName：获取数据库的驱动名称
         * - driverVersion：获取数据库的驱动版本号
         * - isReadOnly：查看数据库是否只允许读操作
         * - supportsTransactions：查看数据库是否支持事务
         *
         */
        DatabaseMetaData dbmd = connection.getMetaData();
        ResultSet resultSet = dbmd.getTables(null, "%", "%", new String[]{"TABLE"});
        while (resultSet.next()) {
            String tableName = resultSet.getString("TABLE_NAME");

            ResultSet rs1 = dbmd.getColumns(null, "%", tableName, "%");
            ResultSet rs2 = dbmd.getColumns(null, "%", tableName, "%");
            //获取表信息
            String tableRemark = "";
            ResultSet rsTableInfo = dbmd.getTables(null, null, tableName, new String[]{"TABLE"});
            while(rsTableInfo.next()){
                tableRemark = rsTableInfo.getString("REMARKS");
            }
            System.out.println(tableName + " | " + tableRemark);
            if (pw != null){
                pw.write(tableName + " | " + tableRemark + "\n");
            }
            while (rs1.next()) {
                String name = rs1.getString("COLUMN_NAME");
                String remark = rs1.getString("REMARKS");
                System.out.println(name + ":" + remark);
                if (pw != null){
                    pw.write(name + ":" + remark + "\n");
                }
            }
            System.out.println("————————————————————————————————————————————————");
            if (pw != null){
                pw.write("————————————————————————————————————————————————" + "\n");
            }
        }
        System.out.println("——————————完成——————————");
        if (pw != null){
            pw.write("——————————完成——————————" + "\n");
            pw.flush();
            pw.close();
        }


    }

    private static void addTables() throws SQLException{
        DatabaseMetaData dbmd = connection.getMetaData();
        ResultSet rs = dbmd.getTables(connection.getCatalog(), "SCOTT", null, new String[]{"TABLE"});
        while(rs.next()) {
            tablesList.add(rs.getString("TABLE_NAME"));
        }
    }

    // 创建数据库连接
    private static Connection getConnections() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
