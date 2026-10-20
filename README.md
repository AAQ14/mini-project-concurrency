# mini-project-concurrency

# Concurrent CSV Data Processor
A Spring Boot application that reads employee data from a CSV file, calculates each employee's salary increase concurrently using a thread pool, and writes the updated salaries to a new CSV file.

## Features
- Upload a CSV file through a REST endpoint
- Read and parse employee records (name, salary, joined date, role, percent of projects completed)
- Calculate salary increases concurrently with Java's Executor framework
- Thread-safe shared data using `ReentrantLock`, `Semaphore` and `AtomicInteger`
- Write the updated data (old and new salary) to a new CSV file


## Tech Stack
- Java
- Spring Boot
- Maven
- Lombok


## CSV Format
Each line has six columns: `id, name, salary, joinedDate, role, percentCompleted`
```
1,Alice,52000.0,2019-05-12,Employee,0.8
2,Bob,68000.0,2020-08-23,Manager,0.6
3,Charlie,75000.0,2021-03-19,Director,0.7
```
- `joinedDate` uses the format `yyyy-MM-dd`
- `percentCompleted` is a decimal (0.8 means 80%)
- Role is one of `Director`, `Manager`, `Employee`
- Blank or invalid lines are skipped

### Output file
The updated data is saved in the application's working folder as `updated_employees_<timestamp>.csv` (the timestamp keeps every upload from overwriting a previous file). Columns:
```
name,role,joinedDate,percentCompleted,oldSalary,newSalary
```

## Possible Improvements
- Frontend page with a before/after salary table and a raise percentage input
