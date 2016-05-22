package com.marceldias.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Game {

    @Id
    private String key;
    private Color[] colors = Color.values();
    private final Integer codeLength = 8;
    private final Long gameTime = 5l;
    @JsonIgnore
    private List<Color> code = new ArrayList<>();
    private User user;
    @JsonIgnore
    private LocalDateTime createdAt;
    private boolean solved = false;
    private List<GuessResult> guesses = new ArrayList<>();
    @Transient
    private GuessResult result;

    public Game() {
        createdAt = LocalDateTime.now();
        code = Color.generateColorCode(codeLength);
    }

    public Game(User user) {
        this();
        this.user = user;
    }

    public boolean isExpired() {
        LocalDateTime fiveMinutes = createdAt.plusMinutes(gameTime);
        return LocalDateTime.now().isAfter(fiveMinutes);
    }

    public String getKey() {
        return key;
    }

    public List<Color> getCode() {
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

    public Integer getCodeLength() {
        return codeLength;
    }

    public GuessResult getResult() {
        return result;
    }

    public void setResult(GuessResult result) {
        this.result = result;
        this.guesses.add(result);
    }

    public Color[] getColors() {
        return colors;
    }
}
