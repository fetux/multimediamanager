package com.fetu.multimedia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

/**
 * Created by fetu on 24/05/15.
 */
public class NewFileActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.new_file);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        return super.onMenuItemSelected(featureId, item);


    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        Intent i;

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButtonAudio:
                if (checked) {
                    i = new Intent(this, NewAudioFileActivity.class);
                    i.putExtra("name",((EditText) findViewById(R.id.name)).getText().toString());
                    i.putExtra("size",((EditText) findViewById(R.id.size)).getText().toString());
                    i.putExtra("path",((EditText) findViewById(R.id.path)).getText().toString());
                    i.putExtra("hashtags",((EditText) findViewById(R.id.hashtags)).getText().toString());
                    startActivity(i);
                }
                    break;
            case R.id.radioButtonVideo:
                if (checked) {
                    i = new Intent(this, NewVideoFileActivity.class);
                    i.putExtra("name",((EditText) findViewById(R.id.name)).getText().toString());
                    i.putExtra("size",((EditText) findViewById(R.id.size)).getText().toString());
                    i.putExtra("path",((EditText) findViewById(R.id.path)).getText().toString());
                    i.putExtra("hashtags",((EditText) findViewById(R.id.hashtags)).getText().toString());
                    startActivity(i);
                }
                    break;
            case R.id.radioButtonImage:
                if (checked) {
                    i = new Intent(this, NewImageFileActivity.class);
                    i.putExtra("name",((EditText) findViewById(R.id.name)).getText().toString());
                    i.putExtra("size",((EditText) findViewById(R.id.size)).getText().toString());
                    i.putExtra("path",((EditText) findViewById(R.id.path)).getText().toString());
                    i.putExtra("hashtags",((EditText) findViewById(R.id.hashtags)).getText().toString());
                    startActivity(i);
                }
                    break;

        }


    }

}
