import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ArrayList<Student> students = new ArrayList<Student>();

    private static class Student {
        private final String name;
        private final double grade;

        Student(String name, double grade) {
            this.name = name;
            this.grade = grade;
        }
    }

    public static void main(String[] args) {
        System.out.println("================================");
        System.out.println("       STUDENT GRADE TRACKER");
        System.out.println("================================");

        boolean running = true;
        while (running) {
            System.out.println("\\n1. Add student grade");
            System.out.println("2. View all students");
            System.out.println("3. View summary report");
            System.out.println("4. Exit");
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1: addStudent(); break;
                case 2: displayStudents(); break;
                case 3: displaySummary(); break;
                case 4:
                    running = false;
                    System.out.println("Thank you for using Student Grade Tracker.");
                    break;
                default: System.out.println("Invalid option. Choose 1 to 4.");
            }
        }
        SCANNER.close();
    }

    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = SCANNER.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        double grade = readDouble("Enter grade (0-100): ");
        if (grade < 0 || grade > 100) {
            System.out.println("Grade must be between 0 and 100. Student was not added.");
            return;
        }

        students.add(new Student(name, grade));
        System.out.println("Student grade added successfully.");
    }

    private static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }

        System.out.println("\\n---------------- STUDENT RECORDS ----------------");
        System.out.printf("%-5s %-25s %10s%n", "No.", "Student Name", "Grade");
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            System.out.printf("%-5d %-25s %10.2f%n", i + 1, s.name, s.grade);
        }
    }

    private static void displaySummary() {
        if (students.isEmpty()) {
            System.out.println("No grades available. Add students first.");
            return;
        }

        double total = 0;
        Student topStudent = students.get(0);
        Student lowStudent = students.get(0);

        for (Student s : students) {
            total += s.grade;
            if (s.grade > topStudent.grade) topStudent = s;
            if (s.grade < lowStudent.grade) lowStudent = s;
        }

        System.out.println("\\n============== SUMMARY REPORT ==============");
        System.out.println("Number of students : " + students.size());
        System.out.printf("Average grade      : %.2f%n", total / students.size());
        System.out.printf("Highest grade      : %.2f (%s)%n", topStudent.grade, topStudent.name);
        System.out.printf("Lowest grade       : %.2f (%s)%n", lowStudent.grade, lowStudent.name);
        System.out.println("============================================");
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(SCANNER.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(SCANNER.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
