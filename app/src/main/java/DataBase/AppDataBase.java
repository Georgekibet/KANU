package DataBase;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.util.UUID;

import data.collect.com.kanu.R;

/**
 * Created by george on 10/10/2015.
 */
public class AppDataBase extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "kanu_db";
    private static final int DATABASE_VERSION = 1;

    private Dao<User, String> userDOA;
    private Dao<Farmer,UUID>   farmetDOA;



    public  AppDataBase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION,
                /**
                 * R.raw.ormlite_config is a reference to the ormlite_config.txt file in the
                 * /res/raw/ directory of this project
                 * */
                R.raw.ormlite_config);
    }
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {

            /**
             * creates the Tables
             */
            TableUtils.createTable(connectionSource, Farmer.class);
            TableUtils.createTable(connectionSource, User.class);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try {
            TableUtils.dropTable(connectionSource,Farmer.class,false);
            TableUtils.dropTable(connectionSource,User.class,false);

            onCreate(database,connectionSource);
        }
        catch (Exception e){e.printStackTrace();}

    }
}
