


import com.mysql.jdbc.jdbc2.optional.PreparedStatementWrapper;
import java.sql.*;
import java.util.Scanner;
public class SRS {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // TODO code application logic here
        
        SqlDao obj = new SqlDao();
        obj.createTableAdmin();
        obj.createTableCourse();
        obj.createTableBranch();
        obj.createTableStudent();
        obj.createTableFees();
        obj.createTableFaculty();
        obj.createTableExams();
       
     
    }       
        

        
}

class SqlDao {

    Connection con = null;

    public SqlDao() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/srs", "root", "dolly");
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
    }
    
    public boolean createTableAdmin() throws SQLException{
        
        
        String sql="CREATE TABLE IF NOT EXISTS ADMIN\n"
                + "(NAME VARCHAR(15) NOT NULL,\n"
                + "USER_NAME VARCHAR(15),\n"
                + "PASSWORD VARCHAR(15) NOT NULL,\n"
                + "CONSTRAINT PF_UN PRIMARY KEY(USER_NAME));";
        
        Statement st = con.createStatement();
        st.execute(sql);
        
        return true;
    }

public boolean createTableCourse() throws SQLException {
        String sql2 = "CREATE TABLE IF NOT EXISTS COURSE(\n"
                + "COURSE_ID INT(2)NOT NULL,\n"
                + "COURSE_NAME VARCHAR(9) NOT NULL,\n"
                + "CONSTRAINT PK_L PRIMARY KEY(COURSE_ID)"
                + ");";
        Statement st2 = con.createStatement();
        st2.execute(sql2);
        // con.close();
        return true;

    }

    public boolean createTableBranch() throws SQLException {
        String sql3 = "CREATE TABLE IF NOT EXISTS BRANCH(\n"
                + "COURSE_ID INT(2) NOT NULL,\n"
                + "BRANCH_ID INT(3),\n"
                + "BRANCH_NAME VARCHAR(27) NOT NULL,\n"
                + "HOD VARCHAR(51) NOT NULL ,\n"
                + "CONTACT_NO INT(10) NOT NULL,\n"
                + "CONSTRAINT PK_N PRIMARY KEY(BRANCH_ID),\n"
                + "CONSTRAINT FK_k FOREIGN KEY(COURSE_ID)REFERENCES COURSE(COURSE_ID) ON DELETE CASCADE"
                + ");";
        Statement sto = con.createStatement();
        sto.execute(sql3);
        // con.close();
        return true;

    }

    public boolean createTableStudent() throws SQLException {

        String sql56 = "CREATE TABLE IF NOT EXISTS STUDENT(\n"
                + "USN VARCHAR(10) NOT NULL,\n"
                + "S_NAME VARCHAR(51) NOT NULL,\n"
                + "GENDER CHAR(1) NOT NULL,\n"
                + "DOB DATE NOT NULL,\n"
                + "ADDRESS VARCHAR(99) NOT NULL,\n"
                + "S_PHONE INT(10) NOT NULL,\n"
                + "PARENT_NAME VARCHAR(51) NOT NULL,\n"
                + "P_PHONE INT(10) NOT NULL,\n"
                + "GUARDIAN_NAME VARCHAR(51),\n"
                + "G_PHONE INT(10) NOT NULL,\n"
                + "BRANCH_ID INT(3) NOT NULL,\n"
                + "COURSE_ID INT(3) NOT NULL,\n"
                + "AADHAR_NO INT(12) NOT NULL,\n"
                + "STAY_TYPE VARCHAR(9) NOT NULL,\n"
                + "AGE INT(2) NOT NULL,\n"
                + "YEAR_OF_JOIN INT(4) NOT NULL,\n"
                + "PRESENT_SEM INT(1) NOT NULL,\n"
                + "CATEGORY_OF_EXAM VARCHAR(20) NOT NULL,\n"
                + "CET_COMEDK_OTHER_RANK INT(8) NOT NULL,\n"
                + "PUC FLOAT(4) NOT NULL,\n"
                + "TENTH FLOAT(4) NOT NULL,\n"
                + "CONSTRAINT PK_P PRIMARY KEY(USN),\n"
                + " CONSTRAINT FK_I FOREIGN KEY(BRANCH_ID) REFERENCES BRANCH(BRANCH_ID)ON DELETE CASCADE,\n"
                + "CONSTRAINT FK_C FOREIGN KEY(COURSE_ID) REFERENCES COURSE(COURSE_ID)ON DELETE CASCADE\n"
                + ");";
        Statement stl = con.createStatement();
        stl.execute(sql56);
        //con.close();
        return true;
    }

    public int createTableFees() throws SQLException {
        String sql4 = "CREATE TABLE IF NOT EXISTS FEES(\n"
                + "RECEIPT_ID INT(9),\n"
                + "USN VARCHAR(10),\n"
                + "TOTAL_AMTPAID INT(9) NOT NULL,\n"
                + "PAID_BY VARCHAR(51) NOT NULL,\n"
                + "DUE_AMT INT(9) NOT NULL,\n"
                + "CONSTRAINT PK_Ok PRIMARY KEY(RECEIPT_ID),\n"
                + "CONSTRAINT FK_Kk FOREIGN KEY(USN) REFERENCES STUDENT(USN) ON DELETE CASCADE\n"
                + ");";
        PreparedStatement st4 = con.prepareStatement(sql4);

        //con.close();
        return st4.executeUpdate();

    }

    public boolean createTableFaculty() throws SQLException {
        String sql5 = "CREATE TABLE IF NOT EXISTS FACULTY\n"
                + "(FACULTY_ID INT(2)NOT NULL,\n"
                + "FACULTY_NAME INT(2)NOT NULL,\n"
                + "COURSE_ID INT(2)NOT NULL,\n"
                + "BRANCH_ID INT(3)NOT NULL,\n"
                + "FACULTY_SALARY INT(9)NOT NULL,\n"
                + "QUALIFICATION VARCHAR(9) NOT NULL,\n"
                + "PHONE_NO INT(10) NOT NULL,\n"
                + "ADDRESS VARCHAR(90) NOT NULL,\n"
                + "CONSTRAINT PK_N FOREIGN KEY(BRANCH_ID)REFERENCES BRANCH(BRANCH_ID)ON DELETE CASCADE\n "
                + ");";
        Statement st5 = con.createStatement();
        st5.execute(sql5);
        //con.close();
        return true;
    }

    public boolean createTableExams() throws SQLException {
        String sql6 = "CREATE TABLE IF NOT EXISTS EXAMS\n"
                + "(BRANCH_ID INT(3)NOT NULL,\n"
                + "USN VARCHAR(10),\n"
                + "SGPA1 FLOAT(4),\n"
                + "SGPA2 FLOAT(4),\n"
                + "SGPA3 FLOAT(4),\n"
                + "SGPA4 FLOAT(4),\n"
                + "SGPA5 FLOAT(4),\n"
                + "SGPA6 FLOAT(4),\n"
                + "SGPA7 FLOAT(4),\n"
                + "SGPA8 FLOAT(4),\n"
                + "CGPA FLOAT(4),\n"
                + "CONSTRAINT FK_N FOREIGN KEY(USN)REFERENCES STUDENT(USN) ON DELETE CASCADE\n "
                + ");";
        Statement st6 = con.createStatement();
        st6.execute(sql6);
        //con.close();
        return true;
    }    
    
   public int insertAdmin(String name,String uname ,String pass) throws SQLException{
       
       
       String sql = "INSERT INTO ADMIN VALUES(?,?,?)";
       
       PreparedStatement ps = con.prepareStatement(sql);
       
       ps.setString(1, name);
       ps.setString(2, uname);
       ps.setString(3, pass);
       
       int n=ps.executeUpdate();
       
       return n;
   } 
   
    
}

    

