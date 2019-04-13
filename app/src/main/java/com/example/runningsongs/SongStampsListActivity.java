package com.example.runningsongs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.runningsongs.models.Song;
import com.example.runningsongs.models.SongStamp;

import java.util.ArrayList;
import java.util.List;

public class SongStampsListActivity extends AppCompatActivity {

    SongStamp[] stampsList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_stamps_list);

        Intent i = getIntent();
        stampsList = (SongStamp[]) i.getSerializableExtra("STAMPS");
        populateListView();
    }

    private void populateListView() {
        List<String> titles = new ArrayList<String>();

        for(SongStamp ss: stampsList) {
            titles.add(ss.song.title);
        }

        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,titles);
        listView = (ListView) findViewById(R.id.songsListView);
        listView.setAdapter(adapter);
    }


}
