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
    private Mapa mapa;

    @Override
    public void show() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        mapa = new Mapa();

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
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);

        // Define parâmetros do jogador
        float playerX = 2.5f; // Posição no grid
        float playerY = 2.5f;
        float playerAngle = direcional.getAngle();

        // Projeção de "raios"
        int numRays = 100; // Resolução horizontal
        float fov = (float) Math.toRadians(60); // Campo de visão
        float rayStep = fov / numRays;

        for (int i = 0; i < numRays; i++) {
            float rayAngle = playerAngle - fov / 2 + i * rayStep;
            float distance = castRay(playerX, playerY, rayAngle);

            // Calcula altura da parede
            float wallHeight = 480 / distance;

            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.GRAY);
            shapeRenderer.rect(i * 8, 240 - wallHeight / 2, 8, wallHeight); // 8 é a largura de cada "coluna"
            shapeRenderer.end();
        }

        // Renderiza o Direcional
        direcional.draw(shapeRenderer, batch);
    }

    private float castRay(float startX, float startY, float angle) {
        float x = startX;
        float y = startY;
        float stepX = (float) Math.cos(angle) * 0.1f;
        float stepY = (float) Math.sin(angle) * 0.1f;

        while (true) {
            x += stepX;
            y += stepY;

            int tileX = (int) x;
            int tileY = (int) y;

            if (mapa.getTile(tileX, tileY) == 1) {
                return (float) Math.sqrt((x - startX) * (x - startX) + (y - startY) * (y - startY));
            }
        }
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
