package com.battle.multi;

/**
 * 断点续传
 * Created by Bing.Z on 2017/4/5.
 */
public class DownLoadTest {
    /**
     * @param args
     */
    public static void main(String[] args) {

        String filepath = "http://dl.download.csdn.net/down11/20170329/39113992b4c521bcecb8e09ebbf7b590.pdf?response-content-disposition=attachment%3Bfilename%3D%22%E5%9E%83%E5%9C%BE%E5%9B%9E%E6%94%B6%E7%9A%84%E7%AE%97%E6%B3%95%E4%B8%8E%E5%AE%9E%E7%8E%B0%2C.pdf%22&OSSAccessKeyId=9q6nvzoJGowBj4q1&Expires=1491387477&Signature=G5VeMZhnGYzODCEZ4OzmHrjK%2FNY%3D";
        MultiTheradDownLoad load = new MultiTheradDownLoad(filepath ,4);
        load.downloadPart();
    }
}
