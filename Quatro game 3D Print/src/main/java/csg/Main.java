package csg;

import org.abstractica.javacsg.*;


public class Main {
    public static void main(String[] args) {
        JavaCSG csg = JavaCSGFactory.createNoCaching();

        Board board = new Board(33,10,200,(200 - 10),4);
        SquarePiece squarePieceSmall = new SquarePiece(30,60,40);
        SquarePiece squarePieceBig = new SquarePiece(30,80,40);

        Geometry3D sqaureResultSmallNoHole = squarePieceSmall.getSmallSqaurePieceNohole(csg);
        Geometry3D sqaureResultSmallHole = squarePieceSmall.getSmallSqaurePieceHole(csg);
        Geometry3D sqaureResultbigNoHole = squarePieceBig.getBigSqaurePieceNoHole(csg);
        Geometry3D sqaureResultbigHole = squarePieceBig.getBigSqaurePieceHole(csg);
        Geometry3D boardResult = board.getBoard(csg);

        csg.view(boardResult,0);
        csg.view(sqaureResultSmallNoHole,1);
        csg.view(sqaureResultSmallHole,2);
        csg.view(sqaureResultbigNoHole,3);
        csg.view(sqaureResultbigHole,4);

    }
}
