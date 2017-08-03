/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ComputerDAO;
import dto.ComputerDTO;
import java.util.List;

/**
 *
 * @author Nups
 */
public class ComputerAction {
    ComputerDAO dao = null;

    public ComputerAction() {
        dao = new ComputerDAO();
    }
    public ComputerDTO create(ComputerDTO c){
        return dao.create(c);
    }
    public ComputerDTO update(ComputerDTO c){
        return dao.update(c);
    }
    public List<ComputerDTO> readAll(){
        return dao.readAll();
    }
    public boolean delete(String code){
        return dao.delete(code);
    }
    public List<ComputerDTO> readByPrice(){
        return dao.readByPrice();
    }
    public List<ComputerDTO> readByPriceBetween(double price1, double price2){
       return dao.readByPriceBetween(price1, price2);
    }
    public List<ComputerDTO> readByName(String name){
        return dao.readByName(name);
    }
}
