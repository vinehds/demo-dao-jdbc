package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private  final Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;

        try {

            st = conn.prepareStatement(
                    "INSERT INTO department "
                    + "(Id, Name) VALUES "
                    + "(?, ?)");

            st.setInt(1, obj.getId());
            st.setString(2, obj.getName());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0){
                System.out.println("Done! insert completed!");
            } else {
                System.out.println("Erro inesperado!");
            }

        } catch (Exception e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Department obj) {

        PreparedStatement st = null;

        try {

            st = conn.prepareStatement(
                    "UPDATE department "
                         + "SET Name = ? "
                         + "WHERE Id = ?",
                         Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0){
                System.out.println("Done! Update completed!");
            } else {
                System.out.println("Erro inesperado!");
            }

        } catch (Exception e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "DELETE FROM department WHERE department.id = ?"
            );

            st.setInt(1, id);

            int rowsAfected = st.executeUpdate();

            if (rowsAfected > 0){
                System.out.printf("Done! Delete completed! ");
            }
            else{
                System.out.println("Erro Inesperado: Nenhum departamento com o id foi identificado!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT * FROM department WHERE department.id = ?"
            );

            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()){
                Department dep = new Department();
                dep.setName(rs.getString("Name"));
                dep.setId(id);

                return dep;
            }
            else{
                System.out.println("Erro Inesperado: Nenhum departamento com o id foi identificado!");
                return null;
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    List<Department> listOfDepartments = new ArrayList<>();
    @Override
    public List<Department> findAll() {

        Statement st = null;
        ResultSet rs = null;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(
                    "SELECT * FROM department ");

            while (rs.next()){
                Department dep = new Department();
                dep.setName(rs.getString("Name"));
                dep.setId(rs.getInt("Id"));

                listOfDepartments.add(dep);
            }
            return listOfDepartments;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

        private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));

        return dep;
    }
}
