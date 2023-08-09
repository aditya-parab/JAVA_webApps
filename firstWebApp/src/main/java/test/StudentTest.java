package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.neebalgurukul.model.Student;
import com.neebalgurukal.exceptions.UserAlreadyExistsException;
import com.neebalgurukul.dao.StudentDao;
import com.neebalgurukul.exceptions.UserNotFoundException;


public class StudentTest {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		StudentDao dao = new StudentDao();
		List<Student> studentsList = new ArrayList<Student>();
		
		try {
			
//			studentsList =  dao.getStudentDetails();
//			for(Student obj : studentsList) {
//				System.out.println(obj);
//			}
			
//			Student obj2 = dao.getStudent(1);
//			System.out.println(obj2);
			
//			System.out.println(dao.insertRecord("aditya","aditya@gmail.com", "history", 90));
System.out.println(dao.updateCourse("CCC@gmail.com", "superpowers2"));
		}
		
		catch(UserAlreadyExistsException e) {
			System.out.println(e);
		}
		catch(UserNotFoundException e) {
			System.out.println(e);
		}
		catch (SQLException e) {
			System.out.println("SQL Exception: "+e.getMessage());
			e.printStackTrace();
		}

	}

}
