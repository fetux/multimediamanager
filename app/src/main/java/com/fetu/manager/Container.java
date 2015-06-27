
package com.fetu.manager;

import com.orm.dsl.Ignore;

import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;

;

/**
 * @author Federico Daguerre, Gerónimo Lazzarino, Gonzalo Aristoy.
 * @version 1.0 23/06/2015
 * @see Nodo: Superclase; Manager: Subclase; Album: Subclase.
 */
public abstract class Container extends Nodo {

    @Ignore
    private TreeSet<File> files; //Sort by File name
    @Ignore
    private TreeSet<Album> albums;

    public TreeSet<File> getFiles() {return (files == null) ? files = new TreeSet<File>() : files;}

    public TreeSet<Album> getAlbums() { return (albums == null) ? albums = new TreeSet<Album>() : albums;}

    public void setAlbums(TreeSet<Album> albums) { this.albums = albums;}

    public void setFiles(TreeSet<File> files) {this.files = files;}

    public Container(){

    files = new TreeSet<File>();
    albums = new TreeSet<Album>();


    }

    /**
     * Obtiene el total de archivos por tipo.
     * Por parametro se le pasa un entero: 0 = todos los tipos, 1 = tipo imagen, 2 = tipo audio, 3 = tipo video.
     * @param Integer: type.
     * @return Integer
     */
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

    /**
     * Obtiene el total de archivos por tipo.
     * Por parametro se le pasa un entero: 0 = todos los tipos, 1 = tipo imagen, 2 = tipo audio, 3 = tipo video.
     * @param Integer: type.
     * @return Integer
     */
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

    /**
     * Remueve un archivo pasado por parámetro.
     * Por parametro se le pasa un entero: ID del archivo.
     * @param Long: id_file.
     * @return Integer.
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



    /**
     * Agrega un álbum nuevo al contenedor.
     * Por parametro se le pasa un String con el nombre y el número del contenedor.
     * @param String: name; Long: container.
     * @return Boolean.
     */
    public Boolean addAlbum(String name, Long container){

        Album a = new Album(name,container);

        a.save();

        Manager.getInstance().loadFromDb();

        //return this.albums.add(a) ? true : false;

        if (this.albums.add(a)){
            this.save();
            return true;
        } else
            return false;


    }


    /**
     * Remueve un álbum del contenedor.
     * Por parametro se le pasa el ID de un Álbum.
     * @param Long: id_album.
     * @return Integer.
     */
    public Integer removeAlbum(Long id_album){

        //Album a = new Album(id_album);
        Album a = Album.findById(Album.class,id_album);

        a.delete();

        return (a != null && this.getAlbums().remove(a)) ? 1 : 0;

    }



    /**
     * Devuelve un archivo seleccionado por ID.
     * Por parámetro se le pasa un ID .
     * @param Long: id_file.
     * @return File.
     */
    public File getFileById(Long id_file,String name){

        File f = new File(id_file,name);

        File fAux = null;
        Boolean found = false;


        Iterator<File> iterator = this.getFiles().iterator();
        while(iterator.hasNext() && !found){

            fAux = (File) iterator.next();
            if(f.equals(fAux)) found = true;

        }

        return found ? fAux : null;

    }


    /**
     * Devuelve un álbum seleccionado por ID.
     * Por parametro se le pasa un ID.
     * @param Long: id_file.
     * @return Album.
     */
    public Album getAlbumById(Long id_album){

        Album a = new Album(id_album);
        Album aAux = null;
        Boolean found = false;
        Iterator<Album> iterator = this.getAlbums().iterator();
        while(iterator.hasNext() && !found){

            aAux = (Album) iterator.next();

            if(aAux.equals(a)) found = true;

        }

        return found ? aAux : null;

    }

