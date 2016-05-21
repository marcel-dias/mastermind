package com.marceldias.model;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Game {

    @Id
    private String key;
    private Color[] colors = Color.values();
    private final Integer codeLength = 8;
    private final Long gameTime = 5l;
    private Guess code;
    private User user;
    private LocalDateTime createdAt;
    private boolean solved = false;
    private List<GuessResult> guesses = new ArrayList<>();

    public Game(User user) {
        this.user = user;
        createdAt = LocalDateTime.now();
        code = generateCode();
    }

    protected Guess generateCode() {
        String code = RandomStringUtils.random(codeLength, Color.COLORS);
        return new Guess(code);
    }

    public boolean isExpired() {
        LocalDateTime fiveMinutes = createdAt.plusMinutes(gameTime);
        return LocalDateTime.now().isAfter(fiveMinutes);
    }

    public String getKey() {
        return key;
    }

    public Guess getCode() {
        return code;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    protected void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<GuessResult> getGuesses() {
        return guesses;
    }

    public Integer getGuesesCount() {
        return guesses.size();
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }
}
