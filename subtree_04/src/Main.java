import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    static class Edge {
        int v, d;

        Edge(int v, int d) {
            this.v = v;
            this.d = d;
        }
    }

    static class Qel {
        int v, d;

        Qel(int v, int d) {
            this.v = v;
            this.d = d;
        }

        public int getV() {
            return v;
        }

        public int getD() {
            return d;
        }
    }

    static class BfsRes {
        int dist, nv;

        public BfsRes(int dist, int nv) {
            this.dist = dist;
            this.nv = nv;
        }
    }

    static int n, l;
    static ArrayList<ArrayList<Edge>> e2darr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String[] arr = reader.readLine().split(" ");
        n = Integer.valueOf(arr[0]);
        l = Integer.valueOf(arr[1]);

        ArrayList<Edge> earr = new ArrayList<>();
        for (int i = 0; i < n; i++) e2darr.add(new ArrayList<>());

        for (int i = 1; i < n; i++) {
            arr = reader.readLine().split(" ");
            int v = Integer.valueOf(arr[0]);
            int d = Integer.valueOf(arr[1]);
            e2darr.get(v).add(new Edge(i, d));
            e2darr.get(i).add(new Edge(v, d));
            earr.add(new Edge(v,d));
        }
        reader.close();

        int a = 1;
        int maxres = 1;
        for (Edge edge : earr) {
            int b = edge.v;
            int d = edge.d;
            int maxsumd = l - d;
            ArrayList<BfsRes> res1 = new ArrayList<>();
            bfs(b, a, res1);

            ArrayList<BfsRes> res2 = new ArrayList<>();
            bfs(a, b, res2);

            int ir1 = res1.size() - 1, ir2 = 0;
            while (ir1 >= 0 && ir2 < res2.size()) {
                while (ir1 >= 0 && res1.get(ir1).dist + res2.get(ir2).dist > maxsumd) ir1--;
                if (ir1 < 0) break;
                int r1 = res1.get(ir1).dist;
                int r2 = res2.get(ir2).dist;
                if (r1 + r2 <= maxsumd && (2*(r1 + d) >= r1 + d + r2) && (2*(r2 + d) >= r1 + d + r2))
                    maxres = Math.max(maxres, res1.get(ir1).nv + res2.get(ir2).nv);
                ir2++;
            }
            a++;
        }

        System.out.println(maxres);
    }

    private static void bfs(int a, int ex, ArrayList<BfsRes> res) {
        Set<Integer> visited = new HashSet<>();
        visited.add(ex);
        TreeSet<Qel> q = new TreeSet<>(Comparator.comparing(Qel::getD).thenComparing(Qel::getV));
        q.add(new Qel(a, 0));

        while (!q.isEmpty()) {
            Qel first = q.pollFirst();
            if (first.d <= l/2 + 1) {
                for (Edge ed : e2darr.get(first.v))
                    if (!visited.contains(ed.v)) {
                        q.add(new Qel(ed.v, first.d + ed.d));
                    }
            }
            res.add(new BfsRes(first.d, visited.size()));
            visited.add(first.v);
        }
    }
}