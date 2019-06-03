package com.sunny.source.file;

import com.sunny.utils.FileUtil;

import java.io.InputStream;
import java.util.Properties;

/**
 * 加载xml配置文件<br>
 * 
 * create by zsunny
 * data: 2018/10/20
 **/
public class LoadXml extends AbstractLoadProperties{

    private LoadXml() {
    }

    public static LoadXml getInstance(){
        return LoadXmlHolder.loadXml;
    }

    @Override
    public Object loadSources(String path) throws Exception {
        Properties properties = new Properties();

        InputStream in = FileUtil.getFileInputStream(path);

        if(null == in)
            return null;

        properties.loadFromXML(in);

        return convertToMap(properties);
    }

    private static class LoadXmlHolder{
        private static LoadXml loadXml = new LoadXml();
    }
}
