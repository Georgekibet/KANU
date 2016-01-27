package data.collect.com.kanu;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gordonwong.materialsheetfab.MaterialSheetFab;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import utils.Fab;
import utils.StringUtils;


public class MainActivity extends ActionBarActivity implements BaseFragment.OnFabsetup {
    MaterialSheetFab materialSheetFab;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    Fab fabButton;
    private int phone,idnumber;
    private String firstName,otherNAmes;

    TextView createNote;
    ListView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(data.collect.com.kanu.R.layout.activity_main);
        setupActionBar();
        List<NavMenu> list=new ArrayList<NavMenu>();
        list.add(new NavMenu(R.drawable.ic_aspirants, "KANU History", "All About Kanu"));
        list.add(new NavMenu(R.drawable.ic_history, "Kanu National leaders","Current KANU leaders"));
        list.add(new NavMenu(R.drawable.ic_leaders,"Party Members","All the current party members"));
        list.add(new NavMenu(R.drawable.ic_news,"News and Updates","Catch all the political news"));
        list.add(new NavMenu(R.drawable.ic_history,"Events","All About Kanu"));
        list.add(new NavMenu(R.drawable.ic_contact_us,"Contact us","Our Party offices around the country"));
        //list.add(new NavMenu(R.drawable.ic_aspirants,"Party Aspirtants","All About Kanu"));
        //list.add(new NavMenu(R.drawable.ic_contact_us,"Contact us","Our Party offices around the country"));


        navigationView=(ListView)findViewById(R.id.navigation_menu);


       // BaseAdapter adapter=new NAvigationMenuAdapter(this,list);
        NAvigationMenuAdapter adapter=new NAvigationMenuAdapter(this,R.layout.activity_main,list);

        navigationView.setAdapter(adapter);

        navigationView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(MainActivity.this,"ccccc",Toast.LENGTH_LONG).show();


                NavigationView navigationView= (NavigationView) findViewById(R.id.navigation_view);

                FragmentManager fm=getFragmentManager();
                Fragment fragment=new DataListingFragment();
                Bundle bundle=new Bundle();
                switch (position){

                    case 0:
                        fragment=new HistoryFragment();
                        bundle=new Bundle();
                        bundle.putString("url", StringUtils.DefaultUrl);
                        fragment.setArguments(bundle);
                        _currentFragment=(BaseFragment)fragment;
                        break;
                    case 1:

                        fragment=new HistoryFragment();
                        bundle=new Bundle();
                        bundle.putString("url", StringUtils.DefaultUrl);
                        fragment.setArguments(bundle);
                        _currentFragment=(BaseFragment)fragment;
                        break;
                    case 2:
                        fragment=new DataListingFragment();
                        _currentFragment=(BaseFragment)fragment;
                        break;
                    case 3:
                        fragment=new HistoryFragment();
                        bundle=new Bundle();
                        bundle.putString("url", StringUtils.KanuNews);
                        fragment.setArguments(bundle);
                        _currentFragment=(BaseFragment)fragment;
                        break;
                    case 4:
                        fragment=new HistoryFragment();
                        bundle=new Bundle();
                        bundle.putString("url", StringUtils.KanuEvents);
                        fragment.setArguments(bundle);

                        _currentFragment=(BaseFragment)fragment;
                        break;
                    case 5:
                       showContacts();
                        break;
                       default:
                }
                FragmentTransaction fragmentTransaction=fm.beginTransaction();
                fragmentTransaction.replace(data.collect.com.kanu.R.id.fragment_place, fragment);
                fragmentTransaction.commit();

