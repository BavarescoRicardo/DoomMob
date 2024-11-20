package io.github.doommob;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Cenario {
    private float x, y; // Center position
    private float radius; // Outer circle radius
    private Color borderColor;
    private Color dotColor;

    private float lineEndX, lineEndY; // End point of the line

    public Cenario(float x, float y, float radius, Color borderColor,
                   Color dotColor) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.borderColor = borderColor;
        this.dotColor = dotColor;

        // Default line pointing to the top
        this.lineEndX = radius;
        this.lineEndY = radius;
    }

    public void setLineEnd(float endX, float endY) {
        this.lineEndX = endX;
        this.lineEndY = endY;
    }

    public void draw(ShapeRenderer shapeRenderer) {
        // Draw the outer circle
        shapeRenderer.setColor(borderColor);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.circle(x, y, radius);

        // Draw the line from the center to the dynamic endpoint
        shapeRenderer.line(x, y, lineEndX, lineEndY);
        shapeRenderer.end();

        // Draw the inner dot
        shapeRenderer.setColor(dotColor);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(x, y, 20); // Inner dot radius
        shapeRenderer.end();
    }
}
