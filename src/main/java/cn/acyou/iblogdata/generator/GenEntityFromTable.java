package cn.acyou.iblogdata.generator;

import com.google.common.base.CaseFormat;

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
public class GenEntityFromTable {

    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String pwd = "root123";
    private static final String user = "root";
    /**
     * 正常情况下读取表注释时，是取不出来的。
     * 需要增加useInformationSchema=true配置
     */
    private static final String url = "jdbc:mysql://localhost:3306/admui?useInformationSchema=true&useUnicode=true&characterEncoding=UTF-8";
    private static final String TABLE_NAME = "t_content_prop_info";// 表名
    private static final String PACKAGE = "com.muran.ifree.domain.prop";//你的实体类所在的包的位置

    private static Connection connection = null;

    public static void main(String[] args) throws Exception{
        //generateEntity();
        generateMapper(TABLE_NAME);
    }


    private static void generateMapper(String tableName) {
        connection = getConnections();//获取连接
        String sql = "select GROUP_CONCAT(COLUMN_NAME SEPARATOR ', ')  as cool from INFORMATION_SCHEMA.Columns where table_name='" + tableName + "'";//SQL语句
        try {
            Statement statement = connection.createStatement();//创建Statement
            ResultSet resultSet = statement.executeQuery(sql);//执行SQL获取ResultSet
            while (resultSet.next()){//遍历ResultSet获取结果
                String result = resultSet.getString("cool");
                System.out.println("    <sql id=\"Base_Column_List\">");
                System.out.println("        " + result);
                System.out.println("    </sql>");

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

    private static void generateEntity(){
        FileSystemView fsv = FileSystemView.getFileSystemView();
        String path = fsv.getHomeDirectory().toString();//获取当前用户桌面路径
        connection = getConnections();
        try {
            DatabaseMetaData dbmd = connection.getMetaData();
            ResultSet resultSet = dbmd.getTables(null, "%", "%", new String[]{"TABLE"});
            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                //System.out.println(tableName);
                if (TABLE_NAME.equals(tableName)) {//这里干掉IF可对库里面所有表直接生成
                    ResultSet rs1 = dbmd.getColumns(null, "%", tableName, "%");
                    ResultSet rs2 = dbmd.getColumns(null, "%", tableName, "%");
                    //获取表信息
                    String tableRemark = "";
                    ResultSet rsTableInfo = dbmd.getTables(null, null, TABLE_NAME, new String[]{"TABLE"});
                    while(rsTableInfo.next()){
                        String tableCat = rsTableInfo.getString("TABLE_CAT");  //表类别(可为null)
                        String tableSchemaName = rsTableInfo.getString("TABLE_SCHEM");//表模式（可能为空）,在oracle中获取的是命名空间,其它数据库未知
                        String tableName2 = rsTableInfo.getString("TABLE_NAME");  //表名
                        String tableType = rsTableInfo.getString("TABLE_TYPE");  //表类型,典型的类型是 "TABLE"、"VIEW"、"SYSTEM TABLE"、"GLOBAL TEMPORARY"、"LOCAL TEMPORARY"、"ALIAS" 和 "SYNONYM"。
                        String remarks = rsTableInfo.getString("REMARKS");       //表备注
                        System.out.println(tableCat + " - " + tableSchemaName + " - " +tableName2 + " - " + tableType + " - " + remarks);
                        tableRemark = remarks;
                    }

                    File directory = new File(path + "\\" + convertCamelCase(TABLE_NAME) + ".java");
                    FileWriter fw = new FileWriter(directory);
                    PrintWriter pw = new PrintWriter(fw);
                    pw.write("package " + PACKAGE + ";\r\n");
                    pw.write("\r\n");
                    pw.write("import java.io.Serializable;\r\n");
                    pw.write("import java.util.Date;\r\n");
                    pw.write("\r\n");
                    pw.write("/**\r\n");
                    pw.write(" * " + TABLE_NAME + " 实体类\r\n");
                    pw.write(" * " + getDate() + " " + tableRemark + "\r\n");
                    pw.write(" */ \r\n");
                    pw.write("public class " + convertCamelCase(TABLE_NAME) + " implements Serializable{\r\n");
                    pw.write("\r\n    private static final long serialVersionUID = "+ String.valueOf(new Random().nextLong()) +"L;\r\n");
                    System.out.println();
                    System.out.println(TABLE_NAME + "表信息：");
                    System.out.println();
                    while (rs1.next()) {
                        //System.out.println("private " + sqlType2JavaType(rs1.getString("TYPE_NAME")) + "	" + rs1.getString("COLUMN_NAME") + ";");
                        if (directory.exists()) {
                        } else {
                            directory.createNewFile();
                        }
                        String typeName = rs1.getString("TYPE_NAME");
                        String type = sqlType2JavaType(typeName);
                        String name = rs1.getString("COLUMN_NAME");
                        String remark = rs1.getString("REMARKS");
                        System.out.println("<id column=\"" + name + "\" jdbcType=\"" + typeName.toUpperCase() + "\" property=\"" + convertcamelCase(name) + "\"/>");
                        createPrtype(pw, type, name, remark);
                    }
                    //提供Get和Set方法
                    pw.write("\r\n");
                    while (rs2.next()) {
                        String name = rs2.getString("COLUMN_NAME");
                        String type = rs2.getString("TYPE_NAME");
                        createMethod(pw, type, name);
                    }
                    pw.write("}\r\n");

                    pw.flush();
                    pw.close();
                    System.out.println("=====操作结果=====");
                    System.out.println("生成成功！文件在你的桌面。");
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

    private static String convertCamelCase(String filedName){
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, filedName.toLowerCase());
    }

    private static String convertcamelCase(String filedName){
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, filedName);
    }


    /**
     * 生成属性
     */
    private static void createPrtype(PrintWriter pw, String type, String name, String remark) {
        if (remark != null && !"".equals(remark)) {
            pw.write("\t/**\r\n");
            pw.write("\t * " + remark + "\r\n");
            pw.write("\t */\r\n");
        } else {
            pw.write("\t//" + name + "\r\n");
        }
        pw.write("    @Column(name = \"" + name + "\")\r\n");
        pw.write("    private " + type + "	" + convertcamelCase(name) + ";\r\n");
    }

    /**
     * 生成方法
     */
    private static void createMethod(PrintWriter pw, String type, String name) {
        pw.write("    public void set" + convertCamelCase(name) + "(" + sqlType2JavaType(type) + " " + convertcamelCase(name) + "){\r\n");
        pw.write("        this." + convertcamelCase(name) + " = " + convertcamelCase(name) + ";\r\n");
        pw.write("    }\r\n");
        pw.write("    public " + sqlType2JavaType(type) + " get" + convertCamelCase(name) + "(){\r\n");
        pw.write("        return " + convertcamelCase(name) + ";\r\n");
        pw.write("    }\r\n");
        pw.write("\r\n");
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

    // 判断属性类型
    private static String sqlType2JavaType(String sqlType) {
        String str = null;
        if (sqlType.equalsIgnoreCase("bit")) {
            str = "Boolean";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            str = "byte";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            str = "short";
        } else if (sqlType.equalsIgnoreCase("int")) {
            str = "Integer";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            str = "Long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            str = "float";
        } else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
                || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")
                || sqlType.equalsIgnoreCase("smallmoney")) {
            str = "Double";
        } else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
                || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
                || sqlType.equalsIgnoreCase("text")) {
            str = "String";
        } else if (sqlType.equalsIgnoreCase("datetime") || sqlType.equalsIgnoreCase("timestamp")) {
            str = "Date";
        } else if (sqlType.equalsIgnoreCase("image")) {
            str = "Blod";
        }
        return str;
    }

    // 获取格式化后的时间
    private static String getDate() {
        String time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        time = sdf.format(new Date());
        return time;
    }
}