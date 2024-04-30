package csg;

import org.abstractica.javacsg.Geometry2D;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;

public class Board {
    private final double brickSize;
    private final double height;
    private final double boardSize;
    private final double ringSize;
    private final double gamePieceHeight;

    public Board(double brickSize, double height, double boardSize, double ringSize, double gamePieceHeight) {

        this.brickSize = brickSize;
        this.height = height;
        this.boardSize = boardSize;
        this.ringSize = ringSize;
        this.gamePieceHeight = gamePieceHeight;
    }

    public Geometry3D getBoard(JavaCSG csg) {
        Geometry3D board = createBoard(csg);
        return board;
    }

    public Geometry3D createRow(JavaCSG csg){
        double moveBrick = brickSize + 3;
        Geometry2D circle = csg.circle2D(brickSize,128);
        Geometry3D extrudCircle = csg.linearExtrude(gamePieceHeight+1,false,circle);
        Geometry3D extrudCircleMoved = csg.translate3D(-boardSize/4,52,6.5).transform(extrudCircle);

        Geometry2D circle1 = csg.circle2D(brickSize,128);
        Geometry3D extrudCircle1 = csg.linearExtrude(gamePieceHeight+1,false,circle1);
        Geometry3D extrudCircleMoved1 = csg.translate3D(-boardSize/4 + moveBrick,52,6.5).transform(extrudCircle1);

        Geometry3D cirle22 = csg.union3D(extrudCircleMoved, extrudCircleMoved1);

        Geometry2D circle2 = csg.circle2D(brickSize,128);
        Geometry3D extrudCircle2 = csg.linearExtrude(gamePieceHeight+1,false,circle2);
        Geometry3D extrudCircleMoved2 = csg.translate3D(-boardSize/4 + (moveBrick*2),52,6.5).transform(extrudCircle2);

        Geometry3D cirle33 = csg.union3D(cirle22, extrudCircleMoved2);

        Geometry2D circle3 = csg.circle2D(brickSize,128);
        Geometry3D extrudCircle3 = csg.linearExtrude(gamePieceHeight+1,false,circle3);
        Geometry3D extrudCircleMoved3 = csg.translate3D(-boardSize/4 + (moveBrick*3),52,6.5).transform(extrudCircle3);

        Geometry3D finalRow = csg.union3D(cirle33, extrudCircleMoved3);

        return finalRow;
    }


    public Geometry3D createBoard(JavaCSG csg) {
        Geometry3D board = csg.box3D(boardSize,boardSize,height,false);
        Geometry2D ring2D = csg.ring2D(ringSize - 10,ringSize,128);
        Geometry3D ring3D = csg.linearExtrude(gamePieceHeight+1,false,ring2D);
        Geometry3D ring3DTop = csg.translate3D(0,0,height-gamePieceHeight).transform(ring3D);

        Geometry3D board1 = csg.difference3D(board,ring3DTop);

        Geometry3D row1 = createRow(csg);
        Geometry3D board2 = csg.difference3D(board1, row1);

        Geometry3D row2 = csg.translate3D(0,-(brickSize+5)*1,0).transform(row1);
        Geometry3D board3 = csg.difference3D(board2, row2);

        Geometry3D row3 = csg.translate3D(0,-(brickSize+5)*2,0).transform(row1);
        Geometry3D board4 = csg.difference3D(board3, row3);

        Geometry3D row4 = csg.translate3D(0,-(brickSize+5)*3,0).transform(row1);
        Geometry3D finalBoard = csg.difference3D(board4, row4);

        return finalBoard;
    }
}
