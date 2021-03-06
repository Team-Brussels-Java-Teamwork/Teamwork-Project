package GameObjects.Obstacles;

import java.util.Random;

import GameObjects.Animations.*;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import team.brussels.NinjaCoinCollector;


/**
 * Created by Simeon on 4/8/2016.
 */
public class Bomb {
    private Random rand = new Random();
    private static final int StartWidth = NinjaCoinCollector.WIDTH - 100;
    private static final int StartHeight = 85;
    private static final float SideVelocityX = 4f;
    private static final float SideVelocityY = 0;

    private TextureRegion textureRegion;
    private Vector2 position;
    private Vector2 sideVelocity;

    private Rectangle bounds;
    private TextureRegion movingLeft;

    private AnimationBombMoveLeft bombMoveLeftAnimation;

    public Bomb() {
        this.position = new Vector2(StartWidth, StartHeight);
        this.sideVelocity = new Vector2(SideVelocityX, SideVelocityY);
        this.movingLeft = new TextureRegion(NinjaCoinCollector.getResource("Bomb"));
        this.bombMoveLeftAnimation = new AnimationBombMoveLeft(this.movingLeft);
        this.textureRegion = this.movingLeft;
        this.bounds = new Rectangle(this.position.x, this.position.y, this.textureRegion.getRegionWidth(), this.textureRegion.getRegionHeight());
    }

    public TextureRegion getTexture() {
        return this.textureRegion;
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void update(float dt) {
        this.position.sub(this.sideVelocity);
        this.bombMoveLeftAnimation.update(dt);

        if (this.position.x < -30) {
            this.resetPosition();
        }

        this.bounds.setPosition(this.position.x, this.position.y);
    }

    public void resetPosition(){
        if (rand.nextInt(1000) > 990) {
            this.position.x = NinjaCoinCollector.WIDTH - 115;
        }
        else {
            this.position.x = -31;
        }
    }
}
