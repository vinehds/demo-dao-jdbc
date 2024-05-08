package application;

import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class Program {
    public static void main(String[] args){

        Department obj = new Department(1, "Books");
        System.out.println(obj);

        Seller seller = new Seller(21, "bob", "bobo@gmail.com", new Date(), 2500.00, obj);
        System.out.println(seller);
    }
}
