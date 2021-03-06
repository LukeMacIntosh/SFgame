package com.mygdx.eggbandit.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.eggbandit.TbMenu;
import com.mygdx.eggbandit.TbsMenu;

/**
 * Created by luke on 2016-04-20.
 */

public class ScrMenu implements Screen, InputProcessor {
    com.mygdx.eggbandit.Main main;
    TbsMenu tbsMenu;
    TbMenu tbPlay, tbInstruct;
    Stage stage;
    SpriteBatch batch;
    BitmapFont screenName;
    Music mMenusong = Gdx.audio.newMusic(Gdx.files.internal("music/menu.mp3"));
    Viewport viewport;
    OrthographicCamera ocCam;
    float fGameworldWidth = 1920, fGameworldHeight = 1080;
    public static Texture backgroundTexture;
    public static Sprite backgroundSprite;

    public ScrMenu(com.mygdx.eggbandit.Main main) { //Referencing the main class.
        this.main = main;
    }

    public void show() {
        ocCam = new OrthographicCamera();
        ocCam.setToOrtho(false);
        viewport = new FillViewport(fGameworldWidth, fGameworldHeight, ocCam);
        viewport.apply();
        ocCam.position.set(fGameworldWidth / 2, fGameworldHeight / 2, 0);
        stage = new Stage();
        tbsMenu = new TbsMenu();
        batch = new SpriteBatch();
        backgroundTexture = new Texture("backgrounds/menu.jpg");
        backgroundSprite = new Sprite(backgroundTexture);
        screenName = new BitmapFont(Gdx.files.internal("fonts/8bit.fnt"));
        screenName.getData().setScale(5, 5);
        screenName.setColor(Color.WHITE);
        tbPlay = new TbMenu("PLAY", tbsMenu);
        tbInstruct = new TbMenu("INFO", tbsMenu);
        // Gdx.graphics.getWidth/Height is only necessary for the buttons
        // We scaled the whole game using out nWid and nHei variables,
        // but for some reason the buttons didn't like that and they need special treatment.
        tbPlay.setSize(Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 4);
        tbPlay.setY(Gdx.graphics.getHeight() / 2 - Gdx.graphics.getHeight() / 16);
        tbPlay.setX(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 7 - Gdx.graphics.getWidth()/100);
        tbInstruct.setSize(Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 4);
        tbInstruct.setY(Gdx.graphics.getHeight() / 2 - Gdx.graphics.getHeight() / 3);
        tbInstruct.setX(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 7 - Gdx.graphics.getWidth()/100);
        stage.addActor(tbPlay);
        stage.addActor(tbInstruct);
        //mMenusong = Gdx.audio.newMusic(Gdx.files.internal("music/menu.wav"));
        if (!mMenusong.isPlaying()) {
            mMenusong.play();
            mMenusong.setLooping(true);
        } // so the song doesn't overlap
        Gdx.input.setInputProcessor(stage);
        btnPlayListener();
        btnInstructListener();
    }

    public void renderBackground() {
        backgroundSprite.draw(batch);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        ocCam.position.set(fGameworldWidth / 2, fGameworldHeight / 2, 0);
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ocCam.update();
        batch.setProjectionMatrix(ocCam.combined);
        batch.begin();
        renderBackground();
        screenName.draw(batch, "EGG BANDIT", 200, 975);
        batch.end();
        stage.act();
        stage.draw();
    }

    public void btnPlayListener() {
        tbPlay.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                main.currentState = com.mygdx.eggbandit.Main.GameState.PLAY;
                main.updateState();
                mMenusong.stop();
            }
        });
    }

    public void btnInstructListener() {
        tbInstruct.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                main.currentState = com.mygdx.eggbandit.Main.GameState.INSTRUCT;
                main.updateState();
            }
        });
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
        mMenusong.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}