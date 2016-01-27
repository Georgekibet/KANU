package Repositories;

import android.content.Context;
import android.database.Cursor;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import DataBase.AppDataBase;
import DataBase.Farmer;
import DataBase.User;

/**
 * Created by george on 10/10/2015.
 */
public class FarmerRepository {

    AppDataBase helper;
    Context context;
    public  FarmerRepository(Context context){
        helper= new AppDataBase(context);
        this.context=context;



    }

  public void saveFarmer(Farmer farmer) throws SQLException{
      helper.getWritableDatabase();
      helper.getDao(Farmer.class).createIfNotExists(farmer);
      helper.close();
  }
    public List<Farmer> getAll() throws SQLException {
        return helper.getDao(Farmer.class).queryForAll();
    }


}
