package io.github.doommob;

public class Mapa {
    private int[][] grid;

    public Mapa() {
        // Define o layout do mapa
        grid = new int[][]{
            {1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1},
            {1, 0, 1, 0, 1},
            {1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1}
        };
    }

    public int getTile(int x, int y) {
        return grid[y][x]; // Note que y vem antes de x
    }

    public int getWidth() {
        return grid[0].length;
    }

    public int getHeight() {
        return grid.length;
    }
}
