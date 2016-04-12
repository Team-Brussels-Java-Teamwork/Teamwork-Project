package GameObjects.Coins;

import java.util.Random;

import GameObjects.Animations.*;
import Interfaces.IPickable;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import team.brussels.NinjaCoinCollector;

public class CoinFish implements IPickable {
    private Random rand = new Random();
    private static final int StartWidth = 0;
    private static final int StartHeight = 300;
    private static final float DownVelocityX = 0;
    private static final float DownVelocityY = 5f;

    private TextureRegion textureRegion;
    private Vector2 position;
    private Vector2 downVelocity;

    private Rectangle bounds;
    private TextureRegion movingDown;

    private AnimationCoinFishFall coinMoveDownAnimation;



    public CoinFish() {
        this.position = new Vector2(StartWidth+rand.nextInt(1200), StartHeight+rand.nextInt(1200));
        this.downVelocity = new Vector2(DownVelocityX, DownVelocityY);
        this.movingDown = new TextureRegion(NinjaCoinCollector.getResource("CoinFish"));
        this.coinMoveDownAnimation = new AnimationCoinFishFall(this.movingDown);
        this.textureRegion = this.movingDown;
        this.bounds = new Rectangle(this.position.x, this.position.y, this.textureRegion.getRegionWidth(), this.textureRegion.getRegionHeight());
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

    public void update(float dt) {

        this.position.sub(this.downVelocity);
        this.coinMoveDownAnimation.update(dt);

        if (this.position.y < +50) {
            this.position.y=-100;
            if (rand.nextInt(1000) > 990) {
                this.position.y = NinjaCoinCollector.HEIGHT + 315;
                this.position.x = StartWidth+rand.nextInt(1200);
            }
        }
    }
}
