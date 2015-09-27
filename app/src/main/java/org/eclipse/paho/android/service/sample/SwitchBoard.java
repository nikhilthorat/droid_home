package org.eclipse.paho.android.service.sample;

import java.util.ArrayList;
import java.util.List;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Switch;

/**
 * Created by Nikhil on 26/09/2015.
 */
public class SwitchBoard  extends ListActivity{

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.switchboard);

        ListView listView = getListView();
        List<RowItem> rowItems;

        rowItems = new ArrayList<RowItem>();
        String[] switchInfo = getResources().getStringArray(R.array.switches);

        Intent intent = getIntent();
        Connection c = (Connection) intent.getSerializableExtra("ConnectionHandle");

        for (int i = 0; i < switchInfo.length; i++) {
            String[] separated = switchInfo[i].split(",");
            int imageID = getResources().getIdentifier("@drawable/"+separated[1],
                    "id", getPackageName());
            RowItem item = new RowItem(imageID, separated[0], new Switch(this));
            rowItems.add(item);
        }

        setListAdapter(new CustomListViewAdapter(this, R.layout.list_item, rowItems, c));
    }
}