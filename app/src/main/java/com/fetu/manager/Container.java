package com.fetu.manager;

import android.util.Log;

import com.orm.SugarRecord;

import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;

;

/**
 * Created by fetu on 26/04/15.
 */
public class Container extends SugarRecord<Container>{

    private TreeSet<File> files; //Sort by File name
    private TreeSet<Album> albums;

    public TreeSet<File> getFiles() {return files;}

    public TreeSet<Album> getAlbums() { return albums;}

    public void setAlbums(TreeSet<Album> albums) { this.albums = albums;}

    public void setFiles(TreeSet<File> files) {this.files = files;}



    public Container(){files = new TreeSet<File>(); albums = new TreeSet<Album>();}


    /* INFORMATION ABOUT FILES */

    public Integer getTotalFiles(Integer type){


        File f;
        Integer total = 0;

        Iterator<File> iterator = files.iterator();
        while(iterator.hasNext()){

            f = (File) iterator.next();
            if(type == 0 || (type== 1 && f instanceof ImageFile) || (type == 2 && f instanceof AudioFile) || (type == 3 && f instanceof VideoFile))
                total ++;

        }

        return total;
    }


    public Integer getTotalSize(){

        File f;
        Integer total = 0;

        Iterator<File> iterator = files.iterator();
        while(iterator.hasNext()){

            f = (File) iterator.next();
            total += f.getSize();

        }

        return total;
    }
    /* No borra los archivos del contenedor del cual es invocado.
    public Integer removeFile(Integer id_file){

        File file = new File(id_file);
        Boolean deleted = false;

        Iterator<Album> albums  = this.getAlbums().iterator();
        while (albums.hasNext()){

            Album a = albums.next();

            Iterator<File> files = a.getFiles().iterator();
            while(files.hasNext() && !deleted){

                File f = files.next();
                if(f.equals(file)){
                    a.getFiles().remove(f);
                    deleted = true;
                }
            }

            a.removeFile(id_file);

        }

        return (deleted) ? 1 : 0;

    }
    */

    public Integer removeFile(Long id_file){

        File file = new File(id_file);
        Boolean deleted = false;

        Iterator<File> files = this.getFiles().iterator();
        while(files.hasNext() && !deleted){

            File f = files.next();
            if(f.equals(file)){
                this.getFiles().remove(f);
                deleted = true;
            }
        }

        Iterator<Album> albums  = this.getAlbums().iterator();
        while (albums.hasNext()){

            Album a = albums.next();

            a.removeFile(id_file);

        }

        return (deleted) ? 1 : 0;

    }


    public Integer addAlbum(String name){

        Album a = new Album((!this.albums.isEmpty()) ? this.albums.last().getId() + new Long(1) : new Long(1),name);

        a.save();

        return this.getAlbums().add(a) ? 1: 0;

    }


    public Integer removeAlbum(Long id_album){

        Album a = new Album(id_album);

        return (a != null && this.getAlbums().remove(a)) ? 1 : 0;

    }



    public File getFileById(Long id_file){

        File f = new File(id_file);
        File fAux = null;
        Boolean found = false;


        Iterator<File> iterator = this.getFiles().iterator();
        while(iterator.hasNext() && !found){

            fAux = (File) iterator.next();
            if(f.equals(fAux)) found = true;

        }

        return found ? fAux : null;

    }

    public Album getAlbumById(Long id_album){

        Album a = new Album(id_album);
        Album aAux = null;
        Boolean found = false;
        Iterator<Album> iterator = this.getAlbums().iterator();
        while(iterator.hasNext() && !found){

            aAux = (Album) iterator.next();
            Log.i("Album: ", aAux.toString());
            if(aAux.equals(a)) found = true;

        }

        return found ? aAux : null;

    }


    /* SEARCH METHODS */

    public TreeSet<File> searchFilesBySize(String operator,Integer size){

        TreeSet<File> results = new TreeSet<File>();

        Iterator<File> iterator = this.getFiles().iterator();
        while(iterator.hasNext()){

            File file = (File) iterator.next();
            switch(operator){
                case "<":   if (file.getSize() < size) results.add(file); break;
                case "<=":  if (file.getSize() <= size) results.add(file); break;
                case ">":  if (file.getSize() > size) results.add(file); break;
                case ">=":  if (file.getSize() >= size) results.add(file); break;
            }


        }

        return results;
    }

    public TreeSet<File> searchFilesByName(String name){

        TreeSet<File> results = new TreeSet<File>();

        Iterator<File> iterator = this.getFiles().iterator();
        while(iterator.hasNext()){

            File f = (File) iterator.next();
            if (f.getName().contains(name)) results.add(f);
        }

        return results;

    }

    public TreeSet<File> searchFilesByDateMod(Date date){

        TreeSet<File> results = new TreeSet<File>();

        Iterator<File> iterator = this.getFiles().iterator();
        while(iterator.hasNext()){

            File f = (File) iterator.next();
            if(date.after(f.getDate_last_mod())) results.add(f);
        }

        return results;

    }

    public TreeSet<File> searchFilesByDateRep(Date date){

        TreeSet<File> results = new TreeSet<File>();

        Iterator<File> iterator = this.getFiles().iterator();
        while(iterator.hasNext()){

            File f = (File) iterator.next();
            if(date.after(f.getDate_last_rep())) results.add(f);
        }

        return results;

    }


    public TreeSet<File> sortFiles(final String by){

        TreeSet<File> sortedSet = new TreeSet<File>(new Comparator<File>() {
            @Override
            public int compare(File lhs, File rhs) {

                switch (by){
                    case "name": return lhs.getName().compareTo(rhs.getName());
                    case "size": return lhs.getSize().compareTo(rhs.getSize());
                    //case "path": return lhs.getPath().compareTo(rhs.getPath()); Los paths iguales los elimina
                    case "hashtags": return lhs.getHashtags().compareTo(rhs.getHashtags());

                }
                return 0;
            }
        });

        sortedSet.addAll(files);

        return sortedSet;

    }

}
