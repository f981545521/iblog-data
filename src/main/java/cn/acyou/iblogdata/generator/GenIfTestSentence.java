package cn.acyou.iblogdata.generator;

import java.sql.*;

/**
 * 生成Mapper 中的iftest 语句
 * @author youfang
 * @version [1.0.0, 2019-01-16 下午 04:55]
 **/
public class GenIfTestSentence {
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String pwd = "root123";
    /**
     * 正常情况下读取表注释时，是取不出来的。
     * 需要增加useInformationSchema=true配置
     */
    private static final String url = "jdbc:mysql://localhost:3306/er?useInformationSchema=true&useUnicode=true&characterEncoding=UTF-8";
    private static final String TABLE_NAME = "t_product_spu";// 表名

    private static Connection connection = null;
    public static void main(String[] args) {
        generateIfTestSentence();
    }

    private static void generateIfTestSentence() {
        connection = getConnections();
        try {
            DatabaseMetaData dbmd = connection.getMetaData();
            ResultSet resultSet = dbmd.getTables(null, "%", "%", new String[]{"TABLE"});
            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                if (TABLE_NAME.equals(tableName)) {
                    ResultSet rs1 = dbmd.getColumns(null, "%", tableName, "%");
                    //获取表信息
                    ResultSet rsTableInfo = dbmd.getTables(null, null, TABLE_NAME, new String[]{"TABLE"});
                    while(rsTableInfo.next()){
                        String tableCat = rsTableInfo.getString("TABLE_CAT");
                        String tableSchemaName = rsTableInfo.getString("TABLE_SCHEM");
                        String tableName2 = rsTableInfo.getString("TABLE_NAME");
                        String tableType = rsTableInfo.getString("TABLE_TYPE");
                        String remarks = rsTableInfo.getString("REMARKS");
                        System.out.println(tableCat + " - " + tableSchemaName + " - " +tableName2 + " - " + tableType + " - " + remarks);
                    }
                    while (rs1.next()) {
                        //列名称
                        String name = rs1.getString("COLUMN_NAME");
                        //Java canmel 命名
                        String javaName = GenerateUtil.convertcamelCase(name);
                        System.out.println("<if test=\""+javaName+"!=null\">"+name+" = #{"+javaName+"},</if>");

                    }
                    System.out.println("=====操作完成=====");
                }
            }
        } catch (Exception e) {
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
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
