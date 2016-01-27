package data.collect.com.kanu;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import utils.StringUtils;

/**
 * Created by george on 1/23/2016.
 */
public class HistoryFragment extends BaseFragment {

    WebView myWebView;
    String myBlogAddr = "https://en.wikipedia.org/wiki/Kenya_African_National_Union";
    String myUrl;
   protected ProgressDialog prDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentweb, container, false);
        myWebView = (WebView)view.findViewById(R.id.mywebview);
        try {
            myBlogAddr=getArguments().getString("url", StringUtils.DefaultUrl);
        }catch (Exception e){}

        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new MyWebViewClient());
        prDialog = new ProgressDialog(getActivity());
        myWebView.getSettings().setBuiltInZoomControls(true);

        if(myUrl == null){
            myUrl = myBlogAddr;
        }
        myWebView.loadUrl(myUrl);

        return view;

    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            myUrl = url;
            view.loadUrl(url);
            return true;
        }
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

            prDialog.setMessage("Loading ...");
            prDialog.show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            try {
                prDialog.dismiss();

            } catch (Exception e) {
            }

        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);}
    @Override
    public void onBackPressed()
    {
        android.app.Fragment fragment=new HistoryFragment();
        FragmentManager fm=getFragmentManager();

        //Bundle bundle=new Bundle();
       // fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction=fm.beginTransaction();
        fragmentTransaction.replace(data.collect.com.kanu.R.id.fragment_place, fragment);
        fragmentTransaction.commit();
    }


}