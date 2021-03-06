package com.fetu.manager;

import com.orm.SugarRecord;


public abstract class Nodo extends SugarRecord<Nodo> implements Comparable<Object>{


    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int compareTo(Object another) {

        Nodo a = (Nodo) another;

        int compId = this.id.compareTo(a.getId());

        return  (compId != 0) ? compId : this.getName().compareTo(a.getName());

    }
}
