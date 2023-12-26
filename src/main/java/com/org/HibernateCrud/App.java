package com.org.HibernateCrud;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class App 
{
    public static void main( String[] args )
    {
        Configuration cfg= new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory sf= cfg.buildSessionFactory();
        Session session= sf.openSession();

        
       // InsertEmployee(session);
       // getAllEmployees(session);
       // getEmployeeById(session);
        //updateEmployee(session);
        deleteEmployee(session);
        
   
    }
    public static void InsertEmployee(Session session) {
        Transaction t = session.beginTransaction();
        Employee emp = new Employee();
        emp.setEmployeeId(2);
        emp.setEmployeeName("suyash");
        emp.setEmail("suyash@gmail.com");
        Date d = new Date();
        
        emp.setDoj(d);
        emp.setSalary(20000.00);
        
        session.persist(emp);
        t.commit();
        session.close();
        System.out.println("employee added");
       
    }
    public static void getAllEmployees(Session session) {
    	List<Employee> employees = session.createQuery("from Employee",Employee.class).list();
    	
    	if(!employees.isEmpty()) {
    		System.out.println(" ");
    		System.out.println("-----------------------------");
    		System.out.println("  ");
    		for(Employee employee:employees) {
    			System.out.println("Employee ID="+employee.getEmployeeId());
    			System.out.println("Employee Name="+employee.getEmployeeName());
    			System.out.println("Employee Email="+employee.getEmail());
    			System.out.println("Employee Date of timing="+employee.getDoj());
    			System.out.println("Employee salary="+employee.getSalary());
    			System.out.println(" ");
        		System.out.println("-----------------------------");
        		System.out.println("  ");
    			
    		}
    	}
    	
    }
    public static void getEmployeeById(Session session) {
    	Scanner sc= new Scanner(System.in);
    	System.out.println("enter the employee id");
    	int id= sc.nextInt();
    	Employee employee=session.get(Employee.class, id);
    	
    	if(employee!=null) {
    		System.out.println(employee);
    		
    	}
    	else {
    		System.out.println("employee not found");
    	}
    	
    }
    @SuppressWarnings("deprecation")
	public static void updateEmployee(Session session) {
    	
    	Scanner sc= new Scanner(System.in);
    	System.out.println("enter the employee id");
    	int id= sc.nextInt();
    	Employee employee=session.get(Employee.class, id);
    	
    	if(employee!=null) {
    		employee.setEmployeeName("raviraj");
    		employee.setEmail("raviraj@gmail.com");
    		employee.setSalary(10000.00);
    		session.beginTransaction();
    		
    		session.update(employee);
    		
    		session.getTransaction().commit();
    		
    		System.out.println("update successfull");
    		
    	}
    	else {
    		System.out.println("employee not found");
    	}
    }
	public static void deleteEmployee(Session session) {
    	Scanner sc= new Scanner(System.in);
    	System.out.println("enter the employee id");
    	int id= sc.nextInt();
    	Employee employee=session.get(Employee.class, id);
    	
    	if(employee!=null) {
    		
    		session.beginTransaction();
    		
    		session.delete(employee);
    		
    		session.getTransaction().commit();
    		
    		System.out.println("delete  successfully");
    		
    	}
    	else {
    		System.out.println("employee not found");
    	}
    	
    }
}
