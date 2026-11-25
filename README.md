# Digital Voting System

A desktop voting application written in **Java (Swing)** with a **MySQL** database. Registered voters log in with their National ID (NID) and password and cast one vote on a digital ballot. An administrator can log in to view the results and the winner.

The project was built as a 3rd-semester university project in NetBeans.

> This is a student project for learning purposes. It is not affiliated with the Election Commission of Bangladesh or with any political party. The party symbols in `Election/` belong to their respective owners.

---

## Features

- **Voter login** with NID and password, checked against the database
- **One vote per voter:** a voter who has already voted cannot log in again
- **Digital ballot** with four candidates and their election symbols, plus an "Are you sure?" confirmation before the vote is recorded
- **Change password** option for voters
- **Admin login** with separate admin accounts
- **Election results:** votes per candidate, total votes counted, and a pop-up naming the winner, or saying the result is a tie

---

## How it works

```
                    ┌──────────── Front (Welcome screen) ────────────┐
                    │                                                 │
                 [Voter]                                           [Admin]
                    │                                                 │
              Login (NID + password)                     Admin_Login (NID + password)
              checks table `info`                        checks table `info_2`
              and that Vote = 0                                       │
                    │                                            Admin_Page
              sets Vote = 1                              ┌────────────┴────────────┐
                    │                             [Election Result]       [Stop the Process]
          Password_Change ("User Choice")         reads table `voting`,       exits the app
          ┌─────────┴──────────┐                  shows counts + winner
       [Vote]          [Change Password]
          │              updates password,
       Ballot            resets Vote = 0, exits
  confirm → adds 1 to
  Can1…Can4 in `voting`, exits
```

| Class | Role |
|-------|------|
| `DVM` | Entry point (`main`); opens the welcome screen |
| `Front` | Welcome screen with **Voter** and **Admin** buttons |
| `Login` | Voter login; checks NID, password and whether the voter has already voted |
| `Password_Change` | "User Choice" window: go to the ballot or change the password |
| `Ballot` | Ballot paper with four candidates; records one vote and closes |
| `Admin_Login` | Admin login against the admin table |
| `Admin_Page` | Shows results and the winner, or stops the program |

---

## Project structure

```
Digital-Voting-System/
├── src/dvm/            Java source code
├── Election/           Candidate symbols and Election Commission logo
├── dist/
│   ├── DVM.jar         Pre-built runnable JAR
│   └── lib/            MySQL Connector/J 5.1.13 and image copies
├── build/              Compiled classes (NetBeans build output)
├── nbproject/          NetBeans project settings
├── build.xml           Ant build script
└── manifest.mf
```

---

## Requirements

- **Java 8** or later (the project targets Java 1.8)
- **MySQL** or **MariaDB** (for example, through [XAMPP](https://www.apachefriends.org/))
- **MySQL Connector/J**, included as `dist/lib/mysql-connector-java-5.1.13-bin.jar`
- **NetBeans** (optional), to open and build the project

> **Note:** The bundled Connector/J 5.1.13 works with MySQL 5.x and MariaDB. To use MySQL 8, download a current [Connector/J](https://dev.mysql.com/downloads/connector/j/) and add it to the project's libraries.

---

## Database setup

The app connects to `jdbc:mysql://localhost:3306/evm` as user **`root`** with an **empty password** (the XAMPP default). The repository doesn't include a database script, so create the database with the SQL below. It matches the tables and columns the code uses.

```sql
CREATE DATABASE evm;
USE evm;

-- Voters: column order matters (NID, password, Vote)
CREATE TABLE info (
    NID      VARCHAR(20) PRIMARY KEY,
    password VARCHAR(50) NOT NULL,
    Vote     INT NOT NULL DEFAULT 0      -- 0 = not voted yet, 1 = voted
);

-- Admins: column order matters (NID, password)
CREATE TABLE info_2 (
    NID      VARCHAR(20) PRIMARY KEY,
    password VARCHAR(50) NOT NULL
);

-- Vote counts: exactly ONE row, one column per candidate
CREATE TABLE voting (
    Can1 INT NOT NULL DEFAULT 0,   -- Abdul Kasem
    Can2 INT NOT NULL DEFAULT 0,   -- M.A. Aziz
    Can3 INT NOT NULL DEFAULT 0,   -- Abdul Bari
    Can4 INT NOT NULL DEFAULT 0    -- Md. Azad
);
INSERT INTO voting VALUES (0, 0, 0, 0);

-- Sample accounts
INSERT INTO info   VALUES ('1001', 'voter123', 0), ('1002', 'voter456', 0);
INSERT INTO info_2 VALUES ('9001', 'admin123');
```

The code reads columns by position, so keep them in the order shown. NIDs should be numbers, because the code puts them into SQL queries without quotes.

---

## Running the application

### Option 1: NetBeans

1. Start MySQL and create the database as described in [Database setup](#database-setup).
2. Open the project folder in NetBeans (**File → Open Project**).
3. If the MySQL connector shows as missing, right-click **Libraries → Add JAR/Folder** and choose `dist/lib/mysql-connector-java-5.1.13-bin.jar`.
4. Run the project (**F6**). The main class is `dvm.DVM`.

### Option 2: Pre-built JAR

```bash
cd dist
java -jar DVM.jar
```

Keep the `lib/` folder next to `DVM.jar`, because the JAR loads the MySQL connector from there.

### Images

The image paths are written into the code as absolute Windows paths, for example:

```java
new ImageIcon("D:\\3rd Semester\\Project\\DVM\\Election\\jasod.jpg")
```

The app still runs if those paths don't exist, but the logo and candidate symbols won't appear. To fix this, either put the project at `D:\3rd Semester\Project\DVM`, or change the paths in `Front.java`, `Ballot.java` and `Admin_Page.java` to relative ones (e.g. `"Election/jasod.jpg"`) and run the app from the project folder.

---

## Usage

**Voter**

1. Click **Voter** on the welcome screen.
2. Enter your NID and password, then click **Login**.
3. Choose **Vote**, click **Vote** under your candidate, and confirm. The vote is saved and the app closes.

**Admin**

1. Click **Admin** on the welcome screen and log in with an admin NID and password.
2. Click **Election Result** to see each candidate's votes and the winner.
3. Click **Stop the Process** to close the application.

To start a new election, reset the counts and voter flags:

```sql
UPDATE voting SET Can1 = 0, Can2 = 0, Can3 = 0, Can4 = 0;
UPDATE info SET Vote = 0;
```

---

## Configuration

The settings below are written directly in each class (`Login`, `Admin_Login`, `Admin_Page`, `Ballot`, `Password_Change`), so change them in every file:

| Setting | Current value |
|---------|---------------|
| JDBC driver | `com.mysql.jdbc.Driver` |
| Database URL | `jdbc:mysql://localhost:3306/evm` |
| DB user / password | `root` / *(empty)* |

The candidate names, the number of candidates (4), and the "Total Voter: 59" count on the results screen are also written directly in the code.

---

## Author

**Utsha Das**
