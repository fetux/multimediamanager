package com.fetu.manager;

import android.util.Log;

import com.fetu.multimedia.MainActivity;

import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;


public class Manager extends Container {

    private static Manager instance;
    private Integer disk_space;
    private TreeSet<File> list;  /*Current list */

    public TreeSet<File> getList() {
        return list;
    }

    public void setList(TreeSet<File> list) {
        this.list = list;
    }

    private Manager(Integer ds){
        super();
        disk_space = ds;
        setId(new Long(0));
        list = new TreeSet<File>();
        loadFromDb();

    }


    public static Manager getInstance(){

        return (instance == null) ? instance = new Manager(80000) : instance;

    }


    public void loadFromDb(){
        Iterator<Album> albums = Album.findAll(Album.class);
        while(albums.hasNext()){

            Album a = albums.next();

            if (a.getContainer() == null){
                this.getAlbums().add(a);
            } else {

                Album container = this.getAlbumById(a.getContainer());

                if (container != null){
                    container.getAlbums().add(a);
                } else {

                    Iterator<Album> parents = this.getAlbums().iterator();
                    Boolean found = false;
                    while (parents.hasNext() && !found){
                        Album parent = parents.next();
                        container = parent.getAlbumById(a.getContainer());
                        if (container != null) found = true;
                    }

                    if (found) container.getAlbums().add(a);
                }

            }
        }


        Iterator<AudioFile> audios = AudioFile.findAll(AudioFile.class);

        while(audios.hasNext()){

            AudioFile a = audios.next();

            this.getFiles().add(a);

            String containers = a.getContainers();
            String[] splittedContainers = containers.split(",");

            if (splittedContainers[0] != "") {

                for (String value : splittedContainers) {

                    Album container = this.getAlbumById(Long.valueOf(value));

                    if (container != null) {
                        container.linkFile(a);
                    } else {

                        Iterator<Album> parents = this.getAlbums().iterator();
                        Boolean found = false;
                        while (parents.hasNext() && !found) {
                            Album parent = parents.next();
                            container = parent.getAlbumById(Long.valueOf(value));
                            if (container != null) {
                                found = true;
                            } else {
                                Iterator<Album> grandparents = parent.getAlbums().iterator();
                                while (grandparents.hasNext() && !found) {
                                    Album gparent = grandparents.next();
                                    container = gparent.getAlbumById(Long.valueOf(value));
                                    if (container != null) found = true;
                                }
                            }
                        }

                        if (found) container.linkFile(a);
                    }
                }
            }

        }

        Iterator<VideoFile> videos = VideoFile.findAll(VideoFile.class);

        while(videos.hasNext()){

            VideoFile v = videos.next();

            this.getFiles().add(v);

            String containers = v.getContainers();
            String[] splittedContainers = containers.split(",");

            if (splittedContainers[0] != ""){
                for(String value : splittedContainers)
                {

                    Album container = this.getAlbumById(Long.valueOf(value));

                    if (container != null){
                        container.linkFile(v);
                    } else {

                        Iterator<Album> parents = this.getAlbums().iterator();
                        Boolean found = false;
                        while (parents.hasNext() && !found){
                            Album parent = parents.next();
                            container = parent.getAlbumById(Long.valueOf(value));
                            if (container != null) {
                                found = true;
                            } else {
                                Iterator<Album> grandparents = parent.getAlbums().iterator();
                                while (grandparents.hasNext() && !found) {
                                    Album gparent = grandparents.next();
                                    container = gparent.getAlbumById(Long.valueOf(value));
                                    if (container != null) found = true;
                                }
                            }
                        }

                        if (found) container.linkFile(v);
                    }
                }
            }

        }

        Iterator<ImageFile> images = ImageFile.findAll(ImageFile.class);

        while(images.hasNext()){

            ImageFile i = images.next();

            this.getFiles().add(i);

            String containers = i.getContainers();
            String[] splittedContainers = containers.split(",");

            if (splittedContainers[0] != "") {

                for (String value : splittedContainers) {

                    Album container = this.getAlbumById(Long.valueOf(value));

                    if (container != null) {
                        container.linkFile(i);
                    } else {

                        Iterator<Album> parents = this.getAlbums().iterator();
                        Boolean found = false;
                        while (parents.hasNext() && !found) {
                            Album parent = parents.next();
                            container = parent.getAlbumById(Long.valueOf(value));

                            if (container != null){
                                found = true;
                            }
                            else {
                                Iterator<Album> grandparents = parent.getAlbums().iterator();
                                while (grandparents.hasNext() && !found) {
                                    Album gparent = grandparents.next();
                                    container = gparent.getAlbumById(Long.valueOf(value));
                                    if (container != null) found = true;
                                }
                            }
                        }

                        if (found) container.linkFile(i);
                    }
                }
            }
        }
    }

    public Integer getDiskSpace(){return disk_space;}


    public Boolean addFile(String name, Integer size, String path, String hashtags, Integer bits_per_second, Integer duration){

        AudioFile f = new AudioFile(name,size,path,hashtags,bits_per_second,duration);

        f.save();

        MainActivity.getLibrary().add(f);

        return this.getFiles().add(f) ? true : false;

    }

