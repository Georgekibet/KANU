package data.collect.com.kanu;

/**
 * Created by george on 10/9/2015.
 */
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;


import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import DataBase.Farmer;
import Repositories.FarmerRepository;
import utils.DateConverter;
import utils.DatePickerFragment;

public class InputFragment extends BaseFragment  {

String firstname="",othernames="";
    int idNumber=0,phoneNumber=0;
    EditText firstNameEd;
    EditText otherNameEd;
    EditText idNumberEd;
    EditText phoneNumberEd;
    Date date;

    @Override

    public View onCreateView(LayoutInflater inflater ,ViewGroup container, Bundle savedInstanceState) {


      View view=inflater.inflate( data.collect.com.kanu.R.layout.input_layout, container, false);


        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton fab= (FloatingActionButton) view.findViewById(data.collect.com.kanu.R.id.fab);
        firstNameEd=(EditText)view.findViewById(data.collect.com.kanu.R.id.first_name);
        otherNameEd=(EditText)view.findViewById(data.collect.com.kanu.R.id.other_names);
        idNumberEd=(EditText)view.findViewById(data.collect.com.kanu.R.id.id_number);
         phoneNumberEd=(EditText)view.findViewById(data.collect.com.kanu.R.id.farmer_phone);




       final Button dateButton=(Button)view.findViewById(data.collect.com.kanu.R.id.button_date);
        dateButton.setOnClickListener(onDatePickerClick());

       fab.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {


               if(firstNameEd.getText().length()>2){
                   firstname=firstNameEd.getText().toString();
                   othernames=otherNameEd.getText().toString();
               }
               else {firstNameEd.setError("Cannot be empty"); return;};
               if(idNumberEd.getText().length()>2){
                   idNumber=Integer.parseInt(idNumberEd.getText().toString());
                   phoneNumber=Integer.parseInt(phoneNumberEd.getText().toString());
               }
               else{ idNumberEd.setError("Cannot be empty"); return;};

               if(!dateButton.getText().toString().trim().startsWith("Y")){
                  date=new DateConverter().ConvertStrigtodate("yyyy-MM-dd",dateButton.getText().toString());
               }
              else{ dateButton.setError("cannot be empty");return;}

               Farmer farmer= new Farmer();
               farmer.setId(UUID.randomUUID());
               farmer.setDOB(((MainActivity) getActivity()).getDateString());
               farmer.setDateCreated(Calendar.getInstance().getTime());
               farmer.setOtherNames(othernames);
               farmer.setFirstNAme(firstname);
               farmer.setPhoneNumber(phoneNumber);
               farmer.setIdNumer(idNumber);
               farmer.setDOB(date);

               try {

     new FarmerRepository(getActivity()).saveFarmer(farmer);

         }
catch (Exception e){}


               FragmentManager fm=getFragmentManager();
               Fragment fragment=new DataListingFragment();
               FragmentTransaction fragmentTransaction=fm.beginTransaction();
               fragmentTransaction.replace(data.collect.com.kanu.R.id.fragment_place, fragment);
               fragmentTransaction.commit();
           }
       });

    }

    public View.OnClickListener onDatePickerClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialog=new DatePickerFragment();
                dialog.show(getFragmentManager(),"datePicker");


                dialog.getTag().toString();
            }
        };
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }




}

