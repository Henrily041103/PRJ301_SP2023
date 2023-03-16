/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PHT
 */
public class ToyFacade {

    public List<Toy> select() throws SQLException {
        List<Toy> list = null;
        //Tạo connection để kết nối vào DBMS
        Connection con = DBContext.getConnection();
        //Tạo đối tượng statement
        Statement stm = con.createStatement();
        //Thực thi lệnh SELECT
        ResultSet rs = stm.executeQuery("select * from toy");
        list = new ArrayList<>();
        while (rs.next()) {
            Toy toy = new Toy();
            toy.setId(rs.getString("id"));
            toy.setName(rs.getString("name"));
            toy.setPrice(rs.getDouble("price"));
            toy.setExpDate(rs.getDate("expDate"));
            toy.setBrandId(rs.getString("brand"));
            list.add(toy);
        }
        con.close();
        return list;
    }

    public static List<Toy> getRecords(int start, int total) {
        List<Toy> list = new ArrayList<Toy>();
        try {
            Connection con = DBContext.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from toy");
            ps.setInt(2, start-1);
            ps.setInt(1, total);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Toy toy = new Toy();
                toy.setId(rs.getString("id"));
                toy.setName(rs.getString("name"));
                toy.setPrice(rs.getDouble("price"));
                toy.setExpDate(rs.getDate("expDate"));
                toy.setBrandId(rs.getString("brand"));
                list.add(toy);
            }
            con.close();
            System.out.println(list.size());
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

public int GetNoOfRecords() throws SQLException {
        List<Toy> list = null;
        //Tạo connection để kết nối vào DBMS
        Connection con = DBContext.getConnection();
        //Tạo đối tượng statement
        Statement stm = con.createStatement();
        //Thực thi lệnh SELECT
        ResultSet rs = stm.executeQuery("select * from toy");
        list = new ArrayList<>();
        while (rs.next()) {
            Toy toy = new Toy();
            toy.setId(rs.getString("id"));
            toy.setName(rs.getString("name"));
            toy.setPrice(rs.getDouble("price"));
            toy.setExpDate(rs.getDate("expDate"));
            toy.setBrandId(rs.getString("brand"));
            list.add(toy);
        }
        con.close();
        return list.size();
    }

    public void create(Toy toy) throws SQLException {
        //Tạo connection để kết nối vào DBMS
        Connection con = DBContext.getConnection();
        //Tạo đối tượng PreparedStatement
        PreparedStatement stm = con.prepareStatement("insert toy values(?, ?, ?, ?, ?)");
        stm.setString(1, toy.getId());
        stm.setString(2, toy.getName());
        stm.setDouble(3, toy.getPrice());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        stm.setString(4, sdf.format(toy.getExpDate()));
        stm.setString(5, toy.getBrandId());
        //Thực thi lệnh sql
        int count = stm.executeUpdate();
        //Đóng kết nối
        con.close();
    }

    public Toy read(String id) throws SQLException {
            Toy toy = null;
        //Tạo connection để kết nối vào DBMS
        Connection con = DBContext.getConnection();
        //Tạo đối tượng PreparedStatement
        PreparedStatement stm = con.prepareStatement("select * from toy where id = ?");
        stm.setString(1, id);
        //Thực thi lệnh sql
        ResultSet rs = stm.executeQuery();
        //Load dữ liệu vào đối tượng toy nếu có
        if (rs.next()) {
            toy = new Toy();
            toy.setId(rs.getString("id"));
            toy.setName(rs.getString("name"));
            toy.setPrice(rs.getDouble("price"));
            toy.setExpDate(rs.getDate("expDate"));
            toy.setBrandId(rs.getString("brand"));
        }
        //Đóng kết nối
        con.close();
        return toy;
    }

    public void update(Toy toy) throws SQLException {
        //Tạo connection để kết nối vào DBMS
        Connection con = DBContext.getConnection();
        //Tạo đối tượng PreparedStatement
        PreparedStatement stm = con.prepareStatement("update toy set name = ?, price = ?, expDate = ?, brand = ? where id = ?");
        stm.setString(1, toy.getName());
        stm.setDouble(2, toy.getPrice());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        stm.setString(3, sdf.format(toy.getExpDate()));
        stm.setString(4, toy.getBrandId());
        stm.setString(5, toy.getId());
        //Thực thi lệnh sql
        int count = stm.executeUpdate();
        //Đóng kết nối
        con.close();
    }

    public void delete(String id) throws SQLException {
        //Tạo connection để kết nối vào DBMS
        Connection con = DBContext.getConnection();
        //Tạo đối tượng PreparedStatement
        PreparedStatement stm = con.prepareStatement("delete from toy where id = ?");
        stm.setString(1, id);
        //Thực thi lệnh sql
        int count = stm.executeUpdate();
        con.close();
    }
}
