package com.fetu.multimedia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fetu.manager.AudioFile;
import com.fetu.manager.File;
import com.fetu.manager.Manager;

/**
 * Created by fetu on 21/05/15.
 */
public class FileConfigActivity extends Activity {


    EditText initRep;
    EditText endRep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_config);

        Bundle extras = getIntent().getExtras();

        Long id_file = (Long) extras.getSerializable("id");
        String name = (String) extras.getSerializable("name");

        final File file = Manager.getInstance().getFileById(id_file,name);

        LinearLayout lLayout = (LinearLayout) findViewById(R.id.llayout);

        if (file instanceof AudioFile){

            TextView textView = new TextView(this);
            textView.setText("Inicio de la reproducción");
            lLayout.addView(textView);

            initRep = new EditText(this);
            initRep.setInputType(InputType.TYPE_CLASS_NUMBER);
            lLayout.addView(initRep);

            TextView textView2 = new TextView(this);
            textView2.setText("Fin de la reproducción");
            lLayout.addView(textView2);

            endRep = new EditText(this);
            endRep.setInputType(InputType.TYPE_CLASS_NUMBER);
            lLayout.addView(endRep);

            Button button = new Button(this);
            button.setText("CONFIGURAR");
            lLayout.addView(button);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if ( (Integer.valueOf(initRep.getText().toString()) < 0 || Integer.valueOf(initRep.getText().toString()) > ((AudioFile) file).getDuration())
                            && ( Integer.valueOf(endRep.getText().toString()) < 0 || Integer.valueOf(endRep.getText().toString()) < ((AudioFile) file).getDuration())
                    && Integer.valueOf(initRep.getText().toString()) >= Integer.valueOf(endRep.getText().toString())
                            && ( initRep.getText().toString().trim().length() == 0 || endRep.getText().toString().trim().length() == 0 )){
                        Toast.makeText(getApplicationContext(),"Valor incorrecto",Toast.LENGTH_SHORT).show();
                    } else {
                        ((AudioFile) file).setInitRep(Integer.valueOf(initRep.getText().toString()));
                        ((AudioFile) file).setEndRep(Integer.valueOf(endRep.getText().toString()));

                        finish();

                        Intent i = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

                    }

                }
            });

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
