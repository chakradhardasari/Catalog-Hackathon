# Automatic Health Monitoring System

## Overview

The **Automatic Health Monitoring System** is designed to assist patients, particularly in rural and sub-urban communities, by maintaining their diagnosis reports automatically. This system reduces the need for repetitive diagnosing, saving both time and money.

## Features

- **Patient Record Management**: Add, view, and update patient records, including their medical history and diagnoses.
- **Data Persistence**: Patient records are saved to a file (`patient_records.ser`), ensuring data is retained between sessions.
- **Visit History Tracking**: Tracks and records each patient's visit history, including initial diagnoses and follow-up visits.
- **Report Generation**: Generates a detailed report for each patient, which includes their ID, name, age, gender, current diagnosis, and complete visit history. The report is saved as a text file in the format `<patient_id>_report.txt`.

## How to Use

### Running the Program

1. **Add Patient Record**:
   - Choose the option to add a new patient.
   - Enter the patient's ID, name, age, gender, and initial diagnosis.
   - The patient record is created and stored.

2. **View Patient Record**:
   - Enter the patient's ID to retrieve and view their details, including visit history.

3. **Update Patient Record**:
   - Enter the patient's ID and a new diagnosis to update their record.
   - The new diagnosis is added to their visit history as a follow-up visit.

4. **Generate Report**:
   - Enter the patient's ID to generate a report summarizing their details and visit history.
   - The report is saved as a text file named after the patient's ID (e.g., `P001_report.txt`).

5. **Exit**:
   - The system saves all records to `patient_records.ser` and exits.

### Example Files

- **`patient_records.ser`**:
  - A binary file that stores serialized patient records. This file is automatically created and updated by the system.

- **Example Report Files**:
  - Generated reports for individual patients:
    - `P001_report.txt`
    - `P002_report.txt`
  - Each report includes the patient's ID, name, age, gender, current diagnosis, and visit history.

## Requirements

- **Java**: Make sure you have Java installed to run the program.

## Installation

1. **Clone the Repository**:
   - Clone the repository to your local machine.

2. **Compile and Run**:
   - Navigate to the project directory.
   - Compile the Java files using `javac Main.java`.
   - Run the program using `java Main`.

## Future Enhancements

- **Database Integration**: Store patient records in a database for scalability and advanced querying.
- **User Authentication**: Implement user roles and authentication for secure access.
- **Graphical User Interface (GUI)**: Develop a user-friendly interface to improve usability.
- **Additional Health Metrics**: Include more comprehensive health data, such as lab results and prescriptions.

