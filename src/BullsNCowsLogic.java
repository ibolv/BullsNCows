import java.lang.reflect.Array;
import java.util.Random;

public class BullsNCowsLogic {
    final private char[] hiddenNumber;
    private int counterAttempts;
    final private String[] cows = new String[]{" коров", " корова", " коровы"};
    final private String[] bulls = new String[]{" быков ", " бык ", " быка "};
    final private String[] attempts = new String[]{" попытку ", " попытки ", " попыток "};

    private boolean game;
    public BullsNCowsLogic() {
        hiddenNumber = randomHiddenNumber().toCharArray();
        counterAttempts = 0;
    }

    private String correctOutput(int value, String[] str) {
        int output = value % 10;
        return switch (output) {
            case 1 -> str[1];
            case 4,2,3 -> str[2];
            default -> str[0];
        };
    }

    private String randomHiddenNumber() {
        String str = "";
        Random random = new Random();
        while (str.length() < 4) {
            String randomNum = random.nextInt(10) + "";

            if (!str.contains(randomNum)) {
                str += randomNum;
            }

        }
        return str;
    }

    public String checkStep(char[] num) {
        int countBulls = 0;
        int countCow = 0;
        counterAttempts++;
        for (int i = 0; i < hiddenNumber.length; i++) {
            for (int j = 0; j < hiddenNumber.length; j++) {
                if (hiddenNumber[i] == num[j] && i != j) {
                    countCow++;
                }
            }
            if (hiddenNumber[i] == num[i]) {
                countBulls++;
            }
        }
        if (countBulls == 4) {
            String str = new String(hiddenNumber);
            game = true;
            return " Строка " + str + " была угадана за " + counterAttempts + correctOutput(counterAttempts, attempts);
        } else {
            String str = new String(num);
            return " Запрос " + str + " Ответ: " + countBulls + correctOutput(countBulls, bulls) + countCow + correctOutput(countCow, cows);
        }
    }

    public boolean getGame() {
        return game;
    }

    public String getHiddenNumber() {
        return new String(hiddenNumber);
    }
}
