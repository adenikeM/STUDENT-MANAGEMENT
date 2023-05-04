package studentCreation;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

public class AppConstants {

    public static final String FILE_NAME = "student.txt";
    public static final File FILE = new File(FILE_NAME);
    public static List<Student> STUDENT_LIST = new ArrayList<>();
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
}
