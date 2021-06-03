package objetos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.MyGame;

public class Nave extends Actor implements HealtActor{

    private TextureRegion nave;
    public Vector2 pos= new Vector2();
    public float velocity = 200f;
    public Rectangle naveRect;

    public Nave() {
        nave = new TextureRegion(MyGame.MANAGER.get("nave.png",Texture.class),500,500);
        setSize(110,90);
        this.naveRect = new Rectangle(getX(), getY(), getWidth()/2f, getHeight()/1.5f);
    }

    @Override
    public void act(float delta) {
        setPosition(pos.x, pos.y);
        naveRect.setPosition(pos.x+25, pos.y);
        pos.x = MathUtils.clamp(pos.x, 0, Gdx.graphics.getWidth()-getWidth());
        checkHealth();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(nave,pos.x,pos.y,getOriginX(),getOriginY(),getWidth(),
                   getHeight(),getScaleX(),getScaleY(),getRotation());

    }

    private float health = 1;

    @Override
    public float getHealt() {
        return health;
    }

    @Override
    public void setHealth(float health) {
        this.health = health;
    }

    @Override
    public void sumHealth(float sum) {
        health += sum;
    }
    private void checkHealth() {
        if (health < 0) health = 0;
        if (health > 1) health = 1;
    }
}
