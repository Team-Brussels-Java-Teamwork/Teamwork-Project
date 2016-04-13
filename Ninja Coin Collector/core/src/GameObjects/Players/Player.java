package GameObjects.Players;

import GameObjects.Animations.AnimationJumpLeft;
import GameObjects.Animations.AnimationJumpRight;
import GameObjects.Animations.AnimationWalkLeft;
import GameObjects.Animations.AnimationWalkRight;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import team.brussels.NinjaCoinCollector;

public class Player {
    private static int StartWidth = NinjaCoinCollector.WIDTH / 2;
    private static int StartHeight = NinjaCoinCollector.EARTH_HEIGHT;
    private static int Lives = 3;
    private static int CoinsCollected = 0;
    private static int CanonLimit = 100;
    private static float JumpLimit = 200;
    private static float SideVelocityX = 5f;
    private static float SideVelocityY = 0;
    private static float UpVelocityX = 0;
    private static float UpVelocityY = 7;
    private static float UpSideVelocityX = 3;
    private static float UpSideVelocityY = 0;

    private TextureRegion textureRegion;
    private Vector2 position;
    private Vector2 sideVelocity;
    private Vector2 upVelocity;
    private Vector2 upSideVelocity;

    private Rectangle bounds;
    private PlayerState playerState;
    private TextureRegion standingLeft;
    private TextureRegion standingRight;
    private TextureRegion walkLeft;
    private TextureRegion walkRight;
    private TextureRegion jumpLeft;
    private TextureRegion jumpRight;
    private AnimationWalkLeft walkLeftAnimation;
    private AnimationWalkRight walkRightAnimation;
    private AnimationJumpLeft jumpLeftAnimation;
    private AnimationJumpRight jumpRightAnimation;

    private int lives;
    private int coinsCollected;

