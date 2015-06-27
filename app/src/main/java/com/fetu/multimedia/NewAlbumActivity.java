package com.fetu.multimedia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fetu.manager.Album;
import com.fetu.manager.Manager;

/**
 * Created by fetu on 21/05/15.
 */
public class NewAlbumActivity extends Activity {


    Long id_container; // Id del container donde se creara el album

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_album);

        Bundle extras = getIntent().getExtras();
        id_container = extras.getLong("container");

    }


    public void onButtonCreateClicked(View v){

        EditText name = (EditText) findViewById(R.id.name);


        if (id_container != 0){
            Album album = Manager.getInstance().getAlbumById(id_container);
            album.addAlbum(name.getText().toString(),album.getId());
        } else {
            Manager.getInstance().addAlbum(name.getText().toString(),null);
        }


        Toast.makeText(this,"El Album fue creado con éxito!",Toast.LENGTH_LONG).show();

        finish();

        Intent i = new Intent(this,MainActivity.class);
        startActivity(i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_reports, menu);

        return  super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {



        switch (item.getItemId()){

            case R.id.action_settings :
                Toast.makeText(this, "Configuración", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_exit :
                Toast.makeText(this,"Salir de la app",Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_library :

                finish();

                break;
        }

        return super.onOptionsItemSelected(item);
    }



}
