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
<<<<<<< HEAD
    
=======

>>>>>>> 26cbdad56e122b2ad7c05e0255d7c7e15c6d166d

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
<<<<<<< HEAD
        public void open ( int x, int y){

        }
=======
        public void open ( int x, int y) {
            int surrounded = 0;
            if(getTile(y,x).isExplosive()== false) {

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
                else if (x == width - 1 && y == 0) {
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
                else if (x == 0 && y == height - 1) {
                    if (getTile(height - 1, x + 1).isExplosive() == true) {
                        surrounded++;
                    }
                    for (int i = 0; i < 2; i++) {
                        if (getTile(1, i).isExplosive() == true) {
                            surrounded++;
                        }
                    }
                }
//            bottom right corner
                else if (x == width - 1 && y == height - 1) {
                    if (getTile(height - 1, width - 1).isExplosive() == true) {
                        surrounded++;
                    }
                    for (int i = width - 1; i > width - 3; i--) {
                        if (getTile(height - 1, i).isExplosive() == true) {
                            surrounded++;
                        }
                    }
                }
//            left wall
                else if (x == 0 && y != 0 && y != height - 1) {
                    if (getTile(y - 1, x).isExplosive()) {
                        surrounded++;
                    }
                    if (getTile(y + 1, x).isExplosive()) {
                        surrounded++;
                    }
                    for (int i = -1; i < 2; i++) {
                        if (getTile(y + i, x + 1).isExplosive()) {
                            surrounded++;
                        }
                    }
                }
//            right wall
                else if (x == width - 1 && y != 0 && y != height - 1) {
                    if (getTile(y - 1, x).isExplosive()) {
                        surrounded++;
                    }
                    if (getTile(y + 1, x).isExplosive()) {
                        surrounded++;
                    }
                    for (int i = -1; i < 2; i++) {
                        if (getTile(y + i, x - 1).isExplosive()) {
                            surrounded++;
                        }
                    }
                }
//            top wall
                else if (y == 0 && x != 0 && x != width - 1) {
                    if (getTile(y, x - 1).isExplosive()) {
                        surrounded++;
                    }
                    if (getTile(y, x + 1).isExplosive()) {
                        surrounded++;
                    }
                    for (int i = -1; i < 2; i++) {
                        if (getTile(y + 1, x + i).isExplosive()) {
                            surrounded++;
                        }
                    }
                }
//            bottom wall
                else if (y == height-1 && x != 0 && x != width - 1) {
                    if (getTile(y, x - 1).isExplosive()) {
                        surrounded++;
                    }
                    if (getTile(y, x + 1).isExplosive()) {
                        surrounded++;
                    }
                    for (int i = -1; i < 2; i++) {
                        if (getTile(y -1, x + i).isExplosive()) {
                            surrounded++;
                        }
                    }
                }
                else{
                    for (int i = -1; i < 2; i++) {
                        if (getTile(y - 1, x + i).isExplosive()) {
                            surrounded++;
                        }
                    }
                    for (int j = -1; j < 2; j++) {
                        if (getTile(y + 1, x + j).isExplosive()) {
                            surrounded++;
                        }
                    }
                        if (getTile(y, x - 1).isExplosive()) {
                            surrounded++;
                        }
                        if (getTile(y, x + 1).isExplosive()) {
                            surrounded++;
                        }
                    }

                viewNotifier.notifyOpened(x, y, surrounded);
                if (surrounded == 0){
                    if(y != 0) {
                        open(x, y - 1);
                    }
                    if(y != height-1) {
                        open(x, y + 1);
                    }
                    if(x != 0) {
                        open(x - 1, y);
                    }
                    if(x != width-1) {
                        open(x + 1, y);
                    }

                }
                }
            }


>>>>>>> 26cbdad56e122b2ad7c05e0255d7c7e15c6d166d

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


