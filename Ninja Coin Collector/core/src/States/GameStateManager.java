package States;

import team.brussels.NinjaCoinCollector;

import java.util.Stack;

public class GameStateManager {
    private Stack<State> stack;
    private NinjaCoinCollector game;
    private State previousState;

    public GameStateManager(NinjaCoinCollector game) {
        this.game = game;
        this.stack = new Stack<State>();
        this.previousState = null;
    }

    public NinjaCoinCollector getGame() {

        return game;
    }

    public State getPreviousState() {

        return previousState;
    }

    public void setPreviousState(State previousState) {

        this.previousState = previousState;
    }

    public void push(State state){

        this.stack.push(state);
    }

    public void pop(){

        this.stack.pop();
    }

    public void set(State state){
        State prev = this.stack.pop();
        setPreviousState(prev);
        this.stack.push(state);
    }

    public void update(float dt){

        this.stack.peek().update(dt);
    }

    public void render(float dt){

        stack.peek().render(dt);
    }


}
