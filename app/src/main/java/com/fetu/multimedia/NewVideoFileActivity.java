package com.fetu.multimedia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fetu.manager.Manager;

/**
 * Created by fetu on 24/05/15.
 */
public class NewVideoFileActivity extends Activity{

    TextView name;
    TextView size;
    TextView path;
    TextView hashtags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.new_video_file);

        Bundle extras = getIntent().getExtras();

        name = (TextView) findViewById(R.id.name);
        name.setText(extras.getString("name"));

        size = (TextView) findViewById(R.id.size);
        size.setText(extras.getString("size"));

        path = (TextView) findViewById(R.id.path);
        path.setText(extras.getString("path"));

        hashtags = (TextView) findViewById(R.id.hashtags);
        hashtags.setText(extras.getString("hashtags"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        return super.onMenuItemSelected(featureId, item);



    }

    public void onButtonCreateClicked(View v){

        TextView duration = (TextView) findViewById(R.id.duration);
        TextView resolution = (TextView) findViewById(R.id.resolution);
        TextView frames = (TextView) findViewById(R.id.frames);

        Manager.getInstance().addFile(
                name.getText().toString(),
                Integer.parseInt(size.getText().toString()),
                path.getText().toString(),
                hashtags.getText().toString(),
                Integer.parseInt(duration.getText().toString()),
                resolution.getText().toString(),
                Integer.parseInt(frames.getText().toString())
        );

        Toast.makeText(this, "El archivo de VIDEO fue creado con éxito!", Toast.LENGTH_LONG).show();

        finish();

        Intent i = new Intent(this,MainActivity.class);
        startActivity(i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

    }


}
