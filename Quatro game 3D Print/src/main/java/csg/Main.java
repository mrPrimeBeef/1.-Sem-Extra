package csg;

import org.abstractica.javacsg.*;


public class Main {
    public static void main(String[] args) {
        JavaCSG csg = JavaCSGFactory.createNoCaching();

        Board board = new Board(33,10,200,(200 - 10),4);
        SquarePieces squarePiecesSmall = new SquarePieces(30,60,40);
        SquarePieces squarePiecesBig = new SquarePieces(30,80,40);
        CylindercalPieces cylindercalPiecesSmall = new CylindercalPieces(30,60,40);
        CylindercalPieces cylindercalPiecesBig = new CylindercalPieces(30,80,40);

        Geometry3D sqaureResultSmallNoHole = squarePiecesSmall.getSmallSqaurePieceNohole(csg);
        Geometry3D cylindercalResultSmallNoHole  = cylindercalPiecesSmall.getSmallCylinderPieceNoHole(csg);
        Geometry3D sqaureResultSmallHole = squarePiecesSmall.getSmallSqaurePieceHole(csg);
        Geometry3D cylindercalResultSmallHole  = cylindercalPiecesSmall.getSmallCylinderPieceHole(csg);
        Geometry3D sqaureResultbigNoHole = squarePiecesBig.getBigSqaurePieceNoHole(csg);
        Geometry3D cylindercalResultBigNoHole  = cylindercalPiecesBig.getBigCylinderPieceNoHole(csg);
        Geometry3D sqaureResultbigHole = squarePiecesBig.getBigSqaurePieceHole(csg);
        Geometry3D cylindercalResultBigHole  = cylindercalPiecesBig.getBigCylinderPieceHole(csg);
        Geometry3D boardResult = board.getBoard(csg);

        csg.view(boardResult,0);
        csg.view(sqaureResultSmallNoHole,1);
        csg.view(sqaureResultSmallHole,2);
        csg.view(sqaureResultbigNoHole,3);
        csg.view(sqaureResultbigHole,4);
        csg.view(cylindercalResultSmallNoHole,5);
        csg.view(cylindercalResultSmallHole,6);
        csg.view(cylindercalResultBigHole,7);
        csg.view(cylindercalResultBigNoHole,8);

    }
}
