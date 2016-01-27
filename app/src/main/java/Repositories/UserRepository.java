package Repositories;

import android.content.Context;

import java.sql.SQLException;
import java.util.List;

import DataBase.AppDataBase;
import DataBase.Farmer;
import DataBase.User;

/**
 * Created by george on 10/10/2015.
 *
 */

public class UserRepository {

    AppDataBase helper;
    Context context;
    public UserRepository(Context context){
        this.context=context;
        helper=new AppDataBase(context);
    }

    public void saveUser(User user) throws SQLException{
        helper.getWritableDatabase();
        helper.getDao(User.class).createIfNotExists(user);
        helper.close();
    }
    public List<User> getAllUsers() throws SQLException {
        return helper.getDao(User.class).queryForAll();
    }
}
