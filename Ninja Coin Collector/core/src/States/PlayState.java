package States;

import GameObjects.Coins.CoinFish;
import GameObjects.Coins.CoinStar;
import GameObjects.Obstacles.Bomb;
import GameObjects.Obstacles.Rock;
import GameObjects.Players.Player;
import Interfaces.Pickable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import team.brussels.NinjaCoinCollector;

import java.util.ArrayList;
import java.util.Random;

public class PlayState extends State {
    private static int MAX_DIFFICULTY = 99;
    private static int DIFFICULTY_CHANGER = 50;
    private static int STARTING_DIFFICULTY = 10;
    private static int STARTING_MAX_ROCKS = 6;
    private int difficulty;
    private int maxRocksCount;
    private Random rockPositionXGenerator = new Random(); // generate random rock position at X
    private Random rockGenerator = new Random(); // generate Rock or not - chance 50 %

    private Texture background;
    private Player player;
    private Bomb bomb;
    private Pickable coinStar1;
    private Pickable coinStar2;
    private Pickable coinFish3;
    private Pickable coinFish4;
    private Pickable coinStar5;
    private Pickable coinStar6;
    private ArrayList<Rock> rocks;
    private ArrayList<Pickable> coins;
    private BitmapFont fontLives;
    private BitmapFont fontCoins;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        this.background = this.getGameStateManager().getGame().getResource("PlayBackground");
        this.player = new Player();
        this.bomb = new Bomb();
        this.seedCoins();
        this.rocks = new ArrayList<Rock>();
        this.difficulty = this.STARTING_DIFFICULTY;
        this.maxRocksCount = this.STARTING_MAX_ROCKS;
        this.fontCoins = new BitmapFont();
        this.fontCoins.setColor(Color.GOLD);
        this.fontCoins.getData().setScale(2, 2);
        this.fontLives = new BitmapFont();
        this.fontLives.setColor(Color.GREEN);
        this.fontLives.getData().setScale(2, 2);
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.getGameStateManager().getGame().getBatch().begin();
        this.getGameStateManager().getGame().getBatch().draw(this.background, 0, 0);
        this.getGameStateManager().getGame().getBatch().draw(this.player.getTexture(), this.player.getPosition().x, this.player.getPosition().y);
        this.getGameStateManager().getGame().getBatch().draw(this.bomb.getTexture(), this.bomb.getPosition().x, this.bomb.getPosition().y);

        for (Pickable coin : this.coins) {
            this.getGameStateManager().getGame().getBatch().draw(coin.getTexture(), coin.getPosition().x, coin.getPosition().y);
        }

        for (Rock rock : rocks) {
            this.getGameStateManager().getGame().getBatch().draw(rock.getTexture(), rock.getPosition().x, rock.getPosition().y);
        }

        this.fontLives.draw(this.getGameStateManager().getGame().getBatch(), "LIVES: " + this.player.getLives(), 50, NinjaCoinCollector.HEIGHT - 100);
        this.fontCoins.draw(this.getGameStateManager().getGame().getBatch(), "COINS: " + this.player.getCoinsCollected(), NinjaCoinCollector.WIDTH - 250, NinjaCoinCollector.HEIGHT - 100);
        this.getGameStateManager().getGame().getBatch().end();
    }

    @Override
    public void update(float dt) {
        this.player.update(dt);
        this.bomb.update(dt);
        this.updateCoinPosiotion(dt);
        this.generateRock();
        this.moveRocksDown();
        this.collisionDetectorForCoins();
        this.collisionDetectorForBomb();
        this.collisionDetectorForRocks();
        super.update(dt);
    }

    public void generateRock(){
        if (this.rocks.size() < this.maxRocksCount) {

            int randomFactor = this.rockGenerator.nextInt(101 - this.difficulty);
            if (randomFactor == 1) {
                float randomX = (float) rockPositionXGenerator.nextInt(NinjaCoinCollector.WIDTH - 100);
                Vector2 startingPosition = new Vector2(randomX, NinjaCoinCollector.HEIGHT);
                Rock rock = new Rock(startingPosition);
                this.rocks.add(rock);
            }
        }
    }

    public void moveRocksDown(){
        for (int i = 0; i < this.rocks.size(); i++) {
            rocks.get(i).moveDown();
            if (rocks.get(i).getPosition().y <= NinjaCoinCollector.EARTH_HEIGHT){
                rocks.get(i).dispose();
                this.rocks.remove(i);
                i--;
            }
        }
    }

    public void seedCoins(){
        this.coinStar1 = new CoinStar();
        this.coinStar2 = new CoinStar();
        this.coinFish3 = new CoinFish();
        this.coinFish4 = new CoinFish();
        this.coinStar5 = new CoinStar();
        this.coinStar6 = new CoinStar();
        this.coins = new ArrayList<Pickable>();
        this.coins.add(coinStar1);
        this.coins.add(coinStar2);
        this.coins.add(coinFish3);
        this.coins.add(coinFish4);
        this.coins.add(coinStar5);
        this.coins.add(coinStar6);
    }

    public void updateCoinPosiotion(float dt){
        for (Pickable coin : this.coins) {
            coin.update(dt);
        }
    }

    public void adjustDifficulty(){
        this.difficulty += 10;
        if (this.difficulty > MAX_DIFFICULTY){
            this.difficulty = MAX_DIFFICULTY;
        }
        if (this.player.getCoinsCollected() % 500 == 0){
            this.maxRocksCount += 3;
        }
    }

    public void collisionDetectorForRocks(){
        for (Rock rock : this.rocks) {
            if (this.player.getBounds().overlaps(rock.getBounds())){
                int currentLives = this.player.getLives();
                this.player.setLives(currentLives - 1);
                if (this.player.getLives() == 0){
                    this.getGameStateManager().set(new ExitState(this.getGameStateManager()));
                }
                this.resetState();
                break;
            }
        }
    }

    public void collisionDetectorForBomb(){
        if (this.player.getBounds().overlaps(this.bomb.getBounds())){
            int currentLives = this.player.getLives();
            this.player.setLives(currentLives - 1);
            if (this.player.getLives() == 0){
                this.getGameStateManager().set(new ExitState(this.getGameStateManager()));
            }
            this.resetState();
        }
    }

    public void collisionDetectorForCoins(){
        for (Pickable coin : this.coins) {
            if (this.player.getBounds().overlaps(coin.getBounds())){
                int currentCoins = this.player.getCoinsCollected();
                int value = coin.getCoinValue();
                this.player.setCoinsCollected(currentCoins + value);
                coin.resetPosition();

                if (this.player.getCoinsCollected() % this.DIFFICULTY_CHANGER == 0) {
                    this.adjustDifficulty();
                }
            }
        }
    }

    public void resetState(){
        this.bomb.resetPosition();
        this.rocks.clear();
        this.coins.clear();
        this.seedCoins();
    }


}
