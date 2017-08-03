/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ComputerAction;
import dao.ComputerDAO;
import dto.ComputerDTO;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author Nups
 */
public class ProgramRun {
         public double checkDouble(String s){
            try{
                double n = Double.parseDouble(s);
                return n;
            }catch(Exception e){
                return -1;
            }
        }
         public int checkInt(String s){
             try{
                 int m = Integer.parseInt(s);
                 return m;
             }catch(Exception e){
                 return -1;
             }
         }
    public static void main(String[] args) {
        
//        DBConnector con = new DBConnector();
//        Statement st = null;
//        ResultSet rs = null;
//        String s = "SELECT * FROM COMPUTER";
//        try {
//            st = con.getCon().createStatement();
//            rs = st.executeQuery(s);
//        } catch (SQLException ex) {
//            Logger.getLogger(ProgramRun.class.getName()).log(Level.SEVERE, null, ex);
//        }
        ComputerDTO dt  = new ComputerDTO();
        ComputerDAO dao  = new ComputerDAO();
        Scanner sc = new Scanner(System.in);
        ComputerAction action = new ComputerAction();
        do{
            System.out.println("CHOICE NUMBER");
            System.out.println("1. ADD");
            System.out.println("2. LIST");
            System.out.println("3. UPDATE");
            System.out.println("4. DELETE");
            System.out.println("5. LIST BY PRICE");
            System.out.println("6. SEARCH");
            System.out.println("7. SEARCH BY NAME");
            System.out.println("8. Exit");
            int choice = sc.nextInt();
            switch(choice){
                case 1:
                    System.out.println("NHAP DU LIEU CHO COMPUTER: ");
                    System.out.println("Nhap code: ");
                    String code = sc.next();
                    dt.setCode(code);
                    System.out.println("Nhap name: ");
                    String name = sc.next();
                    dt.setName(name);
                    System.out.println("Nhap price: ");
                    double price = sc.nextDouble();
                    dt.setPrice(price);
                    System.out.println("Nhap quantity: ");
                    int quantity = sc.nextInt();
                    dt.setQuantity(quantity);
                    action.create(dt);
                    break;
                case 2:
                    System.out.println("LIST ALL:");
                    for(ComputerDTO c: dao.readAll()){
                        System.out.println("----------------------------------");
                        System.out.println(c.getCode());
                        System.out.println(c.getName());
                        System.out.println(c.getPrice());
                        System.out.println(c.getQuantity());
                        System.out.println("----------------------------------");
                    }
                    break;
                case 3:
                    System.out.println("UPDATE PRICE TU COMPUTER DUA VAO CODE: ");
                    System.out.println("Nhap code: ");
                    String code1 = sc.next();
                    dt.setCode(code1);
                    System.out.println("Nhap Price: ");
                    double price1 = sc.nextDouble();
                    dt.setPrice(price1);
                    action.update(dt);
                    break;
                case 4:
                    System.out.println("XOA DU LIEU COMPUTER DUA VAO PRICE: ");
                    System.out.println("Nhap price: ");
                    String code5 = sc.next();
                  dt.setCode(code5);
                    action.delete(code5);
                    break;
                case 5:
                    System.out.println("Nhap Price");
                    double price3 = sc.nextDouble();
                    dt.setPrice(price3);
                    List<ComputerDTO> lst = action.readByPrice();
                    for(ComputerDTO item : lst){
                        System.out.println("----------------------------------");
                        System.out.println(item.getCode());
                        System.out.println(item.getName());
                        System.out.println(item.getPrice());
                        System.out.println(item.getQuantity());
                        System.out.println("----------------------------------");
                    }
                    break;
                case 6:
                    System.out.println("Nhap price 1: ");
                    double price4 = sc.nextDouble();
                    dt.setPrice(price4);
                    System.out.println("Nhap price 2: ");
                    double price5 = sc.nextDouble();
                    dt.setPrice(price5);                  
                    for(ComputerDTO prs: dao.readByPriceBetween(price4, price5)){
                        System.out.println("----------------------------------");
                        System.out.println(prs.getCode());
                        System.out.println(prs.getName());
                        System.out.println(prs.getPrice());
                        System.out.println(prs.getQuantity());
                        System.out.println("----------------------------------");
                    }
                    break;
                case 7:
                    System.out.println("Nhap name: ");
                    String name1 = sc.next();
                    dt.setName(name1);
                    for(ComputerDTO nas: dao.readByName(name1)){
                        System.out.println("----------------------------------");
                        System.out.println(nas.getCode());
                        System.out.println(nas.getName());
                        System.out.println(nas.getPrice());
                        System.out.println(nas.getQuantity());
                        System.out.println("----------------------------------");
                    }
                    break;
                case 8:
                    System.exit(choice);
            }
        }while(true);
    }
}
