import java.util.Scanner;

public class UserInput {
    private BullsNCowsLogic game;
    private RecordNRead recordNRead;
    public UserInput() {
        game = new BullsNCowsLogic();
        recordNRead = new RecordNRead("Games.txt");
    }

    public String getInput(String input) throws InputException {

        if (input.length() != 4) {
            throw new InputException(input, "Число должно быть 4-х значным");
        } else {
            return input;
        }

    }

    public void runGame(){
        Scanner scanner = new Scanner(System.in);
        String result = "";
        while(!game.getGame()) {
            System.out.println("Введите число: ");
            String inputStr = scanner.nextLine();
            char[] num = inputStr.toCharArray();
            String step = game.checkStep(num);
            result = result + step + "\n";
            System.out.println(step);
        }
        recordNRead.recordToFile(result, game.getHiddenNumber());

    }
}
