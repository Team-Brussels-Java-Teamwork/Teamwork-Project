package States;

import GameObjects.Coin.Coin;
import GameObjects.Players.Player;
import GameObjects.Rock.Rock;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class PlayState extends State {
    private Texture background;
    private Player player;
    private Rock rock;
    private Coin coin1;
    private Coin coin2;
    private Coin coin3;
    private Coin coin4;
    private Coin coin5;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        this.background = this.getGameStateManager().getGame().getResource("PlayBackground");
        this.player = new Player();
        this.rock = new Rock();
        this.coin1 = new Coin();
        this.coin2 = new Coin();
        this.coin3 = new Coin();
        this.coin4 = new Coin();
        this.coin5 = new Coin();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.getGameStateManager().getGame().getBatch().begin();
        this.getGameStateManager().getGame().getBatch().draw(this.background, 0, 0);
        this.getGameStateManager().getGame().getBatch().draw(this.player.getTexture(), this.player.getPosition().x, this.player.getPosition().y);
        this.getGameStateManager().getGame().getBatch().draw(this.rock.getTexture(), this.rock.getPosition().x, this.rock.getPosition().y);
        this.getGameStateManager().getGame().getBatch().draw(this.coin1.getTexture(), this.coin1.getPosition().x, this.coin1.getPosition().y);
        this.getGameStateManager().getGame().getBatch().draw(this.coin2.getTexture(), this.coin2.getPosition().x, this.coin2.getPosition().y);
        this.getGameStateManager().getGame().getBatch().draw(this.coin3.getTexture(), this.coin3.getPosition().x, this.coin3.getPosition().y);
        this.getGameStateManager().getGame().getBatch().draw(this.coin4.getTexture(), this.coin4.getPosition().x, this.coin4.getPosition().y);
        this.getGameStateManager().getGame().getBatch().draw(this.coin5.getTexture(), this.coin5.getPosition().x, this.coin5.getPosition().y);
        this.getGameStateManager().getGame().getBatch().end();
    }

    @Override
    public void update(float dt) {

        this.player.update(dt);
        this.rock.update(dt);
        this.coin1.update(dt);
        this.coin2.update(dt);
        this.coin3.update(dt);
        this.coin4.update(dt);
        this.coin5.update(dt);
    }
}
