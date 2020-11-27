package cn.caixiaobai.springboot.mybatisplus_mbg;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 *  mybatisplus代码生成器
 */
public class CodeGenerator {
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator generator = new AutoGenerator();
        // set freemarker engine
        generator.setTemplateEngine(new FreemarkerTemplateEngine());


        //全局配置 GlobalConfig
        GlobalConfig globalConfig = new GlobalConfig();
        //设置生成的目录
        globalConfig.setOutputDir(System.getProperty("user.dir") + "/src/main/java");
        //设置作者
        globalConfig.setAuthor("caixiaobai");
        //生成文件后打开文件所在文件的文件夹下
        globalConfig.setOpen(true);
        //是否覆盖原来生成的文件
        globalConfig.setFileOverride(true);
        //去掉service默认自带的前缀I
        globalConfig.setServiceName("%sService");
        //设置主键策略 默认
        globalConfig.setIdType(IdType.ID_WORKER);
        //设置日期类型  仅仅只是日期
        globalConfig.setDateType(DateType.ONLY_DATE);
        //设置swagger
        // globalConfig.setSwagger2(true);

        //将配置好的全局配置，配置进代码生成器里
        generator.setGlobalConfig(globalConfig);


        DataSourceConfig dsc = new DataSourceConfig();
        //设置数据库配置
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUrl("jdbc:mysql:///springboot_redis_mbg?useUnicode=true&allowMultiQueries=true&characterEncoding=UTF-8&useSSL=false");
        dsc.setUsername("root");
        dsc.setPassword("root");

        generator.setDataSource(dsc);


        //设置包的配置
        PackageConfig pc = new PackageConfig();
        //设置模块名
        pc.setModuleName("springboot");
        //设置模块的父包
        pc.setParent("cn.caixiaobai");
        //设置实体类的的包名
        pc.setEntity("pojo");
        pc.setController("controller");
        pc.setService("service");
        pc.setMapper("mapper");
        generator.setPackageInfo(pc);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();

        //设置生成哪些表,如果不设置的话，默认生成当前数据源中的所有表
        //strategy.setInclude("roles");

        //设置包的命名规则
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //设置字段的命名规则
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);

        //设置lombok
        //strategy.setEntityLombokModel(true);

        //逻辑删除
        //自动填充配置




        //设置乐观锁
        //strategy.setVersionFieldName("version");
        //开启restful的驼峰命名格式
        strategy.setRestControllerStyle(true);
        //设置请求中使用下划线命名而不是驼峰命名
        //strategy.setControllerMappingHyphenStyle(true);


        generator.setStrategy(strategy);



        //执行方法
        generator.execute();
    }
}
