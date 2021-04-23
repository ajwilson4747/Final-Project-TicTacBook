package com.example.tictactoe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class LibraryHunt extends View {

    private final int boardColor;
    private final int winningColor;
    private final int playerOneColor;
    private final int playerTwoColor;

    private boolean winningLine =false;

    private final Paint paint = new Paint();

    private final GameLogic game;

    private int cellSize = getWidth() / 3;

    //THE CONSTRUCTOR SETS EVERYTHING UP AND CONNECTS THE VARIABLES

    public LibraryHunt(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        game = new GameLogic();

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LibraryHunt, 0, 0);

        try {
            boardColor = a.getInt(R.styleable.LibraryHunt_boardColor, 0);
            winningColor = a.getInt(R.styleable.LibraryHunt_winningColor, 0);
            playerOneColor = a.getInt(R.styleable.LibraryHunt_playerOneColor,0);
            playerTwoColor = a.getInt(R.styleable.LibraryHunt_playerTwoColor, 0);
        } finally {
            a.recycle();
        }
    }

    //THIS CHANGES THE SIZE OF THE GAMEBOARD DEPENDING ON THE SCREEN OF THE PHONE
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        int dimensions = Math.min(getMeasuredWidth(), getMeasuredHeight());
        cellSize = dimensions / 3;
        setMeasuredDimension(dimensions, dimensions);
    }

    //CONTINUALLY UPDATE THE CANVAS WITH CHANGES TO THE GAMEBOARD,PLAYER MARKINGS, ETC
    @Override
    protected void onDraw(Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        drawGameBoard(canvas);
        drawMarkers(canvas);

        //troubleshooting purposes
        //drawPlayer1(canvas,2,1);
        //drawPlayer2(canvas, 0,2);

    }

    //HANDLES WHEN THE PLAYERS TOUCH THE SCREEN TO MARK THE BOARD
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            int row = (int) Math.ceil(y / cellSize);
            int col = (int) Math.ceil(x / cellSize);

            if (!winningLine)
                if (game.updateGameBoard(row, col))
                    invalidate();

                if(game.winnerCheck())
                    winningLine=true;
                    invalidate();
                //update the players turn for each round
                if (game.getPlayer() % 2 == 0) {
                    game.setPlayer(game.getPlayer()-1);
                } else {
                    game.setPlayer(game.getPlayer() + 1);
                }
                return true;
            }
            return false;
        }


    //THIS DRAWS THE ACTUAL GAME BOARD ONTO THE CANVAS
    private void drawGameBoard(Canvas canvas){
        paint.setColor(boardColor);
        paint.setStrokeWidth(16);
        for(int c=1; c<3; c++){
            canvas.drawLine(cellSize*c,0,
                    cellSize*c, canvas.getWidth(), paint);

            for(int r=1; r<3; r++){
                canvas.drawLine(0,
                        cellSize*r,canvas.getWidth(), cellSize*r, paint);
            }
        }
    }


    //THIS ENABLES THE PLAYERS TO MARK THE GAMEBOARD
    private void drawMarkers(Canvas canvas){
        for(int r=0; r<3; r++){
            for(int c=0; c<3; c++){
                if(game.getGameBoard()[r][c] !=0){
                    if(game.getGameBoard()[r][c] ==1){
                        drawPlayer1(canvas,r,c);
                    }
                    else{
                        drawPlayer2(canvas,r,c);
                    }
                }
            }
        }
    }

    //TAKE IN THE NAMES OF THE PLAYERS AND SET THE VARIABLES SO IT CAN ACCESSIBLE TO THE GAME DISPLAY
    public void setUpGame(Button playAgain, Button home, TextView playerDisplay, String[] names){
        game.setPlayAgain(playAgain);
        game.setHome(home);
        game.setPlayerDisplay(playerDisplay);
        game.setNames(names);
    }

    //RESET THE GAME BY ERASING EVERYTHING BUT THE GAMEBOARD AND SETTING INITIAL VARIABLES BACK TO A DEFAULT
    public void resetGame(){
        game.resetGameBoard();
        winningLine = false;
    }

    //THIS IS FOR PLAYER ONE'S BOOK
    private void drawPlayer1(Canvas canvas, int row, int col){
        paint.setColor(playerOneColor);
        canvas.drawRect((float) ((col+1)*cellSize -cellSize*0.5),
                (float) (row*cellSize + cellSize*0.2),
                (float) (col*cellSize + cellSize*0.5),
        (float) ((row+1)*cellSize - cellSize*0.2), paint); //the extra parameter at the end
                                                        //of the line is to provide a margin
                                                        //space from the walls
    }

    //THIS IS FOR PLAYER TWO'S BOOK
    private void drawPlayer2(Canvas canvas, int row, int col){
        paint.setColor(playerTwoColor);
        canvas.drawRect((float) ((col+1)*cellSize -cellSize*0.5),
                (float) (row*cellSize + cellSize*0.2),
                (float) (col*cellSize + cellSize*0.5),
                (float) ((row+1)*cellSize - cellSize*0.2), paint); //the extra parameter at the end
        //of the line is to provide a margin
        //space from the walls
    }
}
