package me.cadox8.commons;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.awt.image.BufferedImage;

@RequiredArgsConstructor
public class Card {

    private final String text;

    @Setter private BufferedImage base;
    @Setter private int requeiredHoles = -1;


    public BufferedImage build() {
        return null;
    }
}
