package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;

public class Program {
    public static void main(String[] args){

        System.out.println("=== TESTE 01: Seller find by Id");
        SellerDao sellerDao = DaoFactory.createSellerDao();
        Seller seller = sellerDao.findById(3);

        System.out.println(seller);

        System.out.println("\n TESTE 02: Sellers by department");
        Department department = new Department(2, null);

        List<Seller> list = sellerDao.findByDepartment(department);

        for(Seller s : list){
            System.out.println(s);
        }

        System.out.println("\n TESTE 03: find all");
        list = sellerDao.findAll();

        for(Seller s : list){
            System.out.println(s);
        }

    }
}
