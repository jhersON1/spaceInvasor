package objetos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Enemigo extends Actor {

    //private TextureRegion enemigo;
    public Vector2 pos = new Vector2();
    public Vector2 velocidad = new Vector2();
    public Rectangle enemigoRect;
    //para la animación
    private Animation animation;
    private float tiempo;
    private TextureRegion [] regionsMomiviento;
    private Texture imagen;
    private TextureRegion frameActual;
    public float x1 ;
    public boolean repetirAnimacion;
    //bandera para saber si la nave se movera hacia la izquierda o hacia la derecha
    public boolean cambioDireccion;

    private Nave nave;


    public Enemigo(int cantImagenes, boolean estadoCiclo, float frameDuration, Nave nave1){
        setSize(60,60);
        this.repetirAnimacion = estadoCiclo;
        cargarAnimacion(cantImagenes, frameDuration);
        this.tiempo = 0f;
        this.x1 = 0;
        this.velocidad.x = 80f;
        this.velocidad.y = -150f;
        this.enemigoRect = new Rectangle(getX(), getY(), getWidth(), getHeight());
        this.cambioDireccion = true;
        this.nave = nave1;
    }
    private void cargarAnimacion(int cantImagenes, float frameDuration){
        this.imagen = new Texture(Gdx.files.internal("NavesYExplosion.png"));
        TextureRegion [] [] tmp = TextureRegion.split(imagen, imagen.getWidth()/14, imagen.getHeight());
        this.regionsMomiviento = new TextureRegion[cantImagenes];
        for (int i=0; i < cantImagenes; i++){
            regionsMomiviento[i] = tmp[0][i];
        }
        this.animation = new Animation(frameDuration,regionsMomiviento);
    }

    @Override
    public void act(float delta) {
        if (cambioDireccion == true){
            estado1(delta);
        }
        if(cambioDireccion != true){
            estado2(delta);
        }
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        tiempo += Gdx.graphics.getDeltaTime();
        frameActual = (TextureRegion) animation.getKeyFrame(tiempo,repetirAnimacion);

        batch.draw(frameActual,pos.x,pos.y,getOriginX(),getOriginY(),getWidth(),
                getHeight(),getScaleX(),getScaleY(),getRotation());

    }

    private void estado1(float delta){
        if (repetirAnimacion == true){
            enemigoRect.setPosition(pos);
        }else{
            enemigoRect.setPosition(-50,-50);
        }
        pos.x += velocidad.x * delta;
        pos.x = MathUtils.clamp(pos.x, x1, x1+64);
        if(pos.x <= x1 || pos.x > x1 + 63 ){
            velocidad.x = velocidad.x *(-1);
        }
        if (pos.y < 0){
            remove();
        }
        if (tiempo > 2.6 && repetirAnimacion == false) {
            remove();
            System.out.println("enemigo eliminado");
        }
    }
    public void estado2(float delta){
        enemigoRect.setPosition(pos);
        pos.y += velocidad.y * delta;
        pos.x += velocidad.x * delta;
        if (pos.y < 0 || pos.y > Gdx.graphics.getHeight() - getHeight()){
            velocidad.y = velocidad.y * (-1);
        }
        if(pos.x < nave.pos.x){
            if (velocidad.x < 0){
                velocidad.x = velocidad.x * (-1);
            }
        }else{
            if(velocidad.x > 0){
                velocidad.x = velocidad.x * (-1);
            }
        }
        pos.x = MathUtils.clamp(pos.x, 0, Gdx.graphics.getWidth());
        pos.y = MathUtils.clamp(pos.y, 0, Gdx.graphics.getHeight());
    }
}
