package com.fetu.manager;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by fetu on 22/04/15.
 */

public class File extends SugarRecord<File> implements Comparable<Object>{

    private Long id;
    private String name;
    private Integer size;
    private Date date_last_rep;
    private Date date_last_mod;
    private Integer reproductions;
    private String path;
    private String hashtags;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public File(){}

    public File(Long id,String name,Integer size, Date date_last_rep, Date date_last_mod, Integer reproductions,String path,String hashtags) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.date_last_rep = date_last_rep;
        this.date_last_mod = date_last_mod;
        this.reproductions = reproductions;
        this.path = path;
        this.hashtags = hashtags;
    }

    public File(Long id,String name,Integer size,String path,String hashtags) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.date_last_rep = new Date();
        this.date_last_mod = new Date();
        this.reproductions = 0;
        this.path = path;
        this.hashtags = hashtags;
    }

    public File(Long id_file){
        this.id = id_file;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Date getDate_last_rep() {
        return date_last_rep;
    }

    public void setDate_last_rep(Date date_last_rep) {
        this.date_last_rep = date_last_rep;
    }

    public Date getDate_last_mod() {
        return date_last_mod;
    }

    public void setDate_last_mod(Date date_last_mod) {
        this.date_last_mod = date_last_mod;
    }

    public Integer getReproductions() {
        return reproductions;
    }

    public void setReproductions(Integer reproductions) {
        this.reproductions = reproductions;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHashtags() {
        return hashtags;
    }

    public void setHashtags(String hashtags) {
        this.hashtags = hashtags;
    }


    @Override
    public int compareTo(Object another) {

        File f = (File) another;

        return this.id.compareTo(f.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof File)) return false;

        File file = (File) o;

        if (id != null ? !id.equals(file.id) : file.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {

        String last_rep = (date_last_rep != null) ?  date_last_rep.toString() : "nunca reproducido";
        return "Id: " + id.toString() + '\n' +
               "Nombre: " + name + '\n' +
               "Tamaño: " + size.toString() + '\n' +
               "Ultima reproducción: " + last_rep + '\n' +
               "Ultima modificacion: " + date_last_mod.toString() + '\n' +
               "Reproducciones: " + reproductions.toString() + '\n' +
               "Path: " + path + '\n' +
               "Hastags: " + hashtags;

    }

}
