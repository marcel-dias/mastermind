package com.marceldias.model;

import java.util.List;

public class GuessResult {

    private List<Color> guess;
    private Integer exact;
    private Integer near;

    public List<Color> getGuess() {
        return guess;
    }

    public void setGuess(List<Color> guess) {
        this.guess = guess;
    }

    public Integer getNear() {
        return near;
    }

    public void setNear(Integer near) {
        this.near = near;
    }

    public Integer getExact() {
        return exact;
    }

    public void setExact(Integer exact) {
        this.exact = exact;
    }
}
