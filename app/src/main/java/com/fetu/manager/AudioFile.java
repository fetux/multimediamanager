package com.fetu.manager;;

/**
 * Created by fetu on 22/04/15.
 */
public class AudioFile extends File implements Durable {

    private Integer duration; // In seconds
    private Integer bits_per_second;
    private Integer initRep;
    private Integer endRep;

    public AudioFile(){}

    public AudioFile(Long id, String name, Integer size, String path, String hashtags, Integer bits_per_second, Integer duration) {
        super(id, name, size, path, hashtags);
        this.bits_per_second = bits_per_second;
        this.duration = duration;

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