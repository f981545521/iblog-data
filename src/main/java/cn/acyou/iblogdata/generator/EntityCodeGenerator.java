package cn.acyou.iblogdata.generator;

import com.google.common.base.CaseFormat;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 使用MySQL，根据数据库表名，生成实体类
 *
 * @author youfang
 * @version [1.0.0, 2018-06-11 下午 02:52]
 **/
public class EntityCodeGenerator {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "confluence";
    private static final String PASSWORD = "confluence123";
    /**
     * 正常情况下读取表注释时，是取不出来的。
     * 需要增加useInformationSchema=true配置
     */
    private static final String URL = "jdbc:mysql://localhost:3306/ares_confluence?useInformationSchema=true&useUnicode=true&characterEncoding=UTF-8";
    /**
     * 表名
     */
    private static final String TABLE_NAME = "t_promotion_group_buying_order_info";
    /**
     * 你的实体类所在的包的位置
     */
    private static final String PACKAGE = "com.liesun.ares.promotion.entity.group";
    /**
     * 类名文件名(下划线也会自动转换)
     */
    private static final String CLASS_NAME = convertCamelCase("group_buying_order_info");
    private static final String AUTHOR = "youfang";

    private static Connection connection = null;
    private static final String MAPPER_PACKAGE = PACKAGE.replace("entity", "mapper");
    private static final String PROJECT_PATH = System.getProperty("user.dir");
    /** 生成文件路径  */
    private static final String ENTITY_FILE_PATH = PROJECT_PATH +
            "\\confluence-service\\src\\main\\java\\com\\liesun\\ares\\promotion\\entity\\group";
    private static final String MAPPER_INTERFACE_PATH = PROJECT_PATH +
            "\\confluence-service\\src\\main\\java\\com\\liesun\\ares\\promotion\\mapper\\group";
    private static final String MAPPER_XML_PATH = PROJECT_PATH +
            "\\confluence-service\\src\\main\\\\resources\\mapper\\promotion\\group";
    private static StringBuilder ALL_FILED = new StringBuilder();
    private static StringBuilder ALL_ALISA_FILED = new StringBuilder();
    private static String PK_NAME = "";


    public static void main(String[] args) {
        generateEntity(CLASS_NAME);
    }


