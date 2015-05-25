package com.fetu.multimedia;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fetu.manager.File;
import com.fetu.manager.Manager;


public class MainActivity extends ListActivity{


    public static Manager app = new Manager(800000);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);







        app.addFile("Bob Marley - Buffalo Soldier.ogg",4000, "/home/fetu/musica/", "#marley #reggae", 128, 300);
        app.addFile("Andres Calamaro - Flaca.ogg",4680,"/home/fetu/musica/","#calamaro #rock",128,367);
/*
        app.addFile("archivo1.jpg",3754,"/home/fetu/imagenes/","#imagen1",5,1920,1080);
        app.addFile("archivo2.png",5614,"/home/fetu/imagenes/","#imagenPNG",5,1920,1080);

        app.addFile("archivo1.mp4",2876,"/home/fetu/videos/","#video1",397,"1920x1080",60);
        app.addFile("archivo1.ogv",3414,"/home/fetu/videos/","#videoOGV",567,"1920x1080",120);

        TreeSet<File> origin = app.getFiles();

        TreeSet<File> result = app.sortFiles("size");


        Iterator<File> iterator = origin.iterator();
        while(iterator.hasNext()){

            File f = iterator.next();
            Log.i("Original", f.getId().toString());
        }

        Iterator<File> iterator2 = result.iterator();
        while(iterator2.hasNextandroid:longClickable="true"()){

            File f = (File) iterator2.next();
            Log.i("Result", f.toString());
        }

        app.addAlbum("Mi album");

        Album a = app.getAlbumById(new Long(1));

        Log.i("Album obtenido: ", a.toString());

        a.addAlbum("Mi subalbum");

        File f = app.getFileById(new Long(3));

        a.linkFile(f);



        Album sa = a.getAlbumById(new Long(1));

        File ff = app.getFileById(new Long(5));

       //Log.i("ff",ff);

        sa.linkFile(ff);

        iterator = sa.getFiles().iterator();
        while(iterator.hasNext()){

            f = iterator.next();
            Log.i("Original SubAlbum", f.toString());
        }


        TreeSet<File> busqueda = app.searchFilesByName("chivo");

        Iterator<File> iterator3 = busqueda.iterator();
        while(iterator3.hasNext()){

            File fb = (File) iterator3.next();
            Log.i("Busqueda", fb.toString());
        }




        a.addAlbum("Mi SubAlbum 2");

        Album sa2 = a.getAlbumById(new Long(2));

        sa2.linkFile(app.getFileById(new Long(2)));
        sa2.linkFile(app.getFileById(new Long(3)));
        sa2.linkFile(app.getFileById(new Long(5)));


        Log.i("Albums Gestor", app.getAlbums().toString());
        Log.i("Albums Mi Album",a.getAlbums().toString());
        Log.i("Archivos Mi Album",a.getFiles().toString());
        Log.i("Albums Mi SubAlbum 1",sa.getAlbums().toString());
        Log.i("Archivos Mi SubAlbum 1",sa.getFiles().toString());
        Log.i("Albums Mi SubAlbum 2",sa2.getAlbums().toString());
        Log.i("Archivos Mi SubAlbum2",sa2.getFiles().toString());



        app.removeFile(new Long(5));

        Log.i("Despues de eliminar","hola");
        Log.i("Albums Gestor", app.getAlbums().toString());
        Log.i("Archivos Gestor",app.getFiles().toString());
        Log.i("Albums Mi Album",a.getAlbums().toString());
        Log.i("Archivos Mi Album",a.getFiles().toString());
        Log.i("Albums Mi SubAlbum 1",sa.getAlbums().toString());
        Log.i("Archivos Mi SubAlbum 1",sa.getFiles().toString());
        Log.i("Albums Mi SubAlbum 2",sa2.getAlbums().toString());
        Log.i("Archivos Mi SubAlbum2",sa2.getFiles().toString());


        ArrayList<File> files = new ArrayList<File>();
        Iterator<File> iterator4 = origin.iterator();
        while(iterator4.hasNext()){

            File file = iterator4.next();

            files.add(file);

        }
        */

        //app.save();

        //app.playList(origin);


        //setListAdapter(new ArrayAdapter<File>(this,R.layout.file,R.id.name,files));
        setListAdapter(new FileAdapter(this, app.getFiles()));
        registerForContextMenu(getListView());


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return  super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {



        switch (item.getItemId()){

            case R.id.action_settings :
                Toast.makeText(this, "Configuración", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_exit :
                finish();
                break;

            case R.id.action_new_file :
                //Toast.makeText(this,"Añadir nuevo archivo",Toast.LENGTH_SHORT).show();

                Intent n = new Intent(this,NewFileActivity.class);
                startActivity(n);

                break;

            case R.id.action_new_folder :
                Intent a = new Intent(this,NewAlbumActivity.class);
                startActivity(a);
                break;

            case R.id.action_play :

                Intent i = new Intent(this,PlayActivity.class);
                startActivity(i);

                break;

            case R.id.action_reports :

                Intent r = new Intent(this,ReportsActivity.class);
                startActivity(r);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);



        v.showContextMenu();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        if (v.getId()==getListView().getId()) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;

            TextView name = (TextView)  info.targetView.findViewById(R.id.name);
            menu.setHeaderTitle(name.getText().toString());



            menu.add(Menu.NONE, 1, 1, "Reproducir");
            menu.add(Menu.NONE, 2, 2, "Añadir a la lista de reproducción");
            menu.add(Menu.NONE, 3, 3, "Añadir a Album");
            menu.add(Menu.NONE, 4, 4, "Ver detalles");
            menu.add(Menu.NONE, 5, 5, "Editar");
            menu.add(Menu.NONE, 6, 6, "Eliminar");

        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        // info.id Numero de item en la listview
        // item.getItemId() Numero de item en el menu

        long listItemIndex = info.id;
        int menuItemIndex = item.getItemId();

        File file = (File) getListAdapter().getItem((int) info.id);

        switch (item.getItemId()) {

            //Reproducir
            case 1 :

                break;

            // Añadir a la lista de reproducción
            case 2 :

                break;

            //Añadir a Album
            case 3 :

                break;

            // Ver detalles
            case 4 :

                break;

            // Editar
            case 5 :

                break;

            // Eliminar
            case 6 :

                break;

        }

        Toast.makeText(this,item.getTitle(),Toast.LENGTH_SHORT).show();
        return true;
    }
}
