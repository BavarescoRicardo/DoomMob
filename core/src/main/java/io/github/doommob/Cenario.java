package io.github.doommob;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Cenario {
    private float x, y; // Center position
    private float radius; // Outer circle radius
    private Color borderColor;
    private Color dotColor;

    private float angle; // Angle of the line in radians

    public Cenario(float x, float y, float radius, Color borderColor, Color dotColor) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.borderColor = borderColor;
        this.dotColor = dotColor;
        this.angle = 0; // Default pointing upwards
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public void draw(ShapeRenderer shapeRenderer) {
        // Draw the outer circle
        shapeRenderer.setColor(borderColor);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.circle(x, y, radius);

        // Calculate endpoint of the line based on the angle
        float lineEndX = x + radius * (float) Math.cos(angle);
        float lineEndY = y + radius * (float) Math.sin(angle);

        // Draw the line from the center to the calculated endpoint
        shapeRenderer.line(x, y, lineEndX, lineEndY);
        shapeRenderer.end();

        // Draw the inner dot
        shapeRenderer.setColor(dotColor);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(x, y, 20); // Inner dot radius
        shapeRenderer.end();
    }
}
