package csg;

import org.abstractica.javacsg.Geometry2D;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;

public class Board {
    private final double brickSize;
    private final double height;
    private final double boardSize;

    public Board(double brickSize, double height, double boardSize) {

        this.brickSize = brickSize;
        this.height = height;
        this.boardSize = boardSize;
    }

    public Geometry3D getBoard(JavaCSG csg) {
        Geometry3D board = createBoard(csg);
        return board;
    }

    public Geometry3D createRow(JavaCSG csg){
        Geometry2D circle = csg.circle2D(30,128);
        Geometry3D extrudCircle = csg.linearExtrude(4,false,circle);
        Geometry3D extrudCircleMoved = csg.translate3D(-100,40,6.5).transform(extrudCircle);

        //Geometry3D rowFinal = csg.union3D(gameRow, gameBrick3);

        return extrudCircleMoved;
    }


    public Geometry3D createBoard(JavaCSG csg) {
        Geometry3D board = csg.box3D(boardSize,boardSize,height,false);

        Geometry3D row1 = createRow(csg);
        Geometry3D board1 = csg.difference3D(board, row1);

        Geometry3D row2 = csg.translate3D(0,-(brickSize+5)*1,0).transform(row1);
        Geometry3D board2 = csg.difference3D(board1, row2);

        Geometry3D row3 = csg.translate3D(0,-(brickSize+5)*2,0).transform(row1);
        Geometry3D board3 = csg.difference3D(board2, row3);

        Geometry3D row4 = csg.translate3D(0,-(brickSize+5)*3,0).transform(row1);
        Geometry3D finalBoard = csg.difference3D(board3, row4);

        return board1;
    }
}
