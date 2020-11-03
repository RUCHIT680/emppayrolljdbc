import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CRUD {
    public List<Emp_info> readData(Connection connection) throws CustomException;
    public List<Emp_info> readData() throws CustomException, SQLException;

    public void insertData();

    public void updateData(Connection connection, String column, String name, String value) throws CustomException;
    public void updateData(String column, String name, String value) throws CustomException, SQLException;

    public void deleteData();
}
class CustomException extends Exception{
    public CustomException(String message){
        super(message);
    }
}
class EmployeePayrollServiceMain {
    public static void main(String[] args) throws CustomException {
        System.out.println("Welcome to The Employee Payroll Service Database Program");
        // UC1- creating database and getting connection
        JDBCdemo jdbc_con = new JDBCdemo();
        Connection con = jdbc_con.getConnection();

        // UC2- retrieve emp data from database
        EmpUpdate emp_op = new EmpUpdate();
        emp_op.readData(con);
        emp_op.readData();
    }
}