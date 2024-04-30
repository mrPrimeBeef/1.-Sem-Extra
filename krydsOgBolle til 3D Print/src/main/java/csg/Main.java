package csg;

import org.abstractica.javacsg.*;


public class Main {
    public static void main(String[] args) {
        JavaCSG csg = JavaCSGFactory.createNoCaching();


        Cross cross = new Cross(30,5,4);
        Board board = new Board(34,10,5,4);
        Ring ring = new Ring(30,4);
        Geometry3D crossResult = cross.getGeometry(csg);
        Geometry3D boardResult = board.getBoard(csg);
        Geometry3D ringResult = ring.createRing(csg);

        csg.view(crossResult,0);
        csg.view(boardResult,1);
        csg.view(ringResult,2);
    }
}
