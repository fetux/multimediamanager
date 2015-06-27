package com.fetu.manager;;


public class AudioFile extends File implements Durable {

    private Integer duration; // In seconds
    private Integer bits_per_second;
    private Integer initRep;
    private Integer endRep;

    public AudioFile(){}

    public AudioFile(String name, Integer size, String path, String hashtags, Integer bits_per_second, Integer duration) {
        super(name, size, path, hashtags);
        this.bits_per_second = bits_per_second;
        this.duration = duration;

    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getBits_per_second() {
        return bits_per_second;
    }

    public void setBits_per_second(Integer bits_per_second) {
        this.bits_per_second = bits_per_second;
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
    public String play() {
         /* Inicio de la simulación */

        return this.toString();
    }

    @Override
    public String toString() {
        return super.toString() + '\n' +
                "Duracion: " + duration + '\n' +
                "Bit/s: " + bits_per_second + '\n' +
                "Inicio de la reproducción: " + initRep + '\n' +
                "Fin de la reproducción: " + endRep;
    }

    @Override
    public void stop() {

    }
}