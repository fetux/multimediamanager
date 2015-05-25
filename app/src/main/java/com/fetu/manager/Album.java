package com.fetu.manager;


import java.util.TreeSet;


/**
 * Created by fetu on 22/04/15.
 */

public class Album extends Container implements Comparable<Object> {

    private Long id;
    private String name;


    public Long getId() {
        return id;
    }

    public Album(){}

    public Album(Long id, String name) {
        this.id = id;
        this.name = name;
        this.setFiles(new TreeSet<File>());
        this.setAlbums(new TreeSet<Album>());
    }

    public Album(Long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object another) {

        Album a = (Album) another;

        return this.id.compareTo(a.getId());

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Album)) return false;

        Album album = (Album) o;

        if (id != null ? !id.equals(album.id) : album.id != null) return false;
        //if (name != null ? !name.equals(album.name) : album.name != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public Integer linkFile(File f) {

        this.getFiles().add(f);
        this.save();

        //return this.getFiles().add(f) ? 1 : 0;
        return 1;
    }

    public Integer unLinkFile(File f) {

        return this.getFiles().remove(f) ? 1 : 0;
    }

}