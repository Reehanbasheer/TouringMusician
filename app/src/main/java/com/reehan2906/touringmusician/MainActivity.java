package com.reehan2906.touringmusician;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TourMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout layout = (LinearLayout) findViewById(R.id.top_layout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 30.0f);
        map = new TourMap(this);
        map.setLayoutParams(params);
        layout.addView(map, 0);
        final Button modeButton = (Button) findViewById(R.id.mode_selector);
        modeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this, modeButton);
                popup.getMenuInflater()
                        .inflate(R.menu.modepopup, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        map.setInsertMode(item.getTitle().toString());
                        return true;
                    }
                });

                popup.show();
            }
        });
    }

    public void onReset(View v) {
        map.reset();
        TextView message = (TextView) findViewById(R.id.game_status);
        if (message != null) {
            message.setText("Tap map to add new tour stops.");
        }
    }
}