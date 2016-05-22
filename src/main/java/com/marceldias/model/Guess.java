package com.marceldias.model;

import java.util.ArrayList;
import java.util.List;

public class Guess {

	private List<Color> code = new ArrayList<>();
    private String gameKey;

	public Guess() {
    }

	public List<Color> getCode() {
		return code;
	}

    public void setCode(List<Color> code) {
        this.code = code;
    }

    public String getGameKey() {
        return gameKey;
    }

    public void setGameKey(String gameKey) {
        this.gameKey = gameKey;
    }

    @Override
    public String toString() {
        return "Guess{" +
                " code=" + code +
                ", gameKey='" + gameKey + '\'' +
                '}';
    }
}
