import java.io.*;
import java.util.*;

class Patient implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private int age;
    private String gender;
    private String diagnosis;
    private List<String> visitHistory;

    public Patient(String id, String name, int age, String gender, String diagnosis) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.diagnosis = diagnosis;
        this.visitHistory = new ArrayList<>();
        this.visitHistory.add(diagnosis + " (Initial Diagnosis)");
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public List<String> getVisitHistory() {
        return visitHistory;
    }

    public void addVisit(String diagnosis) {
        this.diagnosis = diagnosis;
        this.visitHistory.add(diagnosis + " (Follow-up Visit)");
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Gender: " + gender + ", Diagnosis: " + diagnosis;
    }
}

public class Main {
    private static final String FILE_NAME = "patient_records.ser";
    private static HashMap<String, Patient> patientRecords = new HashMap<>();

    public static void main(String[] args) {
        loadPatientRecords();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nAutomatic Health Monitoring System");
            System.out.println("1. Add Patient Record");
            System.out.println("2. View Patient Record");
            System.out.println("3. Update Patient Record");
            System.out.println("4. Generate Report");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addPatient(scanner);
                    break;
                case 2:
                    viewPatient(scanner);
                    break;
                case 3:
                    updatePatient(scanner);
                    break;
                case 4:
                    generateReport(scanner);
                    break;
                case 5:
                    savePatientRecords();
                    System.out.println("Exiting system...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addPatient(Scanner scanner) {
        System.out.print("Enter Patient ID: ");
        String id = scanner.next();
        if (patientRecords.containsKey(id)) {
            System.out.println("Patient ID already exists. Use update option.");
            return;
        }

        System.out.print("Enter Patient Name: ");
        String name = scanner.next();
        System.out.print("Enter Patient Age: ");
        int age = scanner.nextInt();
        System.out.print("Enter Patient Gender (M/F): ");
        String gender = scanner.next().toUpperCase();
        System.out.print("Enter Initial Diagnosis: ");
        String diagnosis = scanner.next();

        Patient patient = new Patient(id, name, age, gender, diagnosis);
        patientRecords.put(id, patient);
        System.out.println("Patient record added successfully!");
    }

    private static void viewPatient(Scanner scanner) {
        System.out.print("Enter Patient ID: ");
        String id = scanner.next();
        if (patientRecords.containsKey(id)) {
            Patient patient = patientRecords.get(id);
            System.out.println(patient);
            System.out.println("Visit History: " + patient.getVisitHistory());
        } else {
            System.out.println("No record found for the given ID.");
        }
    }

    private static void updatePatient(Scanner scanner) {
        System.out.print("Enter Patient ID: ");
        String id = scanner.next();
        if (patientRecords.containsKey(id)) {
            System.out.print("Enter New Diagnosis: ");
            String diagnosis = scanner.next();
            Patient patient = patientRecords.get(id);
            patient.addVisit(diagnosis);
            System.out.println("Patient record updated successfully!");
        } else {
            System.out.println("No record found for the given ID.");
        }
    }

    private static void generateReport(Scanner scanner) {
        System.out.print("Enter Patient ID: ");
        String id = scanner.next();
        if (patientRecords.containsKey(id)) {
            Patient patient = patientRecords.get(id);
            System.out.println("Generating report for " + patient.getName() + "...");
            String report = "Patient Report:\n" +
                            "ID: " + patient.getId() + "\n" +
                            "Name: " + patient.getName() + "\n" +
                            "Age: " + patient.getAge() + "\n" +
                            "Gender: " + patient.getGender() + "\n" +
                            "Current Diagnosis: " + patient.getDiagnosis() + "\n" +
                            "Visit History:\n" + String.join("\n", patient.getVisitHistory());
            System.out.println(report);
            saveReportToFile(id, report);
        } else {
            System.out.println("No record found for the given ID.");
        }
    }

    private static void saveReportToFile(String id, String report) {
        try (PrintWriter out = new PrintWriter(id + "_report.txt")) {
            out.println(report);
            System.out.println("Report saved to " + id + "_report.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Error saving report to file.");
        }
    }

    private static void loadPatientRecords() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            patientRecords = (HashMap<String, Patient>) ois.readObject();
            System.out.println("Patient records loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("No previous records found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading patient records: " + e.getMessage());
        }
    }

    private static void savePatientRecords() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(patientRecords);
            System.out.println("Patient records saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving patient records: " + e.getMessage());
        }
    }
}
