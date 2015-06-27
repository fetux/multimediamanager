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
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fetu.manager.Album;
import com.fetu.manager.AudioFile;
import com.fetu.manager.File;
import com.fetu.manager.ImageFile;
import com.fetu.manager.Manager;
import com.fetu.manager.Nodo;
import com.fetu.manager.VideoFile;

import java.util.Iterator;
import java.util.TreeSet;


public class MainActivity extends ListActivity{


    //public static Manager Manager.getInstance() = new Manager(800000);


    static TreeSet<Nodo> library;
    Long id_container;
    Long id_up_container;

    public static TreeSet<Nodo> getLibrary() {
        return library;
    }

    public static void setLibrary(TreeSet<Nodo> library) {
        library = library;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id_container = new Long(0);
        id_up_container = null;

        if (library == null){
            library = new TreeSet<Nodo>();
            library.addAll(new TreeSet<Nodo>(Manager.getInstance().getFiles()));
            library.addAll(new TreeSet<Nodo>(Manager.getInstance().getAlbums()));
        }



        setListAdapter(new FileAdapter(this, library));

        registerForContextMenu(getListView());

    }

    @Override
    protected void onResume() {
        super.onResume();
        ((BaseAdapter)getListAdapter()).notifyDataSetChanged();
    }

    public void updateList(){
        Manager.getInstance().loadFromDb();
        library.clear();
        library.addAll(Manager.getInstance().getFiles());
        library.addAll(Manager.getInstance().getAlbums());
        setListAdapter(new FileAdapter(this, library));
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

            case R.id.action_up_level :

                if (this.id_container != 0 ){

                    if (this.id_up_container != null){

                        Album album = Manager.getInstance().getAlbumById(id_up_container);

                        if (album == null){

                            Iterator<Album> parents = Manager.getInstance().getAlbums().iterator();
                            Boolean found = false;
                            while (parents.hasNext() && !found){
                                Album parent = parents.next();
                                album = parent.getAlbumById(id_up_container);
                                if (album != null) found = true;
                            }

                        }

                        library.clear();
                        library.addAll(album.getFiles());
                        library.addAll(album.getAlbums());
                        id_container = album.getId();
                        id_up_container = album.getContainer();
                        setListAdapter(new FileAdapter(this, library));
                        registerForContextMenu(getListView());

                    }
                    else {
                        library.clear();
                        library.addAll(Manager.getInstance().getFiles());
                        library.addAll(Manager.getInstance().getAlbums());
                        id_container =  new Long(0);
                        id_up_container = null;
                        setListAdapter(new FileAdapter(this, library));
                        registerForContextMenu(getListView());
                    }


                } else {

                }

                break;


            case R.id.action_settings :
                Toast.makeText(this, "Configuración", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_exit :
                finish();
                break;

            case R.id.action_new_file :
                Intent n = new Intent(this,NewFileActivity.class);
                startActivity(n);
                 break;

            case R.id.action_new_folder :
                Intent a = new Intent(this,NewAlbumActivity.class);
                a.putExtra("container",id_container);
                startActivity(a);
                break;

            case R.id.action_play :

                //Manager.getInstance().setList(Manager.getInstance().getFiles());
                Manager.getInstance().getList().clear();
                Iterator<Nodo> nodos = library.iterator();
                while (nodos.hasNext()){
                    Nodo nodo = nodos.next();
                    if (nodo instanceof File){
                        Manager.getInstance().getList().add((File) nodo);
                    }
                }

                Intent i = new Intent(this,PlayActivity.class);
                startActivity(i);

                break;

            case R.id.action_play_sorted:

                Manager.getInstance().getList().clear();
                Intent is = new Intent(this,PlaySortedActivity.class);
                startActivity(is);
                break;

            case R.id.action_sort :


                Intent s = new Intent(this,SortActivity.class);
                startActivity(s);
                break;

            case R.id.action_filter :

                Intent f = new Intent(this,FilterActivity.class);
                startActivity(f);

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


            Nodo nodo = (Nodo) getListAdapter().getItem((int) info.id);

            if (nodo instanceof File) {

                menu.add(Menu.NONE, 1, 1, "Reproducir");
                menu.add(Menu.NONE, 2, 2, "Añadir a la lista de reproducción");
                menu.add(Menu.NONE, 3, 3, "Añadir a Album");
                menu.add(Menu.NONE, 4, 4, "Ver detalles");
                menu.add(Menu.NONE, 5, 5, "Configurar");
                menu.add(Menu.NONE, 6, 6, "Eliminar");

            }
            else if (nodo instanceof Album) {

                menu.add(Menu.NONE, 1, 1, "Abrir");
                menu.add(Menu.NONE, 2, 2, "Añadir a la lista de reproducción");
                menu.add(Menu.NONE, 3, 3, "Añadir a Album");
                menu.add(Menu.NONE, 4, 4, "Ver detalles");
                menu.add(Menu.NONE, 5, 5, "Editar");
                menu.add(Menu.NONE, 6, 6, "Eliminar");

            }

        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        // info.id Numero de item en la listview
        // item.getItemId() Numero de item en el menu

        long listItemIndex = info.id;
        int menuItemIndex = item.getItemId();

        Nodo nodo = (Nodo) getListAdapter().getItem((int) info.id);

        if (nodo instanceof File){

            File file = (File) nodo;

            switch (item.getItemId()) {

                //Reproducir
                case 1 :

                    TreeSet<File> treeSet = new TreeSet<File>();
                    treeSet.add(file);
                    Manager.getInstance().setList(treeSet);

                    Intent i = new Intent(this,PlayActivity.class);
                    startActivity(i);

                    break;

                // Añadir a la lista de reproducción
                case 2 :

                    Manager.getInstance().getList().add(file);
                    Toast.makeText(this,file.getName()+" se añadio a la lista actual",Toast.LENGTH_SHORT).show();
                    break;

                //Añadir a Album
                case 3 :

                    Intent in = new Intent(this,AlbumActivity.class);
                    if (file instanceof AudioFile){
                        in.putExtra("type","Audio");
                    } else if(file instanceof VideoFile){
                        in.putExtra("type","Video");
                    } else if(file instanceof ImageFile){
                        in.putExtra("type","Image");
                    }
                    in.putExtra("id",file.getId());
                    startActivity(in);


                    break;

                // Ver detalles
                case 4 :

                    Intent d = new Intent(this,FileDetailActivity.class);

                    d.putExtra("name",file.getName());
                    d.putExtra("size",file.getSize());
                    d.putExtra("date_last_mod",file.getDate_last_mod());
                    d.putExtra("date_last_rep",file.getDate_last_rep());
                    d.putExtra("reproductions",file.getReproductions());
                    d.putExtra("hashtags",file.getHashtags());

                    if (file instanceof AudioFile){
                        d.putExtra("type","Audio");
                        d.putExtra("duration",((AudioFile) file).getDuration());
                        d.putExtra("bits_per_second",((AudioFile) file).getBits_per_second());
                    } else if (file instanceof ImageFile){
                        d.putExtra("type","Imagen");
                        d.putExtra("resolution",((ImageFile) file).getResolution());
                        d.putExtra("width",((ImageFile) file).getWidth());
                        d.putExtra("height",((ImageFile) file).getHeight());
                    } else if (file instanceof VideoFile){
                        d.putExtra("type","Video");
                        d.putExtra("duration",((VideoFile) file).getDuration());
                        d.putExtra("resolution",((VideoFile) file).getResolution());
                        d.putExtra("frames",((VideoFile) file).getFrames());
                    }

                    startActivity(d);

                    break;

                // Configurar
                case 5 :

                    Intent c = new Intent(this,FileConfigActivity.class);

                    c.putExtra("id", file.getId());
                    c.putExtra("name",file.getName());

                    startActivity(c);

                    break;

                // Eliminar
                case 6 :

                    /*
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                    alertDialogBuilder.setPositiveButton(R.string.yes,new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            file.delete();

                        }
                    });

                    alertDialogBuilder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    alertDialogBuilder.setMessage(R.string.dialog_delete_file);

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                    */
                    file.delete();
                    updateList();
                    Toast.makeText(this,file.getName()+" fue eliminado",Toast.LENGTH_SHORT).show();
            }
        } else if(nodo instanceof Album){

            Album album = (Album) nodo;

            switch (item.getItemId()) {

                //Abrir Album
                case 1:
                    library.clear();
                    library.addAll(album.getFiles());
                    library.addAll(album.getAlbums());
                    id_up_container = album.getContainer();
                    id_container = album.getId();
                    setListAdapter(new FileAdapter(this, library));
                    registerForContextMenu(getListView());

                    break;
                // Eliminar un Album
                case 6:
                    Manager.getInstance().removeAlbum(album.getId());
                    Manager.getInstance().loadFromDb();
                    library.clear();
                    library.addAll(album.getFiles());
                    library.addAll(album.getAlbums());
                    setListAdapter(new FileAdapter(this, library));
                    registerForContextMenu(getListView());

            }

        }



        Toast.makeText(this,item.getTitle(),Toast.LENGTH_SHORT).show();
        return true;
    }
}
