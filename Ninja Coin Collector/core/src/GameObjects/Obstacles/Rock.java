package GameObjects.Obstacles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import team.brussels.NinjaCoinCollector;

public class Rock {
    private static final float FALLING_DOWN_SPEED_X = 0;
    private static final float FALLING_DOWN_SPEED_Y = -5f;
    private Texture texture;
    private Vector2 position;
    private Vector2 velocity;
    private Rectangle bounds;



    public Rock(Vector2 position) {
        this.texture = NinjaCoinCollector.getResource("Rock");
        this.position = position;
        this.velocity = new Vector2(FALLING_DOWN_SPEED_X, FALLING_DOWN_SPEED_Y);
        this.bounds = new Rectangle(this.position.x, this.position.y, this.texture.getWidth(), this.texture.getHeight());
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Texture getTexture() {
        return texture;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void moveDown(){
        this.position.add(this.velocity);
        this.bounds.setPosition(this.position.x, this.position.y);
    }

    public void dispose(){
        this.texture = null;
    }
}
