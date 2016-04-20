package GameObjects.Coins;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class Coin {
    protected static final int StartWidth = 0;
    protected static final int StartHeight = 700;
    protected static final float DownVelocityX = 0;
    protected static final float DownVelocityY = 5f;

    protected TextureRegion textureRegion;
    protected Vector2 position;
    protected Vector2 downVelocity;

    public TextureRegion getTexture() {
        return this.textureRegion;
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }


}
