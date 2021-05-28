package com;

import java.util.Arrays;

public class Team {
    private String t_id;
    private String t_name;
    private Footballer[] t_foots;
    private Integer t_size;

    public Team(String t_id, String t_name, Footballer[] t_foots, Integer t_size) {
        this.t_id = t_id;
        this.t_name = t_name;
        this.t_foots = t_foots;
        this.t_size = t_size;
    }

    public Team(int size) {
        this.t_size = size;
        t_foots = new Footballer[t_size];
    }

    public String getT_id() {
        return t_id;
    }

    public void setT_id(String t_id) {
        this.t_id = t_id;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public Footballer[] getT_foots() {
        return t_foots;
    }

    public void setT_foots(Footballer[] t_foots) {
        this.t_foots = t_foots;
    }

    public Integer getT_size() {
        return t_size;
    }

    public void setT_size(Integer t_size) {
        this.t_size = t_size;
    }

    @Override
    public String toString() {
        return "com.Team{" +
                "t_id='" + t_id + '\'' +
                ", t_name='" + t_name + '\'' +
                ", t_foots=" + Arrays.toString(t_foots) +
                ", t_size=" + t_size +
                '}';
    }
}
