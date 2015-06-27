package com.fetu.multimedia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.fetu.manager.Album;
import com.fetu.manager.AudioFile;
import com.fetu.manager.File;
import com.fetu.manager.ImageFile;
import com.fetu.manager.VideoFile;

import java.util.ArrayList;
import java.util.Iterator;


public class AlbumActivity extends Activity {

    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        Bundle extras = getIntent().getExtras();

        Long id_file = (Long) extras.getSerializable("id");



        switch(extras.getString("type")){
            case "Audio":
                file = AudioFile.findById(AudioFile.class,id_file);
                break;
            case "Video":
                file = VideoFile.findById(VideoFile.class, id_file);
                break;
            case "Image":
                file = ImageFile.findById(ImageFile.class, id_file);
                break;
        }



        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayList<String> list = new ArrayList<String>();

        list.add("Seleccione un Album");

        Iterator<Album> albums = Album.findAll(Album.class);
        while (albums.hasNext()){
            list.add(((Album) albums.next()).getName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "Selecciono el item: " + position + " - " + id, Toast.LENGTH_LONG).show();

                if (id != 0){

                    if (file.getContainers().isEmpty()){
                        file.setContainers(String.valueOf(id));
                    } else {

                        String[] containers = file.getContainers().split(",");

                        int i=0;
                        Boolean found = false;
                        while ( i < containers.length && !found){
                            if (containers[i].equals(String.valueOf(id))) found = true;
                            i++;
                        }

                        if (!found){
                            file.setContainers(file.getContainers()+","+String.valueOf(id));
                            file.save();


                            Intent in = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));


                        } else {
                            Toast.makeText(getApplicationContext(),"El archivo ya s encuentra en el Album seleccionado",Toast.LENGTH_SHORT).show();
                        }


                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_album, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
