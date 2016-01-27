package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by george on 1/16/2016.
 */
public class DateConverter {
    public DateConverter(){}

    public  Date ConvertStrigtodate(String formart,String datestring) {
       Date date= new Date();
        try {
       DateFormat format = new SimpleDateFormat(formart, Locale.ENGLISH);

           date = format.parse(datestring);
       } catch (ParseException e) {
           e.printStackTrace();
       }
        return date;
   }
}
