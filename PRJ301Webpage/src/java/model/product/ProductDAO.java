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
import java.util.HashMap;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author ACER NITRO FPT
 */
public class ProductDAO {

    public List<ProductDTO> select() throws SQLException {
        List<ProductDTO> list = null;
        Connection con = DBUtils.getConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select * from product");
        list = new ArrayList<>();
        while (rs.next()) {
            ProductDTO product = new ProductDTO();
            product.setId(rs.getString("ProId"));
            product.setBrand(rs.getString("ProBrand"));
            product.setType(rs.getString("ProType"));
            product.setPrice(rs.getDouble("price"));
            product.setSale(rs.getInt("sale"));
            product.setStock(rs.getInt("stock"));
            product.setAgeGroup(rs.getString("ageGroup"));
            product.setSize(rs.getString("size"));
            product.setColor(rs.getString("color"));
            list.add(product);
        }
        con.close();
        return list;
    }

    public int GetNoOfRecords() throws SQLException {
        List<ProductDTO> list = null;
        Connection con = DBUtils.getConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select * from product");
        list = new ArrayList<>();
        while (rs.next()) {
            ProductDTO product = new ProductDTO();
            product.setId(rs.getString("ProId"));
            product.setBrand(rs.getString("ProBrand"));
            product.setType(rs.getString("ProType"));
            product.setPrice(rs.getDouble("price"));
            product.setSale(rs.getInt("sale"));
            product.setStock(rs.getInt("stock"));
            product.setAgeGroup(rs.getString("ageGroup"));
            product.setSize(rs.getString("size"));
            product.setColor(rs.getString("color"));
            list.add(product);
        }
        con.close();
        return list.size();
    }

    public static List<ProductDTO> getRecords(int start, int total) {
        List<ProductDTO> list = new ArrayList<ProductDTO>();
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from product");
            ps.setInt(2, start - 1);
            ps.setInt(1, total);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductDTO product = new ProductDTO();
                product.setId(rs.getString("ProId"));
                product.setBrand(rs.getString("ProBrand"));
                product.setType(rs.getString("ProType"));
                product.setPrice(rs.getDouble("price"));
                product.setSale(rs.getInt("sale"));
                product.setStock(rs.getInt("stock"));
                product.setAgeGroup(rs.getString("ageGroup"));
                product.setSize(rs.getString("size"));
                product.setColor(rs.getString("color"));
                list.add(product);
            }
            con.close();
            System.out.println(list.size());
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void create(ProductDTO product) throws SQLException {
        //Tạo connection để kết nối vào DBMS
        Connection con = DBUtils.getConnection();
        //Tạo đối tượng PreparedStatement
        PreparedStatement stm = con.prepareStatement("insert product values(?, ?, ?, ?, ?, ?, ? ,? ,?)");

        stm.setString(1, product.getId());
        stm.setString(2, product.getBrand());
        stm.setString(3, product.getType());
        stm.setDouble(4, product.getPrice());
        stm.setInt(5, product.getSale());
        stm.setInt(6, product.getStock());
        stm.setString(7, product.getAgeGroup());
        stm.setString(8, product.getSize());
        stm.setString(9, product.getColor());
        int count = stm.executeUpdate();
        con.close();
    }

    public ProductDTO read(String id) throws SQLException {
        ProductDTO product = null;
        //Tạo connection để kết nối vào DBMS
        Connection con = DBUtils.getConnection();
        //Tạo đối tượng PreparedStatement
        PreparedStatement stm = con.prepareStatement("select * from product where id = ?");
        stm.setString(1, id);
        //Thực thi lệnh sql
        ResultSet rs = stm.executeQuery();
        //Load dữ liệu vào đối tượng toy nếu có
        if (rs.next()) {
            product.setId(rs.getString("ProId"));
            product.setBrand(rs.getString("ProBrand"));
            product.setType(rs.getString("ProType"));
            product.setPrice(rs.getDouble("price"));
            product.setSale(rs.getInt("sale"));
            product.setStock(rs.getInt("stock"));
            product.setAgeGroup(rs.getString("ageGroup"));
            product.setSize(rs.getString("size"));
            product.setColor(rs.getString("color"));
        }
        con.close();
        return product;
    }

    public void update(ProductDTO product) throws SQLException {
        //Tạo connection để kết nối vào DBMS
        Connection con = DBUtils.getConnection();
        //Tạo đối tượng PreparedStatement
        PreparedStatement stm = con.prepareStatement("update product set ProBrand = ?, ProType = ?, price = ?, sale = ?, stock = ?, ageGroup = ?, size = ?, color = ? where ProID = ?");
        stm.setString(1, product.getBrand());
        stm.setString(2, product.getType());
        stm.setDouble(3, product.getPrice());
        stm.setInt(4, product.getSale());
        stm.setInt(5, product.getStock());
        stm.setString(6, product.getAgeGroup());
        stm.setString(7, product.getSize());
        stm.setString(8, product.getColor());
        stm.setString(9, product.getId());
        //Thực thi lệnh sql
        int count = stm.executeUpdate();
        //Đóng kết nối
        con.close();
    }

    public void delete(String id) throws SQLException {
        //Tạo connection để kết nối vào DBMS
        Connection con = DBUtils.getConnection();
        //Tạo đối tượng PreparedStatement
        PreparedStatement stm = con.prepareStatement("delete from product where ProId = ?");
        stm.setString(1, id);
        //Thực thi lệnh sql
        int count = stm.executeUpdate();
        con.close();
    }

    public void lowerStock(HashMap<ProductDTO, Integer> cart) throws SQLException {
        Connection con = DBUtils.getConnection();
        for (ProductDTO i : cart.keySet()) {            
            PreparedStatement stm = con.prepareStatement("update product set stock = ? where ProID = ?");
            stm.setInt(1, (i.getStock() - cart.get(i)));
            stm.setString(2, i.getId());
            int count = stm.executeUpdate();
        }

        con.close();
    }
}
