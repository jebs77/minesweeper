package model;

import notifier.IGameStateNotifier;
import view.MinesweeperView;

import java.util.ArrayList;
import java.util.Random;

public class Minesweeper extends AbstractMineSweeper {
    private int width;
    private int height;
    private int mines;
    private AbstractTile[][] board;
    private IGameStateNotifier viewNotifier;

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

        height = col;
        width = row;
        mines = explosionCount;
        board = new Tile[height][width];
        Random rand = new Random();
        int placedMines = 0;
        while ( placedMines < mines) {
            int x = rand.nextInt(col - 1);
            int y = rand.nextInt(row - 1);
            if (getTile(y, x) == null) {
                board[x][y] = generateExplosiveTile();
                placedMines++;
            }
        }
        for(int i = 0; i< col; i++){
            for (int j = 0;  j< row; j++){
                if (getTile(j, i)  == null){
                    board[i][j] = generateEmptyTile();
                }
            }

        }





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
        public AbstractTile getTile(int y, int x) {
            if((0 <= x && x < height)&& (0 <= y && y <width) ){
                return board[x][y];
            }
            else {
                return null;
            }
        }



        @Override
        public void open ( int x, int y) {
            int surrounded = 0;


//                top left corner
                if (x == 0 && y == 0) {
                    if (getTile(0, 1).isExplosive() == true) {
                        surrounded++;
                    }
                    for (int i = 0; i < 2; i++) {
                        if (getTile(1, i).isExplosive() == true) {
                            surrounded++;
                        }
                    }
                }
//            top right corner
                if (x == width - 1 && y == 0) {
                    if (getTile(0, x - 1).isExplosive() == true) {
                        surrounded++;
                    }
                    for (int i = width - 1; i > width - 3; i--) {
                        if (getTile(1, i).isExplosive() == true) {
                            surrounded++;
                        }
                    }
                }
//            bottom left cornser
            if (x == 0 && y == height-1) {
                if (getTile(height-1, x +1).isExplosive() == true) {
                    surrounded++;
                }
                for (int i = 0 ; i <2; i++) {
                    if (getTile(1, i).isExplosive() == true) {
                        surrounded++;
                    }
                }
                }
//            bottom right corner
            if (x == width-1 && y == height-1) {
                if (getTile(height - 1, width - 1).isExplosive() == true) {
                    surrounded++;
                }
                for (int i = width - 1; i > width - 3; i--) {
                    if (getTile(height - 1, i).isExplosive() == true) {
                        surrounded++;
                    }
                }
            }
            viewNotifier.notifyOpened(x, y, surrounded);
            }


        @Override
        public void flag( int x, int y){
        board[x][y].flag();
        }

        @Override
        public void unflag( int x, int y){
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


