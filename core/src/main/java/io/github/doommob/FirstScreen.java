package io.github.doommob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FirstScreen implements Screen {
    private SpriteBatch batch;
    private Texture demonTexture;

    @Override
    public void show() {
        // Initialize your SpriteBatch and load the texture
        batch = new SpriteBatch();
        demonTexture = new Texture(Gdx.files.internal("Demon.png"));
    }

    @Override
    public void render(float delta) {
        // Clear the screen (optional, ensures a clean slate each frame)
        Gdx.gl.glClearColor(0, 0, 0, 1); // Black background
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);

        // Begin drawing and render the texture
        batch.begin();
        batch.draw(demonTexture, 100, 100); // Draw at position (100, 100)
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // Handle resizing logic if needed
    }

    @Override
    public void pause() {
        // Handle pause state if needed
    }

    @Override
    public void resume() {
        // Handle resume state if needed
    }

    @Override
    public void hide() {
        // Cleanup when the screen is hidden
    }

    @Override
    public void dispose() {
        // Dispose of assets to free resources
        batch.dispose();
        demonTexture.dispose();
    }
}
