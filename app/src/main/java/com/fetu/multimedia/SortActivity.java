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

import com.fetu.manager.File;
import com.fetu.manager.Manager;
import com.fetu.manager.Nodo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;


public class SortActivity extends Activity {

    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);


        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayList<String> list = new ArrayList<String>();

        list.add("Seleccione un parametro de ordenamiento");
        list.add("Nombre");
        list.add("Tamaño");
        list.add("Hashtags");
        list.add("Cant. de reproducciones");
        list.add("Fecha de reproducción");
        list.add("Fecha de modificación");



        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "Selecciono el item: " + position + " - " + id, Toast.LENGTH_LONG).show();



                if (id != 0){


                    TreeSet<Nodo> toSort = new TreeSet<Nodo>();

                    Iterator<Nodo> it = MainActivity.library.iterator();

                    while (it.hasNext()){

                        Nodo nodo = it.next();

                        if (nodo instanceof File) toSort.add(nodo);

                    }



                    switch ((int) id){

                        case 1:
                            //Nombre
                            MainActivity.library = Manager.getInstance().sortFiles("name",toSort);
                            break;
                        case 2:
                            MainActivity.library = Manager.getInstance().sortFiles("size",toSort);
                            break;
                        case 3:
                            MainActivity.library = Manager.getInstance().sortFiles("hashtags",toSort);
                            break;
                        case 4:
                            MainActivity.library = Manager.getInstance().sortFiles("reproductions",toSort);
                            break;
                        case 5:
                            MainActivity.library = Manager.getInstance().sortFiles("daterep",toSort);
                            break;
                        case 6:
                            MainActivity.library = Manager.getInstance().sortFiles("datemod",toSort);
                            break;

                    }

                    //Manager.getInstance().setList((TreeSet)MainActivity.library);

                    finish();

                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

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
        getMenuInflater().inflate(R.menu.menu_playsorted, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_play) {

            Intent i = new Intent(this,PlaySortedActivity.class);
            startActivity(i);

        }

        return super.onOptionsItemSelected(item);
    }


}
