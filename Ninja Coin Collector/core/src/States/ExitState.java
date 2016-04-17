package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import team.brussels.NinjaCoinCollector;

/**
 * Created by Nasko on 13.4.2016 г..
 */
public class ExitState extends State {

    private static final int PlayButtonStartX = 200;
    private static final int PlayButtonStartY = 80;
    private static final int ExitButtonStartX = 700;
    private static final int ExitButtonStartY = 80;


    private Camera camera;
    private Viewport viewport;

    private Image background;
    private Image previousBackground;
    private Button playBtn;
    private Button exitBtn;
    private Stage stage;

    private BitmapFont fontCoins;

    public ExitState(GameStateManager gsm) {
        super(gsm);
        this.camera = new OrthographicCamera();
        this.viewport = new StretchViewport(NinjaCoinCollector.WIDTH, NinjaCoinCollector.HEIGHT, camera);
        this.viewport.apply();
        this.camera.position.set(camera.viewportWidth, camera.viewportHeight, 0);
        this.stage = new Stage(this.viewport);

        Gdx.input.setInputProcessor(this.stage);

        this.background = new Image(NinjaCoinCollector.getResource("ExitBackground"));
        this.previousBackground = new Image(NinjaCoinCollector.getResource("PlayBackground"));
        this.initializeButtons();
        this.stage.addActor(this.previousBackground);
        this.stage.addActor(this.background);
        this.stage.addActor(this.playBtn);
        this.stage.addActor(this.exitBtn);

        this.fontCoins = new BitmapFont();
        this.fontCoins.setColor(Color.GOLD);
        this.fontCoins.getData().setScale(2, 2);
    }

    public void handleInput() {
        if (this.playBtn.isChecked()){
            this.getGameStateManager().set(new PlayState(this.getGameStateManager()));
        }
        if (this.exitBtn.isChecked()){
            Gdx.app.exit();
        }
    }

    @Override
    public void update(float dt) {
        this.handleInput();
        super.update(dt);

    }

    @Override
    public void render(float dt) {
        this.camera.update();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        PlayState previosState = (PlayState)this.getGameStateManager().getPreviousState();

        this.getGameStateManager().getGame().getBatch().begin();
        this.stage.draw();
        this.getGameStateManager().getGame().getBatch().end();

        this.getGameStateManager().getGame().getBatch().begin();
        this.fontCoins.draw(this.getGameStateManager().getGame().getBatch(), "TOTAL COINS: " + previosState.getPlayer().getCoinsCollected(), NinjaCoinCollector.WIDTH / 2 - 150, NinjaCoinCollector.HEIGHT / 2);
        this.getGameStateManager().getGame().getBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        this.stage.getViewport().update(width, height);
        this.camera.position.set(camera.viewportWidth, camera.viewportHeight, 0);
    }

    private void initializeButtons(){
        Skin skin = new Skin(NinjaCoinCollector.getButtonAtlas());

        Button.ButtonStyle playStyle = new Button.ButtonStyle();
        playStyle.up = skin.getDrawable("PlayButton");
        this.playBtn = new Button(playStyle);
        this.playBtn.setPosition(PlayButtonStartX, PlayButtonStartY);

        Button.ButtonStyle exitStyle = new Button.ButtonStyle();
        exitStyle.up = skin.getDrawable("ExitButton");
        this.exitBtn = new Button(exitStyle);
        this.exitBtn.setPosition(ExitButtonStartX, ExitButtonStartY);
    }
}

