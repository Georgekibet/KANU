package data.collect.com.kanu;

import android.app.Fragment;


/**
 * Created by george on 10/10/2015.
 */
public class BaseFragment extends Fragment  {

    public interface OnFabsetup{
        public void setUpFab(int resId);
        public void onFabClicked();
    }

    public void onBackPressed()
    {
        // add code in super class when override
    }


}
