import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {
    private static String QUIT = "q";
    private static LFUcache<Integer> cache = new LFUcache<>(5);

    public static void main(String[] args) {


        try (BufferedReader lineReader = new BufferedReader(new InputStreamReader(System.in))) {
            String sequence;
            while (!QUIT.equals(sequence = lineReader.readLine())) {
                System.out.println(Command(sequence.split("\\s+")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String Command(String[] input){
        String out;
        switch (input[0]){
            case "add":
                int key = Integer.parseInt(input[1]);
                cache.add(key, key);
                out = "ok";
                break;
            case "get":
                int val = cache.get(Integer.parseInt(input[1]));
                out = "ok";
                break;
            case "print":
                out = cache.ToString();
                break;
            default:
                out = "err";
        }
        return out;
    }
}
