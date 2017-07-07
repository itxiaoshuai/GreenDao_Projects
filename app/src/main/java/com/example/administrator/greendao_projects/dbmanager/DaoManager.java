package com.example.administrator.greendao_projects.dbmanager;


import android.content.Context;

import com.student.dao.DaoMaster;
import com.student.dao.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

/*
 *  @项目名：  GreenDao_Projects 
 *  @包名：    com.example.administrator.greendao_projects.dbmanager
 *  @文件名:   DaoManager
 *  @创建者:   Administrator
 *  @创建时间:  2017/6/20 20:20
 *  @描述：     1.创建数据库
 *             2.创建数据库的表
 *             3.包含对数据库的CURD
 *             4.对数据库的升级
 */
public class DaoManager {
    private static final String TAG     = DaoManager.class.getSimpleName();
    private static final String DB_NAME = "mydb.sqlite";//数据库名称
    private volatile static DaoManager              daoManager;//多线程访问
    private static          DaoMaster.DevOpenHelper helper;
    private static          DaoMaster               daoMaster;
    private static          DaoSession              daoSession;
    private static          Context                 context;

    /**
     * 使用单例模式获取操作数据库的对象
     * @return
     */
    public static DaoManager getInstance() {
        DaoManager instance = null;
        if (daoManager == null) {
            synchronized (DaoManager.class) {
                if (instance == null) {
                    instance = new DaoManager();
                    daoManager = instance;
                }
            }
        }
        return instance;
    }

    /**
     * 判断是否存在数据库，如果没有则创建数据库
     * @return
     */
    public DaoMaster getDaoMaster() {
        if (daoMaster == null) {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
            daoMaster = new DaoMaster(helper.getWritableDb());
        }
        return daoMaster;
    }

    /**
     * 完成对数据库的添加 删除 修改 查询的操作，仅仅是一个接口
     * @return
     */
    public DaoSession getDaoSession() {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    /**
     * 打开输出日志的操作，默认是关闭的
     */
    public void setDebug() {
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }

    /**
     * 关闭所有的操作，数据库开启的时候，使用完毕了必须要关闭
     */
    public void closeConnection() {
        closeHelper();
        closeDaoSession();
    }

    public void closeHelper() {
        if (helper != null) {
            helper.close();
            helper = null;
        }
    }

    public void closeDaoSession() {
        if (daoSession != null) {
            daoSession.clear();
            daoSession = null;
        }
    }

    public void init(Context context) {
        this.context = context;
    }
}
