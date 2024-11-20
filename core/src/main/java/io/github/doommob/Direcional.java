package io.github.doommob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Direcional {
    private float x, y; // Center position
    private float radius; // Outer circle radius
    private float innerDotX, innerDotY; // Inner circle position
    private Color borderColor;
    private Color dotColor;
    private Texture circleTexture;

    public Direcional(float x, float y, float radius, Color borderColor, Color dotColor, Cenario cenario) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.innerDotX = x; // Start inner dot at the center
        this.innerDotY = y;
        this.borderColor = borderColor;
        this.dotColor = dotColor;
        circleTexture = new Texture(Gdx.files.internal("circle.png"));
    }
    public void draw(ShapeRenderer shapeRenderer, SpriteBatch batch) {
        // Draw the outer circle
        shapeRenderer.setColor(borderColor);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.circle(x, y, radius);
        shapeRenderer.end();

        // Draw the inner dot
        shapeRenderer.setColor(dotColor);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(innerDotX, innerDotY, 20);
        shapeRenderer.end();

        // Draw the texture
        batch.begin();
        batch.draw(circleTexture, x - radius, y - radius, radius * 2, radius * 2);
        batch.end();
    }

    public void dispose() {
        circleTexture.dispose();
    }

    public boolean isTouched(float touchX, float touchY) {
        float dx = touchX - x;
        float dy = touchY - y;
        return Math.sqrt(dx * dx + dy * dy) <= radius;
    }

    public void updateInnerDotPosition(float touchX, float touchY) {
        float dx = touchX - x;
        float dy = touchY - y;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        if (distance <= radius) {
            innerDotX = touchX;
            innerDotY = touchY;
        } else {
            // Keep the inner dot constrained to the circle's border
            innerDotX = x + dx / distance * radius;
            innerDotY = y + dy / distance * radius;
        }
    }

    public float getAngle() {
        return (float) Math.atan2(innerDotY - y, innerDotX - x);
    }
}
