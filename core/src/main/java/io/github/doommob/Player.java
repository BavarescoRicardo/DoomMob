package io.github.doommob;

public class Player {
    private float x, y; // Posição do jogador
    private float angle; // Ângulo de visão (em radianos)

    public Player(float x, float y, float angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getAngle() {
        return angle;
    }

    public void move(float distance, Mapa mapa) {
        float newX = x + (float) Math.cos(angle) * distance;
        float newY = y + (float) Math.sin(angle) * distance;

        // Verifica colisão
        if (mapa.getTile((int) newX, (int) y) == 0) {
            x = newX;
        }
        if (mapa.getTile((int) x, (int) newY) == 0) {
            y = newY;
        }
    }

    public void move(float dx, float dy, Mapa mapa) {
        float newX = x + dx;
        float newY = y + dy;

        // Verifica colisões apenas onde o tile é 0 (espaço vazio)
        if (mapa.getTile((int) newX, (int) y) == 0) {
            x = newX;
        }
        if (mapa.getTile((int) x, (int) newY) == 0) {
            y = newY;
        }
    }



    public void rotate(float deltaAngle) {
        angle += deltaAngle;
    }
}
