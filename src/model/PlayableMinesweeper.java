package model;

import notifier.IGameStateNotifier;

import java.time.LocalTime;

public interface PlayableMinesweeper {
    int getWidth();
    int getHeight();
    LocalTime getStartTime();
    void startNewGame(Difficulty level);
    void startNewGame(int row, int col, int explosionCount);
    void open(int x, int y);
    void toggleFlag(int x, int y);
    void setGameStateNotifier(IGameStateNotifier notifier);
}

