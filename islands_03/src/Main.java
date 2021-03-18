import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static ArrayList<ArrayList<Integer>> ed;
    static int[] cluster;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String[] tokens = reader.readLine().split(" ");
        n = Integer.valueOf(tokens[0]);
        int m = Integer.valueOf(tokens[1]);
        ed = new ArrayList<>();
        for (int i = 0; i < n; i++) ed.add(new ArrayList<>());
        for (int i = 0; i < m; i++) {
            tokens = reader.readLine().split(" ");
            int v1 = Integer.valueOf(tokens[0]) - 1;
            int v2 = Integer.valueOf(tokens[1]) - 1;
            ed.get(v1).add(v2);
            ed.get(v2).add(v1);
        }
        reader.close();

        cluster = new int[n];
        List<Integer> vcl = new ArrayList<>();
        Arrays.fill(cluster, -1);
        for (int i = 0; i < n; i++)
            if (cluster[i] == -1) {
                findCluster(i, vcl.size());
                vcl.add(i);
            }

        int res = 2;
        for (Integer start :  vcl) {
            if (!allVertexMatched(start)) {
                res = 1;
                break;
            }
        }

        System.out.println(res);
    }

    private static boolean allVertexMatched(int start) {
        int ncl = cluster[start];
        int[] colors = new int[n];
        int kcol = 1;
        int[] path = new int[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(colors, -1);
        while (true) {
            int st = -1;
            for (int i = 0; i < n; i++) if (cluster[i] == ncl && colors[i] == -1) {
                st = i;
                break;
            }
            if (st == -1) return true;
            Arrays.fill(path, -1);
            path[0] = st;
            Arrays.fill(visited, false);
            visited[st] = true;
            int lp = dfs(0, path, colors, visited);
            if (lp == -1) {
                return false;
            } else {
                colors[path[0]] = kcol;
                colors[path[1]] = kcol;
                kcol++;
                for (int i = 2; i < lp; i += 2)
                    colors[path[i + 1]] = colors[path[i]];
            }
        }
    }

    private static int dfs(int k, int[] path, int[] colors, boolean[] visited) {
        int cur = path[k];
        if (k > 0 && colors[path[k]] != colors[path[k-1]]) {
            int nextColor = colors[path[k]];
            for (Integer i : ed.get(cur)) {
                if (!visited[i] && colors[i] == nextColor) {
                    path[k + 1] = i;
                    visited[i] = true;
                    int ans = dfs(k + 1, path, colors, visited);
//                    visited[i] = false;
                    if (ans >= 0) return ans;
                }
            }
        } else {
            for (Integer i : ed.get(cur)) if (!visited[i]) {
                path[k + 1] = i;
                if (colors[i] == -1) {
                    return k + 2;
                } else {
                    visited[i] = true;
                    int ans = dfs(k + 1, path, colors, visited);
//                    visited[i] = false;
                    if (ans >= 0) return ans;
                }
            }
        }
        return -1;
    }

    private static void findCluster(int k, int ncl) {
        cluster[k] = ncl;
        for (int i = 0; i < ed.get(k).size(); i++)
            if (cluster[ed.get(k).get(i)] == -1) findCluster(ed.get(k).get(i), ncl);
    }
}
