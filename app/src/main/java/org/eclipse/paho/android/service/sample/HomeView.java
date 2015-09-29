package org.eclipse.paho.android.service.sample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class HomeView extends ActionBarActivity {
    private final String TAG = "DROID_HOME";
    Connection c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        GridView gridView;
        GridViewAdapter gridAdapter;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.homegridview);

        Intent intent = getIntent();
        c = (Connection) intent.getSerializableExtra("ConnectionHandle");

        gridView = (GridView) findViewById(R.id.gridView);
        gridAdapter = new GridViewAdapter(this, R.layout.grid_item, getData());
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                RoomImageItem item = (RoomImageItem) parent.getItemAtPosition(position);
                //Create intent
                Intent intent = new Intent(HomeView.this, SwitchBoard.class);
                intent.putExtra("title", item.getTitle());
                intent.putExtra("image", item.getImage());
                intent.putExtra("ConnectionHandle", c);

                //Start details activity
                startActivity(intent);
            }
        });
    }

    private ArrayList<RoomImageItem> getData() {
        final ArrayList<RoomImageItem> imageItems = new ArrayList<>();
        String[] imgs = getResources().getStringArray(R.array.image_ids);
        for (int i = 0; i < imgs.length; i++) {
            int imageID = getResources().getIdentifier("@drawable/"+imgs[i],
                    "id", getPackageName());
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imageID);
            imageItems.add(new RoomImageItem(bitmap, imgs[i]));
        }
        return imageItems;
    }
}