    /**
     * 生成实体文件&Mapper文件
     * @param className 类名
     */
    private static void generateEntity(String className) {
        String path = ENTITY_FILE_PATH;
        connection = getConnections();
        try {
            DatabaseMetaData dbmd = connection.getMetaData();
            //获取主键
            ResultSet pkRs = dbmd.getPrimaryKeys(null, null, TABLE_NAME);
            while (pkRs.next()) {
                PK_NAME = (String) pkRs.getObject(4);
            }

            ResultSet resultSet = dbmd.getTables(null, "%", "%", new String[]{"TABLE"});
            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                //这里干掉IF可对库里面所有表直接生成
                if (TABLE_NAME.equals(tableName)) {
                    ResultSet rs1 = dbmd.getColumns(null, "%", tableName, "%");
                    ResultSet rs2 = dbmd.getColumns(null, "%", tableName, "%");
                    //获取表信息
                    String tableRemark = "";
                    ResultSet rsTableInfo = dbmd.getTables(null, null, TABLE_NAME, new String[]{"TABLE"});
                    while (rsTableInfo.next()) {
                        //表类别(可为null)
                        String tableCat = rsTableInfo.getString("TABLE_CAT");
                        //表模式（可能为空）,在oracle中获取的是命名空间,其它数据库未知
                        String tableSchemaName = rsTableInfo.getString("TABLE_SCHEM");
                        //表名
                        String tableName2 = rsTableInfo.getString("TABLE_NAME");
                        //表类型,典型的类型是 "TABLE"、"VIEW"、"SYSTEM TABLE"、"GLOBAL TEMPORARY"、"LOCAL TEMPORARY"、"ALIAS" 和 "SYNONYM"。
                        String tableType = rsTableInfo.getString("TABLE_TYPE");
                        //表备注
                        String remarks = rsTableInfo.getString("REMARKS");
                        System.out.println(tableCat + " - " + tableSchemaName + " - " + tableName2 + " - " + tableType + " - " + remarks);
                        tableRemark = remarks;
                    }
                    String fileName;//文件名
                    if (className!= null && !"".equals(className.trim())) {
                        fileName = className;
                    } else {
                        fileName = convertCamelCase(TABLE_NAME);
                    }
                    //Mapper文件
                    File mapperDirectory = new File(MAPPER_INTERFACE_PATH + "\\" + fileName + "Mapper" + ".java");
                    FileWriter mapperFw = new FileWriter(mapperDirectory);
                    PrintWriter mapperPw = new PrintWriter(mapperFw);
                    mapperPw.write("package " + MAPPER_PACKAGE + ";\r\n");
                    mapperPw.write("\r\n");
                    mapperPw.write("import com.liesun.ares.Mapper;\r\n");
                    mapperPw.write("import " + PACKAGE + "." + CLASS_NAME + ";\r\n");
                    mapperPw.write("\r\n");
                    mapperPw.write("import java.util.List;\r\n");
                    mapperPw.write("\r\n");
                    mapperPw.write("/**\r\n");
                    mapperPw.write(" * " + TABLE_NAME + " Mapper\r\n");
                    mapperPw.write(" * " + getDate() + " " + tableRemark + "\r\n");
                    mapperPw.write(" * @author "+AUTHOR+"\r\n");
                    mapperPw.write(" */ \r\n");
                    mapperPw.write("\r\n");
                    mapperPw.write("public interface " + CLASS_NAME + "Mapper" + " extends Mapper<" + CLASS_NAME + "> {\r\n");
                    mapperPw.write("\r\n");
                    mapperPw.write("    /**\r\n");
                    mapperPw.write("     * 批量更新\r\n");
                    mapperPw.write("     * @param list 修改记录\r\n");
                    mapperPw.write("     */\r\n");
                    mapperPw.write("    int updateListSelective(List<"+CLASS_NAME+"> list);\r\n");
                    mapperPw.write("\r\n");
                    mapperPw.write("\r\n");
                    mapperPw.write("}\r\n");
                    mapperPw.flush();
                    mapperPw.close();


                    //XML 文件
                    File xmlDirectory = new File(String.format("%s\\%sMapper.xml", MAPPER_XML_PATH, fileName));
                    FileWriter xmlFw = new FileWriter(xmlDirectory);
                    PrintWriter xmlPw = new PrintWriter(xmlFw);
                    xmlPw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
                    xmlPw.write("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\r\n");
                    xmlPw.write("<mapper namespace=\"" + MAPPER_PACKAGE + "." + fileName + "Mapper" + "\">\r\n");


                    //实体类
                    File javaDirectory = new File(String.format("%s\\%s.java", path, fileName));
                    FileWriter javaFw = new FileWriter(javaDirectory);
                    PrintWriter javaPw = new PrintWriter(javaFw);



                    javaPw.write("package " + PACKAGE + ";\r\n");
                    javaPw.write("\r\n");
                    javaPw.write("import javax.persistence.Id;\r\n");
                    javaPw.write("import javax.persistence.GeneratedValue;\r\n");
                    javaPw.write("import javax.persistence.Column;\r\n");
                    javaPw.write("import javax.persistence.Table;\r\n");
                    javaPw.write("import java.io.Serializable;\r\n");
                    javaPw.write("import java.util.Date;\r\n");
                    javaPw.write("\r\n");
                    javaPw.write("/**\r\n");
                    javaPw.write(" * " + TABLE_NAME + " 实体类\r\n");
                    javaPw.write(" * " + getDate() + " " + tableRemark + "\r\n");
                    javaPw.write(" * @author "+AUTHOR+"\r\n");
                    javaPw.write(" */ \r\n");
                    if (className!= null && !"".equals(className.trim())) {
                        className = convertCamelCase(TABLE_NAME);
                    }
                    javaPw.write("@Table(name = \"" + TABLE_NAME + "\")\r\n");
                    javaPw.write("public class " + className + " implements Serializable{\r\n");
                    javaPw.write("\r\n    private static final long serialVersionUID = " + new Random().nextLong() + "L;\r\n");
                    System.out.println();
                    System.out.println(TABLE_NAME + "表信息：");
                    System.out.println();
                    xmlPw.write("\r\n    <resultMap id=\"Base_Result_Map\" type=\"" + PACKAGE + "." + CLASS_NAME + "\">");
                    while (rs1.next()) {
                        if (javaDirectory.exists()) {
                        } else {
                            javaDirectory.createNewFile();
                        }
                        String typeName = rs1.getString("TYPE_NAME");
                        String type = sqlType2JavaType(typeName);
                        String name = rs1.getString("COLUMN_NAME");
                        if (ALL_FILED.length() <= 0) {
                            ALL_FILED.append(name);
                        } else {
                            ALL_FILED.append(",").append(name);
                        }
                        if (ALL_ALISA_FILED.length() <= 0) {
                            ALL_ALISA_FILED.append("t.").append(name);
                        } else {
                            ALL_ALISA_FILED.append(", t.").append(name);
                        }
                        String remark = rs1.getString("REMARKS");
                        String result = "result";
                        if (remark.contains("主键")) {
                            result = "id";
                        }
                        xmlPw.write("\r\n        <" + result + " column=\"" + name + "\" jdbcType=\"" + typeName2JDBCType(typeName) + "\" property=\"" + convertcamelCase(name) + "\"/>");
                        createPrtype(javaPw, type, name, remark);
                    }
                    xmlPw.write("\r\n    </resultMap>");
                    //提供Get和Set方法
                    javaPw.write("\r\n");
                    while (rs2.next()) {
                        String name = rs2.getString("COLUMN_NAME");
                        String type = rs2.getString("TYPE_NAME");
                        createMethod(javaPw, type, name);
                    }
                    javaPw.write("}\r\n");
                    xmlPw.write("\r\n");
                    //获取所有字段
                    xmlPw.write("\r\n    <sql id=\"Base_Column_List\">");
                    xmlPw.write("\r\n        " + ALL_FILED.toString());
                    xmlPw.write("\r\n    </sql>");
                    xmlPw.write("\r\n");
                    //获取所有字段(别名)
                    xmlPw.write("\r\n    <sql id=\"Alisa_Column_List\">");
                    xmlPw.write("\r\n        " + ALL_ALISA_FILED.toString());
                    xmlPw.write("\r\n    </sql>");
                    xmlPw.write("\r\n");
                    xmlPw.write("\r\n");
                    xmlPw.write("    <update id=\"updateListSelective\">\r\n");
                    xmlPw.write("        <foreach collection=\"list\" item=\"it\">\r\n");
                    xmlPw.write("           update "+TABLE_NAME+"\r\n");
                    xmlPw.write("           <set>\r\n");
                    StringBuilder sb = generateIfTestSentence("it.");
                    xmlPw.write(sb.toString());
                    xmlPw.write("           </set>\r\n");
                    xmlPw.write("           WHERE id = #{it.id};\r\n");
                    xmlPw.write("        </foreach>\r\n");
                    xmlPw.write("    </update>\r\n");
                    xmlPw.write("\r\n");
                    xmlPw.write("\r\n");
                    xmlPw.write("</mapper>");
                    javaPw.flush();
                    javaPw.close();
                    xmlPw.flush();
                    xmlPw.close();

                    System.out.println("=====生成成功！=====");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 下划线 转 大写类名
     * @param filedName 字段名
     * @return 大写类名
     */
    private static String convertCamelCase(String filedName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, filedName.toLowerCase());
    }
    /**
     * 下划线 转 小写方法名
     * @param filedName 字段名
     * @return 小写方法名
     */
    private static String convertcamelCase(String filedName) {
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
        if (!PK_NAME.equals("") && name.equals(PK_NAME)){
            pw.write("    @Id\r\n");
            pw.write("    @GeneratedValue(generator = \"JDBC\")\r\n");
        }
        pw.write("    @Column(name = \"" + name + "\")\r\n");
        //if ("Date".equals(type)) {
        //    pw.write("    @JsonFormat(pattern = \"yyyy-MM-dd HH:mm:ss\", timezone = \"GMT+8\")\r\n");
        //}
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
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
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
        } else if (sqlType.equalsIgnoreCase("numeric")
                || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")
                || sqlType.equalsIgnoreCase("smallmoney")|| sqlType.equalsIgnoreCase("double")) {
            str = "Double";
        } else if(sqlType.equalsIgnoreCase("decimal") ){
            str = "BigDecimal";
        } else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
                || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
                || sqlType.equalsIgnoreCase("text")  || sqlType.equalsIgnoreCase("longtext")) {
            str = "String";
        } else if (sqlType.equalsIgnoreCase("date") || sqlType.equalsIgnoreCase("datetime") || sqlType.equalsIgnoreCase("timestamp")) {
            str = "Date";
        } else if (sqlType.equalsIgnoreCase("image")) {
            str = "Blod";
        }
        return str;
    }

