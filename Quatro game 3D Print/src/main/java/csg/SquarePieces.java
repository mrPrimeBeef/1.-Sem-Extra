package csg;

import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;

import java.util.ArrayList;

public class SquarePieces {
    private final double brickSize;
    private final double height;
    private final double indentHeight;

    public SquarePieces(double brickSize, double height, int indentHeight) {
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

        Geometry3D indentSquare = csg.difference3D(sqaure,indent);

        Geometry3D edge = roundTop(csg);

        Geometry3D cutout = cutout(csg);


        indentSquare = csg.union3D(indentSquare,edge);

        Geometry3D squareRoundTop = csg.difference3D(indentSquare,cutout);

        Geometry3D hole = csg.flatRing3D(0,18,12,128,false);
        Geometry3D holeMoved = csg.translate3D(0,0,height-8).transform(hole);
        Geometry3D finalSqaure = csg.difference3D(squareRoundTop,holeMoved);


        Geometry3D magnet = magnetSpace(csg);
        Geometry3D finalWMagnet = csg.difference3D(finalSqaure,magnet);

        return finalWMagnet;
    }


    public Geometry3D createSqaurePieceNoHole(JavaCSG csg, double height){
        Geometry3D sqaure = csg.box3D(brickSize,brickSize,this.height,false);
        Geometry3D indent = createIndent(csg);

        sqaure = csg.difference3D(sqaure,indent);

        Geometry3D magnet = magnetSpace(csg);
        Geometry3D finalWMagnet = csg.difference3D(sqaure,magnet);

        Geometry3D edge = roundTop(csg);
        Geometry3D cutout = cutout(csg);

        Geometry3D cutoutSquare = csg.difference3D(finalWMagnet,cutout);

        Geometry3D finalSqaure = csg.union3D(cutoutSquare,edge);

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

    public Geometry3D roundTop(JavaCSG csg){
            ArrayList<Geometry3D> edgeArray = new ArrayList<>();
            double y = (brickSize/2)-2;
            double x = (-brickSize/2)+2;
            double zAngle = 90;

            for (int i = 0; i < 4; i++){
                zAngle += 90;
                Geometry3D cylinder = csg.cylinder3D(4,brickSize-4,128,false);
                Geometry3D cylinderRotated = csg.rotate3D(csg.degrees(0),csg.degrees(90),csg.degrees(0)).transform(cylinder);
                Geometry3D cylinderMoved = csg.translate3D(x,y,2).transform(cylinderRotated);

                Geometry3D sphere = csg.sphere3D(4,128,false);
                Geometry3D sphereMoved = csg.translate3D(x,y,0).transform(sphere);

                Geometry3D edge = csg.union3D(cylinderMoved,sphereMoved);

                Geometry3D rotatedEdge = csg.rotate3D(csg.degrees(0),csg.degrees(0),csg.degrees(zAngle)).transform(edge);

                edgeArray.add(rotatedEdge);
            }
            Geometry3D edge = csg.union3D(edgeArray);
            Geometry3D box = csg.box3D(brickSize-4,brickSize-4,4,false);

            Geometry3D fuseEdge = csg.union3D(edge,box);


            Geometry3D roundedTop = csg.translate3D(0,0,height-2).transform(fuseEdge);


            return roundedTop;
    }

    public Geometry3D magnetSpace(JavaCSG csg){
        Geometry3D finalMagnet = csg.box3D(4.73,9.77,2.88,false);
        Geometry3D finalMagnet1 = csg.translate3D(0,0,-0.1).transform(finalMagnet);

        return finalMagnet1;
    }

    public Geometry3D cutout(JavaCSG csg) {
        ArrayList<Geometry3D> cutoutArray = new ArrayList<>();
        double zAngle = 0;
        Geometry3D box = csg.box3D(3,3,height*2,false);
        box = csg.translate3D(1,1,0).transform(box);

        Geometry3D cyl = csg.cylinder3D(4,height*2+2,128,false);
        cyl = csg.translate3D(0,0,-1).transform(cyl);

        Geometry3D cutout = csg.difference3D(box,cyl);
        cutout = csg.translate3D(brickSize/2-2,brickSize/2-2,-1).transform(cutout);

        for (int i = 1; i < 5; i++) {
            cutout = csg.rotate3D(csg.degrees(0), csg.degrees(0), csg.degrees(zAngle)).transform(cutout);

            cutoutArray.add(cutout);
            zAngle += 90;
        }
        Geometry3D finalCutout = csg.union3D(cutoutArray);

        return finalCutout;
    }

}
