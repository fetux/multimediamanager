package com.fetu.manager;;

/**
 * Created by fetu on 22/04/15.
 */
public class ImageFile extends File implements Zoomable{

    private Integer resolution; //In pixels x pixels
    private Integer width;
    private Integer height;
    private Integer zoom;

    public ImageFile(){}

    public ImageFile(Long id, String name, Integer size, String path, String hashtags, Integer resolution, Integer width, Integer height) {
        super(id, name, size, path, hashtags);
        this.resolution = resolution;
        this.width = width;
        this.height = height;

        this.save();
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
