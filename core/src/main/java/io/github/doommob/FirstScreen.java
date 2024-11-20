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
    private Player player;

    @Override
    public void show() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        mapa = new Mapa();

        // Configuramos a posição inicial do jogador no centro de um quarto
        player = new Player(3.5f, 3.5f, 0); // Posição inicial no quarto (3,3) com ângulo 0 radianos

        // Criamos o direcional e cenário
        cenario = new Cenario(580, 520, 485, Color.WHITE, Color.RED);
        direcional = new Direcional(2000, 800, 100, Color.BLUE, Color.CHARTREUSE, cenario);

        // Input handler para detectar o toque no direcional
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                float touchX = screenX;
                float touchY = Gdx.graphics.getHeight() - screenY; // Inverte o eixo Y
                if (direcional.isTouched(touchX, touchY)) {
                    direcional.updateInnerDotPosition(touchX, touchY);
                }
                return true;
            }
        });
    }


    private void updatePlayerPosition() {
        // Verifica se o direcional está sendo usado
        if (direcional.isBeingTouched()) {
            float movementIntensity = direcional.getMovementIntensity(); // Intensidade do movimento (0 a 1)
            float moveSpeed = 0.05f * movementIntensity; // Ajustar a velocidade de movimento com base na intensidade

            // Atualiza o ângulo do jogador
            float angleDelta = direcional.getAngle();
            player.rotate(angleDelta);

            // Calcula o deslocamento com base no ângulo do jogador
            float dx = (float) Math.cos(player.getAngle()) * moveSpeed;
            float dy = (float) Math.sin(player.getAngle()) * moveSpeed;

            // Move o jogador (com verificação de colisão)
            player.move(dx, dy, mapa);
        }
    }



    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);

        // Atualiza a posição do jogador
        updatePlayerPosition();

        // Renderiza o mapa como background
        renderMap();

        // Renderiza os raios para visão 3D
        renderRays();

        // Renderiza o direcional
        direcional.draw(shapeRenderer, batch);
    }

    private void renderMap() {
        int tileSize = 50; // Tamanho de cada tile para visualização

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (int y = 0; y < mapa.getHeight(); y++) {
            for (int x = 0; x < mapa.getWidth(); x++) {
                if (mapa.getTile(x, y) == 1) {
                    shapeRenderer.setColor(Color.GRAY); // Paredes
                } else {
                    shapeRenderer.setColor(Color.BLACK); // Espaço vazio
                }
                shapeRenderer.rect(x * tileSize, y * tileSize, tileSize, tileSize);
            }
        }
        shapeRenderer.end();
    }

    private float castRay(float startX, float startY, float angle) {
        float x = startX;
        float y = startY;
        float stepX = (float) Math.cos(angle) * 0.2f;
        float stepY = (float) Math.sin(angle) * 0.2f;

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

    private void renderRays() {
        float playerX = player.getX(); // Posição X do jogador no mapa
        float playerY = player.getY(); // Posição Y do jogador no mapa
        float playerAngle = player.getAngle(); // Ângulo de visão

        int numRays = 100; // Resolução horizontal
        float fov = (float) Math.toRadians(60); // Campo de visão (em radianos)
        float rayStep = fov / numRays;

        for (int i = 0; i < numRays; i++) {
            float rayAngle = playerAngle - fov / 2 + i * rayStep;
            float distance = castRay(playerX, playerY, rayAngle); // Calcula a distância do raio

            // Evitar divisão por zero
            distance = Math.max(distance, 0.01f);

            // Calcula altura da parede baseada na distância do raio
            float wallHeight = 480 / distance;

            // Renderiza a coluna correspondente
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.GRAY);
            shapeRenderer.rect(i * 8, 240 - wallHeight / 2, 8, wallHeight); // Largura fixa de 8 pixels por "coluna"
            shapeRenderer.end();
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
