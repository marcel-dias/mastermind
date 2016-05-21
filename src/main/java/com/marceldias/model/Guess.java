package com.marceldias.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

public class Guess {

	@Id
	private String id;
	private List<Color> colors;
	private LocalDateTime createdAt;

	public Guess() {
        createdAt = LocalDateTime.now();
    }

	public Guess(String colors) {
        this();
		this.colors = populateColors(colors);
	}

	protected List<Color> populateColors(String colors) {
        if (colors == null || colors.isEmpty()) {
            throw new IllegalArgumentException("colors is null or is empty!");
        }
		List<Color> colorList = new ArrayList<>();
		for (String color : colors.toUpperCase().split("")) {
			colorList.add(Color.valueOf(color));
		}
		return colorList;
	}

	public String getId() {
		return id;
	}

	public List<Color> getColors() {
		return colors;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
