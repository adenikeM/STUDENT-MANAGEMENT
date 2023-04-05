package studentCreation;

import static studentCreation.Message.displayWelcomeMessage;
import static studentCreation.Message.selectBaseOperation;

public class Console {
    public static void main(String[] args) {
        if (AppConstants.FILE.exists()) {
            Utils.getStudentFromFile();
        }
        displayWelcomeMessage();
        int baseOperation = selectBaseOperation();
        while (baseOperation != -1) {
            if (baseOperation == 1) {
                Utils.getStudentFromUser();
                Utils.saveToFile();
            } else if (baseOperation == 2) {
                Utils.viewStudent();
            }
            baseOperation = selectBaseOperation();
        }
    }
}