package toystopinventorymanagementsystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import java.io.*;
import java.lang.reflect.Array;
import java.util.Scanner;
/**
 *
 * @author Fahad Satti
 */
public class Toystopinventorymanagementsystem implements java.io.Serializable {
    ToyStopService tsService = new ToyStopService();
    public void init(){
        
        tsService.initEmployees();
        tsService.initStores();
        tsService.initToys();
        System.out.println("Init complete");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        Toystopinventorymanagementsystem tsims = new Toystopinventorymanagementsystem();
        Toy newtoy = new Toy();
        tsims.init();
        
        
        Scanner scanner = new Scanner(System.in);
        int days = 60;
        short user_response;
        tsims.Serialize();
        tsims.Deserialize();
        for (int i = 0; i < days; i++){
            tsims.Deserialize();
            if (days == 7 || days == 14 || days == 21 || days == 28 || days == 35 || days == 42 || days == 49 || days == 56){
                tsims.add100toys();
            }
            tsims.showMenu();
            user_response = scanner.nextShort( );
            if(user_response == 1){
                tsims.printAll();
                
            }
            else if(user_response == 2){
                if (days >= 180){
                    tsims.addnewstore();
                    
                }
                else{
                    System.out.println("Cannot add a new store right now");
                    
                }
            }
            else if (user_response == 3){
                tsims.addnewemp();
                
            }
            else if(user_response == 4){
                tsims.addanewtoy(newtoy);
            }
            else if(user_response == 5){
                tsims.Serialize();
            }
            
            
            
        }
        //load previous data
        //tsims.loadData();
        
        //tsims.showMenu();
        //tsims.printAll();
        
    }
    
    

    private void loadData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void showMenu() {
        System.out.println("Welcome to Toy Stop Inventory Management System");
        System.out.println("Enter 1 to show all data");
        System.out.println("Enter 2 to add a new Store");
        System.out.println("Enter 3 to add a new Employee");
        System.out.println("Enter 4 to add a new Toy");
        System.out.println("Enter 0 to save state");
        
        
    }
    
    private void addnewstore(){
        tsService.addStore();
    }

    private void printAll() {
        System.out.println(this.tsService.stores);
    }
    
    private void addnewemp() {
        tsService.addEmployee();
    }
    
    private void addanewtoy(Toy newToy){
        tsService.addnewtoy(newToy);
    }
    
    private void add100toys(){
       tsService.init100Toys();
    }
    
    private void remove100toys(){
        
    }
    //private void checkstores(){
        
      //  for (int j = 0; j < Array.getLength(this.tsService.stores);j++){
        //    if (tsService.noofemp() == 0){
          //      for(int k = 0; k < Array.getLength(this.tsService.stores);k++ ){
            //        if (tsService.noofemp() > 1){
              //          
                //    }
              //  }
            //}
        //}
    //}
    public void Serialize() {
       
            try {
                FileOutputStream fileOut =
                new FileOutputStream("G:\\Abdullah\\5th semester\\AP\\LAb work\\Lab 7\\toystopinventorymanagementsystem\\src\\toystopinventorymanagementsystem\\data.ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(tsService);
                out.close();
                fileOut.close();
                System.out.printf("Serialized data is saved in data.ser");
            }
            catch(IOException i) {
                i.printStackTrace();
            }
        
    }
    
    public void Deserialize() {

        
            
            try {
                FileInputStream fileIn = new FileInputStream("G:\\Abdullah\\5th semester\\AP\\LAb work\\Lab 7\\toystopinventorymanagementsystem\\src\\toystopinventorymanagementsystem\\data.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                tsService = (ToyStopService) in.readObject();
                in.close();
                fileIn.close();
            }
            catch(IOException i) {
                i.printStackTrace();
                return;
            }
            catch(ClassNotFoundException c) {
                System.out.println("Employee class not found");
                c.printStackTrace();
                return;
            }
      
         
        
    }
    
}
