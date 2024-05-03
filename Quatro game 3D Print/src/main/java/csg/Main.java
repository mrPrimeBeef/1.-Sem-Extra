package csg;

import org.abstractica.javacsg.*;


public class Main {
    public static void main(String[] args) {
        JavaCSG csg = JavaCSGFactory.createNoCaching();

        Board board = new Board(33,10,220,(220 - 10),4);
        SquarePieces squarePiecesSmall = new SquarePieces(30,60,40);
        SquarePieces squarePiecesBig = new SquarePieces(30,80,40);
        cylindricalPieces cylindricalPiecesSmall = new cylindricalPieces(30,60,40);
        cylindricalPieces cylindricalPiecesBig = new cylindricalPieces(30,80,40);

        Geometry3D sqaureResultSmallNoHole = squarePiecesSmall.getSmallSqaurePieceNohole(csg);
        Geometry3D cylindricalResultSmallNoHole  = cylindricalPiecesSmall.getSmallCylindricalPieceNoHole(csg);
        Geometry3D sqaureResultSmallHole = squarePiecesSmall.getSmallSqaurePieceHole(csg);
        Geometry3D cylindricalResultSmallHole  = cylindricalPiecesSmall.getBigCylindricalPieceHole(csg);
        Geometry3D sqaureResultBigNoHole = squarePiecesBig.getBigSqaurePieceNoHole(csg);
        Geometry3D cylindricalResultBigNoHole  = cylindricalPiecesBig.getBigCylindricalPieceNoHole(csg);
        Geometry3D sqaureResultbigHole = squarePiecesBig.getBigSqaurePieceHole(csg);
        Geometry3D cylindricalResultBigHole  = cylindricalPiecesBig.getBigCylindricalPieceHole(csg);
        Geometry3D boardResult = board.getBoard(csg);

        Geometry3D workInProgress = board.magnetSpace(csg);

        csg.view(boardResult,0);
        csg.view(sqaureResultSmallNoHole,1);
        csg.view(sqaureResultSmallHole,2);
        csg.view(sqaureResultBigNoHole,3);
        csg.view(sqaureResultbigHole,4);
        csg.view(cylindricalResultSmallNoHole,5);
        csg.view(cylindricalResultSmallHole,6);
        csg.view(cylindricalResultBigHole,7);
        csg.view(cylindricalResultBigNoHole,8);


        csg.view(workInProgress,9);


    }
}
