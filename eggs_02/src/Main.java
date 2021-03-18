import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] token = reader.readLine().split(" ");
        int m = Integer.valueOf(token[0]);
        int n = Integer.valueOf(token[1]);
        reader.close();
        // m = c + s
        // n = 4 * c + 2 * s
        // n = 2*c + 2*m
        if ((n - 2*m) % 2 != 0) throw new RuntimeException("wrong input");
        int c = (n - 2*m)/2;
        int s = m - c;
        System.out.println("" + c + " " + s);
    }
}
