package States;

import GameObjects.Players.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class PlayState extends State {
    private Texture background;
    private Player player;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        this.background = this.getGameStateManager().getGame().getResource("PlayBackground");
        this.player = new Player();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.getGameStateManager().getGame().getBatch().begin();
        this.getGameStateManager().getGame().getBatch().draw(this.background, 0, 0);
        this.getGameStateManager().getGame().getBatch().draw(this.player.getTexture(), this.player.getPosition().x, this.player.getPosition().y);
        this.getGameStateManager().getGame().getBatch().end();
    }

    @Override
    public void update(float dt) {
        this.player.update(dt);
    }

}
