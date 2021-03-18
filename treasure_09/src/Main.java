import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Main {
    static class Pt {
        public Pt(int x, int y) {
            this.x = x;
            this.y = y;
        }
        int x, y;
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
    }

    static class Rect {
        public Rect(Pt a, Pt b) {
            this.a = a;
            this.b = b;
        }
        Pt a, b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String[] tokens = reader.readLine().split(" ");
        int w = Integer.valueOf(tokens[0]);
        int h = Integer.valueOf(tokens[1]);
        int m = Integer.valueOf(tokens[2]);
        int n = Integer.valueOf(tokens[3]);
        int k = Integer.valueOf(tokens[4]);
        List<Pt> pts = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            tokens = reader.readLine().split(" ");
            pts.add(new Pt(Integer.valueOf(tokens[0]), Integer.valueOf(tokens[1])));
        }
        reader.close();

        TreeSet<Integer> xst = new TreeSet<>(pts.stream().map(pt -> pt.getX() + 1).collect(Collectors.toSet()));
        xst.add(0);
        xst.add(w - m);
        TreeSet<Integer> yst = new TreeSet<>(pts.stream().map(pt -> pt.getY() + 1).collect(Collectors.toSet()));
        yst.add(0);
        yst.add(h - n);
        for (Pt pt : pts) {
            if (pt.x - m >= 0) xst.add(pt.x - m);
            if (pt.y - n >= 0) yst.add(pt.y - n);
        }

        Rect res = new Rect(null, null);

        for (Integer x1: xst) {
            if (res == null) break;
            if (x1 + m > w) break;
            for (Integer y1: yst) {
                if (y1 + n > h) break;
                boolean found = false;
                for (Pt pt : pts) {
                    if (pt.x >= x1 && pt.x < x1 + m && pt.y >= y1 && pt.y < y1 + n) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    res = intRect(res, x1, y1, x1 + m - 1, y1 + n - 1);
                    if (res == null) break;
                }
            }
        }

        System.out.println(res == null ? "NO" : (res.a == null ? "BAD TEST" : "YES"));
        //System.out.println("" + res.a.x + ", " + res.a.y + " - " + res.b.x + ", " + res.b.y);
    }

    private static Rect intRect(Rect res, int x1, int y1, int x2, int y2) {
        if (res.a == null) {
            return new Rect(new Pt(x1, y1), new Pt(x2, y2));
        }
        Pt a = new Pt(Math.max(res.a.x, x1), Math.max(res.a.y, y1));
        Pt b = new Pt(Math.min(res.b.x, x2), Math.min(res.b.y, y2));
        if (a.x > b.x) return null;
        if (a.y > b.y) return null;
        return new Rect(a, b);
    }
}
