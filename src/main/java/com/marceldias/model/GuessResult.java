package com.marceldias.model;

public class GuessResult {

    private Guess guess;
    private Integer exact;
    private Integer near;

    public Guess getGuess() {
        return guess;
    }

    public void setGuess(Guess guess) {
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
