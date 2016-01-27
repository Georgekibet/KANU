package data.collect.com.kanu;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import DataBase.Farmer;

/**
 * Created by george on 1/22/2016.
 */
public class NAvigationMenuAdapter extends ArrayAdapter<NavMenu> {


    Context context;

    public NAvigationMenuAdapter(Context context, int resourceId, //resourceId=your layout
                                 List<NavMenu> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        TextView details;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        NavMenu rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.farmer_list_item, null);
            holder = new ViewHolder();
            holder.txtTitle = (TextView) convertView.findViewById(R.id.farmer);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView1);
            holder.details=(TextView) convertView.findViewById(R.id.textView_date);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.txtTitle.setText(rowItem.getTittle());
        holder.imageView.setImageResource(rowItem.getImg());
        holder.details.setText(rowItem.getDescription());

        return convertView;
    }}