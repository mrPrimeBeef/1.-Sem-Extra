package csg;

import org.abstractica.javacsg.Geometry2D;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;

public class Board {
    private final double brickSize;
    private final double height;
    private final double boardSize;
    private final double dividerSize;
    private final double gamePieceHeight;

    public Board(double brickSize, double height, double dividerSize, double gamePieceHeight) {

        this.brickSize = brickSize;
        this.height = height;
        this.boardSize = (brickSize * 3) + (4 * dividerSize);
        // størrelsen på boardet er 3 brikker og 4 linjer i mellem på 5 mm mellemrum
        this.dividerSize = dividerSize;
        this.gamePieceHeight = gamePieceHeight;

    }

    public Geometry3D getBoard(JavaCSG csg) {
        Geometry3D board = createBoard(csg);
        return board;
    }

    public Geometry3D createRow(JavaCSG csg){
        Geometry3D gameBrick = csg.box3D(brickSize,brickSize,20,false);
        gameBrick = csg.translate3D(-(brickSize + dividerSize),(brickSize + dividerSize),(height- gamePieceHeight)).transform(gameBrick);
        Geometry3D gameBrick2 = csg.translate3D((brickSize + dividerSize),0,0).transform(gameBrick);
        Geometry3D gameRow = csg.union3D(gameBrick, gameBrick2);
        Geometry3D gameBrick3 = csg.translate3D((brickSize + dividerSize),0,0).transform(gameBrick2);
        Geometry3D rowFinal = csg.union3D(gameRow, gameBrick3);

        return rowFinal;
    }


    public Geometry3D createBoard(JavaCSG csg) {
        Geometry3D board = csg.box3D(boardSize,boardSize,height,false);

        Geometry3D row1 = createRow(csg);
        Geometry3D board1 = csg.difference3D(board, row1);

        Geometry3D row2 = csg.translate3D(0,-(brickSize + dividerSize)*1,0).transform(row1);
        Geometry3D board2 = csg.difference3D(board1, row2);

        Geometry3D row3 = csg.translate3D(0,-(brickSize + dividerSize)*2,0).transform(row1);
        Geometry3D board3 = csg.difference3D(board2, row3);

        return board3;
    }
}
