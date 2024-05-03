package csg;

import org.abstractica.javacsg.Geometry2D;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;

import java.util.ArrayList;

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
        ArrayList<Geometry3D> row = new ArrayList<Geometry3D>();
        double moveBrick = brickSize + 3;
        double y = 57;
        double x =(-boardSize/4);

        for (int i = 0; i < 4; i++){
            Geometry2D circle = csg.circle2D(brickSize,128);
            Geometry3D extrudCircle = csg.linearExtrude(gamePieceHeight+1,false,circle);

            Geometry3D magnet = magnetSpace(csg);
            Geometry3D circleWMagnet = csg.union3D(extrudCircle,magnet);

            Geometry3D extrudCircleMoved = csg.translate3D(x+(moveBrick*i),y,6.5).transform(circleWMagnet);


            for (int j = 1; j < 5; j++){

                Geometry3D rowMoved = csg.translate3D(0,(-(brickSize+5))*j,0).transform(extrudCircleMoved);

                row.add(rowMoved);
            }

        }

        Geometry3D finalRow = csg.union3D(row);

        finalRow = csg.rotate3DZ(csg.degrees(45)).transform(finalRow);

        finalRow = csg.translate3D(-brickSize*0.8,brickSize*0.8,0).transform(finalRow);

        return finalRow;
    }


    public Geometry3D createBoard(JavaCSG csg) {

        Geometry3D startBoard = csg.box3D(boardSize,boardSize,height,false);
        Geometry2D ring2D = csg.ring2D(ringSize - 10,ringSize,128);
        Geometry3D ring3D = csg.linearExtrude(gamePieceHeight+1,false,ring2D);
        Geometry3D ring3DTop = csg.translate3D(0,0,height-gamePieceHeight).transform(ring3D);

        Geometry3D board = csg.difference3D(startBoard,ring3DTop);
        Geometry3D row = createRow(csg);

        board = csg.difference3D(board,row);

        Geometry3D edge = createBoardEdge(csg);
        Geometry3D finalBoard = csg.union3D(board, edge);

        return finalBoard;
    }

    public Geometry3D createBoardEdge(JavaCSG csg){
        ArrayList<Geometry3D> edgeArray = new ArrayList<>();
        double z = height/2;
        double y = boardSize/2;
        double x = -boardSize/2;
        double zAngle = 90;

        for (int i = 0; i < 4; i++){
            zAngle += 90;
            Geometry3D cylinder = csg.cylinder3D(10,boardSize,128,false);
            Geometry3D cylinderRotated = csg.rotate3D(csg.degrees(0),csg.degrees(90),csg.degrees(0)).transform(cylinder);
            Geometry3D cylinderMoved = csg.translate3D(x,y,z).transform(cylinderRotated);

            Geometry3D sphere = csg.sphere3D(10,128,false);
            Geometry3D sphereMoved = csg.translate3D(x,y,0).transform(sphere);

            Geometry3D edge = csg.union3D(cylinderMoved,sphereMoved);

            Geometry3D rotatedEdge = csg.rotate3D(csg.degrees(0),csg.degrees(0),csg.degrees(zAngle)).transform(edge);

            edgeArray.add(rotatedEdge);
        }
        Geometry3D finalEdge = csg.union3D(edgeArray);

        return finalEdge;
    }

    public Geometry3D magnetSpace(JavaCSG csg){
        Geometry3D finalMagnet = csg.cylinder3D(6,2.1,128,false);
        finalMagnet = csg.translate3D(0,0,-2).transform(finalMagnet);

        return finalMagnet;
    }
}