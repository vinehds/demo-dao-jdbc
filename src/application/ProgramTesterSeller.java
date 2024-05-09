package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ProgramTesterSeller {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

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

        System.out.println("\n TESTE 03: Seller insert");
        Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.00, department);

        System.out.println("\n TESTE 04: Insert seller");
        sellerDao.insert(newSeller);
        System.out.println("Inserted! new id: " + newSeller.getId());

        System.out.println("\n TESTE 05: update Seller");
        seller = sellerDao.findById(1);
        seller.setName("Marta Cry");
        sellerDao.update(seller);
        System.out.println("Done! ");

        System.out.println("\n TESTE 03: Seller delete");
        System.out.println("Enter the id for Delete Teste");
        int id = sc.nextInt();

        sellerDao.deleteById(id);

        sc.close();
    }
}
