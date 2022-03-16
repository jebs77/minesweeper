package model;

import java.util.ArrayList;
import java.util.Random;

public class Minesweeper extends AbstractMineSweeper {
    private int width;
    private int height;
    private int mines;
    private AbstractTile[][] board;

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
            startNewGame(8,8,10);
        } else if (level == Difficulty.MEDIUM) {
            startNewGame(16,16,40);
        } else {
            startNewGame(16,30,99);
        }
    }

    @Override
    public void startNewGame(int col, int row, int explosionCount) {
        width = row;
        height = col;
        mines = explosionCount;
        board = new Tile[width][height];
        Random rand = new Random();
        int placedMines = 0;
        while ( placedMines < mines){
            int x = rand.nextInt(row);
            int y = rand.nextInt(col);
            if(getTile(x, y) == null){
                board[x][y] = generateExplosiveTile();
                placedMines ++;
            }
        for(int i = 0; i< col; i++){
            for (int j = 0;  j< row; j++){
                if (getTile(i, j)  != null){
                    board[i][j] = generateEmptyTile();
                }
            }
        }
        }

    System.out.println(board);
    }

    @Override
    public void toggleFlag(int x, int y) {
        AbstractTile newTile = getTile(x, y);
        if (newTile.isFlagged()) {
            newTile.unflag();
        } else {
            newTile.flag();


            }
        }

        @Override
        public void setWorld (AbstractTile[][] world){

            board = world;
            height = world.length;
            width = world[0].length;
        }

        @Override
        public AbstractTile getTile(int x, int y) {
            if((0 <= x && x < width)&& (0 <= y && y <height) ){
                return board[y][x];
            }
            else {
                return null;
            }
        }



        @Override
        public void open ( int x, int y){
            
        }

        @Override
        public void flag ( int x, int y){
            board[x][y].flag();
        }

        @Override
        public void unflag ( int x, int y){
            board[x][y].unflag();
        }

        @Override
        public void deactivateFirstTileRule () {

        }



        @Override
        public AbstractTile generateEmptyTile() {
            AbstractTile emptyTile = new Tile( false);
            return emptyTile;
        }


        @Override
        public AbstractTile generateExplosiveTile () {
            AbstractTile newTile = new Tile(true);
            return newTile;
        }


    }


