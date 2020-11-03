import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class EmpUpdate implements CRUD
{
    static List<Emp_info> employee_list = new ArrayList<>();
    private PreparedStatement p_stmt;
    private static EmpUpdate emp_DBO;
    public EmpUpdate(){
    }
    public static EmpUpdate getInstance(){
        if(emp_DBO == null)
            emp_DBO = new EmpUpdate();
        return emp_DBO;
    }

    @Override
    public List<Emp_info> readData(Connection con) throws CustomException {
    public List<Emp_info> readData() throws CustomException,SQLException {
        JDBCdemo jdbc_con = new JDBCdemo();
        Connection con = jdbc_con.getConnection();
        try{
            String query = "Select * from emppayroll";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("Name");
                String phone = rs.getString("phone_no");
                String address = rs.getString("Address");
                String department = rs.getString("Department");
                Date start = rs.getDate("start");
                char gender = rs.getString("gender").charAt(0);
                double salary = rs.getDouble("salary");
                Emp_info emp = new Emp_info(id,name,phone,address,department,gender,salary,start);
                employee_list.add(emp);
                this.employee_list.add(emp);
            }
        }catch(Exception e){
            throw new CustomException("Read Process Unsuccessful");
        }finally {
            con.close();
        }
        return employee_list;
    }
    @Override
    public void insertData() { }

    @Override
    public void updateData(Connection con, String column, String name, String value) throws CustomException {
    public void updateData(String column, String name, String value) throws CustomException, SQLException {
        JDBCdemo jdbc_con = new JDBCdemo();
        Connection con = jdbc_con.getConnection();
        try{
            PreparedStatement stmt = con.prepareStatement("Update emppayroll set salary = ? where name = ?");
            stmt.setDouble(1,Double.parseDouble(value));
            stmt.setString(2,name);
            stmt.executeUpdate();

            p_stmt = con.prepareStatement("Update employee set salary = ? where name = ?");
            p_stmt.setDouble(1,Double.parseDouble(value));
            p_stmt.setString(2,name);
            p_stmt.executeUpdate();
        }catch(Exception e){
            throw new CustomException("Read Process Unsuccessful");
        }finally {
            if(con != null)
                con.close();
        }
    }

    @Override
    public void deleteData() { }

    public ResultSet getEmployeeDataFromDB(String query) {
        ResultSet rs = null;
        Connection con = null;
        try{
            JDBCdemo jdbc_con = new JDBCdemo();
            con = jdbc_con.getConnection();
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public Emp_info getPayrollDataByName(String name) throws SQLException {
        JDBCdemo jdbc_con = new JDBCdemo();
        Connection con = jdbc_con.getConnection();
        p_stmt = con.prepareStatement("Select * from payroll where name = ?");
        p_stmt.setString(1,name);
        ResultSet rs = p_stmt.executeQuery();
        Emp_info emp = null;
        while(rs.next()){
            int id = rs.getInt("id");
            String phone = rs.getString("phone_no");
            String address = rs.getString("Address");
            String department = rs.getString("Department");
            Date start = rs.getDate("start");
            char gender = rs.getString("gender").charAt(0);
            double salary = rs.getDouble("salary");
            emp = new Emp_info(id,name,phone,address,department,gender,salary,start);
        }
        return emp;
    }

    public ResultSet retrieveEmployeesByDate(String startDate, String endDate){
        String query = "Select * from emppayroll where start date between " + startDate + " and " + endDate;
        ResultSet rs = this.getEmployeeDataFromDB(query);
        return rs;
    }
} 