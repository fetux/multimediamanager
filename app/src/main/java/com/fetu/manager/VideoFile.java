package com.fetu.manager;

;

/**
 * Created by fetu on 22/04/15.
 */
public class VideoFile extends File implements Durable,Zoomable{

    private Integer duration; // In seconds
    private String resolution; // In pixels
    private Integer frames;
    private Integer zoom;

    private Integer initRep;
    private Integer endRep;


    public VideoFile(){}

    public VideoFile(Long id, String name, Integer size, String path, String hashtags, Integer duration, String resolution, Integer frames) {
        super(id, name, size, path, hashtags);
        this.duration = duration;
        this.resolution = resolution;
        this.frames = frames;

    }

    @Override
    public void zoom(Integer z){
        zoom = z;
    }

    @Override
    public String play() {
        /* Inicio de la simulación */

        return  this.toString();

    }

    @Override
    public String toString() {
        return super.toString() + '\n' +
                "Duracion: " + duration + '\n' +
                "Resolucion: " + resolution + '\n' +
                "Cuadros: " + frames + '\n' +
                "Zoom: " + zoom + '\n' +
                "Inicio de la reproducción: " + initRep + '\n' +
                "Fin de la reproducción: " + endRep + '\n';

    }

    @Override
    public void stop() {

    }

}