    /**
     * MySQL type -> mybatis jdbc type
     * https://blog.csdn.net/benben683280/article/details/78798901
     *
     * @param typeName 类型名
     * @return jdbc type
     */
    private static String typeName2JDBCType(String typeName) {
        if (typeName.equalsIgnoreCase("DATETIME")) {
            return "TIMESTAMP";
        }
        if (typeName.equalsIgnoreCase("INT")) {
            return "INTEGER";
        }
        if (typeName.equalsIgnoreCase("LONGTEXT")) {
            return "VARCHAR";
        }
        if (typeName.equalsIgnoreCase("TEXT")) {
            return "VARCHAR";
        }
        return typeName.toUpperCase();
    }

    // 获取格式化后的时间
    private static String getDate() {
        String time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        time = sdf.format(new Date());
        return time;
    }

    private static StringBuilder generateIfTestSentence(String alisa) {
        connection = getConnections();
        StringBuilder sb = new StringBuilder();
        try {
            DatabaseMetaData dbmd = connection.getMetaData();
            ResultSet resultSet = dbmd.getTables(null, "%", "%", new String[]{"TABLE"});
            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                if (TABLE_NAME.equals(tableName)) {
                    ResultSet rs1 = dbmd.getColumns(null, "%", tableName, "%");
                    //获取表信息
                    while (rs1.next()) {
                        //列名称
                        String name = rs1.getString("COLUMN_NAME");

                        if (!"".equals(PK_NAME) && name.equals(PK_NAME)){
                            //跳过主键
                            continue;
                        }

                        //Java canmel 命名
                        String javaName = convertcamelCase(name);
                        String typeName = rs1.getString("TYPE_NAME");
                        String type = sqlType2JavaType(typeName);
                        String stringSqlType = "";
                        if ("String".equals(type)) {
                            stringSqlType = " and " + alisa + javaName + "!=''";
                        }
                        String iftest = "<if test=\"" + alisa + javaName + "!=null" + stringSqlType + "\">" + name + " = #{" + alisa + javaName + "},</if>\r\n";
                        sb.append("                " + iftest);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return sb;
        }
    }
}

