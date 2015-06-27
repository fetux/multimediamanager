package com.fetu.manager;;

public class ImageFile extends File implements Zoomable{

    private Integer resolution; //In pixels x pixels
    private Integer width;
    private Integer height;
    private Integer zoom;
    private Integer duration = 10;

    public ImageFile(){}

    public ImageFile(String name, Integer size, String path, String hashtags, Integer resolution, Integer width, Integer height) {
        super(name, size, path, hashtags);
        this.resolution = resolution;
        this.width = width;
        this.height = height;

        this.save();
    }

    public Integer getResolution() {
        return resolution;
    }

    public void setResolution(Integer resolution) {
        this.resolution = resolution;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getZoom() {
        return zoom;
    }

    public void setZoom(Integer zoom) {
        this.zoom = zoom;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public void zoom(Integer z) {
        zoom = z;
    }

    @Override
    public String toString() {
        return super.toString() + '\n' +
                "Resolucion='" + resolution + '\n' +
                "Ancho: " + width + '\n' +
                "Alto: " + height + '\n' +
                "Zoom: " + zoom;
    }

    public String show(){

        return this.toString();

    }




}
