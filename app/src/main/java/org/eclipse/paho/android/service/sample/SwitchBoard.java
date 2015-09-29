package org.eclipse.paho.android.service.sample;

import java.util.ArrayList;
import java.util.List;
import android.app.ListActivity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Switch;

public class SwitchBoard  extends ListActivity{

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.switchboard);

        Connection c = (Connection) getIntent().getSerializableExtra("ConnectionHandle");
        String roomname = getIntent().getStringExtra("title");
        Bitmap roomimage = getIntent().getParcelableExtra("image");
        setTitle(roomname.toUpperCase());
        BitmapDrawable icon = new BitmapDrawable(getResources(), roomimage);
        getActionBar().setIcon(icon);

        List<RowItem> rowItems;
        rowItems = new ArrayList<RowItem>();

        int switchboardID = getResources().getIdentifier(roomname, "array", getPackageName());
        String[] switchInfo = getResources().getStringArray(switchboardID);

        for (int i = 0; i < switchInfo.length; i++) {
            String[] separated = switchInfo[i].split(",");
            int imageID = getResources().getIdentifier("@drawable/"+separated[1],
                    "id", getPackageName());
            RowItem item = new RowItem(imageID, separated[0], new Switch(this));
            rowItems.add(item);
        }

        setListAdapter(new CustomListViewAdapter(this, R.layout.list_item, rowItems, c, roomname));
    }
}