               /* Fragment fragment = new DataListingFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();



                if (position == 0) {

                    fragmentTransaction.replace(data.collect.com.kanu.R.id.fragment_place, fragment);
                    fragmentTransaction.commit();
                } else if (position == 1) {
                    fragment = new HistoryFragment();

                    fragmentTransaction.replace(data.collect.com.kanu.R.id.fragment_place, fragment);
                    fragmentTransaction.commit();
                } else
                    fragmentTransaction.replace(data.collect.com.kanu.R.id.fragment_place, fragment);
                fragmentTransaction.commit();*/
                drawerLayout.closeDrawer(navigationView);
            }
        });

        FragmentManager fm=getFragmentManager();
        Fragment fragment=new HistoryFragment();
        FragmentTransaction fragmentTransaction=fm.beginTransaction();
        fragmentTransaction.replace(data.collect.com.kanu.R.id.fragment_place, fragment);
        fragmentTransaction.commit();

       // TextView dumpDbTv= (TextView) findViewById(data.collect.com.kanu.R.id.drawer_dumpdb);
       /* dumpDbTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_LONG).show();

                new DBBack_Up().execute();
            }
        });*/


        drawerLayout = (DrawerLayout) findViewById(data.collect.com.kanu.R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle
                (
                        this,
                        drawerLayout,
                        R.string.notes,
                        R.string.notes
                )
        {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().hide();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().show();
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);

        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
    }


   /* */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(data.collect.com.kanu.R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == data.collect.com.kanu.R.id.action_settings) {
            return true;
        }
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }
    private void setupActionBar() {
        Toolbar toolbar=(Toolbar) findViewById(data.collect.com.kanu.R.id.toolbar);
        //toolbar.setNavigationIcon(R.drawable.ic_list_black_48dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setDis
    }
    private void setupDrawer() {
        drawerLayout.setDrawerListener(drawerToggle);
    }

    public MaterialSheetFab getFabSheet(){
        return  materialSheetFab;
    }

    public void setFabButton(int resid){
        fabButton.setImageResource(resid);
    }

    @Override
    public void setUpFab(int resId) {



    }

    @Override
    public void onFabClicked() {

    }

    public void setDate(String date){

           Button b= (Button)findViewById(data.collect.com.kanu.R.id.button_date);
           this.dateString=date;
           b.setText(date);
    }
    public Date getDateString(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");

        Date date=new Date();
        try {

             date = formatter.parse(this.dateString);
            System.out.println(date);
            System.out.println(formatter.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  date;
    }
    private String dateString="yy/mm/dd";


    private class DBBack_Up extends AsyncTask<String, Void, Boolean> {
        ProgressDialog dialog;
        // can use UI thread here
        protected void onPreExecute() {
            /*dialog=new ProgressDialog(getApplicationContext());
            dialog.setMessage("Exporting database...");
            dialog.show();*/
        }

        // automatically done on worker thread (separate from UI thread)
        protected Boolean doInBackground(final String... args) {
            String DATABASE_NAME = "mydata_db";
            File from = getApplicationContext().getDatabasePath(DATABASE_NAME);
            File exportDir = Environment.getExternalStorageDirectory();
            String backupDBPath = "mydata_db.db";
            File file = new File(exportDir, backupDBPath);

            try {
                this.copyFile(from, file);
                return true;
            } catch (IOException e) {
                Log.e("mypck", e.getMessage(), e);
                return false;
            }
        }

        // can use UI thread here
        protected void onPostExecute(final Boolean success) {
           /* if (dialog.isShowing()) {
                dialog.dismiss();
            }*/


            if (success) {
                Toast.makeText(getApplicationContext(), "Export successful!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Export failed", Toast.LENGTH_SHORT).show();
            }
        }

        @SuppressWarnings("resource")
        void copyFile(File src, File dst) throws IOException {
            FileChannel inChannel = new FileInputStream(src).getChannel();
            FileChannel outChannel = new FileOutputStream(dst).getChannel();
            try {
                inChannel.transferTo(0, inChannel.size(), outChannel);
            } finally {
                if (inChannel != null)
                    inChannel.close();
                if (outChannel != null)
                    outChannel.close();
            }
        }
    }
    private BaseFragment _currentFragment;

    @Override
    public void onBackPressed()
    {

        try {
            _currentFragment.onBackPressed();
        }catch (Exception e){
            super.onBackPressed();
        }

    }
    public void showContacts(){
      final   Dialog d=new Dialog(MainActivity.this);
        d.setContentView(R.layout.contactus);
        d.setTitle("Contact Us");
        TextView textView= (TextView) d.findViewById(R.id.contactus_facebook);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new HistoryFragment();
                FragmentManager fm=getFragmentManager();

                Bundle bundle=new Bundle();
                bundle.putString("url", StringUtils.FacebookUrl);

                fragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction=fm.beginTransaction();
                fragmentTransaction.replace(data.collect.com.kanu.R.id.fragment_place, fragment);
                fragmentTransaction.commit();
                d.dismiss();
            }
        });
        d.show();
    }
}
