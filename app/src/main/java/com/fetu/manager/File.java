package com.fetu.manager;

import java.util.Date;


public class File extends Nodo implements Comparable<Object>{

    private Integer size;
    private Date date_last_rep;
    private Date date_last_mod;
    private Integer reproductions;
    private String path;
    private String hashtags;

    private String containers;

    public String getContainers() {
        return containers;
    }

    public void setContainers(String containers) {
        this.containers = containers;
    }


    public File(){}

    public File(String name,Integer size, Date date_last_rep, Date date_last_mod, Integer reproductions,String path,String hashtags) {

        this.setName(name);
        this.size = size;
        this.date_last_rep = date_last_rep;
        this.date_last_mod = date_last_mod;
        this.reproductions = reproductions;
        this.path = path;
        this.hashtags = hashtags;
        this.containers = "";
    }

    public File(String name,Integer size,String path,String hashtags) {

        this.setName(name);
        this.size = size;
        this.date_last_rep = new Date();
        this.date_last_mod = new Date();
        this.reproductions = 0;
        this.path = path;
        this.hashtags = hashtags;
        this.containers = "";
    }


    public File(Long id_file){
        this.id = id_file;

    }

    public File (Long id_file,String name){
        this.id = id_file;
        this.setName(name);
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

        Nodo f = (Nodo) another;

        return (this.getName()).compareTo(f.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof File)) return false;

        Nodo f = (Nodo) o;

        boolean eqId = this.getId().equals(f.getId());

        return (eqId) ? eqId : (this.getName()).equals(f.getName());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {


        String last_rep = (date_last_rep != null) ?  date_last_rep.toString() : "nunca reproducido";
        return "Id: " + id.toString() + '\n' +
               "Nombre: " + getName() + '\n' +
               "Tamaño: " + size.toString() + '\n' +
               "Ultima reproducción: " + last_rep + '\n' +
               "Ultima modificacion: " + date_last_mod.toString() + '\n' +
               "Reproducciones: " + reproductions.toString() + '\n' +
               "Path: " + path + '\n' +
               "Hastags: " + hashtags;


    }


}
