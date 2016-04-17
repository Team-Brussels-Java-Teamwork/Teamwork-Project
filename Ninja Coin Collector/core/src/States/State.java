package States;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;


public abstract class State implements Screen {

    private GameStateManager gameStateManager;
    private Stage stage;

    public State(GameStateManager gsm) {
        this.gameStateManager = gsm;
    }
    public Stage getStage() {
        return this.stage;
    }

    public GameStateManager getGameStateManager() {
        return this.gameStateManager;
    }

    public void update(float dt){
        this.chechForEscape();
    }

    public void chechForEscape(){
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
        //super.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
