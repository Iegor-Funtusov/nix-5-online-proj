package level1.task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TriangleVertices {
    double x;
    double y;

    TriangleVertices() throws IOException {
        makeVertices();
    }

    TriangleVertices(double x, double y) {
    }

    public TriangleVertices makeVertices() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter X - coordinate of vertice (e.g. 1) and press Enter: ");
        x = Double.parseDouble(reader.readLine());

        System.out.println("Enter Y - coordinate of vertice (e.g. 2) and press Enter: ");
        y = Double.parseDouble(reader.readLine());

        return new TriangleVertices(x, y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
