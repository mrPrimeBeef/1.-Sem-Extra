package csg;

import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;

public class SquarePiece {
    private final double brickSize;
    private final double height;
    private final double indentHeight;

    public SquarePiece(double brickSize, double height, int indentHeight) {
        this.brickSize = brickSize;
        this.height = height;
        this.indentHeight = indentHeight;
    }

    public Geometry3D getSmallSqaurePieceNohole(JavaCSG csg){
        return createSqaurePieceNoHole(csg, height);
    }

    public Geometry3D getSmallSqaurePieceHole(JavaCSG csg){
        return createSqaurePieceHole(csg, this.height);
    }

    public Geometry3D getBigSqaurePieceNoHole(JavaCSG csg){
        return createSqaurePieceNoHole(csg,this.height);
    }
    public Geometry3D getBigSqaurePieceHole(JavaCSG csg){
        return createSqaurePieceHole(csg,this.height);
    }

    public Geometry3D createSqaurePieceHole(JavaCSG csg, double height){
        Geometry3D sqaure = csg.box3D(brickSize,brickSize,height,false);
        Geometry3D indent = createIndent(csg);

        Geometry3D indentSqaure = csg.difference3D(sqaure,indent);

        Geometry3D hole = csg.flatRing3D(0,8,4,128,false);
        Geometry3D holeMoved = csg.translate3D(0,0,height-3).transform(hole);
        Geometry3D finalSqaure = csg.difference3D(indentSqaure,holeMoved);
        return finalSqaure;
    }


    public Geometry3D createSqaurePieceNoHole(JavaCSG csg, double height){
        Geometry3D sqaure = csg.box3D(brickSize,brickSize,this.height,false);
        Geometry3D indent = createIndent(csg);

        Geometry3D finalSqaure = csg.difference3D(sqaure,indent);
        return finalSqaure;
    }

    public Geometry3D createIndent(JavaCSG csg){
        Geometry3D cylinder = csg.cylinder3D(5,brickSize+1,128,false);
        Geometry3D cylinderRotated = csg.rotate3D(csg.degrees(0),csg.degrees(90),csg.degrees(0)).transform(cylinder);
        Geometry3D cylinderMoved = csg.translate3D(-brickSize/2,brickSize/2,indentHeight).transform(cylinderRotated);

        Geometry3D cylinderRotated2 = csg.rotate3D(csg.degrees(90),csg.degrees(0),csg.degrees(0)).transform(cylinder);
        Geometry3D cylinderMoved2 = csg.translate3D(-brickSize/2,brickSize/2,indentHeight).transform(cylinderRotated2);

        Geometry3D cylinderMerge1 = csg.union3D(cylinderMoved,cylinderMoved2);

        Geometry3D cylinderRotated3 = csg.rotate3D(csg.degrees(0),csg.degrees(90),csg.degrees(0)).transform(cylinder);
        Geometry3D cylinderMoved3 = csg.translate3D(-brickSize/2,(brickSize/2)-brickSize,indentHeight).transform(cylinderRotated3);

        Geometry3D cylinderMerge2 = csg.union3D(cylinderMerge1,cylinderMoved3);

        Geometry3D cylinderRotated4 = csg.rotate3D(csg.degrees(90),csg.degrees(0),csg.degrees(0)).transform(cylinder);
        Geometry3D cylinderMoved4 = csg.translate3D((-brickSize/2)+brickSize,brickSize/2,indentHeight).transform(cylinderRotated4);

        Geometry3D cylinderMerge3 = csg.union3D(cylinderMerge2,cylinderMoved4);

        return cylinderMerge3;
    }

}
