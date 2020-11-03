
public class EmployeeOperations {
    public void updateEmployeeObject(String name, String value){
        for(Emp_info e: EmpUpdate.employee_list){
            if(e.getName().equals(name)) {
                e.setSalary(Double.parseDouble(value));
                System.out.println(e.getSalary());
            }
        }
    }

    public Emp_info getEmployeeDataFromObject(String name){
        Emp_info emp = null;
        for(Emp_info e: EmpUpdate.employee_list){
            if(e.getName().equals(name))
                emp = e;
        }
        return emp;
    }
} 


