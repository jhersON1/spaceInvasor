package objetos;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class Escudo extends Actor implements HealtActor {
    private float health;
    public Escudo() {
        this.health = 1;
    }

    public float getHealth() {
        return this.health;
    }

    @Override
    public float getHealt() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public void sumHealth(float sum) {
        health += sum;
    }

    private void checkHealth() {
        if (health < 0) health = 0;
        if (health > 1) health = 1;
    }

    private float timer = 0;

    @Override
    public void act(float delta) {
        timer += delta;
        if (timer > 2 && health < 1) {
            health++;
        }
    }
}
