package GameObjects.Animations;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class AnimationWalkRight {
    private static final int FrameCount = 7;
    private static final float CycleTime = 0.5f;
    private ArrayList<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameWidth;
    private int frame;

    public AnimationWalkRight(TextureRegion region){
        this.frames = new ArrayList<TextureRegion>();
        this.frameWidth = region.getRegionWidth() / FrameCount;
        for (int i = 0; i < FrameCount; i++) {
            this.frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
        }
        this.maxFrameTime = CycleTime / FrameCount;
        this.frame = 0;
    }

    public void update(float dt){
        this.currentFrameTime += dt;
        if (this.currentFrameTime >= this.maxFrameTime){
            this.frame++;
            this.currentFrameTime = 0;
        }
        if (this.frame >= this.FrameCount){
            this.frame = 0;
        }
    }

    public TextureRegion getFrame(){
        return this.frames.get(this.frame);
    }
}
