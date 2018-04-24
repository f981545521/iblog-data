package cn.acyou.iblogdata.generator;

import cn.acyou.iblog.model.Po;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.StringUtility;
import tk.mybatis.mapper.MapperException;
import tk.mybatis.mapper.generator.MapperPlugin;

import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import static cn.acyou.iblogdata.generator.CodeGenerator2.PK_TYPE;
import static cn.acyou.iblogdata.generator.CodeGenerator2.PO_FIELDS;

/**
 * @author youfang
 */
public class IblogMapperPlugin extends MapperPlugin {
    private Set<String> mappers = new HashSet<>();
    private boolean validate = true;


    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        //PK
        topLevelClass.addImportedType(Po.class.getName());
        topLevelClass.setSuperClass("Po");
        List<IntrospectedColumn> columns = Lists.newArrayList(introspectedTable.getPrimaryKeyColumns());
        columns.addAll(introspectedTable.getBaseColumns());
        PO_FIELDS.set(columns);
        processOptimisticLock(topLevelClass, introspectedTable);
        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
    }

    private void processOptimisticLock(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addImportedType("se.spagettikod.optimist.OptimisticLocking");
        String tableName = introspectedTable.getFullyQualifiedTableNameAtRuntime();
        if (StringUtility.stringContainsSpace(tableName)) {
            tableName = context.getBeginningDelimiter()
                    + tableName
                    + context.getEndingDelimiter();
        }
        topLevelClass.addAnnotation("@OptimisticLocking(\"" + getDelimiterName(tableName) + "\")");
    }

    @Override
    public void setProperties(Properties properties) {
        String mappers = properties.getProperty("mappers");
        if (StringUtility.stringHasValue(mappers)) {
            for (String mapper : mappers.split(",")) {
                this.mappers.add(mapper);
            }
        } else {
            throw new MapperException("Mapper插件缺少必要的mappers属性!");
        }
        String validateStr = properties.getProperty("validate");
        if (StringUtility.stringHasValue(validateStr)) {
            validate = Boolean.parseBoolean(validateStr);
        }
        super.setProperties(properties);
    }

    @Override
    public boolean modelFieldGenerated(Field field,
                                       TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
                                       IntrospectedTable introspectedTable, ModelClassType modelClassType) {

        if (!introspectedColumn.isNullable() && !introspectedColumn.isAutoIncrement()) {
            //非主键增加
            topLevelClass.addImportedType("javax.validation.constraints.NotNull");
            field.addAnnotation("@NotNull");
        }

        if (introspectedColumn.isStringColumn()) {
            topLevelClass.addImportedType("javax.validation.constraints.Size");
            field.addAnnotation("@Size(min = 0, max = " + introspectedColumn.getLength() + " , message = \"长度必须在{min}和{max}之间\")");
        }
        if ("version".equalsIgnoreCase(field.getName())) {
            field.addAnnotation("@Version(\"" + introspectedColumn.getActualColumnName() + "\")");
            topLevelClass.addImportedType("se.spagettikod.optimist.Version");
        }
        if (introspectedTable.getPrimaryKeyColumns().contains(introspectedColumn)) {
            topLevelClass.addImportedType("se.spagettikod.optimist.Identity");
            field.addAnnotation("@Identity(\"" + introspectedColumn.getActualColumnName() + "\")");
        }
        return super.modelFieldGenerated(field, topLevelClass, introspectedColumn,
                introspectedTable, modelClassType);


    }

    /**
     * 生成的Mapper接口
     *
     * @param interfaze
     * @param topLevelClass
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        //获取实体类
        FullyQualifiedJavaType entityType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        //import接口
        for (String mapper : mappers) {
            List<IntrospectedColumn> primaryKeyColumns = introspectedTable.getPrimaryKeyColumns();
            Preconditions.checkArgument(primaryKeyColumns.size() == 1);
            FullyQualifiedJavaType pkType = primaryKeyColumns.get(0).getFullyQualifiedJavaType();
            interfaze.addImportedType(new FullyQualifiedJavaType(mapper));
            interfaze.addImportedType(pkType);
            interfaze.addSuperInterface(new FullyQualifiedJavaType(mapper + "<" + entityType.getShortName() + "," + pkType.getShortName() + ">"));
            PK_TYPE.set(pkType);
        }
        //import实体类
        interfaze.addImportedType(entityType);
        return true;
    }

    @Override
    public boolean clientCountByExampleMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass,
                                              IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientCountByExampleMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientDeleteByExampleMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientDeleteByExampleMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientSelectByExampleWithBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientSelectByExampleWithBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientSelectByExampleWithoutBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientSelectByExampleWithoutBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientUpdateByExampleSelectiveMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientUpdateByExampleSelectiveMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientUpdateByExampleWithBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientUpdateByExampleWithBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientUpdateByExampleWithoutBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientUpdateByExampleWithoutBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean providerCountByExampleMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapCountByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapDeleteByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapExampleWhereClauseElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapSelectByExampleWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapUpdateByExampleSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapUpdateByExampleWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapUpdateByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }
}

