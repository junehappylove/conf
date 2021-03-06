package com.sunny.source.file;

import java.io.IOException;

import org.yaml.snakeyaml.Yaml;

import com.sunny.source.LoadSource;
import com.sunny.utils.FileUtil;

/**
 * created by zsunny
 * Email zsunny@yeah.net
 * Date on 2018/7/30.
 */
public class LoadYaml implements LoadSource {

    private LoadYaml(){}

    private static class LoadYamlHolder{
        private static LoadYaml loadYaml = new LoadYaml();
    }

    public static LoadYaml getInstance(){
        return LoadYamlHolder.loadYaml;
    }

    @Override
    public Object loadSources(String path) throws IOException {

        Yaml yaml = new Yaml();

        if(!FileUtil.judgeFileExist(path))
            return null;

        Object res = yaml.load(FileUtil.readFile(path));
//        System.out.println(res);
        return res;

    }

}
