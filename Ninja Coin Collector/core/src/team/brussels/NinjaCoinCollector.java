package team.brussels;

import States.GameStateManager;
import States.PlayState;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;

public class NinjaCoinCollector extends ApplicationAdapter {
    private SpriteBatch batch;
    private GameStateManager gameStateManager;
    public static final int WIDTH = 1100;
    public static final int HEIGHT = 800;
    public static final String TITLE = "Ninja Coin Collector";
    private static HashMap<String, Texture> resources;

    @Override
    public void create() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        this.batch = new SpriteBatch();
        this.resources = new HashMap<String, Texture>();
        this.resources.put("PlayBackground", new Texture("img/PlayBackground.bmp"));
        this.resources.put("StandingLeft", new Texture("img/StandingLeft.png"));
        this.resources.put("StandingRight", new Texture("img/StandingRight.png"));
        this.resources.put("MovesLeft", new Texture("img/MovesLeft.png"));
        this.resources.put("MovesRight", new Texture("img/MovesRight.png"));
        this.resources.put("JumpsLeft", new Texture("img/JumpsLeft.png"));
        this.resources.put("JumpsRight", new Texture("img/JumpsRight.png"));
        this.resources.put("Rock", new Texture("img/Rock.png"));
        this.resources.put("Coin", new Texture("img/Coin.png"));
        this.gameStateManager = new GameStateManager(this);
        this.gameStateManager.push(new PlayState(this.gameStateManager));
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.gameStateManager.update(Gdx.graphics.getDeltaTime());
        this.gameStateManager.render(Gdx.graphics.getDeltaTime());
    }

    public static Texture getResource(String resourceName) {
        return resources.get(resourceName);
    }
}
