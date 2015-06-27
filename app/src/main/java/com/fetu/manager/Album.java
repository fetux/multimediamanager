package com.fetu.manager;


public class Album extends Container implements Comparable<Object> {

    private Long container;

    public Album(){}

    public Long getContainer() {
        return container;
    }

    public void setContainer(Long container) {
        this.container = container;
    }

    public Album(String name, Long container) {
        super();

        this.container = container;
        this.setName(name);


    }

    public Album(Long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int compareTo(Object another) {

        Nodo a = (Nodo) another;

        int compId = this.id.compareTo(a.getId());

        return  (compId != 0) ? compId : this.getName().compareTo(a.getName());

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Album)) return false;

        Album album = (Album) o;

        if (id != null ? !id.equals(album.id) : album.id != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + getName() + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    public Integer linkFile(File f) {

        this.getFiles().add(f);
        this.save();

        return 1;
    }

    public Integer unLinkFile(File f) {

        return this.getFiles().remove(f) ? 1 : 0;
    }


}