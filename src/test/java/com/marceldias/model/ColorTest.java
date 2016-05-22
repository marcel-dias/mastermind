package com.marceldias.model;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ColorTest {

    @Test
    public void testToColorList() {
        List<Color> expected = Arrays.asList(Color.B, Color.C, Color.R, Color.C, Color.G, Color.M, Color.G, Color.Y);
        String stringColors = "BCRCGMGY";

        List<Color> colors = Color.toColorList(stringColors);
        Assert.assertThat(colors.size(), IsEqual.equalTo(8));
        Assert.assertThat(colors, IsEqual.equalTo(expected));
    }
}
