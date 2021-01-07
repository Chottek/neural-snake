package pl.fox.neuralsnake;

import pl.fox.neuralsnake.world.Field;
import pl.fox.neuralsnake.world.Nest;
import pl.fox.neuralsnake.world.World;

public class Handler {

    private final Game game;

    public Handler(Game game){
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public World getWorld(){
        return getGame().getWorld();
    }

    public Nest getNest(){
        return getWorld().getNest();
    }

    public Field getField(){
        return getWorld().getField();
    }

}
