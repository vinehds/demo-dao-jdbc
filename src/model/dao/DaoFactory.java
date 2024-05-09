package model.dao;

import db.DB;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;

import java.util.List;

public class DaoFactory {

    public static DepartmentDao createDepartmentDao() {
        return new DepartmentDaoJDBC(DB.getConnection());
    }

    public static SellerDao createSellerDao() {
        return new SellerDaoJDBC(DB.getConnection());
    }

}