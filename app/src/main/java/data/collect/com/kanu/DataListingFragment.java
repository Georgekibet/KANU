package data.collect.com.kanu;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gordonwong.materialsheetfab.MaterialSheetFab;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DataBase.Farmer;
import Repositories.FarmerRepository;
import csv.CreateCsv;
import utils.Fab;
import utils.SearchAbleAdapter;

/**
 * Created by george on 10/10/2015.
 */
public class DataListingFragment extends BaseFragment {
    Farmer farmer;
    ListView listView;
    List<Farmer> farmers;

    @Override
    public View onCreateView(LayoutInflater inflater ,ViewGroup container, Bundle savedInstanceState) {


        View view=inflater.inflate( data.collect.com.kanu.R.layout.data_listview, container, false);;

        farmer=new Farmer();

        Date currDateTime = new Date(System.currentTimeMillis());
        listView=(ListView)view.findViewById(data.collect.com.kanu.R.id.data_listview);

        farmers= new ArrayList<Farmer>();

        try {

            farmers=new FarmerRepository(getActivity()).getAll();
        }catch (Exception e){
            farmers.add(farmer);
        }
          return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BaseAdapter adapter= new SearchAbleAdapter(getActivity(),farmers);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),"ccccc",Toast.LENGTH_LONG).show();
            }
        });

        setUpFab(view);
    }
    protected void setUpFab(View view) {
      Fab fabButton = (Fab) view.findViewById(data.collect.com.kanu.R.id.fab);
        View sheetView = view.findViewById(data.collect.com.kanu.R.id.fab_sheet);
        View overlay = view.findViewById(data.collect.com.kanu.R.id.overlay);


        int sheetColor = getResources().getColor(android.R.color.holo_red_dark);
        int fabColor = getResources().getColor(data.collect.com.kanu.R.color.theme_primary);

       // exportFarmersToCSV


        // Initialize material sheet FAB
         new MaterialSheetFab<>(fabButton, sheetView, overlay, sheetColor, fabColor);

        TextView add=(TextView)view.findViewById(data.collect.com.kanu.R.id.fab_sheet_item_note);
        TextView export=(TextView)view.findViewById(data.collect.com.kanu.R.id.fab_sheet_export_farmer);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                Fragment fragment = new InputFragment();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(data.collect.com.kanu.R.id.fragment_place, fragment);
                fragmentTransaction.commit();
                //AppDataBase appDataBaseHelper=new AppDataBase(getActivity()).getDao()
                //   Cursor cursor = getHelper().getReadableDatabase().query(tableName, projection, selection, selectionArgs, groupBy, having, sortOrder
            }
        });

        export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    CreateCsv dataSsv = new CreateCsv();
                    if (dataSsv.exportFarmersToCSV(new FarmerRepository(getActivity()).getAll()))
                        showDialog(dataSsv.getFileAddress());


                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Error here", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }

    public  void openTheFile(String address){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);

        Uri uri = Uri.parse(address);
        intent.setDataAndType(uri, "text/csv");

        startActivity(intent);
    }

    public void showDialog(final String address){
        AlertDialog.Builder dialog= new AlertDialog.Builder(getActivity());
        dialog.setNegativeButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                openTheFile(address);
            }
        });
        dialog.setTitle("CSV file action");
        dialog.setMessage("CSV Export successfully generated");
        dialog.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String pathname= Environment.getExternalStorageDirectory().getAbsolutePath();
                String aEmailList[] = { "gkrugut@gmail.com","grugut@virtualcity.co.ke" };
                File file=new File("", address);
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.putExtra(android.content.Intent.EXTRA_EMAIL, aEmailList);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Collected data");
                intent.putExtra(Intent.EXTRA_TEXT, "Attached please find today's registrations");
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent, "gkrugut@gmail.com"));
            }
        });
        dialog.show();
    }
}
