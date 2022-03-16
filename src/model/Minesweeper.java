package model;

import java.util.ArrayList;

public class Minesweeper extends AbstractMineSweeper{
    private int width;
    private int height;
    private int mines;
    private Tile[][] board;

    public Minesweeper() {
        width = 0;
        height = 0;
        mines = 0;
    }
    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void startNewGame(Difficulty level) {
        if (level == Difficulty.EASY){
            width = 8;
            height = 8;
            mines = 10;
        }
        else if (level == Difficulty.MEDIUM){
            width = 16;
            height = 16;
            mines = 40;

        }
        else{
            width = 16;
            height = 30;
            mines = 99;
        }
        board = new Tile[width][height];
        for( int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                board[i][j] = new Tile();
            }
        }
    }

    @Override
    public void startNewGame(int row, int col, int explosionCount) {
        width = row;
        height = col;
        mines = explosionCount;
        board = new Tile[width][height];
        for( int i = 0; i < width; i++){
            for( int j = 0; j < height; j++ ){
                board[i][j] = new Tile();
            }
        }
    }

    @Override
    public void toggleFlag(int x, int y) {
        if (board != null) {
            AbstractTile newTile = getTile(x, y);
            if (newTile.isFlagged()) {
                newTile.unflag();
            } else {
                newTile.unflag();
            }
        }

    }

    @Override
    public AbstractTile getTile(int x, int y) {
        return board[x][y];
    }

    @Override
    public void setWorld(AbstractTile[][] world) {

    }

    @Override
    public void open(int x, int y) {

    }

    @Override
    public void flag(int x, int y) {

    }

    @Override
    public void unflag(int x, int y) {

    }

    @Override
    public void deactivateFirstTileRule() {

    }

    @Override
    public AbstractTile generateEmptyTile() {
        return null;
    }

    @Override
    public AbstractTile generateExplosiveTile() {
        return null;
    }


}

