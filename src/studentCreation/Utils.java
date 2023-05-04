package studentCreation;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import static studentCreation.AppConstants.*;

public class Utils {

    public static final String SPLIT_PATTERN = ";\n";

    public static int collectIntegerInput(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextInt();
    }


    public static void getStudentFromUser() {
        do {
            System.out.println("Please enter your student details in this format Fullname,address,gender(MALE, FEMALE), date of birth (dd/MM/yyyy) or -1 to stop");
            Scanner scanner = new Scanner(System.in);
            String studentString = scanner.nextLine().trim();
            if (studentString.equals("-1")) {
                return;
            }
            String[] split = studentString.split(",");
            Student student = new Student(STUDENT_LIST.size() + 1,
                    split[0], String.valueOf(split[1]),
                    Gender.valueOf(split[2]), LocalDate.parse(split[3], formatter));
            STUDENT_LIST.add(student);
        } while (true);
    }

    static void saveToFile() {
        try (FileWriter fileWriter = new FileWriter(AppConstants.FILE)) {
            for (Student student : AppConstants.STUDENT_LIST) {
                String message = student.getMessageDetails() + SPLIT_PATTERN;
                fileWriter.write(message);
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
                String dateString = studentStringSplits[4];
                LocalDate birthDate = LocalDate.parse(dateString, formatter);
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
            String dashes = "_____________________________________________________________________________________";
            String header = "LIST OF REGISTERED STUDENTS";
            System.out.printf("%s%n%50s%n%s%n", dashes, header, dashes);
            System.out.printf("| %-4s | %-30s | %-10s | %-10s | %-15s |%n %s %n", "ID", "FULL NAME", "ADDRESS", "GENDER", "DATE OF BIRTH", dashes);

            for (Student student : STUDENT_LIST) {
                if (!student.isDeleted()) {
                    System.out.printf("| %-4s | %-30s | %-10s | %-10s | %-15s | %n", student.getId(), student.getFullName(), student.getAddress(), student.getGender(), student.getBirthDate());
                }
            }
            System.out.println(dashes);
        } else {
            System.out.println("No student record");
        }
    }

    static void viewStudentByID() {
        int studentId = collectIntegerInput("Input the student ID you want to view ");
        if (!STUDENT_LIST.isEmpty()) {
            String dashes = "_____________________________________________________________________________________";
            String header = "LIST OF REGISTERED STUDENTS";
            System.out.printf("%s%n%50s%n%s%n", dashes, header, dashes);
            System.out.printf("| %-4s | %-30s | %-10s | %-10s | %-15s |%n %s %n", "ID", "FULL NAME", "ADDRESS", "GENDER", "DATE OF BIRTH", dashes);
            for (Student student : STUDENT_LIST) {
                if (student.getId() == studentId) {
                    student = getStudent(studentId);
                    System.out.printf("| %-4s | %-30s | %-10s | %-10s | %-15s | %n", student.getId(), student.getFullName(), student.getAddress(), student.getGender(), student.getBirthDate());
                }
            }
        }else {
            System.out.println("No student with ID" + studentId);
        }
        }

    private static Student getStudent(int studentId) {
        if (studentId < 1 || studentId > STUDENT_LIST.size()) {
            try {
                throw new InvalidStudentIdException(String.format("Student with id %d is invalid", studentId));
            } catch (InvalidStudentIdException e) {
                System.out.println(e.getMessage());
                System.exit(0);

            }
        }
        return STUDENT_LIST.get(studentId - 1);
    }

    public static void deleteStudentById() {
        viewStudent();
        int studentId = collectIntegerInput("Enter student ID you want to delete");
        Student student = getStudent(studentId);
        student.delete();
        saveToFile();
        System.out.println("Student with ID" + studentId + "is deleted");
    }
}
