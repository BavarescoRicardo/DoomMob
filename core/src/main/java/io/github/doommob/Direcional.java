package io.github.doommob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Direcional {
    private float x, y; // Center position
    private float radius; // Outer circle radius
    private Color borderColor;
    private Color dotColor;

    private Texture circleTexture;
    private float innerDotX, innerDotY; // Position of the draggable inner circle
    private Cenario cenario; // Reference to Cenario

    public Direcional(float x, float y, float radius, Color borderColor, Color dotColor, Cenario cenario) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.borderColor = borderColor;
        this.dotColor = dotColor;
        this.cenario = cenario;

        // Load the circle texture
        circleTexture = new Texture(Gdx.files.internal("circle.png"));

        // Initialize the inner dot position
        innerDotX = x;
        innerDotY = y;
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
        float distance = (float) Math.sqrt((touchX - innerDotX) * (touchX - innerDotX) + (touchY - innerDotY) * (touchY - innerDotY));
        return distance <= 20;
    }

    public void updateInnerDotPosition(float touchX, float touchY) {
        float dx = touchX - x;
        float dy = touchY - y;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        if (distance <= radius) {
            innerDotX = touchX;
            innerDotY = touchY;
        } else {
            float scale = radius / distance;
            innerDotX = x + dx * scale;
            innerDotY = y + dy * scale;
        }
        // Calculate the angle of the drag
        float angle = (float) Math.atan2(innerDotY - y, innerDotX - x);
        // Notify the Cenario about the new angle
        cenario.setAngle(angle);
    }

    public float getAngle() {
        return (float) Math.atan2(innerDotY - y, innerDotX - x);
    }
}
