package me.cadox8.cah.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.cadox8.cah.Launcher;
import me.cadox8.cah.gfx.textures.Assets;
import me.cadox8.commons.Card;
import me.cadox8.commons.CardData;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class DataManager {

    private static File f = new File(Launcher.GAME_FILE + "data", "data.json");

    private final Gson gson;

    public DataManager() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public static void checkFile() {
        try {
            if (!f.exists()) {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
        } catch (IOException e) {
            System.exit(-1);
        }
    }

    public HashMap<String, List<Card>> loadCards() {
        try {
            final BufferedReader br = new BufferedReader(new InputStreamReader(new URL("https://cadox8.github.io/cah/data/cards.json").openStream()));
            final StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) sb.append(line);

            HashMap<String, List<Card>> cards = gson.fromJson(sb.toString(), CardData.class).buildCards();

            cards.get("cards").forEach(c -> c.setBase(Assets.white));
            cards.get("hole").forEach(c -> c.setBase(Assets.black));
            cards.get("holes").forEach(c -> c.setBase(Assets.black));

            return cards;
        } catch (IOException e) {
            System.exit(-1);
            return new HashMap<>();
        }
    }
    public UserData load() {
        try {
            return gson.fromJson(new FileReader(f), UserData.class);
        } catch (FileNotFoundException e) {
            checkFile();
            return load();
        }
    }

    public void save(UserData data) {
        try {
            BufferedWriter w = new BufferedWriter(new FileWriter(f));

            if (f.exists()) f.delete();
            f.mkdirs();

            w.write(gson.toJson(data));
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
