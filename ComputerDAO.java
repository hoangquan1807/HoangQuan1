/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ComputerDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ultis.DBConnector;

/**
 * 1. Thêm máy tính vào csdl. 2. Xem danh sách máy tính hiện có trong cửa hàng
 * 3. Cập nhật giá trong cửa hàng dựa vào code. 4. Xóa máy tính trong cửa hàng
 * dựa vào giá. 5. Xem danh sách máy tính dựa vào sự tăng dần của giá. 6. Tìm
 * kiếm danh sách các máy tính có giá từ price1 đến price2. Hai giá trị này được
 * nhập vào từ bàn phím. 7. Thoát khỏi chương trình.
 *
 * @author Nups
 */
public class ComputerDAO {

    private final String SQLCREATE = "INSERT INTO COMPUTER VALUES(?,?,?,?)";
    private final String SQLREADALL = "SELECT * FROM COMPUTER";
    private final String SQLUPDATE = "UPDATE COMPUTER SET PRICE = ? WHERE CODE = ?";
    private final String SQLDELETE = "DELETE FROM COMPUTER WHERE PRICE = ?";
    private final String SQLREADBYPRICE = "SELECT * FROM APP.COMPUTER ORDER BY PRICE DESC";
    private final String SQLREADBYPRICE2 = "SELECT * FROM APP.COMPUTER WHERE PRICE BETWEEN PRICE1 AND PRICE 2";
    private final Connection con;

    public ComputerDAO() {
        con = new DBConnector().getCon();
    }

    public ComputerDTO create(ComputerDTO c) {
        try {
            PreparedStatement pr = con.prepareStatement(SQLCREATE, Statement.RETURN_GENERATED_KEYS);
            pr.setString(1, c.getCode());
            pr.setString(2, c.getName());
            pr.setDouble(3, c.getPrice());
            pr.setInt(4, c.getQuantity());
            pr.execute();
            ResultSet rs = pr.getGeneratedKeys();
            int genaratedKey = 0;
            if (rs.next()) {
                genaratedKey = rs.getInt(1);
            }
            return c;
        } catch (SQLException ex) {
            Logger.getLogger(ComputerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public ComputerDTO update(ComputerDTO c) {
        String s = "UPDATE COMPUTER SET PRICE = ? WHERE CODE = ?";
        try {
            PreparedStatement pr = con.prepareStatement(s);
            pr.setDouble(1, c.getPrice());
            pr.setString(2, c.getCode());
            pr.execute();
            return c;
        } catch (SQLException ex) {
            Logger.getLogger(ComputerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<ComputerDTO> readAll() {
        try {
            String s = "SELECT * FROM COMPUTER ORDER BY CODE ASC";
            List<ComputerDTO> list = new ArrayList<ComputerDTO>();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(s);
            if (rs != null) {
                while (rs.next()) {
                    ComputerDTO dt = new ComputerDTO();
                    dt.setCode(rs.getString(1));
                    dt.setName(rs.getString(2));
                    dt.setPrice(rs.getDouble(3));
                    dt.setQuantity(rs.getInt(4));
                    list.add(dt);
                }
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ComputerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean delete(String code) {
        try {
            String s = "DELETE FROM COMPUTER WHERE CODE = ?";

            PreparedStatement st = con.prepareStatement(s);
            st.setString(1, code);
            st.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ComputerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<ComputerDTO> readByPrice() {
        try {
            //ORDER BY PRICE DESC doan nay là sqap xep gia theo giam dan roi
            String s = "SELECT * FROM APP.COMPUTER ORDER BY PRICE DESC";
            List<ComputerDTO> list = new ArrayList<ComputerDTO>();
            PreparedStatement pr = con.prepareStatement(s);       
            ResultSet rs = pr.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    ComputerDTO dt = new ComputerDTO();
                    dt.setCode(rs.getString(1));
                    dt.setName(rs.getString(2));
                    dt.setPrice(rs.getDouble(3));
                    dt.setQuantity(rs.getInt(4));
                    list.add(dt);               
                }
            }
         return list;
        } catch (SQLException ex) {
            Logger.getLogger(ComputerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public List<ComputerDTO> readByPriceBetween(double price1, double price2) {
        try {
            String s = "SELECT * FROM APP.COMPUTER WHERE PRICE BETWEEN ? AND ?";
            List<ComputerDTO> list = new ArrayList<ComputerDTO>();  
            PreparedStatement pr = con.prepareStatement(s); 
            pr.setDouble(1, price1);
            pr.setDouble(2, price2);
            ResultSet rs = pr.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    ComputerDTO dt = new ComputerDTO();
                    dt.setCode(rs.getString(1));
                    dt.setName(rs.getString(2));
                    dt.setPrice(rs.getDouble(3));
                    dt.setQuantity(rs.getInt(4));
                    list.add(dt);               
                }
            }
         return list;
        } catch (SQLException ex) {
            Logger.getLogger(ComputerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public List<ComputerDTO> readByName(String name){
        try {
            String s = "SELECT * FROM APP.COMPUTER WHERE NAME LIKE ?";
            List<ComputerDTO> list = new ArrayList<>();
            PreparedStatement pr = con.prepareStatement(s);
            pr.setString(1, name +'%');
            ResultSet rs = pr.executeQuery();
            if(rs != null){
                while(rs.next()){
                    ComputerDTO dt = new ComputerDTO();
                    dt.setCode(rs.getString(1));
                    dt.setName(rs.getString(2));
                    dt.setPrice(rs.getDouble(3));
                    dt.setQuantity(rs.getInt(4));
                    list.add(dt);
                }
            }
            return list;          
        } catch (SQLException ex) {
            Logger.getLogger(ComputerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
