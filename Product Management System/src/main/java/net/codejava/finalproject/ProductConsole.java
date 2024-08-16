package net.codejava.finalproject;

import java.util.Scanner;
public class ProductConsole {
     private ProductManager pm;
     private ProductIO io;
     
public ProductConsole() {
        this.io = new ProductIO();
        this.pm = new ProductManager(io.load());
    }
       private int menu() {
        System.out.println("-----PRODUCT MENU-----");
        System.out.println("1. Add product        |");
        System.out.println("2. Show all product   |");
        System.out.println("3. Remove product     |");
        System.out.println("4. Exit               |");
        System.out.println("----------------------");
        System.out.println("Enter your choice: ");
        int choice = readInt(1, 4);
        return choice;
    }
      public void start() {
         while (true) {
            int choice = menu();
            switch (choice) {  
                case 1:
                    addProduct();
                    break;
                case 2: 
                    showAll();
                    break;
                case 3:
                    removeProduct();
                    break;
                case 4:
                    System.out.println("++++++++++++++++++++++THANK YOU!++++++++++++++++++++++++++++");
                    System.exit(0);
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
    private int readInt(int min, int max) {
        int choice;
        while (true) {
            try {
                Scanner sc = new Scanner (System.in);
                choice = Integer.parseInt(sc.nextLine());
                if (choice >= min && choice <= max) {
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return choice;
    }
    private float readFloat(int min, float max) {
        float price;
        while (true) {
            try {
                Scanner sc = new Scanner (System.in);
                price = Float.parseFloat(sc.nextLine());
                if (price >= min && price <= max) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Invalid value. Try to enter a float value");
            }
        }
        return price;
    }

    private void addProduct() {
         Scanner sc = new Scanner (System.in);
        System.out.println("Enter product ID: ");
        int id = readInt(0, Integer.MAX_VALUE);
        System.out.println("Enter product name: ");
        String name = sc.nextLine();
        System.out.println("Enter product price: ");
        float price = readFloat(0, Float.MAX_VALUE);
        Product p = new Product(id, name, price);
        this.pm.addProduct(p);
        this.io.save(pm.getListOfProduct());
    }

    private void showAll() {
        System.out.println("===========================All PRODUCTS==============================");
        System.out.println("ID\t\t\tNAME\t\t\tPRICE");
        for (int i = 0; i < this.pm.count(); i++) {
            Product p = this.pm.getProduct(i);
            System.out.println(p.getId()+"\t\t\t"+p.getName()+"\t\t\t"+p.getPrice());
        }
        System.out.println("=====================================================================");
    }

    private void removeProduct() {
        System.out.println("Enter ID of product:");
        int id = readInt(0, Integer.MAX_VALUE);
        boolean result = this.pm.removeProduct(id);
        if(result){
            System.out.println("Product was removed!");
        }else{
            System.out.println("Product not found!");
        }
    }
 
}
