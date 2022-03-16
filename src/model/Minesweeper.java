package model;

import java.util.ArrayList;

public class Minesweeper extends AbstractMineSweeper {
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
        if (level == Difficulty.EASY) {
            width = 8;
            height = 8;
            mines = 10;
        } else if (level == Difficulty.MEDIUM) {
            width = 16;
            height = 16;
            mines = 40;

        } else {
            width = 16;
            height = 30;
            mines = 99;
        }
        board = new Tile[width][height];
        for (int i = 0; i < width; i++) {
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
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                board[i][j] = new Tile();
            }
        }
    }

    @Override
    public void toggleFlag(int x, int y) {
<<<<<<< HEAD
        if (board != null) {
            AbstractTile newTile = getTile(x, y);
            if (newTile.isFlagged()) {
                newTile.unflag();
            } else {
                newTile.flag();
            }
            AbstractTile newTile = getTile(x, y);
            if (newTile.isFlagged()) {
                newTile.unflag();
            } else {
                newTile.flag();
=======
        AbstractTile newTile = getTile(x, y);
        if (newTile.isFlagged()) {
            newTile.unflag();
        } else {
            newTile.flag();
>>>>>>> 46358567fbc776a1be95e5301064b162c85fc76f

            }
        }

<<<<<<< HEAD
        @Override
        public AbstractTile getTile ( int x, int y){
            return board[x][y];
        }

        @Override
        public void setWorld (AbstractTile[][]world){
=======
    @Override
    public AbstractTile getTile(int x, int y) {
        return board[x][y];
    }
//tst
    @Override
    public void setWorld(AbstractTile[][] world) {
>>>>>>> 0cf4f9173d60b38e9384d92dfd5f4cfc20b70d96

        }

        @Override
        public void open ( int x, int y){

        }

        @Override
        public void flag ( int x, int y){

        }

        @Override
        public void unflag ( int x, int y){

        }

        @Override
        public void deactivateFirstTileRule () {

        }

<<<<<<< HEAD
        @Override
        public AbstractTile generateEmptyTile () {

        }
=======
    @Override
    public AbstractTile generateEmptyTile() {
        return null;
    }
>>>>>>> 46358567fbc776a1be95e5301064b162c85fc76f

        @Override
        public AbstractTile generateExplosiveTile () {
            return null;
        }


    }
}

