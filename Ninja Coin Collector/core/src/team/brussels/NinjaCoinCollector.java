package team.brussels;

import States.GameStateManager;
import States.MenuState;
import States.PlayState;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.HashMap;

public class NinjaCoinCollector extends ApplicationAdapter {
    public static final int WIDTH = 1100;
    public static final int HEIGHT = 650;
    public static final String TITLE = "Ninja CoinStar Collector";

    public static final int EARTH_HEIGHT = 45;
    private SpriteBatch batch;
    private GameStateManager gameStateManager;
    private static HashMap<String, Texture> resources;
    private static TextureAtlas buttonAtlas;
    @Override
    public void create() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        this.batch = new SpriteBatch();
        this.resources = new HashMap<String, Texture>();
        this.resources.put("MenuBackground", new Texture("img/MenuBackground.jpg"));
        //this.resources.put("PlayButton", new Texture("img/PlayButton.png"));
        //this.resources.put("ExitButton", new Texture("img/ExitButton.png"));
        this.resources.put("PlayBackground", new Texture("img/PlayBackground.bmp"));
        this.resources.put("StandingLeft", new Texture("img/StandingLeft.png"));
        this.resources.put("StandingRight", new Texture("img/StandingRight.png"));
        this.resources.put("MovesLeft", new Texture("img/MovesLeft.png"));
        this.resources.put("MovesRight", new Texture("img/MovesRight.png"));
        this.resources.put("JumpsLeft", new Texture("img/JumpsLeft.png"));
        this.resources.put("JumpsRight", new Texture("img/JumpsRight.png"));
        this.resources.put("Bomb", new Texture("img/Bomb.png"));
        this.resources.put("CoinStar", new Texture("img/CoinStar.png"));
        this.resources.put("CoinFish", new Texture("img/CoinFish.png"));
        this.resources.put("Rock", new Texture("img/Rock.png"));
        this.resources.put("ExitBackground", new Texture("img/ExitBackground.png"));
        this.buttonAtlas = new TextureAtlas(Gdx.files.internal("packages/buttons.pack"));
        this.gameStateManager = new GameStateManager(this);
        this.gameStateManager.push(new MenuState(this.gameStateManager));
    }

    public SpriteBatch getBatch() {

        return batch;
    }

    @Override
    public void render() {
        this.gameStateManager.update(Gdx.graphics.getDeltaTime());
        this.gameStateManager.render(Gdx.graphics.getDeltaTime());
    }

    public static Texture getResource(String resourceName) {

        return resources.get(resourceName);
    }

    public static TextureAtlas getButtonAtlas() {

        return buttonAtlas;
    }
}
