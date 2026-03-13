# Project Report: Student Record Management System

**Course Name:**  SWE 4302 - OOC II
**Student Name:** Md. Muntahi Hasan Akhiar
**Student ID:** 230042118
**Date:** March 12, 2026

---
## 1. Introduction

The system is designed to store and manage student details such as Student ID, Name, GPA, and Gender. To achieve reusability and type safety, the system architecture leverages **Java Generics** and the **Java Collections Framework**.

## 2. System Components and Task Implementations

### 2.1 The Student Model

Before addressing the specific tasks, a `Student` entity class was created to hold the data. To fulfill Task 4 (grouping by gender), a `gender` attribute was included alongside the required ID, Name, and GPA fields.


```java
class Student {
    private String studentId;
    private String name;
    private double gpa;
    private String gender; 

    public Student(String studentId, String name, double gpa, String gender) {
        this.studentId = studentId;
        this.name = name;
        this.gpa = gpa;
        this.gender = gender;
    }

    // Getters
    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public double getGpa() { return gpa; }
    public String getGender() { return gender; }

    @Override
    public String toString() {
        return "Student{ID='" + studentId + "', Name='" + name + "', GPA=" + gpa + ", Gender='" + gender + "'}";
    }
}
```

### 2.2 Task 1: Generic DataStore Implementation

To make the system reusable for any data type, a generic class `DataStore<T>` was implemented. The `<T>` type parameter allows the class to be instantiated with any object type (in this case, `Student`), ensuring compile-time type safety. It uses an `ArrayList` from the Java Collections Framework as the underlying data structure.

Java

```java
class DataStore<T> {
    private List<T> collection;

    public DataStore() {
        this.collection = new ArrayList<>();
    }

    public void store(T item) {
        collection.add(item);
    }

    public List<T> retrieveAll() {
        return collection;
    }
}
```

### 2.3 Task 2: Sorting Student Records by GPA

To sort the student records, we utilize the `sort()` method available in the Java `List` interface. We provide a custom `Comparator` that compares the GPA of the students. In this implementation, the records are sorted in descending order so that the highest GPAs appear first.


```java
students.sort(Comparator.comparingDouble(Student::getGpa).reversed());
```

### 2.4 Task 3: Searching for a Particular Student

Searching is implemented using a simple, traditional `for-each` loop to iterate through the list of students. Inside the loop, an `if` statement checks if the current student's ID matches the target ID. Once the student is found, a `break` statement is used to exit the loop early, making the search more efficient.

```java
String searchId = "103";
Student foundStudent = null;

for (Student s : students) {
    if (s.getStudentId().equals(searchId)) {
        foundStudent = s; 
        break;
    }
}

if (foundStudent != null) {
    System.out.println("Found: " + foundStudent);
} else {
    System.out.println("Student not found.");
}
```

### 2.5 Task 4: Grouping Students by Gender

To group students into separate lists based on their gender, the `Collectors.groupingBy` method is utilized alongside the Stream API. This function elegantly maps the `gender` string to a corresponding `List<Student>`.


```java
public static Map<String, List<Student>> groupStudentsByGender(List<Student> students) {
    return students.stream()
            .collect(Collectors.groupingBy(Student::getGender));
}
```

---

## 3. Full System Execution (Driver Class)

The following is the main execution class that brings all the components together, populates the generic `DataStore`, and executes the tasks sequentially to demonstrate the system's capabilities.



```java
import java.util.*;
import java.util.stream.Collectors;

public class StudentRecordManagement {

    public static void main(String[] args) {

        DataStore<Student> studentStore = new DataStore<>();
        
        studentStore.store(new Student("101", "Alice Smith", 3.8, "Female"));
        studentStore.store(new Student("102", "Bob Johnson", 3.2, "Male"));
        studentStore.store(new Student("103", "Charlie Brown", 3.9, "Male"));
        studentStore.store(new Student("104", "Diana Prince", 3.5, "Female"));

        List<Student> students = studentStore.retrieveAll();

        // Execution of Task 2
        System.out.println("--- Task 2: Sorted by GPA (Descending) ---");
        students.sort(Comparator.comparingDouble(Student::getGpa).reversed());
        students.forEach(System.out::println);


        // Execution of Task 3 (Using a traditional for-loop)
        System.out.println("\n--- Task 3: Search Result ---");
        String searchId = "103";
        Student foundStudent = null;

        for (Student s : students) {
            if (s.getStudentId().equals(searchId)) {
                foundStudent = s;
                break;
            }
        }

        if (foundStudent != null) {
            System.out.println("Found: " + foundStudent);
        } else {
            System.out.println("Student not found.");
        }

        // Execution of Task 4
        System.out.println("\n--- Task 4: Grouped by Gender ---");
        Map<String, List<Student>> groupedByGender = students.stream()
                .collect(Collectors.groupingBy(Student::getGender));
                
        groupedByGender.forEach((gender, list) -> {
            System.out.println(gender + " Students:");
            list.forEach(s -> System.out.println("  - " + s.getName()));
        });
    }
}
```

## 4. Conclusion

The implementation successfully meets all the requirements outlined in the scenario. By utilizing Java Generics (`DataStore<T>`), the storage system is highly cohesive and decoupled from any specific data type, ensuring it can be reused for other entities (like `Faculty` or `Course`) in the future. Furthermore, utilizing the Java Collections Framework and Stream API allowed for efficient, readable, and concise sorting, searching, and grouping operations.