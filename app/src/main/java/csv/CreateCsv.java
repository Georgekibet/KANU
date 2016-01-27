package csv;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import DataBase.Farmer;

/**
 * Created by george on 10/11/2015.
 */
public class CreateCsv {
    String extStorageDirectory;
    Context context;
    public CreateCsv(Context context){
        this.context=context;
    }
    public CreateCsv(){}

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    public  String fileAddress="";


    public Boolean exportFarmersToCSV(List<Farmer> farmerList) throws IOException
    {
        Boolean success=true;
        File myFile;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        String TimeStampDB = sdf.format(cal.getTime());
        extStorageDirectory= Environment.getExternalStorageDirectory().toString();

        try {
             setFileAddress(extStorageDirectory+"/Export_"+TimeStampDB+".csv");
            myFile = new File(getFileAddress());
            myFile.createNewFile();
            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append("First Name,Otber names,Id Number,Phone number");
            myOutWriter.append("\n");


            for (Farmer farmer:farmerList) {

                myOutWriter.append(farmer.getFirstNAme()+","+farmer.getOtherNames()+","+farmer.getIdNumer()+","+farmer.getPhoneNumber());
                myOutWriter.append("\n");
            }

                  myOutWriter.close();


        } catch (Exception e)
        {
            success=false;
            Log.e(getClass().getSimpleName(), "Error creating new csv");
        }

return  success;
    }
}
