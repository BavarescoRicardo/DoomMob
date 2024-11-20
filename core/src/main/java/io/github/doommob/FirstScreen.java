package io.github.doommob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class FirstScreen implements Screen {
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private Direcional direcional;
    private Cenario cenario;

    @Override
    public void show() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        // Create Cenario at position (580, 520) with radius 485
        cenario = new Cenario(580, 520, 485, Color.WHITE, Color.RED);

        // Create Direcional at position (2200, 800) with radius 100
        direcional = new Direcional(2200, 800, 100, Color.BLUE, Color.CHARTREUSE, cenario);

        // Input handler for touch drag
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                float touchX = screenX;
                float touchY = Gdx.graphics.getHeight() - screenY; // Flip Y-axis
                if (direcional.isTouched(touchX, touchY)) {
                    direcional.updateInnerDotPosition(touchX, touchY);
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); // Clear screen with black
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);

        // Render shapes
        cenario.draw(shapeRenderer);
        direcional.draw(shapeRenderer, batch);
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
        direcional.dispose();
    }
}
