package com.example.administrator.greendao_projects.dbmanager;

import android.content.Context;
import android.util.Log;

import com.student.dao.StudentDao;
import com.student.entity.Student;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/*
 *  @项目名：  GreenDao_Projects 
 *  @包名：    com.example.administrator.greendao_projects.dbmanager
 *  @文件名:   CommonUtils
 *  @创建者:   Administrator
 *  @创建时间:  2017/6/21 19:07
 *  @描述：    完成对某一张表的具体操作，ORM 操作 的对象是 Student
 */
public class CommonUtils {
    private DaoManager mDaoManager;

    public CommonUtils(Context context) {
        mDaoManager = DaoManager.getInstance();
        mDaoManager.init(context);
    }

    /**
     * 完成对数据库中student的插入操作
     * @param studend
     * @return
     */
    public boolean insertStudent(Student studend) {
        boolean flag = false;
        try{
            flag = mDaoManager.getDaoSession().insert(studend) != -1 ? true : false;
            flag=true;
            Log.i("CommonUtils","----insertStudent----result is---"+flag);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 插入多条记录，需要开辟新的线程
     * @param studentList
     * @return
     */
    public boolean insertMultStudent(final List<Student> studentList){
        boolean flag=false;
        try {
            mDaoManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (Student student:studentList) {
                        //因为这里返回值是long，所以try catch 用来改变 flag 证明这行代码已经生效
                        mDaoManager.getDaoSession().insertOrReplace(student);
                    }
                }
            });
            flag=true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 完成对student的某一条记录的修改
     * @param student
     * @return
     */
    public boolean updateStudent(Student student){
        boolean flag =false;
        try {
            //插入失败，抛出异常
            mDaoManager.getDaoSession().update(student);
            flag=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    public boolean deleteStudent(Student student){
        boolean flag =false;
        try {
            mDaoManager.getDaoSession().delete(student);
            flag=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 查询所有记录
     * @returnSt
     */
    public List<Student> listAll(){
        return mDaoManager.getDaoSession().loadAll(Student.class);
    }

    /**
     * 返回单行记录
     * @param key
     * @return
     */
    public Student listOne(long key){
        return mDaoManager.getDaoSession().load(Student.class,key);
    }
    public void query1(){
        //使用native sql进行查询操作
        List<Student> list=mDaoManager.getDaoSession().queryRaw(Student.class,"where name like ? and _id > ?",new String[]{"%李%","1001"});
        Log.i("--->>",""+list);
    }

    /**
     * select * from student where name like ? or name =? or
     */
    public void query2(){
        //查询构造器
        QueryBuilder<Student> queryBuilder = mDaoManager.getDaoSession().queryBuilder(Student.class);
       List<Student> list= queryBuilder
                    .where(StudentDao.Properties.Age.ge(23))
                    .where(StudentDao.Properties.Address.like("湖南"))
                    .list();
        Log.i("--->>",""+list);
    }
}
