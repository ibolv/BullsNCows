import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecordNRead {
    private String currentDate;
    public String path;
    public RecordNRead(String path) {
        this.path = path;
        this.currentDate = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("MM.dd.yyy hh.mm.ss "));
    }

    public void recordToFile(String str, String hiddenNum) {
        int numGame = read();
        if (numGame == 0) numGame = 1; else numGame++;
        String startStr = "Game №" + numGame + " " + currentDate + " Загаданная строка " + hiddenNum  + "\n";
        try (FileWriter writer = new FileWriter(path, true)) {
            writer.write(startStr);
            writer.write(str);
            writer.flush();
        } catch (Exception e) {

        }
    }

    private int read() {
        FileReader reader = null;
        try {
            reader = new FileReader(path);
        } catch (FileNotFoundException e) {
            return 0;
        }
        Scanner scanner = new Scanner(reader);
        int item = 0;
        while (scanner.hasNext()) {
            Pattern pattern = Pattern.compile("№(?<item>\\d+)");
            Matcher matcher = pattern.matcher(scanner.nextLine());
            if (matcher.find()) {
                item = Integer.parseInt(matcher.group("item"));
            }
        }
        return item;
    }

}
