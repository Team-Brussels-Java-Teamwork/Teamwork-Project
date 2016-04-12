package States;

import GameObjects.Coins.CoinFish;
import GameObjects.Coins.CoinStar;
import GameObjects.Players.Player;
import GameObjects.Rock.Rock;
import Interfaces.IPickable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class PlayState extends State {
    private Texture background;
    private Player player;
    private Rock rock;
    private IPickable coinStar1;
    private IPickable coinStar2;
    private IPickable coinStar3;
    private IPickable coinStar4;
    private IPickable coinStar5;
    private IPickable coinStar6;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        this.background = this.getGameStateManager().getGame().getResource("PlayBackground");
        this.player = new Player();
        this.rock = new Rock();
        this.coinStar1 = new CoinStar();
        this.coinStar2 = new CoinStar();
        this.coinStar3 = new CoinFish();
        this.coinStar4 = new CoinFish();
        this.coinStar5 = new CoinStar();
        this.coinStar6 = new CoinStar();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.getGameStateManager().getGame().getBatch().begin();
        this.getGameStateManager().getGame().getBatch().draw(this.background, 0, 0);
        this.getGameStateManager().getGame().getBatch().draw(this.player.getTexture(), this.player.getPosition().x, this.player.getPosition().y);
        this.getGameStateManager().getGame().getBatch().draw(this.rock.getTexture(), this.rock.getPosition().x, this.rock.getPosition().y);
        this.getGameStateManager().getGame().getBatch().draw(this.coinStar1.getTexture(), this.coinStar1.getPosition().x, this.coinStar1.getPosition().y);
        this.getGameStateManager().getGame().getBatch().draw(this.coinStar2.getTexture(), this.coinStar2.getPosition().x, this.coinStar2.getPosition().y);
        this.getGameStateManager().getGame().getBatch().draw(this.coinStar3.getTexture(), this.coinStar3.getPosition().x, this.coinStar3.getPosition().y);
        this.getGameStateManager().getGame().getBatch().draw(this.coinStar4.getTexture(), this.coinStar4.getPosition().x, this.coinStar4.getPosition().y);
        this.getGameStateManager().getGame().getBatch().draw(this.coinStar5.getTexture(), this.coinStar5.getPosition().x, this.coinStar5.getPosition().y);
        this.getGameStateManager().getGame().getBatch().draw(this.coinStar6.getTexture(), this.coinStar6.getPosition().x, this.coinStar6.getPosition().y);
        this.getGameStateManager().getGame().getBatch().end();
    }

    @Override
    public void update(float dt) {

        this.player.update(dt);
        this.rock.update(dt);
        this.coinStar1.update(dt);
        this.coinStar2.update(dt);
        this.coinStar3.update(dt);
        this.coinStar4.update(dt);
        this.coinStar5.update(dt);
        this.coinStar6.update(dt);
    }
}
