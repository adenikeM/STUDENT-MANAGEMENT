package studentCreation;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static studentCreation.AppConstants.STUDENT_LIST;

public class Utils {

    public static final String SPLIT_PATTERN = ";\n";


    public static void getStudentFromUser() {
        do {
            System.out.println("Please enter your student details in this format name,address,gender, date of birth or -1 to stop");
            Scanner scanner = new Scanner(System.in);
            String productString = scanner.nextLine().trim();
            if (productString.equals("-1")) {
                return;
            }
            String[] split = productString.split(",");
            Student student = new Student(STUDENT_LIST.size() + 1,
                    split[0], Integer.parseInt(split[1]), Double.parseDouble(split[2]));
            STUDENT_LIST.add(student);
        } while (true);
    }
    static void saveToFile() {
        try (FileWriter fileWriter = new FileWriter(AppConstants.FILE)) {
            for (Student student : STUDENT_LIST) {
                fileWriter.write(student.getMessageDetails() + SPLIT_PATTERN);
                fileWriter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    }

