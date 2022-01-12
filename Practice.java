import java.sql.*;
import java.util.Locale;
import java.util.Scanner;

class Practice{
    static int choice;
    public static void main(String[] args) throws Exception {
        String tName;
        String url = "jdbc:mysql://localhost:3306/employee";
        String uname = "root";
        String pass = "";

        Connection con = DriverManager.getConnection(url, uname, pass);
        Scanner sc = new Scanner(System.in);
        table(con);
    }

    public static void table(Connection con) throws Exception {
        Scanner sc = new Scanner(System.in);
        String tName;

        System.out.println("Enter table name: ");
        tName = sc.nextLine();
        tName.toLowerCase();
        //System.out.println(tName);
        if (tName.equals("employee")) {
            employee(tName, con);
        } else if (tName.equals("admin")) {
            admin(tName, con);
        } else if (tName.equals("company")) {
            company(tName, con);
        } else {
            System.out.println("Enter correct table name!!\n");
        }
    }
    public static void company(String tName,Connection con) throws Exception{
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Enter 1 for Creating table");
            System.out.println("Enter 2 for Inserting into table");
            System.out.println("Enter 3 for Updating table values");
            System.out.println("Enter 4 for Fetching results");
            System.out.println("Enter 5 for Deleting table values");
            System.out.println("Enter 6 for Dropping/Deleting table");
            System.out.println("Enter 7 to switch table");
            System.out.println("Enter 8 for Exit");
            System.out.println("Enter your choice:");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter table creation query: ");
                    try {
                        String create = "CREATE TABLE  " + tName + "(id INTEGER not NULL, " +
                                " cname VARCHAR(255), " +
                                " valuation Double, " +
                                " PRIMARY KEY ( id ))";

                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(create);
                        System.out.println("Created table in given database...");
                        stmt.close();
                    } catch (Exception e) {
                        System.out.println("Table not created due to error or it is already exists");
                    }
                    break;

                case 2:
                    int id;
                    String cname;
                    double valuation;

                    System.out.println("Enter Company ID: ");
                    id = sc.nextInt();
                    System.out.println("Enter Company Name: ");
                    cname = sc.next();
                    cname += sc.nextLine();
                    System.out.println("Enter Company Valuation: ");
                    valuation = sc.nextDouble();

                    String query = "insert into " + tName + " values (?,?,?)";
                    PreparedStatement st = con.prepareStatement(query);

                    st.setInt(1, id);
                    st.setString(2, cname);
                    st.setDouble(3, valuation);

                    int count = st.executeUpdate();
                    System.out.println(count + " rows affected");
                    st.close();
                    break;

                case 3:
                    int id2;
                    String name2;
                    double valuation2;

                    System.out.println("Enter Company ID: ");
                    id2 = sc.nextInt();
                    System.out.println("Enter Company Name: ");
                    name2 = sc.next();
                    name2 += sc.nextLine();
                    System.out.println("Enter Company Valuation: ");
                    valuation2 = sc.nextDouble();

                    String query2 = "UPDATE "+ tName + " SET cname=?, valuation=? WHERE id=?";

                    PreparedStatement st2 = con.prepareStatement(query2);
                    st2.setString(1, name2);
                    st2.setDouble(2, valuation2);
                    st2.setInt(3, id2);

                    int rowsUpdated = st2.executeUpdate();
                    if (rowsUpdated > 0) {
                        System.out.println("An existing company details was updated successfully with new details!");
                    }
                    st2.close();
                    break;

                case 4:
                    System.out.println("Enter 1 for particular record by id:");
                    System.out.println("Enter 2 for all record fetch:");
                    int ch = sc.nextInt();

                    if (ch == 1) {
                        System.out.println("Enter Company id: ");
                        int id6 = sc.nextInt();

                        String query3 = "select * from " + tName + " where id= " + id6;
                        Statement st3 = con.createStatement();
                        ResultSet rs = st3.executeQuery(query3);
                        while (rs.next()) {
                            String userData = rs.getInt(1) + " : " + rs.getString(2) + " : " + rs.getDouble(3);
                            System.out.println(userData);
                        }
                        System.out.println();
                        st3.close();
                    } else {
                        String query3 = "select * from " + tName + ";";

                        Statement st3 = con.createStatement();
                        ResultSet rs = st3.executeQuery(query3);
                        while (rs.next()) {
                            String userData = rs.getInt(1) + " : " + rs.getString(2) + " : " + rs.getDouble(3);
                            System.out.println(userData);
                        }
                        st3.close();
                    }
                    break;

                case 5:
                    int id1;
                    System.out.println("Enter Employee id for which you want to delete record");
                    id1 = sc.nextInt();

                    String sql = "DELETE FROM " + tName + " WHERE id=?";

                    PreparedStatement st4 = con.prepareStatement(sql);
                    st4.setInt(1, id1);

                    int rowsDeleted = st4.executeUpdate();
                    if (rowsDeleted > 0) {
                        System.out.println("Company details was deleted successfully!");
                    }
                    st4.close();
                    break;

                case 6:
                    String query5 = "drop table " + tName;
                    Statement stmt2 = con.createStatement();
                    stmt2.executeUpdate(query5);
                    System.out.println("Table deleted in given database...");
                    stmt2.close();
                    break;

                case 7:
                    table(con);
                    break;

                case 8:
                    break;

                default:
                    System.out.println("Enter correct choice!!\n");
            }
        } while (choice != 8);
    }

    public static void admin(String tName,Connection con) throws Exception{
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Enter 1 for Creating table");
            System.out.println("Enter 2 for Inserting into table");
            System.out.println("Enter 3 for Updating table values");
            System.out.println("Enter 4 for Fetching results");
            System.out.println("Enter 5 for Deleting table values");
            System.out.println("Enter 6 for Dropping/Deleting table");
            System.out.println("Enter 7 to switch table");
            System.out.println("Enter 8 for Exit");
            System.out.println("Enter your choice:");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter table creation query: ");
                    try {
                        String create = "CREATE TABLE  " + tName + "(id INTEGER not NULL, " +
                                " password VARCHAR(255), " +
                                " PRIMARY KEY ( id ))";

                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(create);
                        System.out.println("Created table in given database...");
                        stmt.close();
                    } catch (Exception e) {
                        System.out.println("Table not created due to error or it is already exists");
                    }
                    break;

                case 2:
                    int id;
                    String pass;

                    System.out.println("Enter Admin ID: ");
                    id = sc.nextInt();
                    System.out.println("Enter Admin Password: ");
                    pass= sc.next();

                    String query = "insert into " + tName + " values (?,?)";
                    PreparedStatement st = con.prepareStatement(query);

                    st.setInt(1, id);
                    st.setString(2, pass);

                    int count = st.executeUpdate();
                    System.out.println(count + " rows affected");
                    st.close();
                    break;

                case 3:
                    int id2;
                    String pass2;

                    System.out.println("Enter Admin ID: ");
                    id2 = sc.nextInt();
                    System.out.println("Enter Admin Password: ");
                    pass2 = sc.next();

                    String query2 = "UPDATE "+ tName + " SET password=? WHERE id=?";

                    PreparedStatement st2 = con.prepareStatement(query2);
                    st2.setString(1, pass2);
                    st2.setInt(2, id2);

                    int rowsUpdated = st2.executeUpdate();
                    if (rowsUpdated > 0) {
                        System.out.println("An existing company details was updated successfully with new details!");
                    }
                    st2.close();
                    break;

                case 4:
                    System.out.println("Enter 1 for particular record by id:");
                    System.out.println("Enter 2 for all record fetch:");
                    int ch = sc.nextInt();

                    if (ch == 1) {
                        System.out.println("Enter Admin id: ");
                        int id6 = sc.nextInt();

                        String query3 = "select * from " + tName + " where id= " + id6;

                        Statement st3 = con.createStatement();
                        ResultSet rs = st3.executeQuery(query3);
                        while (rs.next()) {
                            String userData = rs.getInt(1) + " : " + rs.getString(2);
                            System.out.println(userData);
                        }
                        System.out.println();
                        st3.close();
                    } else {
                        String query3 = "select * from " + tName + ";";

                        Statement st3 = con.createStatement();
                        ResultSet rs = st3.executeQuery(query3);
                        while (rs.next()) {
                            String userData = rs.getInt(1) + " : " + rs.getString(2);
                            System.out.println(userData);
                        }
                        st3.close();
                    }
                    break;

                case 5:
                    int id1;
                    System.out.println("Enter Admin id for which you want to delete record");
                    id1 = sc.nextInt();

                    String sql = "DELETE FROM " + tName + " WHERE id=?";
                    PreparedStatement st4 = con.prepareStatement(sql);
                    st4.setInt(1, id1);

                    int rowsDeleted = st4.executeUpdate();
                    if (rowsDeleted > 0) {
                        System.out.println("Admin details was deleted successfully!");
                    }
                    st4.close();
                    break;

                case 6:

                    String query5 = "drop table " + tName;
                    Statement stmt2 = con.createStatement();
                    stmt2.executeUpdate(query5);
                    System.out.println("Table deleted in given database...");
                    stmt2.close();
                    break;

                case 7:
                    table(con);
                    break;

                case 8:
                    break;

                default:
                    System.out.println("Enter correct choice!!\n");
            }
        } while (choice != 8);
    }

    public static void employee(String tName,Connection con) throws Exception{
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Enter 1 for Creating table");
            System.out.println("Enter 2 for Inserting into table");
            System.out.println("Enter 3 for Updating table values");
            System.out.println("Enter 4 for Fetching results");
            System.out.println("Enter 5 for Deleting table values");
            System.out.println("Enter 6 for Dropping/Deleting table");
            System.out.println("Enter 7 to switch table");
            System.out.println("Enter 8 for Exit");
            System.out.println("Enter your choice:");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter table creation query: ");
                    try {
                        String create = "CREATE TABLE  " + tName + "(id INTEGER not NULL, " +
                                " name VARCHAR(255), " +
                                " designation VARCHAR(255), " +
                                " salary Double, " +
                                " PRIMARY KEY ( id ))";

                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(create);
                        System.out.println("Created table in given database...");
                        stmt.close();
                    } catch (Exception e) {
                        System.out.println("Table not created due to error or it is already exists");
                    }
                    break;

                case 2:
                    int id;
                    String name;
                    String designation;
                    double salary;

                    System.out.println("Enter Employee ID: ");
                    id = sc.nextInt();
                    System.out.println("Enter Employee Name: ");
                    name = sc.next();
                    System.out.println("Enter Employee Designation: ");
                    designation = sc.next();
                    designation += sc.nextLine();
                    System.out.println("Enter Employee Salary: ");
                    salary = sc.nextDouble();

                    String query = "insert into " + tName + " values (?,?,?,?)";
                    PreparedStatement st = con.prepareStatement(query);
                    st.setInt(1, id);
                    st.setString(2, name);
                    st.setString(3, designation);
                    st.setDouble(4, salary);
                    int count = st.executeUpdate();
                    System.out.println(count + " rows affected");
                    st.close();
                    break;

                case 3:
                    int id2;
                    String name2;
                    String designation2;
                    double salary2;

                    System.out.println("Enter Employee ID: ");
                    id2 = sc.nextInt();
                    System.out.println("Enter Employee Name: ");
                    name2 = sc.next();
                    System.out.println("Enter Employee Designation: ");
                    designation2 = sc.next();
                    System.out.println("Enter Employee Salary: ");
                    salary2 = sc.nextDouble();

                    String query2 = "UPDATE " + tName + " SET name=?, designation=?, salary=? WHERE id=?";
                    PreparedStatement st2 = con.prepareStatement(query2);
                    st2.setString(1, name2);
                    st2.setString(2, designation2);
                    st2.setDouble(3, salary2);
                    st2.setInt(4, id2);

                    int rowsUpdated = st2.executeUpdate();
                    if (rowsUpdated > 0) {
                        System.out.println("An existing user was updated successfully with new details!");
                    }
                    st2.close();
                    break;

                case 4:
                    System.out.println("Enter 1 for particular record by id:");
                    System.out.println("Enter 2 for all record fetch:");
                    int ch = sc.nextInt();

                    if (ch == 1) {
                        System.out.println("Enter Employee id: ");
                        int id6 = sc.nextInt();

                        String query3 = "select * from " + tName + " where id= " + id6;

                        Statement st3 = con.createStatement();
                        ResultSet rs = st3.executeQuery(query3);
                        while (rs.next()) {
                            String userData = rs.getInt(1) + " : " + rs.getString(2) + " : " + rs.getString(3) + " : " + rs.getDouble(4);
                            System.out.println(userData);
                        }
                        System.out.println();
                        st3.close();
                    } else {
                        String query3 = "select * from " + tName + ";";

                        Statement st3 = con.createStatement();
                        ResultSet rs = st3.executeQuery(query3);
                        while (rs.next()) {
                            String userData = rs.getInt(1) + " : " + rs.getString(2) + " : " + rs.getString(3) + " : " + rs.getDouble(4);
                            System.out.println(userData);
                        }
                        st3.close();
                    }
                    break;

                case 5:
                    int id1;
                    System.out.println("Enter Employee id for which you want to delete record");
                    id1 = sc.nextInt();

                    String sql = "DELETE FROM " + tName + " WHERE id=?";

                    PreparedStatement st4 = con.prepareStatement(sql);
                    st4.setInt(1, id1);

                    int rowsDeleted = st4.executeUpdate();
                    if (rowsDeleted > 0) {
                        System.out.println("A user was deleted successfully!");
                    }
                    st4.close();
                    break;

                case 6:
                    String query5 = "drop table " + tName;
                    Statement stmt2 = con.createStatement();
                    stmt2.executeUpdate(query5);
                    System.out.println("Table deleted in given database...");
                    stmt2.close();
                    break;

                case 7:
                    table(con);
                    break;

                case 8:
                    break;

                default:
                    System.out.println("Enter correct choice!!\n");
            }
        } while (choice != 8);
    }
}