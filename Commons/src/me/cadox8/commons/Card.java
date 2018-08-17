package me.cadox8.commons;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;

@RequiredArgsConstructor
public class Card {

    private final String text;

    @Setter private BufferedImage base;
    @Setter private int requeiredHoles = -1;

    public BufferedImage build() {
        Graphics g = base.getGraphics();

        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        if (text.split(" ").length <= 3) {
            g.drawString(text, 20, 30);
        } else {
            int x = 0;
            for (String s : formatText()) {
                g.drawString(s, 20, 30 + (x * g.getFont().getSize()));
                x++;
            }
        }
        g.dispose();
        return base;
    }

    private String[] formatText() {
        String[] parts = text.split(" ");
        final String[] text = {"", "", "", ""};

        int path = 0;
        for (int x = 0; x < parts.length; x++) {
            text[path] += parts[x] + " ";

            if (x != 0 && x % 3 == 0) path++;
        }
        return text;
    }
}
