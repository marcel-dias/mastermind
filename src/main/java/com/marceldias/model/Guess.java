package com.marceldias.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

public class Guess {

	@Id
	private String id;
	private List<Color> code;
	private LocalDateTime createdAt;
    private String gameKey;

	public Guess() {
        createdAt = LocalDateTime.now();
    }

	public Guess(String code) {
        this();
		this.code = populateColors(code);
	}

	protected List<Color> populateColors(String code) {
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("code is null or is empty!");
        }
		List<Color> colorList = new ArrayList<>();
		for (String color : code.toUpperCase().split("")) {
			colorList.add(Color.valueOf(color));
		}
		return colorList;
	}

	public String getId() {
		return id;
	}

	public List<Color> getCode() {
		return code;
	}

    public void setCode(String code) {
        this.code = populateColors(code);
    }

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

    public String getGameKey() {
        return gameKey;
    }

    public void setGameKey(String gameKey) {
        this.gameKey = gameKey;
    }
}
