package cn.acyou.iblogdata.generator;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;

import java.sql.*;
import java.util.List;
import java.util.Properties;

/**
 * 使用MySQL，根据数据库表名，生成实体类
 *
 * @author youfang
 * @version [1.0.0, 2018-06-11 下午 02:52]
 **/
public class Generator4Oracle {

    private static final String DRIVER = "oracle.jdbc.OracleDriver";
    private static final String USER = "sfgz";
    private static final String PWD = "sfgz";

    private static final String URL = "jdbc:oracle:thin:@192.168.1.188:1521:orcl";
    private static final String[] TABLE_NAME = ("SFGZ_YWBL_DOSSIER_REMIND").split(",");


    private static final List<String> IGNORE_FILED = Lists.newArrayList("REMARK", "CREATE_TIME", "CREATOR", "CREATOR_NAME", "MODIFY_TIME", "MODIFIOR", "MODIFY_NAME",
            "RECORD_SORT", "IS_USE", "IS_DEL");

    private static Connection connection = null;

    public static void main(String[] args) throws Exception {
        for (String tableName : TABLE_NAME) {
            generateMapper(tableName);
        }
    }


    private static void generateMapper(String tableName) throws Exception {
        connection = getConnections();
        DatabaseMetaData dbmd = connection.getMetaData();
        ResultSet rs1 = dbmd.getColumns(null, "%", tableName, "%");

        try {
            while (rs1.next()) {
                String typeName = rs1.getString("TYPE_NAME");
                String name = rs1.getString("COLUMN_NAME");
                String remark = rs1.getString("REMARKS");
                if (IGNORE_FILED.contains(name)){
                    continue;
                }

                System.out.println("@Comment(\"" + remark + "\")");
                System.out.println("@Column(name = \"" + name + "\")");
                System.out.println("private " + getJavaType(typeName) + " " + convertcamelCase(name) + ";");
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
            Properties props = new Properties();
            props.put("user", USER);
            props.put("password", PWD);
            //Oracle 获取表中的注释
            props.put("remarksReporting", "true");
            connection = DriverManager.getConnection(URL, props);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static String getJavaType(String oracleType) {
        if ("VARCHAR2".equalsIgnoreCase(oracleType) || "CLOB".equalsIgnoreCase(oracleType)) {
            return "String";
        }
        if ("NUMBER".equalsIgnoreCase(oracleType)) {
            return "Integer";
        }
        if ("DATE".equalsIgnoreCase(oracleType)) {
            return "Date";
        }
        return "+++++++++++++++++未知类型：" + oracleType;
    }

    public static String convertCamelCase(String filedName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, filedName.toLowerCase());
    }

    public static String convertcamelCase(String filedName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, filedName);
    }

}
