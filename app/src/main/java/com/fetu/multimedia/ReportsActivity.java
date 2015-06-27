package com.fetu.multimedia;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.fetu.manager.Manager;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

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


    public void onButtonReportViewClicked(View v){

        TextView report = new TextView(this);
        report.setText(Manager.getInstance().reportAll());
        ScrollView sv = (ScrollView) findViewById(R.id.svReports);
        sv.addView(report);

    }

    public void onButtonReportSaveClicked(View v){


        FileOutputStream fos;

        try{
            // En memoria interna
            //fos = openFileOutput("reporte.txt", Context.MODE_PRIVATE);

            //En memoria externa
            fos = new FileOutputStream(Environment.getExternalStorageDirectory() + "/reporteMultimedia.txt",true);
            fos.write(Manager.getInstance().reportAll().getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Toast.makeText(this,"Guardado en" + Environment.getExternalStorageDirectory() + "/reporteMultimedia.txt",Toast.LENGTH_LONG).show();

    }


    public void onButtonRankingViewClicked(View v) {

        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        TextView report = new TextView(this);
        report.setText(Manager.getInstance().reportRanking(new Date(datePicker.getYear(),datePicker.getMonth(),datePicker.getDayOfMonth())));
        ScrollView sv = (ScrollView) findViewById(R.id.svReports);
        sv.addView(report);


    }

    public void onButtonRankingSaveClicked(View v){


        FileOutputStream fos;

        try{
            DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);

            fos = new FileOutputStream(Environment.getExternalStorageDirectory() + "/reporteRanking.txt",true);
            fos.write(Manager.getInstance().reportRanking(new Date(datePicker.getYear(),datePicker.getMonth(),datePicker.getDayOfMonth())).getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Toast.makeText(this,"Guardado en" + Environment.getExternalStorageDirectory() + "/reporteMultimedia.txt",Toast.LENGTH_LONG).show();

    }


}
