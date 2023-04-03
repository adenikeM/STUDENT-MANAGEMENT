package studentCreation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import static studentCreation.AppConstants.FILE;
import static studentCreation.AppConstants.STUDENT_LIST;

public class Utils {

    public static final String SPLIT_PATTERN = ";\n";


    public static void getStudentFromUser() {
        do {
            System.out.println("Please enter your student details in this format Fullname,address,gender(MALE, FEMALE), date of birth (DD/MM/YY) or -1 to stop");
            Scanner scanner = new Scanner(System.in);
            String studentString = scanner.nextLine().trim();
            if (studentString.equals("-1")) {
                return;
            }
            String[] split = studentString.split(",");
            Student student = new Student(STUDENT_LIST.size() + 1,
                    split[0],String.valueOf(split[1]),
                    Gender.valueOf(split[2]), LocalDate.parse(split[3]));
            STUDENT_LIST.add(student);
        } while (true);
    }
    static void saveToFile() {
        try (FileWriter fileWriter = new FileWriter(AppConstants.FILE)) {
            for (Student student :AppConstants.STUDENT_LIST) {
                //System.out.println("DETAILS  ++> " + student.getMessageDetails());
                fileWriter.write(student.getMessageDetails() + SPLIT_PATTERN);
                fileWriter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static String readFromFile() {
        if (!FILE.exists()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        try (FileReader fileReader = new FileReader(FILE)) {
            int i;
            while ((i = fileReader.read()) != -1) {
                sb.append((char) i);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    static void getStudentFromFile() {
        String stringFromFile = readFromFile();

        if (!stringFromFile.isEmpty()) {

            String[] split = stringFromFile.split(SPLIT_PATTERN);

            for (String studentString : split) {
                String[] studentStringSplits = studentString.split(",");

                int id = Integer.parseInt(studentStringSplits[0]);
                String name = studentStringSplits[1];
                String address = studentStringSplits[2];
                String genderString = studentStringSplits[3];
                Boolean isDeleted = Boolean.parseBoolean(studentStringSplits[5]);
                Gender gender = Gender.valueOf(genderString.toUpperCase());
                LocalDate birthDate = LocalDate.parse(studentStringSplits[4]);
//                try {
//                    Gender gender = Gender.valueOf(genderString.toUpperCase());
//                } catch (Exception e) {
//
//                }
//                gender = Gender.MALE;
//                if (genderInt == 2) {
//                    gender = Gender.FEMALE;
//                }
                Student student = new Student(id, name, address, gender, birthDate, isDeleted);
                AppConstants.STUDENT_LIST.add(student);
            }
        }
    }
    static void viewStudent() {
        if (!STUDENT_LIST.isEmpty()) {
            String printf = String.format(String.valueOf(System.out.printf("________________________________________________________%n")));
            System.out.printf("________________________________________________________%n");
            System.out.printf("               LIST OF REGISTERED STUDENTS              %n");
            System.out.printf("                                                        %n");
            System.out.printf("________________________________________________________%n");
            System.out.printf("|%-4s | %-40s | %-50s | %-8s | %-10 |%n", "ID", "FULLNAME", "ADDRESS", "GENDER", "DATE OF BIRTH");
            System.out.printf("________________________________________________________%n");
            StringBuilder sb = new StringBuilder(printf);
            for (Student student : STUDENT_LIST) {
                if (!student.isDeleted()) {
                    String studentLine = String.format("%-4d %-40s %-50ds %-8s %10d %n", student.getId(), student.getFullName(), student.getAddress(), student.getGender(), student.getBirthDate());
                    sb.append(studentLine);
                }
            }
            System.out.println(sb);
        } else {
            System.out.println("No student record");
        }
    }
}


