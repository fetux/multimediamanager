package com.fetu.manager;

;


public class VideoFile extends File implements Durable,Zoomable{

    private Integer duration; // In seconds
    private String resolution; // In pixels
    private Integer frames;
    private Integer zoom;

    private Integer initRep;
    private Integer endRep;


    public VideoFile(){}

    public VideoFile(String name, Integer size, String path, String hashtags, Integer duration, String resolution, Integer frames) {
        super(name, size, path, hashtags);
        this.duration = duration;
        this.resolution = resolution;
        this.frames = frames;

    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public Integer getFrames() {
        return frames;
    }

    public void setFrames(Integer frames) {
        this.frames = frames;
    }

    public Integer getZoom() {
        return zoom;
    }

    public void setZoom(Integer zoom) {
        this.zoom = zoom;
    }

    public Integer getInitRep() {
        return initRep;
    }

    public void setInitRep(Integer initRep) {
        this.initRep = initRep;
    }

    public Integer getEndRep() {
        return endRep;
    }

    public void setEndRep(Integer endRep) {
        this.endRep = endRep;
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
