package controles;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.GamePlayScreen;
import objetos.Bala;
import objetos.Nave;

public class Control extends InputListener {

    private Nave nave;
    private GamePlayScreen gamePlayScreen;

    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        switch (keycode) {
            case Input.Keys.A:
                nave.pos.x = -250;
                return true;
            case Input.Keys.D:
                nave.pos.x =  250;
                return true;
            default:
                return false;
        }
    }
    @Override
    public boolean keyUp(InputEvent event, int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.D:
                nave.pos.x =  0;
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean keyTyped(InputEvent event, char character) {
        if (character != ' '){
            return false;
        }
        Bala bala = new Bala();
        bala.setPosition(nave.getX() + nave.getHeight()/2+3, nave.getWidth()-35);
        gamePlayScreen.stage.addActor(bala);
        return true;
    }
}
