package Interfaces;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;


public interface IPickable {
    public TextureRegion getTexture();

    public Vector2 getPosition();

    public void setPosition(Vector2 position);

    public void update(float dt);
}
