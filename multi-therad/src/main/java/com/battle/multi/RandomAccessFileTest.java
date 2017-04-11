package com.battle.multi;

import java.io.*;

/**
 * Created by Bing.Z on 2017/4/6.
 */
public class RandomAccessFileTest {
    public static void main(String[] args) throws Exception{
        // RandomAccess("G:\\java-lambda\\w.txt","rw",0,2);
        // RandomAccessTem("G:\\java-lambda\\w.txt","rw",0,2);
        RandomAccessTemp("G:\\java-lambda\\w.txt","rw");

    }
    /**
     * model各个参数详解
     * r 代表以只读方式打开指定文件
     * rw 以读写方式打开指定文件
     * rws 读写方式打开，并对内容或元数据都同步写入底层存储设备
     * rwd 读写方式打开，对文件内容的更新同步更新至底层存储设备
     */
    public static void RandomAccess(String path,String mode,long seek,int skipBytes){
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(path,mode);
            raf.writeBytes("If you interesting to me,please give the kiss to me!");
            raf.seek(seek);//指定记录指针指定的位置
            //System.out.println(raf.readLine());//使用seek指针指向0，readLine读取所有内容
            raf.getFilePointer();//获取指针的位置
            raf.skipBytes(skipBytes);//跳过的字节数
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = raf.read(buff))>0){
                System.out.println(new String(buff,0,len));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(raf != null){
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void RandomAccessTem(String path,String mode){
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(path,mode);
            raf.writeBytes("If you interesting to me,please give the kiss to me!");
            raf.seek(raf.length());//通过raf.length()获取总长度，记录指针指向最后
            raf.write("oh yes！".getBytes());//在最后插入oh yes!s
            raf.seek(0);//记录指针指向初始位置
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = raf.read(buff))>0){
                System.out.println(new String(buff,0,len));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(raf != null){
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void RandomAccessTemp(String path,String mode){
        File tempFile = null;
        RandomAccessFile raf = null;
        FileOutputStream out = null;
        FileInputStream input = null;
        try {
            //创建临时文件
            tempFile =File.createTempFile("temp","_temp");
            //在JVM进程退出的时候删除文件,通常用在临时文件的删除
            tempFile.deleteOnExit();
            //创建一个临时文件夹来保存插入点后的数据
            out = new FileOutputStream(tempFile);
            input=new FileInputStream(tempFile);
            //指定文件路径和读写方式
            raf = new RandomAccessFile(path,mode);
            //raf.writeBytes("If you interesting to me,please give the kiss to me!");
            //记录指针指向初始位置
            raf.seek(0);
            byte[] buff = new byte[1024];
            int len = 0;
            //循环读取插入点后的内容
            //读取文件，存入字节数组buff，返回读取到的字符数赋值给len,默认每次将buff数组装满
            while ((len = raf.read(buff))>0){
                // 将读取的数据写入临时文件中
                out.write(buff,0,len);
                System.out.println(new String(buff,0,len));
            }
            //记录指针指向初始位置
            raf.seek(0);
            //插入指定内容
            raf.writeBytes("my name's Toms!");
            //在临时文件中插入指定内容
            while ((len = input.read(buff))>0){
                raf.write(buff,0,len);
            }
            out.close();
            input.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(raf != null){
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