    public Boolean addFile(String name, Integer size, String path, String hashtags, Integer resolution, Integer width, Integer height){

        ImageFile f = new ImageFile(name,size,path,hashtags,resolution,width,height);

        f.save();

        MainActivity.getLibrary().add(f);

        return this.getFiles().add(f) ? true : false;
    }

    public Boolean addFile(String name, Integer size, String path, String hashtags, Integer duration, String resolution, Integer frames){

        VideoFile f = new VideoFile(name,size,path,hashtags,duration,resolution,frames);

        f.save();

        MainActivity.getLibrary().add(f);

        return this.getFiles().add(f) ? true : false;

    }


    public String playList(TreeSet<File> list){


        StringBuilder simulation = new StringBuilder();

        Integer hours=0;
        Integer minutes=0;
        Integer seconds = 0;

        Iterator<File> files = list.iterator();
        Log.i("iterator: ",list.toString());

        while(files.hasNext()){

            File f = files.next();

            if (f instanceof VideoFile) {

                VideoFile video = (VideoFile) f;

                seconds += video.getDuration();

                simulation.append("Iniciando simulación del Video\n");
                simulation.append(video.play() + "\n");

                video.setDate_last_rep(new Date());
                video.setReproductions(video.getReproductions() + 1);

                simulation.append("Finalizando simulación del Video\n\n");

            } else if (f instanceof AudioFile){

                AudioFile audio = (AudioFile) f;

                seconds += audio.getDuration();

                simulation.append("Iniciando simulación del Audio\n");
                simulation.append(audio.play() + "\n");

                audio.setDate_last_rep(new Date());
                audio.setReproductions(audio.getReproductions() + 1);

                simulation.append("Finalizando simulación del Audio\n\n");

            } else {

                ImageFile image = (ImageFile) f;

                seconds += image.getDuration();

                simulation.append("Iniciando simulación de Imágen\n");
                simulation.append(image.show() + "\n");

                image.setDate_last_rep(new Date());
                image.setReproductions(image.getReproductions() + 1);

                simulation.append("Finalizando simulación de Imágen\n\n");
            }

        }



        minutes = seconds / 60;
        seconds -= minutes * 60;

        hours = minutes / 60;
        minutes -= hours * 60;


        simulation.insert(0,"TIEMPO TOTAL DE REPRODUCCIÓN "+hours+":"+minutes+":"+seconds+"\n\n");


        return simulation.toString();
    }


    public String reportAll(){

        TreeSet<Nodo> audio = new TreeSet<Nodo>();
        TreeSet<Nodo> video = new TreeSet<Nodo>();
        TreeSet<Nodo> image = new TreeSet<Nodo>();
        StringBuilder report = new StringBuilder();
        Integer totSizeAudio = 0;
        Integer totSizeVideo = 0;
        Integer totSizeImage = 0;


        Iterator<File> iterator = getFiles().iterator();

        while (iterator.hasNext()){

            File f = iterator.next();

            if (f instanceof AudioFile) {
                totSizeAudio += f.getSize();
                audio.add(f);
            }
            else if (f instanceof VideoFile) {
                totSizeVideo += f.getSize();
                video.add(f);
            }
            else if (f instanceof ImageFile){
                totSizeImage += f.getSize();
                image.add(f);
            }


        }

        audio = sortFiles("size",audio);
        video = sortFiles("size",video);
        image = sortFiles("size",image);


        report.append("REPORTE DE ARCHIVOS AGRUPADOS POR TIPO\n\n");
        report.append("ARCHIVOS DE AUDIO\n");
        report.append(audio.toString());
        report.append("Cantidad de archivos: " + audio.size() + "\n");
        report.append("Tamaño promedio: " + totSizeAudio/audio.size() + "\n");
        report.append("ARCHIVOS DE VIDEO\n");
        report.append(video.toString());
        report.append("Cantidad de archivos: " + video.size() + "\n");
        report.append("Tamaño promedio: " + totSizeVideo/video.size() + "\n");
        report.append("ARCHIVOS DE IMAGEN\n");
        report.append(image.toString());
        report.append("Cantidad de archivos: " + image.size() + "\n");
        report.append("Tamaño promedio: " + totSizeImage/image.size() + "\n");

        return report.toString();
    }



    public String reportRanking(Date d){


        StringBuilder report = new StringBuilder();

        TreeSet<Nodo> ranking = this.searchFilesByDateRep(d);

        TreeSet<Nodo> sortedRanking = new TreeSet<Nodo>(new Comparator<Nodo>() {
            @Override
            public int compare(Nodo lhs, Nodo rhs) {

                int compRep = ((File)lhs).getReproductions().compareTo(((File) rhs).getReproductions());

                return  (compRep != 0) ? compRep : lhs.getName().compareTo(rhs.getName());

            }
        });

        sortedRanking.addAll(ranking);

        report.append("RANKING DE ARCHIVOS POR CANTIDAD DE REPRODUCCIONES\n\n");


        Iterator<Nodo> iterator = sortedRanking.iterator();

        while(iterator.hasNext()){


            File f = (File) iterator.next();

            report.append(f.getName() + "\t#: " + f.getReproductions() + "\n");

        }

        return report.toString();
    }


}
