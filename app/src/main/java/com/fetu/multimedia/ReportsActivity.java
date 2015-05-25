package com.fetu.multimedia;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by fetu on 21/05/15.
 */
public class ReportsActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);


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
