package engine;

import java.io.File;
import java.util.Observable;

/**
 * This class represent the modelisation of a player
 */
public class Player extends Observable {

    private String name;
    private KeyConfiguration keyConfiguration;
    private String skin;

    //CONSTRUCTORS


    public Player(String name, KeyConfiguration keyConfiguration, String skin) {
        this.name = name;
        this.keyConfiguration = keyConfiguration;
        this.skin = skin;
    }

    public Player(String name) {
        this.name = name;
        loadKeyConfiguration(name);
        this.skin = ""; // TODO
    }

    private void loadKeyConfiguration(String name) {
        File file = new File("profiles/" + name + ".p");
        if (file.exists()) {
            // TODO
            throw new UnsupportedOperationException();
        } else {
            this.keyConfiguration = KeyConfiguration.getDefaultConfiguration();
        }
    }


    //SETTERS

    public void setName(String name) {
        this.name = name;
        setChanged();
        notifyObservers();
    }



    //GETTERS

    public String getName() {
        return name;
    }

    public KeyConfiguration getKeyConfiguration() {
        return keyConfiguration;
    }


    public void saveProfile() {
        // TODO
        throw new UnsupportedOperationException();
    }

}
