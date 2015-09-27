package org.eclipse.paho.android.service.sample;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class CustomListViewAdapter extends ArrayAdapter<RowItem> {

    Context context;
    Connection c;

    private class OnItemClickListener implements View.OnClickListener {
        private int mPosition;
        private String mText;

        OnItemClickListener(int position, String text){
            mPosition = position;
            mText = text;
        }

        @Override
        public void onClick(View arg0) {
            Log.d("LIST_DEMO", "onItemClick at position " + mPosition + " " + mText);
        }
    }

    public CustomListViewAdapter(Context context, int resourceId,
                                 List<RowItem> items, Connection c) {
        super(context, resourceId, items);
        this.context = context;
        this.c = c;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtView;
        Switch sw;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        RowItem rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.img1);
            holder.txtView = (TextView) convertView.findViewById(R.id.text1);
            holder.sw = (Switch) convertView.findViewById(R.id.switch1);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.imageView.setImageResource(rowItem.getImageId());
        holder.txtView.setText(rowItem.getText());
        holder.sw.setTag(rowItem.getText());

        //attach a listener to check for changes in state
        holder.sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                String switchName = (String) buttonView.getTag();

                if (isChecked) {
                    Log.d("LIST_DEMO", switchName + " onCheckedChanged on");
                    c.publish(switchName, "ON");
                } else {
                    Log.d("LIST_DEMO", switchName + " onCheckedChanged off");
                    c.publish(switchName, "OFF");
                }
            }
        });

        convertView.setOnClickListener(new OnItemClickListener(position, rowItem.getText()));
        return convertView;
    }
}