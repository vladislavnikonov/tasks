import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Scanner;

public class TextMapExc {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java TextMapExc file1.txt");
            System.exit(1);
        }

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(args[0]);
        } catch (FileNotFoundException e) {
            System.err.println("Error: No file");
            System.exit(1);
        }

        Scanner scanner = new Scanner(fileReader);
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter("file2.txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            System.err.println("Error: Can't create file");
            System.exit(1);
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            for (String part : parts) {
                if (isNumeric(part)) {
                    if (checkNumber(part)) {
                        printWriter.println(line);
                        printWriter.flush();
                    }
                }
            }
        }
    }

    public static boolean checkNumber(String part) {
        HashMap<Character, Integer> symbols = new HashMap<>();

        for (int i = 0; i < part.length(); i++) {
            char key = part.charAt(i);
            if (!symbols.containsKey(key))
                symbols.put(key, 1);
            else
                symbols.put(key, symbols.get(key) + 1);
        }
        return symbols.size() <= 3;
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
