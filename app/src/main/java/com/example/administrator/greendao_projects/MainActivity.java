package com.example.administrator.greendao_projects;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.administrator.greendao_projects.dbmanager.CommonUtils;
import com.student.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity
        extends AppCompatActivity
{
    public static final String TAG = "MainActivity";
    private CommonUtils mCommonUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCommonUtils = new CommonUtils(this);
    }

    public void insertData(View view) {
        Log.e(TAG, "insertData");
        Student student = new Student();
        student.setAddress("深圳");
        student.setName("罗帅");
        student.setAge(24);
        student.setId(1001l);
        mCommonUtils.insertStudent(student);
    }

    public void insertMultData(View view) {
        Log.i(TAG, "insertMultData");
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setAddress("湖南");
            student.setName("李四");
            student.setAge(24+i);
            list.add(student);
        }
        mCommonUtils.insertMultStudent(list);
    }

    public void updateData(View view) {
        Student student = new Student();
        student.setId(1001l);
        student.setAge(100);
        student.setName("jack");
        student.setAddress("银河火箭队");
        mCommonUtils.updateStudent(student);
    }

    public void deleteData(View view) {
        Student student = new Student();
        student.setId(1001l);
        //delete from student where _id=?
    }

    public void queryOne(View view) {
        Log.i(TAG, mCommonUtils.listOne(1001l) + "");
    }

    public void queryMore(View view) {
        List<Student> list = mCommonUtils.listAll();
        Log.i(TAG, list.toString());
    }

    /**
     * 使用复合条件进行查询
     * @param view
     */
    public void queryBuilder(View view){
        mCommonUtils.query2();
    }
}
