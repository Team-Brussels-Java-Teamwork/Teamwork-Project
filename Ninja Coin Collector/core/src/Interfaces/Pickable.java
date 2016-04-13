package Interfaces;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public interface Pickable {
    public TextureRegion getTexture();

    public Vector2 getPosition();

    public void setPosition(Vector2 position);

    public Rectangle getBounds();

    public int getCoinValue();

    public void resetPosition();

    public void update(float dt);
}