    public Player() {
        this.position = new Vector2(StartWidth, StartHeight);
        this.sideVelocity = new Vector2(SideVelocityX, SideVelocityY);
        this.upVelocity = new Vector2(UpVelocityX, UpVelocityY);
        this.upSideVelocity = new Vector2(UpSideVelocityX, UpSideVelocityY);
        this.playerState = PlayerState.Standing;

        this.standingLeft = new TextureRegion(NinjaCoinCollector.getResource("StandingLeft"));
        this.standingRight = new TextureRegion(NinjaCoinCollector.getResource("StandingRight"));
        this.walkLeft = new TextureRegion(NinjaCoinCollector.getResource("MovesLeft"));
        this.walkLeftAnimation = new AnimationWalkLeft(this.walkLeft);
        this.walkRight = new TextureRegion(NinjaCoinCollector.getResource("MovesRight"));
        this.walkRightAnimation = new AnimationWalkRight(this.walkRight);
        this.jumpLeft = new TextureRegion(NinjaCoinCollector.getResource("JumpsLeft"));
        this.jumpLeftAnimation = new AnimationJumpLeft(this.jumpLeft);
        this.jumpRight = new TextureRegion(NinjaCoinCollector.getResource("JumpsRight"));
        this.jumpRightAnimation = new AnimationJumpRight(this.jumpRight);

        this.textureRegion = this.standingLeft;

        this.bounds = new Rectangle(this.position.x, this.position.y, this.textureRegion.getRegionWidth(), this.textureRegion.getRegionHeight());
        this.lives = this.Lives;
        this.coinsCollected = this.CoinsCollected;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getCoinsCollected() {
        return coinsCollected;
    }

    public void setCoinsCollected(int coinsCollected) {
        this.coinsCollected = coinsCollected;
    }

    public Rectangle getBounds() {
        return bounds;
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

    public PlayerState getState() {
        return this.playerState;
    }

    public void setState(PlayerState playState) {

        this.playerState = playState;
    }

    public void update(float dt) {
        this.handleInput();
        if (this.playerState == PlayerState.WalkingLeft){
            this.walkLeftAnimation.update(dt);
        } else if (this.playerState == PlayerState.WalkingRight){
            this.walkRightAnimation.update(dt);
        } else if(this.playerState == PlayerState.JumpLeft){
            this.jumpLeftAnimation.update(dt);
            this.textureRegion = this.jumpLeftAnimation.getFrame();
            if (this.position.y <= JumpLimit) {
                this.position.add(this.upVelocity);
                this.position.sub(this.upSideVelocity);
                if (this.position.x <= 0){
                    this.position.x = 0;
                }
            }
            else {
                this.playerState = PlayerState.FallLeft;
            }
        } else if (this.playerState == PlayerState.FallLeft) {
            this.jumpLeftAnimation.update(dt);
            this.textureRegion = this.jumpLeftAnimation.getFrame();
            this.position.sub(this.upVelocity);
            this.position.sub(this.upSideVelocity);
            if (this.position.x <= 0){
                this.position.x = 0;
            }
            if (this.position.y <= StartHeight){
                this.position.y = StartHeight;
                this.playerState = PlayerState.Standing;
                this.textureRegion = this.standingLeft;
            }
        } else if(this.playerState == PlayerState.JumpRight){
            this.jumpRightAnimation.update(dt);
            this.textureRegion = this.jumpRightAnimation.getFrame();
            if (this.position.y <= JumpLimit) {
                this.position.add(this.upVelocity);
                this.position.add(this.upSideVelocity);
                if (this.position.x >= NinjaCoinCollector.WIDTH - CanonLimit - this.getTexture().getRegionWidth()){
                    this.position.x = NinjaCoinCollector.WIDTH - CanonLimit - this.getTexture().getRegionWidth();
                }
            }
            else {
                this.playerState = PlayerState.FallRight;
            }
        } else if (this.playerState == PlayerState.FallRight) {
            this.jumpRightAnimation.update(dt);
            this.textureRegion = this.jumpRightAnimation.getFrame();
            this.position.sub(this.upVelocity);
            this.position.add(this.upSideVelocity);
            if (this.position.x >= NinjaCoinCollector.WIDTH - CanonLimit - this.getTexture().getRegionWidth()){
                this.position.x = NinjaCoinCollector.WIDTH - CanonLimit - this.getTexture().getRegionWidth();
            }
            if (this.position.y <= StartHeight){
                this.position.y = StartHeight;
                this.playerState = PlayerState.Standing;
                this.textureRegion = this.standingRight;
            }
        }
        else if (this.playerState == PlayerState.JumpUp) {
            if (this.position.y <= JumpLimit) {
                this.position.add(this.upVelocity);
            }
            else {
                this.playerState = PlayerState.FallDown;
            }
        }
        else if (this.playerState == PlayerState.FallDown) {
            this.position.sub(this.upVelocity);
            if (this.position.y <= StartHeight){
                this.position.y = StartHeight;
                this.playerState = PlayerState.Standing;
            }
        }

        this.bounds.setPosition(this.position.x, this.position.y);
    }

    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                if (this.validateMove()) {
                    this.playerState = PlayerState.JumpLeft;
                }
            }
            if (validateMove()) { // player cant move if he in the air jumping
                this.playerState = PlayerState.WalkingLeft;
                this.textureRegion = this.walkLeftAnimation.getFrame();
                this.position.sub(sideVelocity);
                if (this.position.x <= 0){
                    this.position.x = 0;
                }
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                if (this.validateMove()) {
                    this.playerState = PlayerState.JumpRight;
                }
            }
            if (validateMove()) {
                this.playerState = PlayerState.WalkingRight;
                this.textureRegion = this.walkRightAnimation.getFrame();
                this.position.add(sideVelocity);
                if (this.position.x >= NinjaCoinCollector.WIDTH - CanonLimit - this.getTexture().getRegionWidth()){
                    this.position.x = NinjaCoinCollector.WIDTH - CanonLimit - this.getTexture().getRegionWidth();
                }
            }
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            if (validateMove()) {
                this.playerState = PlayerState.JumpUp;
            }
        } else {
            if (this.playerState == PlayerState.WalkingLeft) {
                this.playerState = PlayerState.Standing;
                this.textureRegion = this.standingLeft;
            } else if (this.playerState == PlayerState.WalkingRight) {
                this.playerState = PlayerState.Standing;
                this.textureRegion = this.standingRight;
            }
        }
    }

    public boolean validateMove(){
        if (this.playerState != PlayerState.JumpLeft
                && this.playerState != PlayerState.FallLeft
                && this.playerState != PlayerState.FallRight
                && this.playerState != PlayerState.JumpRight
                && this.playerState != PlayerState.FallDown
                && this.playerState != PlayerState.JumpUp){
            return true;
        }

        return false;
    }
}
