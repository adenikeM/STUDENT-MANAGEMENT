package studentCreation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static studentCreation.AppConstants.FILE;
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
    private static String readFromFile(){
        if(!FILE.exists()){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        try(FileReader fileReader = new FileReader(FILE)) {
            int i;
            while ((i = fileReader.read()) != -1) {
                sb.append((char) i);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
    static void getStudentFromFile(){
        String stringFromFile = readFromFile();

        if (!stringFromFile.isEmpty()) {

            String[] split = stringFromFile.split(SPLIT_PATTERN);

            for (String studentString : split) {
                String[] studentStringSplits = studentString.split(",");

                Student student = new Student(Integer.parseInt(studentStringSplits[0]), studentStringSplits[1],
                        Integer.parseInt(studentStringSplits[2]), Double.parseDouble(studentStringSplits[3]),
                        Boolean.parseBoolean(studentStringSplits[4]));
                AppConstants.STUDENT_LIST.add(student);
            }
        }
    }
    static  void viewStudent(){
        if(!STUDENT_LIST.isEmpty()){
            System.out.printf("________________________________________________________%n");
            System.out.printf("               LIST OF REGISTERED STUDENTS              %n");
            System.out.printf("                                                        %n");
            System.out.printf("________________________________________________________%n");


        }
    }

}

