package GameObjects.Coins;

import java.util.Random;

import GameObjects.Animations.*;
import Interfaces.Pickable;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import team.brussels.NinjaCoinCollector;

public class CoinStar implements Pickable {
    private Random rand = new Random();
    private static final int StartWidth = 0;
    private static final int StartHeight = 700;
    private static final float DownVelocityX = 0;
    private static final float DownVelocityY = 5f;

    private static final int COIN_VALUE = 10;

    private TextureRegion textureRegion;
    private Vector2 position;
    private Vector2 downVelocity;

    private Rectangle bounds;
    private TextureRegion movingDown;

    private AnimationCoinStarFall coinMoveDownAnimation;



    public CoinStar() {
        this.position = new Vector2(StartWidth + rand.nextInt(1200), StartHeight + rand.nextInt(700));
        this.downVelocity = new Vector2(DownVelocityX, DownVelocityY);
        this.movingDown = new TextureRegion(NinjaCoinCollector.getResource("CoinStar"));
        this.coinMoveDownAnimation = new AnimationCoinStarFall(this.movingDown);
        this.textureRegion = this.movingDown;
        this.bounds = new Rectangle(this.position.x, this.position.y, this.textureRegion.getRegionWidth(), this.textureRegion.getRegionHeight());
    }

    public int getCoinValue() {
        return COIN_VALUE;
    }

    public TextureRegion getTexture() {
        return this.textureRegion;
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void update(float dt) {

        this.position.sub(this.downVelocity);
        this.coinMoveDownAnimation.update(dt);

        if (this.position.y <= NinjaCoinCollector.EARTH_HEIGHT) {
            resetPosition();
        }

        this.bounds.setPosition(this.position.x, this.position.y);
    }

    public void resetPosition(){
        this.position.y -= NinjaCoinCollector.HEIGHT;
        if (rand.nextInt(1000) > 950) {
            this.position.y = NinjaCoinCollector.HEIGHT + 200;
            this.position.x = StartWidth+rand.nextInt(1000);
        }
    }
}
