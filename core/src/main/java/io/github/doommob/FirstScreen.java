package io.github.doommob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
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

        // Create Cenario
        cenario = new Cenario(580, 520, 485, Color.WHITE, Color.RED);

        // Create Direcional and link it with Cenario
        direcional = new Direcional(2200, 800, 100, Color.BLUE, Color.CHARTREUSE, cenario);

        // Input handler
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
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);

        // Render textures
        batch.begin();
        batch.end();

        // Render Cenario and Direcional
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
