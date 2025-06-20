# POS System Project Structure

## Directory Layout
```
POSSystem/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── pos/
│       │           ├── app/
│       │           │   └── POSApplication.java
│       │           ├── controller/
│       │           │   └── POSController.java
│       │           ├── model/
│       │           │   ├── Product.java
│       │           │   ├── CartItem.java
│       │           │   └── Transaction.java
│       │           ├── database/
│       │           │   ├── DatabaseConnection.java
│       │           │   └── ProductDAO.java
│       │           └── util/
│       │               └── CurrencyFormatter.java
│       └── resources/
│           ├── fxml/
│           │   └── pos-main.fxml
│           ├── css/
│           │   └── pos-styles.css
│           └── database/
│               └── schema.sql
├── lib/
│   ├── javafx-controls-17.0.2.jar
│   ├── javafx-fxml-17.0.2.jar
│   ├── javafx-base-17.0.2.jar
│   └── postgresql-42.6.0.jar
├── pom.xml (if using Maven)
└── README.md
```

## Setup Instructions

### 1. Create Main Project Folder
Create a folder called `POSSystem` on your desktop or preferred location.

### 2. Create Directory Structure
Inside `POSSystem`, create the following folders:
- `src/main/java/com/pos/app`
- `src/main/java/com/pos/controller`
- `src/main/java/com/pos/model`
- `src/main/java/com/pos/database`
- `src/main/java/com/pos/util`
- `src/main/resources/fxml`
- `src/main/resources/css`
- `src/main/resources/database`
- `lib`

### 3. Required Libraries
Download these JAR files and place them in the `lib` folder:
- JavaFX Controls: javafx-controls-17.0.2.jar
- JavaFX FXML: javafx-fxml-17.0.2.jar
- JavaFX Base: javafx-base-17.0.2.jar
- PostgreSQL Driver: postgresql-42.6.0.jar

### 4. IDE Setup (Recommended: IntelliJ IDEA or Eclipse)
1. Open your IDE
2. Create new Java project
3. Set project location to your `POSSystem` folder
4. Add JAR files from `lib` folder to project classpath

### 5. Database Setup
1. Install PostgreSQL on your computer
2. Create a database called `pos_system`
3. Run the SQL script from `schema.sql`