package utils;


        import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.UUID;

import DataBase.Farmer;
import data.collect.com.kanu.R;

public class SearchAbleAdapter extends BaseAdapter {


    private List<Farmer>filteredData = null;
    private LayoutInflater mInflater;
    private Context context;



    public SearchAbleAdapter(Context context, List<Farmer> data) {
        this.filteredData = data ;
        this.context=context;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return filteredData.size();
    }

    public Object getItem(int position) {
        return filteredData.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.farmer_list_item, null);

            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.farmer);
            holder.text_date=(TextView) convertView.findViewById(R.id.textView_date);

            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(filteredData.get(position).getFirstNAme() + "  " + filteredData.get(position).getOtherNames());
        /*holder.text.setTag(filteredData.get(position).getId());*/

        holder.text_date.setText(filteredData.get(position).getDateCreated()+"");



        return convertView;
    }

    static class ViewHolder {
        TextView text;
        TextView text_date;
        TextView text_devider;

    }



}