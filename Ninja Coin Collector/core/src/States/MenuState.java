package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import team.brussels.NinjaCoinCollector;

/**
 * Created by Nasko on 13.4.2016 г..
 */
public class MenuState extends State {

    private Texture background;
    private Texture playBtn;
    private Texture exitBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = NinjaCoinCollector.getResource("MenuBackground");
        playBtn = NinjaCoinCollector.getResource("StartButton");
        exitBtn = NinjaCoinCollector.getResource("ExitButton");
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            getGameStateManager().set(new PlayState(getGameStateManager()));
        }
    }

    @Override
    public void update(float dt) {
        this.handleInput();

    }

    @Override
    public void render(float dt) {
        this.getGameStateManager().getGame().getBatch().begin();
        this.getGameStateManager().getGame().getBatch().draw(background, 0, 0, NinjaCoinCollector.WIDTH, NinjaCoinCollector.HEIGHT);
        this.getGameStateManager().getGame().getBatch().draw(playBtn, 200, 80);
        this.getGameStateManager().getGame().getBatch().draw(exitBtn, 700, 80);
        this.getGameStateManager().getGame().getBatch().end();
    }
}
