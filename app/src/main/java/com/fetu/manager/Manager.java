package com.fetu.manager;

import android.util.Log;

import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;

;

/**
 * Created by fetu on 22/04/15.
 */
public class Manager extends Container {

    private Integer disk_space;

    public Manager(Integer ds){
        super();
        disk_space = ds;
    }

    public Integer getDiskSpace(){return disk_space;}


    public Integer addFile(String name, Integer size, String path, String hashtags, Integer bits_per_second, Integer duration){

        AudioFile f = new AudioFile((!this.getFiles().isEmpty()) ? this.getFiles().last().getId() + new Long(1) : new Long(1),name,size,path,hashtags,bits_per_second,duration);

        f.save();

        return this.getFiles().add(f) ? 1: 0;

    }

    public Integer addFile(String name, Integer size, String path, String hashtags, Integer resolution, Integer width, Integer height){

        ImageFile f = new ImageFile((!this.getFiles().isEmpty()) ? this.getFiles().last().getId() + new Long(1) : new Long(1),name,size,path,hashtags,resolution,width,height);

        f.save();

        return this.getFiles().add(f) ? 1: 0;
    }

    public Integer addFile(String name, Integer size, String path, String hashtags, Integer duration, String resolution, Integer frames){

        VideoFile f = new VideoFile((!this.getFiles().isEmpty()) ? this.getFiles().last().getId() + new Long(1) : new Long(1),name,size,path,hashtags,duration,resolution,frames);

        f.save();

        return this.getFiles().add(f) ? 1: 0;

    }


    /*
    public void linkFile(Integer id_file, Integer id_album){

        File f = this.getFileById(id_file);
        Album a = this.getAlbumById(id_album);

        if (f != null && a != null && !a.getFiles().contains(f)) a.getFiles().add(f);

    }


    public void unlinkFile(Integer id_file, Integer id_album){

        File f = this.getFileById(id_file);
        Album a = this.getAlbumById(id_album);

        if (f != null && a != null && a.getFiles().contains(f)) a.getFiles().remove(f);

    }
    */



    public String playList(TreeSet<File> list){


        StringBuilder simulation = new StringBuilder();

        Iterator<File> files = list.iterator();
        Log.i("iterator: ",list.toString());

        while(files.hasNext()){

            File f = files.next();

            if (f instanceof VideoFile) {

                VideoFile video = (VideoFile) f;

                simulation.append("Iniciando simulación del Video\n");
                simulation.append(video.play() + "\n");

                video.setDate_last_rep(new Date());
                video.setReproductions(video.getReproductions() + 1);

                simulation.append("Finalizando simulación del Video\n\n");

            } else if (f instanceof AudioFile){

                AudioFile audio = (AudioFile) f;

                simulation.append("Iniciando simulación del Audio\n");
                simulation.append(audio.play() + "\n");

                audio.setDate_last_rep(new Date());
                audio.setReproductions(audio.getReproductions() + 1);

                simulation.append("Finalizando simulación del Audio\n\n");

            } else {

                ImageFile image = (ImageFile) f;

                simulation.append("Iniciando simulación de Imágen\n");
                simulation.append(image.show() + "\n");

                image.setDate_last_rep(new Date());
                image.setReproductions(image.getReproductions() + 1);

                simulation.append("Finalizando simulación de Imágen\n\n");
            }

        }


        return simulation.toString();
    }



}