    /**
     * Devuelve un árbol de archivos buscado por tamaño.
     * Por parametro se le pasa un operador y el tamaño.
     * @param String: operator; Integer: size.
     * @return TreeSet<Nodo>.
     */
    public TreeSet<Nodo> searchFilesBySize(String operator,Integer size){

        TreeSet<Nodo> results = new TreeSet<Nodo>();

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


    /**
     * Devuelve un árbol de archivos buscado por nombre.
     * Por parametro se le pasa un nombre.
     * @param String: name.
     * @return TreeSet<Nodo>.
     */
    public TreeSet<Nodo> searchFilesByName(String name){

        TreeSet<Nodo> results = new TreeSet<Nodo>();

        Iterator<File> iterator = this.getFiles().iterator();
        while(iterator.hasNext()){

            File f = (File) iterator.next();
            if (f.getName().contains(name)) results.add(f);
        }

        return results;

    }

    /**
     * Devuelve un árbol de archivos buscado por hashtags
     * Por parametro se le pasa el hashtag a buscar. Total o parcial
     * @param String: hashtag.
     * @return TreeSet<Nodo>.
     */
    public TreeSet<Nodo> searchFilesByHashtag(String hashtag){

        TreeSet<Nodo> results = new TreeSet<Nodo>();

        Iterator<File> iterator = this.getFiles().iterator();
        while(iterator.hasNext()){

            File f = (File) iterator.next();
            if (f.getHashtags().contains(hashtag)) results.add(f);
        }

        return results;

    }

    /**
     * Devuelve un árbol de archivos buscado fecha de modificación.
     * Por parametro se le pasa una fecha.
     * @param Date: date.
     * @return TreeSet<Nodo>.
     */
    public TreeSet<Nodo> searchFilesByDateMod(Date date){

        TreeSet<Nodo> results = new TreeSet<Nodo>();

        Iterator<File> iterator = this.getFiles().iterator();
        while(iterator.hasNext()){

            File f = (File) iterator.next();
            if(date.after(f.getDate_last_mod())) results.add(f);
        }

        return results;

    }

    /**
     * Devuelve un árbol de archivos buscado fecha de reproducción.
     * Por parametro se le pasa una fecha.
     * @param Date: date.
     * @return TreeSet<Nodo>.
     */
    public TreeSet<Nodo> searchFilesByDateRep(Date date){

        TreeSet<Nodo> results = new TreeSet<Nodo>();

        Iterator<File> iterator = this.getFiles().iterator();
        while(iterator.hasNext()){

            File f = (File) iterator.next();
            if(date.after(f.getDate_last_rep())) results.add(f);
        }

        return results;

    }

    /**
     * Ordena un árbol de archivos por diferentes indicadores: nombre, fecha y hastags.
     * Por parametro se le pasa un parámetro de búsqueda y un árbol de archivos
     * @param final String: by; TreeSet<File>: files.
     * @return TreeSet<Nodo>.
     */
    public TreeSet<Nodo> sortFiles(final String by,TreeSet<Nodo> files){

        TreeSet<Nodo> sortedSet = new TreeSet<Nodo>(new Comparator<Nodo>() {
            @Override
            public int compare(Nodo lhs, Nodo rhs) {


                switch (by){
                    case "name": return lhs.getName().compareTo(rhs.getName());


                    case "size":
                        int compSize = ((File)lhs).getSize().compareTo(((File)rhs).getSize());
                        return (compSize != 0) ? compSize : lhs.getName().compareTo(rhs.getName());
                    case "hashtags":
                        int compHash = ((File)lhs).getHashtags().compareTo(((File)rhs).getHashtags());
                        return (compHash != 0) ? compHash : lhs.getName().compareTo(rhs.getName());
                    case "reproductions":
                        int compRep = ((File)lhs).getReproductions().compareTo(((File)rhs).getReproductions());
                        return (compRep != 0) ? compRep : lhs.getName().compareTo(rhs.getName());
                    case "daterep":
                        int compDrep = ((File)lhs).getDate_last_rep().compareTo(((File)rhs).getDate_last_rep());
                        return (compDrep != 0) ? compDrep : lhs.getName().compareTo(rhs.getName());
                    case "datemod":
                        int compDmod = ((File)lhs).getDate_last_mod().compareTo(((File)rhs).getDate_last_mod());
                        return (compDmod != 0) ? compDmod : lhs.getName().compareTo(rhs.getName());

                }
                return 0;
            }
        });

        sortedSet.addAll(files);

        return sortedSet;

    }

}