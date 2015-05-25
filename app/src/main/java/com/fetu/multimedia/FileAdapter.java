package com.fetu.multimedia;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fetu.manager.File;
import com.fetu.manager.ImageFile;
import com.fetu.manager.VideoFile;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by fetu on 09/05/15.
 */
public class FileAdapter extends BaseAdapter{

    private final Activity actividad;
    private final TreeSet<File> lista;


    public FileAdapter(Activity actividad, TreeSet<File> lista) {
        super();
        this.actividad = actividad;
        this.lista = lista;
    }

    @Override

    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {

        int i=0;

        Iterator<File> iterator = lista.iterator();

        while (iterator.hasNext() && i < position){
            i++;
            iterator.next();
        }


        if (i == position) {

            return iterator.next();
        }

        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = actividad.getLayoutInflater();
        View view = inflater.inflate(R.layout.file,null,true);

        int i=0;
        Iterator<File> iterator = lista.iterator();
        while (iterator.hasNext() && i < position){

            iterator.next();
            i++;
        }

        if (i == position) {

            File f = iterator.next();

            TextView textView = (TextView) view.findViewById(R.id.name);
            textView.setText(f.getName());

            TextView textView2 = (TextView) view.findViewById(R.id.hashtags);
            textView2.setText(f.getHashtags());

            ImageView imageView = (ImageView) view.findViewById(R.id.icon);

            if (f instanceof ImageFile){
                imageView.setImageResource(R.drawable.image);
            } else
                if (f instanceof VideoFile){
                    imageView.setImageResource(R.drawable.video);
                } else {
                    imageView.setImageResource(R.drawable.audio);
                }

            return view;

        }

        return null;

    }

}
