package objetos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.MyGame;

public class Vida extends Actor {

    private Texture barra;
    private HealtActor h;

    public Vida(HealtActor h) {
        this.barra = MyGame.MANAGER.get("barraVida.png", Texture.class);
        //this.barra = new Texture(Gdx.files.internal("barraVida.png"));
        setSize(barra.getWidth(), barra.getHeight()/1.5f);
        //setSize(256, 30);
        this.h = h;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.draw(barra, getX(), getY(), 0, 0, (int) (getWidth() * h.getHealt()), (int) getHeight());

    }


}
