package com.yanqun.test;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyBatis_Generator {
    public static void main(String[] args) throws Exception
    {
        File f = new File("src/generator.xml");
        List<String> warnings = new ArrayList<String>();
        ConfigurationParser cp
                = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(f);
        DefaultShellCallback callBack = new DefaultShellCallback(true);
        MyBatisGenerator generator = new MyBatisGenerator(config, callBack, warnings);
        generator.generate(null);
    }
}
