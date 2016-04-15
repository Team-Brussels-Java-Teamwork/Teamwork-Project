package States;


import com.badlogic.gdx.Screen;
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
        // this.handleInput();
    }

    public void handleInput(){
        // get imput from user
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

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
