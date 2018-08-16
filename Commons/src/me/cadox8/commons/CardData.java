package me.cadox8.commons;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CardData {

    @Getter private String[] cards;
    @Getter private String[] hole;
    @Getter private String[] holes;


    public HashMap<String, List<Card>> buildCards() {
        final HashMap<String, List<Card>> allCards = new HashMap<>();
        final List<Card> cards = new ArrayList<>();
        final List<Card> hole = new ArrayList<>();
        final List<Card> holes = new ArrayList<>();

        Arrays.asList(getCards()).forEach(c -> cards.add(new Card(c)));
        Arrays.asList(getHole()).forEach(c -> {
            Card card = new Card(c);
            card.setRequeiredHoles(1);
            hole.add(card);
        });
        Arrays.asList(getHole()).forEach(c -> {
            Card card = new Card(c);
            card.setRequeiredHoles(2);
            holes.add(card);
        });

        allCards.put("cards", cards);
        allCards.put("hole", hole);
        allCards.put("holes", holes);

        return allCards;
    }
}
