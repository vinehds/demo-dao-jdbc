package application;

import db.DB;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.List;
import java.util.Scanner;

public class ProgramTesterDepartment {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao(); //New DepartmentDao

        //findAll
        List<Department> list = departmentDao.findAll();

        for (Department d : list){
            System.out.println(d);
        }

        //findById
        Department dep = departmentDao.findById(2);
        System.out.println("\n" + dep);

        //insert
        departmentDao.insert(new Department(7, "Jogos"));

        //deleteById
        System.out.print("\nId for delete: ");
        departmentDao.deleteById(sc.nextInt());

        //update
        Department depTester = new Department(1, "Computers");
        departmentDao.update(depTester);



        sc.close();
        DB.closeConnection();
    }
}
