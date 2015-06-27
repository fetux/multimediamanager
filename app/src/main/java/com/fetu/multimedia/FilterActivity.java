package com.fetu.multimedia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.fetu.manager.Manager;
import com.fetu.manager.Nodo;

import java.util.Calendar;
import java.util.Date;
import java.util.TreeSet;

/**
 * Created by fetu on 21/05/15.
 */
public class FilterActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        /*
        TextView textView = new TextView(this);

        textView.setText(Manager.getInstance().playList(Manager.getInstance().getList()));

        ScrollView scrollView = (ScrollView) findViewById(R.id.reproduccion);

        scrollView.addView(textView);
*/

    }


    public void onButtonSizeClicked(View view) {

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroupSize);
        int radioButtonID = radioGroup.getCheckedRadioButtonId();

        EditText editText = (EditText) findViewById(R.id.editTextSize);
        String size = editText.getText().toString();

        if (size.isEmpty() || radioButtonID == -1) {
            Toast.makeText(this,"Debe completar el operador y el tamaño para buscar.",Toast.LENGTH_SHORT).show();
        } else {

            View radioButton = radioGroup.findViewById(radioButtonID);
            int idx = radioGroup.indexOfChild(radioButton);

            TreeSet<Nodo> result = new TreeSet<Nodo>();

            switch (idx){
                case 0:
                    result = Manager.getInstance().searchFilesBySize("<",Integer.valueOf(size));
                    break;
                case 1:
                    result = Manager.getInstance().searchFilesBySize("<=",Integer.valueOf(size));
                    break;
                case 2:
                    result = Manager.getInstance().searchFilesBySize(">",Integer.valueOf(size));
                    break;
                case 3:
                    result = Manager.getInstance().searchFilesBySize(">=",Integer.valueOf(size));
                    break;
            }

            MainActivity.library = result;

            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);


        }



    }


    public void onButtonNameClicked(View view) {

        EditText editText = (EditText) findViewById(R.id.editTextName);
        String name = editText.getText().toString();

        if (name.isEmpty()){
            Toast.makeText(this,"Debe completar el nombre para buscar.",Toast.LENGTH_SHORT).show();
        } else {
            MainActivity.library = Manager.getInstance().searchFilesByName(name);
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        }


    }

    public void onButtonHashtagClicked(View view) {

        EditText editText = (EditText) findViewById(R.id.editTextHashtag);
        String hashtag = editText.getText().toString();

        if (hashtag.isEmpty()){
            Toast.makeText(this,"Debe completar el hashtag para buscar.",Toast.LENGTH_SHORT).show();
        } else {
            MainActivity.library = Manager.getInstance().searchFilesByHashtag(hashtag);
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        }


    }


    public void onButtonDateModClicked(View view) {

        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
        cal.set(Calendar.MONTH, datePicker.getMonth());
        cal.set(Calendar.YEAR, datePicker.getYear());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);

        if (!validateDate(cal.getTime())){
            Toast.makeText(this,"La fecha no puede ser en el futuro.",Toast.LENGTH_SHORT).show();
        } else {
            MainActivity.library.clear();
            MainActivity.library = Manager.getInstance().searchFilesByDateMod(cal.getTime());
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        }

    }

    public void onButtonDateRepClicked(View view) {

        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
        cal.set(Calendar.MONTH, datePicker.getMonth());
        cal.set(Calendar.YEAR, datePicker.getYear());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);

        if (!validateDate(cal.getTime())){
            Toast.makeText(this,"La fecha no puede ser en el futuro.",Toast.LENGTH_SHORT).show();
        } else {

            MainActivity.library.clear();
            MainActivity.library = Manager.getInstance().searchFilesByDateRep(cal.getTime());
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        }

    }


    public Boolean validateDate(Date date){

        Date now = new Date(System.currentTimeMillis());
        return ( date.compareTo(now) == -1) ? true : false;

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
