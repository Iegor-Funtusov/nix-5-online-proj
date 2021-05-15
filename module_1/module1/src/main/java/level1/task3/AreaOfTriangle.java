package level1.task3;

import java.io.IOException;

public class AreaOfTriangle {

    public static void areaTriangle() throws IOException {
        System.out.println("Please enter coordinates for A vertice:");
        TriangleVertices verticesA = new TriangleVertices();
        System.out.println("Please enter coordinates for B vertice:");
        TriangleVertices verticesB = new TriangleVertices();
        System.out.println("Please enter coordinates for C vertice:");
        TriangleVertices verticesC = new TriangleVertices();

        double argForArea = ((verticesB.getX() - verticesA.getX()) * ((verticesC.getY() - verticesA.getY()) -
                (verticesC.getX() - verticesA.getX()) * (verticesB.getY() - verticesA.getY())));
        double areaOfTriangle = 0.5 * Math.abs(argForArea);

        if (areaOfTriangle == 0) {
            System.out.println("This triangle can't be exist. ");
        }
        System.out.println("Area of triangle is: " + areaOfTriangle);
    }
}
