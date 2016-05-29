package com.marceldias.model;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Color represents the avaiable colors in the game
 */
public enum Color implements Serializable {

	R("Red"),
	B("Blue"),
	G("Green"),
	Y("Yellow"),
	O("Orange"),
	P("Purple"),
	C("Cyan"),
	M("Magenta");

    private String label;
    public static final String COLORS;

    Color(String label) {
		this.label = label;
	}

	static {
		COLORS = Arrays.stream(Color.values()).map(Enum::name).collect(Collectors.joining());
	}

    /**
     * Transform a a color sequence string into a Color List
     *
     * @param code String that contains colors as Strings
     * @return A list of Color
     */
    protected static List<Color> toColorList(String code) {
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("code is null or is empty!");
        }
        List<Color> colorList = new ArrayList<>();
        for (String color : code.toUpperCase().split("")) {
            colorList.add(Color.valueOf(color));
        }
        return colorList;
    }

    /**
     * Generates a random list of colors based on avaiable colors
     *
     * @param size the list size to be returned
     * @return A list of Color
     */
    public static List<Color> generateColorCode(Integer size) {
        String code = RandomStringUtils.random(size, Color.COLORS);
        return toColorList(code);
    }
}
