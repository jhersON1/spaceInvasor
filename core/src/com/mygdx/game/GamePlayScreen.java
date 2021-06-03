package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import objetos.*;

import java.util.ArrayList;
import java.util.List;

public class GamePlayScreen extends AbstractScreen{

    public Stage stage;
    private int posicionEnemigo;
    private int numeroDeEnemigos;
    public Nave nave;
    private Bala bala;
    private Enemigo enemigo;
    private float timer;
    private float timerPersecucion;
    private int contadorEnemigos = 0;
    private List<Enemigo> listaDeEnemigos;
    private List<Bala> listaDeBalas;
    private int enemigosVivos;
    private float tiempoEspera;
    private float tiempoDeEspera;

    private Vida vidaNave, vidaEscudo;
    private Escudo escudo;

    private Puntuacion puntuacion;
    public GamePlayScreen(MyGame myGame){

        super(myGame);

    }

    @Override
    public void show() {
        this.listaDeEnemigos = new ArrayList<Enemigo>();
        this.listaDeBalas = new ArrayList<Bala>();
        this.posicionEnemigo = 0;
        this.contadorEnemigos = 0;
        this.numeroDeEnemigos = 6;
        this.stage = new Stage();
        this.nave = new Nave();
        this.timerPersecucion = 5;
        this.enemigosVivos = numeroDeEnemigos;
        this.tiempoEspera = 3;
        this.tiempoDeEspera = 8;
        Image imgFondo = new Image(MyGame.MANAGER.get("fondo3.png", Texture.class));
        imgFondo.setFillParent(true);
        stage.addActor(imgFondo);

        Gdx.input.setInputProcessor(stage);
        nave.setPosition(nave.pos.x,nave.pos.y);
        stage.setKeyboardFocus(nave);
        stage.addActor(nave);


        //this. escudo = new Escudo();
        //this.vidaEscudo = new Vida(escudo);
        this.vidaNave = new Vida(nave);


//        vidaEscudo.setPosition(stage.getWidth() -270, Gdx.graphics.getHeight()-20);
        //vidaEscudo.setPosition(150, 18);
        vidaNave.setPosition(stage.getWidth() -270, Gdx.graphics.getHeight()-20);
        //stage.addActor(escudo);
        //stage.addActor(vidaEscudo);
        stage.addActor(vidaNave);


        puntuacion = new Puntuacion(new BitmapFont());
        puntuacion.setPosition(10, stage.getHeight() - 10);
        puntuacion.puntuacion = 0;
        stage.addActor(puntuacion);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.6f, 0.6f, 0.7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        generarEnemigos(delta,posicionEnemigo,0, 2, true, 0.3f);
        colision();
        update();
        siguienteNivel();
        perseguirANave();

        stage.act();
        stage.draw();
    }

    private void generarEnemigos(float delta, float posx,float posy, int cantImagenes, boolean estadoCiclo, float frameDuration){
        if (contadorEnemigos < numeroDeEnemigos && estadoCiclo == true) {
            // Spawn de un nuevo enemigo
            enemigo = new Enemigo(cantImagenes,estadoCiclo, frameDuration,nave);
            enemigo.pos.x = posx;
            enemigo.pos.y = 400;
            enemigo.enemigoRect.x= enemigo.getX();
            enemigo.enemigoRect.y= enemigo.getY();
            enemigo.x1 = posx;
            stage.addActor(enemigo);
            listaDeEnemigos.add(enemigo);
            contadorEnemigos++;
            posicionEnemigo += 128;
        }else if(estadoCiclo == false){
            enemigo = new Enemigo(cantImagenes,estadoCiclo, frameDuration, nave);
            enemigo.pos.x = posx;
            enemigo.pos.y = posy;
            enemigo.x1 = posx;
            stage.addActor(enemigo);
        }
    }

    public void siguienteNivel(){
        if (enemigosVivos == 0 ) {
            this.posicionEnemigo = 0;
            this.contadorEnemigos = 0;
            this.numeroDeEnemigos = 6;
            this.enemigosVivos = numeroDeEnemigos;
            this.tiempoDeEspera -= 2;
            if (tiempoDeEspera < 2){
                this.tiempoDeEspera = 0.5f;
            }
            enemigo.velocidad.y -= 100;
            enemigo.velocidad.x += 100;
            nave.velocity += 20;
            if (nave.velocity > 400){
                nave.velocity = 400;
            }

        }
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
        Gdx.input.setInputProcessor(null);
    }


    @Override
    public void dispose() {
        stage.dispose();
    }

    private void update(){
        float delta = Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            nave.pos.x +=  nave.velocity * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            nave.pos.x -=  nave.velocity * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            timer -= delta;
            if (timer < 0) {
                bala = new Bala();
                bala.setPosition(nave.getX() + nave.getHeight() / 2 + 3, nave.getWidth() / 1.4f);
                bala.pos.x=nave.getX() + nave.getHeight() / 2 + 3;
                bala.pos.y = nave.getWidth() / 1.4f;
                bala.balaRect.setPosition(bala.pos);
                stage.addActor(bala);
                listaDeBalas.add(bala);
                timer = 0.6f;
                MyGame.MANAGER.get("sonidos/shoot.ogg", Sound.class).play();
            }
        }
    }

    private void perseguirANave(){
        float delta = Gdx.graphics.getDeltaTime();
        timerPersecucion -= delta;
        if (timerPersecucion < 0 && listaDeEnemigos.size() > 0) {
            int n = (int) (Math.random()*enemigosVivos );
            enemigo = listaDeEnemigos.get(n);
            enemigo.cambioDireccion = false;
            timerPersecucion = tiempoDeEspera;
        }
    }

    private void colision (){
        //Enemigo enemigo;
        float delta = Gdx.graphics.getDeltaTime();
        for(int i = 0; i < listaDeEnemigos.size(); i++){
            enemigo =  listaDeEnemigos.get(i);
            tiempoEspera -= delta;
            if (tiempoEspera < 0) {
                if (listaDeEnemigos.get(i).enemigoRect.overlaps(nave.naveRect)) {
                    enemigosVivos--;
                    listaDeEnemigos.get(i).remove();
                    listaDeEnemigos.remove(i);
                    nave.sumHealth(-0.4f);
                    MyGame.MANAGER.get("sonidos/hit.ogg", Sound.class).play();
                    System.out.println("choco con la nave");
                    if(nave.getHealt() <= 0) {
                        game.setScreen(game.GAMEOVER);
                    }
                }
            }
            for (int j = 0; j < listaDeBalas.size(); j++){
                if (listaDeBalas.get(j).balaRect.overlaps(enemigo.enemigoRect)){
                    System.out.println("la bala choco con el enemigo");
                    enemigo.enemigoRect.setPosition(-100,-100);
                    generarEnemigos(2f, enemigo.pos.x, enemigo.pos.y,14, false, 0.1f );
                    enemigosVivos--;
                    listaDeBalas.get(j).remove();
                    listaDeBalas.remove(j);
                    listaDeEnemigos.get(i).remove();
                    listaDeEnemigos.remove(i);
                    puntuacion.puntuacion++;
                    MyGame.MANAGER.get("sonidos/explosion.ogg", Sound.class).play();
                }
            }
        }
    }
}
