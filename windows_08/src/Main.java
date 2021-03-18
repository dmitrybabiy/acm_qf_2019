import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static class Pt {
        int x, y;

        public Pt(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String[] strarr = reader.readLine().split(" ");
        int lx = Integer.valueOf(strarr[0]);
        int ly = Integer.valueOf(strarr[1]);
        strarr = reader.readLine().split(" ");
        Pt pa1 = new Pt(Integer.valueOf(strarr[0]), Integer.valueOf(strarr[1]));
        Pt pa2 = new Pt(Integer.valueOf(strarr[2]), Integer.valueOf(strarr[3]));
        strarr = reader.readLine().split(" ");
        Pt pb1 = new Pt(Integer.valueOf(strarr[0]), Integer.valueOf(strarr[1]));
        Pt pb2 = new Pt(Integer.valueOf(strarr[2]), Integer.valueOf(strarr[3]));
        reader.close();

        if (isIntersected(pa1, pb1, pa2, pb2)) {
            Pt tmp = pa1;
            pa1 = pa2;
            pa2 = tmp;
        }

        double dist1 = Math.min(distPtAndEdge(pa1, pa2, pb2), distPtAndEdge(pb1, pa2, pb2));
        double dist2 = Math.min(distPtAndEdge(pa2, pa1, pb1), distPtAndEdge(pb2, pa1, pb1));
        double dist = Math.max(dist1, dist2);
        if (intersectPerpAndEdge(pa1, pa2, pb1, pb2) && intersectPerpAndEdge(pa2, pa1, pb1, pb2)
            && dist(pa1, pa2) > dist) {
            dist = dist(pa1, pa2);
        }
        if (intersectPerpAndEdge(pb1, pb2, pa1, pa2) && intersectPerpAndEdge(pb2, pb1, pa1, pa2)
                && dist(pb1, pb2) > dist) {
            dist = dist(pb1, pb2);
        }
        if (intersectPerpAndEdge(pa1, pb2, pb1, pb2) && intersectPerpAndEdge(pb2, pa1, pa1, pa2)
                && dist(pa1, pb2) > dist) {
            dist = dist(pa1, pb2);
        }
        if (intersectPerpAndEdge(pb1, pa2, pa1, pa2) && intersectPerpAndEdge(pa2, pb1, pb1, pb2)
                && dist(pb1, pa2) > dist) {
            dist = dist(pb1, pa2);
        }



        System.out.println(String.format("%.3f", dist));
    }

    private static double distPtAndEdge(Pt p, Pt a, Pt b) {
        if (a.x == b.x && a.y == b.y) return 0.0;
        double A = a.y - b.y;
        double B = b.x - a.x;
        double C = a.x * b.y - a.y * b.x;
        return Math.abs(A * p.x + B * p.y + C) / Math.sqrt(A * A + B * B);
    }

    private static boolean isIntersected(Pt a, Pt b, Pt c, Pt d) {
        return intersect_1 (a.x, b.x, c.x, d.x)
                && intersect_1 (a.y, b.y, c.y, d.y)
                && area(a,b,c) * area(a,b,d) <= 0
                && area(c,d,a) * area(c,d,b) <= 0;
    }

    private static int area(Pt a, Pt b, Pt c) {
        return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
    }

    private static boolean intersect_1(int a, int b, int c, int d) {
        if (a > b)  { int tmp = a; a = b; b = tmp; }
        if (c > d)  { int tmp = c; c = d; d = tmp; }
        return Math.max(a,c) <= Math.min(b,d);
    }

    private static boolean intersectPerpAndEdge(Pt p1, Pt p2, Pt p3, Pt p4) {
        int a = p2.x - p1.x;
        int b = -(p1.y - p2.y);
        int c = -a * p1.x - b * p1.y;

        return Math.signum(a * p3.x + b * p3.y + c) * Math.signum(a * p4.x + b * p4.y + c) <= 0.0;
    }

    private static double dist(Pt a, Pt b) {
        return Math.sqrt((a.x - b.x)*(a.x - b.x) + (a.y - b.y)*(a.y - b.y));
    }


}
