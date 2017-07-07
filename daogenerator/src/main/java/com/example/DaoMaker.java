package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class DaoMaker {
    public static void main(String args[]){
        //生成数据库实体类，（版本，包名）
        Schema schema =new Schema(1,"com.student.entity");
        addStudent(schema);
        schema.setDefaultJavaPackageDao("com.student.dao");
        try {
            new DaoGenerator().generateAll(schema,"E:\\java EE code\\GreenDao_Projects\\app\\src\\main\\java-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void addStudent(Schema schema){
        //创建表--》 学生  参数--》name address age
        Entity entity =schema.addEntity("student");//创建数据库的表
        entity.addIdProperty();//主键 int 类型
        entity.addStringProperty("name");
        entity.addStringProperty("address");
        entity.addIntProperty("age");
    }
}
