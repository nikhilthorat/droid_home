package org.eclipse.paho.android.service.sample;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GridViewAdapter extends ArrayAdapter {
    private Context context;
    private int layoutResourceId;
    private ArrayList data = new ArrayList();

    static class ViewHolder {
        TextView imageTitle;
        ImageView image;
    }

    public GridViewAdapter(Context context, int layoutResourceId, ArrayList data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imageTitle = (TextView) row.findViewById(R.id.roomname);
            holder.image = (ImageView) row.findViewById(R.id.roomimage);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        RoomImageItem item = (RoomImageItem)data.get(position);
        holder.imageTitle.setText(item.getTitle().toUpperCase());
        holder.image.setImageBitmap(item.getImage());
        return row;
    }

}
