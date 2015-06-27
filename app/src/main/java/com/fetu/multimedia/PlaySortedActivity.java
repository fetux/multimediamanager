package com.fetu.multimedia;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.fetu.manager.Manager;

import java.util.TreeSet;

/**
 * Created by fetu on 21/05/15.
 */
public class PlaySortedActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        TextView textView = new TextView(this);

        textView.setText(Manager.getInstance().playList((TreeSet)MainActivity.library));

        ScrollView scrollView = (ScrollView) findViewById(R.id.reproduccion);

        scrollView.addView(textView);


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
