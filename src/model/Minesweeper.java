package model;

import java.time.Duration;
<<<<<<< HEAD
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
=======
import java.time.LocalTime;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
>>>>>>> 771162637747a64f95fcaf4683ee9b8578d99b45

public class Minesweeper extends AbstractMineSweeper {
    private int height;
    private int width;
    private int mines;
    private AbstractTile[][] board;
    private int flagCount;
    private int checked_files;
    private LocalTime startTime;
    private Timer timer;

    public Minesweeper() {
        height = 0;
        width = 0;
        mines = 0;
        flagCount = 0;
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
    public LocalTime getStartTime() {
        return startTime;
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
        width = col;
        height = row;
        viewNotifier.notifyFlagCountChanged(0);
        startTime = LocalTime.now();
        flagCount = 0;
        mines = explosionCount;
        viewNotifier.notifyMineCountChanged(mines-flagCount);
        timer = new Timer();
        long delay = 0;
        long period = 1000;
        timer.scheduleAtFixedRate(new GetTimeDifference(), delay, period );
        board = new Tile[width][height];
        Random rand = new Random();
        int placedMines = 0;
        while ( placedMines < mines) {
            int x = rand.nextInt(col);
            int y = rand.nextInt(row );
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

        viewNotifier.notifyNewGame(row, col);

    }

    @Override
    public void toggleFlag(int x, int y) {
        AbstractTile newTile = getTile(y, x);
        if (newTile.isOpened() == false) {
            if (newTile.isFlagged()) {

                newTile.unflag();
                flagCount--;
                viewNotifier.notifyUnflagged(x, y);
                viewNotifier.notifyFlagCountChanged(flagCount);
                viewNotifier.notifyMineCountChanged(mines-flagCount);

            } else {
                newTile.flag();
                flagCount++;
                viewNotifier.notifyFlagged(x, y);
                viewNotifier.notifyFlagCountChanged(flagCount);
                viewNotifier.notifyMineCountChanged(mines-flagCount);

            }
        }

        }

        @Override
        public void setWorld (AbstractTile[][] world){

            board = world;
            width = world.length;
            height = world[0].length;
        }

        @Override
        public AbstractTile getTile(int y, int x) {
            if((0 <= x && x < width)&& (0 <= y && y <height) ){
                return board[x][y];
            }
            else {
                return null;
            }
        }

        @Override
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
                    if (getTile(y, x + 1).isExplosive() == true) {
                        surrounded++;
                    }
                    for (int i = 0; i < 2; i++) {
                        if (getTile(y-1, i).isExplosive() == true) {
                            surrounded++;
                        }
                    }
                }
//            bottom right corner
                else if (x == width - 1 && y == height - 1) {
                    if (getTile(y , x - 1).isExplosive() == true) {
                        surrounded++;
                    }
                    for (int i = width - 1; i > width - 3; i--) {
                        if (getTile(y - 1, i).isExplosive() == true) {
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

                getTile(y,x).open();
                checked_files ++;
                if ((checked_files == width*height-mines) && (flagCount == mines)){
                    timer.cancel();
                    viewNotifier.notifyGameWon();
                }
                viewNotifier.notifyOpened(x, y, surrounded);
                if (surrounded == 0){
                    if(y != 0) {
                        if(getTile(y-1, x).isOpened()== false) {
                            open(x, y - 1);
                        }
                    }
                    if(y != height-1) {
                        if(getTile(y+1, x).isOpened()== false) {
                            open(x, y + 1);
                        }
                    }
                    if(x != 0) {
                        if (getTile(y, x-1).isOpened()== false) {
                            open(x - 1, y);
                        }
                    }
                    if(x != width-1) {
                        if (getTile(y, x+1).isOpened()== false) {
                            open(x + 1, y);
                        }
                    }
                    if(x != 0 && y != 0){
                        if(getTile(y-1,x-1).isOpened() == false){
                            open(x-1,y-1);
                        }
                    }
                    if(x != width-1 && y != 0){
                        if(getTile(y-1,x+1).isOpened() == false){
                            open(x+1,y-1);
                        }
                    }
                    if(x != 0 && y != height-1){
                        if(getTile(y+1,x-1).isOpened() == false){
                            open(x-1,y+1);
                        }
                    }
                    if(x != width-1 && y != height-1){
                        if(getTile(y+1,x+1).isOpened() == false){
                            open(x+1,y+1);
                        }
                    }

                }
                }
            else {
                viewNotifier.notifyExploded(x,y);
                timer.cancel();
                viewNotifier.notifyGameLost();

            }
            }



        @Override
        public void flag( int x, int y){
            board[x][y].flag();
            viewNotifier.notifyFlagged(x, y);
            viewNotifier.notifyFlagCountChanged(flagCount+1);

        }

        @Override
        public void unflag( int x, int y){
            board[x][y].unflag();
            viewNotifier.notifyUnflagged(x, y);
            viewNotifier.notifyFlagCountChanged(flagCount-1);

        }

        @Override
        public void deactivateFirstTileRule () {

        }

        class GetTimeDifference extends TimerTask {
            public void run() {
                Duration difference = Duration.between(startTime, LocalTime.now());
                viewNotifier.notifyTimeElapsedChanged(difference);
            }
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


