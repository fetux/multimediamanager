package com.fetu.multimedia;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by fetu on 21/05/15.
 */
public class FileDetailActivity extends Activity {


    TextView name;
    TextView size;
    TextView type;
    TextView date_last_mod;
    TextView date_last_rep;
    TextView hashtags;
    TextView duration;
    TextView resolution;
    TextView frames;
    TextView width;
    TextView height;
    TextView bits_per_second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_detail);

        Bundle extras = getIntent().getExtras();

        type = (TextView) findViewById(R.id.type);
        type.setText(extras.getString("type"));

        name = (TextView) findViewById(R.id.name);
        name.setText(extras.getString("name"));
        size = (TextView) findViewById(R.id.size);
        size.setText(extras.getString("size"));


        date_last_mod = (TextView) findViewById(R.id.date_last_mod);
        date_last_mod.setText(extras.getSerializable("date_last_mod").toString());
        date_last_rep = (TextView) findViewById(R.id.date_last_rep);
        date_last_rep.setText(extras.getSerializable("date_last_rep").toString());

        hashtags = (TextView) findViewById(R.id.hashtags);
        hashtags.setText(extras.getString("hashtags"));

        switch (extras.getString("type")){
            case "Audio":
                duration = (TextView) findViewById(R.id.duration);
                duration.setText(extras.getSerializable("duration").toString());
                bits_per_second = (TextView) findViewById(R.id.bits_per_second);
                bits_per_second.setText(extras.getSerializable("bits_per_second").toString());
                break;
            case "Video":
                duration = (TextView) findViewById(R.id.duration);
                duration.setText(extras.getSerializable("duration").toString());
                resolution = (TextView) findViewById(R.id.resolution);
                resolution.setText(extras.getString("resolution"));
                frames = (TextView) findViewById(R.id.frames);
                frames.setText(extras.getSerializable("frames").toString());
                break;
            case "Imagen":
                resolution = (TextView) findViewById(R.id.resolution);
                resolution.setText(extras.getString("resolution"));
                width = (TextView) findViewById(R.id.width);
                width.setText(extras.getSerializable("width").toString());
                height = (TextView) findViewById(R.id.height);
                height.setText(extras.getSerializable("height").toString());
                break;
        }






    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_play, menu);

        return  super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {



        switch (item.getItemId()){

            case R.id.action_settings :
                Toast.makeText(this, "Configuración", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_exit :
                Toast.makeText(this,"Salir de la Manager.getInstance()",Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_new_file :
                Toast.makeText(this,"Añadir nuevo archivo",Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_new_folder :
                Toast.makeText(this,"Añadir nueva carpeta",Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_library :

                //Intent i = new Intent(this,MainActivity.class);
                //startActivity(i);

                finish();

                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
