package objetos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.MyGame;

public class Bala extends Actor {

    private TextureRegion bala;
    public Vector2 pos= new Vector2();
    public float velocity = 150f;
    public Rectangle balaRect;

    public Bala(){
        bala = new TextureRegion(MyGame.MANAGER.get("bala.png", Texture.class),1200,1200);
        setSize(20,20);
        float delta = Gdx.graphics.getDeltaTime();
        pos.y += velocity * delta;
        this.balaRect = new Rectangle(getX(), getY(), getWidth()/2, getHeight()/2);
    }

    @Override
    public void act(float delta) {
        setPosition(pos.x, pos.y);
        setPosition(balaRect.x, balaRect.y);
        pos.y += velocity * delta;
        balaRect.y = pos.y;
        balaRect.x = pos.x;
        if (getY() > getStage().getHeight()){
            remove();
            balaRect.setPosition(-1000,-1000);
            System.out.println("eliminado");

        }
/*       System.out.println("regtangulo en y: "+balaRect.y);
        System.out.println("regtangulo en x: "+balaRect.x);
        System.out.println("bala en y: "+pos.y);
        System.out.println("bala en x: "+pos.x);*/
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(bala,getX(),getY(),getOriginX(),getOriginY(),getWidth(),
                getHeight(),getScaleX(),getScaleY(),getRotation());
    }
}
