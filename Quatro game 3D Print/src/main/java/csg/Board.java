package csg;

import org.abstractica.javacsg.Geometry2D;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;

import java.lang.management.ManagementFactory;

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
        double y = 57;
        double x =(-boardSize/4);
        Geometry2D circle = csg.circle2D(brickSize,128);
        Geometry3D extrudCircle = csg.linearExtrude(gamePieceHeight+1,false,circle);
        Geometry3D extrudCircleMoved = csg.translate3D(x,y,6.5).transform(extrudCircle);

        Geometry2D circle1 = csg.circle2D(brickSize,128);
        Geometry3D extrudCircle1 = csg.linearExtrude(gamePieceHeight+1,false,circle1);
        Geometry3D extrudCircleMoved1 = csg.translate3D(x + moveBrick,y,6.5).transform(extrudCircle1);

        Geometry3D cirle22 = csg.union3D(extrudCircleMoved, extrudCircleMoved1);

        Geometry2D circle2 = csg.circle2D(brickSize,128);
        Geometry3D extrudCircle2 = csg.linearExtrude(gamePieceHeight+1,false,circle2);
        Geometry3D extrudCircleMoved2 = csg.translate3D(x + (moveBrick*2),y,6.5).transform(extrudCircle2);

        Geometry3D cirle33 = csg.union3D(cirle22, extrudCircleMoved2);

        Geometry2D circle3 = csg.circle2D(brickSize,128);
        Geometry3D extrudCircle3 = csg.linearExtrude(gamePieceHeight+1,false,circle3);
        Geometry3D extrudCircleMoved3 = csg.translate3D(x + (moveBrick*3),y,6.5).transform(extrudCircle3);

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

        Geometry3D board5 = csg.difference3D(board4, row4);

        Geometry3D indent = createBoardIndent(csg);
        Geometry3D finalBoard = csg.union3D(board5, indent);

        return finalBoard;
    }

    public Geometry3D createBoardIndent(JavaCSG csg){
        double z = height/2;
        double y = boardSize/2;
        double x = -boardSize/2;
        Geometry3D cylinder = csg.cylinder3D(10,boardSize,128,false);
        Geometry3D cylinderRotated = csg.rotate3D(csg.degrees(0),csg.degrees(90),csg.degrees(0)).transform(cylinder);
        Geometry3D cylinderMoved = csg.translate3D(x,y,z).transform(cylinderRotated);

        Geometry3D cylinderRotated2 = csg.rotate3D(csg.degrees(90),csg.degrees(0),csg.degrees(0)).transform(cylinder);
        Geometry3D cylinderMoved2 = csg.translate3D(x,y,z).transform(cylinderRotated2);

        Geometry3D cylinderMerge1 = csg.union3D(cylinderMoved,cylinderMoved2);

        Geometry3D cylinderRotated3 = csg.rotate3D(csg.degrees(0),csg.degrees(90),csg.degrees(0)).transform(cylinder);
        Geometry3D cylinderMoved3 = csg.translate3D(x,y - boardSize,z).transform(cylinderRotated3);

        Geometry3D cylinderMerge2 = csg.union3D(cylinderMerge1,cylinderMoved3);

        Geometry3D cylinderRotated4 = csg.rotate3D(csg.degrees(90),csg.degrees(0),csg.degrees(0)).transform(cylinder);
        Geometry3D cylinderMoved4 = csg.translate3D(x + boardSize,y,z).transform(cylinderRotated4);

        Geometry3D cylinderMerge3 = csg.union3D(cylinderMerge2,cylinderMoved4);

        Geometry3D sphere = csg.sphere3D(10,128,false);
        Geometry3D sphereMoved1 = csg.translate3D(x,y,0).transform(sphere);

        Geometry3D indent1 = csg.union3D(cylinderMerge3,sphereMoved1);

        Geometry3D sphereMoved2 = csg.translate3D(-x,-y,0).transform(sphere);
        Geometry3D indent2 = csg.union3D(indent1,sphereMoved2);

        Geometry3D sphereMoved3 = csg.translate3D(x,-y,0).transform(sphere);
        Geometry3D indent3 = csg.union3D(indent2,sphereMoved3);

        Geometry3D sphereMoved4 = csg.translate3D(-x,y,0).transform(sphere);
        Geometry3D indent4 = csg.union3D(indent3,sphereMoved4);

        return indent4;
    }

    public Geometry3D magnetSpace(JavaCSG csg){
        Geometry3D finalMagnet = csg.box3D(4.73,9.77,2.88,false);
        Geometry3D finalMagnet1 = csg.translate3D(0,0,-0.1).transform(finalMagnet);

        return finalMagnet1;
    }


}
