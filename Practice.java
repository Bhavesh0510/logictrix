import java.sql.*;
import java.util.Scanner;

class Practice{
    public static void main(String[] args) throws Exception{
        int choice=0;
        String tName = "";
        String url = "jdbc:mysql://localhost:3306/employee";
        String uname = "root";
        String pass = "";

        Connection con = DriverManager.getConnection(url,uname,pass);

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter table name");
        tName = sc.nextLine();

        do{
            System.out.println("Enter 1 for Creating table");
            System.out.println("Enter 2 for Inserting into table");
            System.out.println("Enter 3 for Updating table values");
            System.out.println("Enter 4 for Fetching results");
            System.out.println("Enter 5 for Deleting table values");
            System.out.println("Enter 6 for Dropping/Deleting table");
            System.out.println("Enter 7 for Exit");
            System.out.println("Enter your choice:");
            choice = sc.nextInt();


            switch (choice) {
                case 1:
                    System.out.println("Enter table creation query: ");
                    String create = "CREATE TABLE  " + tName + "(Eid INTEGER not NULL, " +
                            " name VARCHAR(255), " +
                            " designation VARCHAR(255), " +
                            " salary FLOAT, " +
                            " PRIMARY KEY ( id ))";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(create);
                    System.out.println("Created table in given database...");
                    stmt.close();
                    break;

                case 2:
                    int id;
                    String name;
                    String designation;
                    float salary;

                    System.out.println("Enter Employee ID: ");
                    id = sc.nextInt();
                    System.out.println("Enter Employee Name: ");
                    name = sc.next();
                    System.out.println("Enter Employee Designation: ");
                    designation = sc.next();
                    System.out.println("Enter Employee Salary: ");
                    salary = sc.nextFloat();

                    String query = "insert into " + tName + " values (?,?,?,?)";
                    PreparedStatement st = con.prepareStatement(query);

                    st.setInt(1,id);
                    st.setString(2, name);
                    st.setString(3, designation);
                    st.setFloat(4, salary);

                    int count = st.executeUpdate();
                    System.out.println(count + " rows affected");
                    st.close();
                    break;

                case 3:
                    int id2;
                    String name2;
                    String designation2;
                    float salary2;

                    System.out.println("Enter Employee ID: ");
                    id2 = sc.nextInt();
                    System.out.println("Enter Employee Name: ");
                    name2 = sc.next();
                    System.out.println("Enter Employee Designation: ");
                    designation2 = sc.next();
                    System.out.println("Enter Employee Salary: ");
                    salary2 = sc.nextFloat();

                    String query2 = "UPDATE Users SET name=?, designation=?, salary=? WHERE id=?";

                    PreparedStatement st2 = con.prepareStatement(query2);
                    st2.setString(1, name2);
                    st2.setString(2, designation2);
                    st2.setFloat(3, salary2);
                    st2.setInt(4, id2);

                    int rowsUpdated = st2.executeUpdate();
                    if (rowsUpdated > 0) {
                        System.out.println("An existing user was updated successfully with new details!");
                    }
                    st2.close();
                    break;

                case 4:
                    String query3 = "select * from " + tName + " internship";

                    Statement st3 = con.createStatement();
                    ResultSet rs = st3.executeQuery(query3);
                    while(rs.next()) {
                        String userData = rs.getInt(1) + " : " + rs.getString(2) +  " : " + rs.getString(3) +  " : " + rs.getFloat(4);
                        System.out.println(userData);
                    }
                    st3.close();

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
                    break;
            }
        }while (choice!=7);
    }
}