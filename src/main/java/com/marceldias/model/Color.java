package com.marceldias.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.stream.Collectors;

public enum Color implements Serializable {

	R("Red"),
	B("Blue"),
	G("Green"),
	Y("Yellow"),
	O("Orange"),
	P("Purple"),
	C("Cyan"),
	M("Magenta");

	private Color(String label) {
		this.label = label;
	}

	static {
		COLORS = Arrays.stream(Color.values()).map(Enum::name).collect(Collectors.joining());
	}

	private String label;
	public static final String COLORS;

	public String getLabel() {
		return label;
	}
}
