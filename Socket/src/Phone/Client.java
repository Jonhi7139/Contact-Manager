package Phone;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client
{
  public static void main(String[] args)
  {
	  
	  boolean on = true;
	  String rep = null;
	  boolean log = true;
	  boolean adm = false;
	  boolean insert = false;
	  
    try (Socket sock = new Socket("localhost", 3333))
    {
      PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
      InputStream in = sock.getInputStream();
      ObjectInputStream inl = new ObjectInputStream(sock.getInputStream());
      InputStreamReader reader = new InputStreamReader(in);
      BufferedReader br = new BufferedReader(reader);
      Scanner sc = new Scanner(System.in);

      
      while (on) {
          
          while (log) {
        	  
              menu();
              
              if(sc.hasNextInt()) {
            	  
            	  int op = sc.nextInt();
            	  sc.nextLine();
            	  
            	  if (op == 1) {
            		  
            		  out.println("log");
            		  
            		  System.out.println(br.readLine());
            		  rep = sc.nextLine();
            		  out.println(rep);
            		  
            		  System.out.println(br.readLine());
            		  rep = sc.nextLine();
            		  out.println(rep);
            		  
            		  String s = br.readLine();
            		  
            		  if (s.equals("ok")) {
            			  
            			  adm = true;
            			  log = false;
            			  
            		  }
            		  else if (s.equals("nouser")) {
            			  
            			  System.out.println("No User found!");
            			  
            		  }
            		  else if (s.equals("nopass")) {
            			  
            			  System.out.println("Wrong password!");
            			  
            		  }
            		  
            	  }
            	  else if (op == 2) {
            		  
            		  out.println("reg");
            		  
            		  System.out.println(br.readLine());
            		  rep = sc.nextLine();
            		  out.println(rep);
            		  
            		  String serv1 = br.readLine();
            		  
            		  if (serv1.equals("ok1")) {
                		  
                		  System.out.println(br.readLine());
                		  rep = sc.nextLine();
                		  out.println(rep);              		  
                		  
            			  String serv2 = br.readLine();
                		  
                		  if (serv2.equals("ok2")) {
                			  
                    		  System.out.println(br.readLine());
                    		  rep = sc.nextLine();
                    		  out.println(rep);
                    		  
                    		  System.out.println(br.readLine());
                			  
                		  }
                		  else if (serv2.equals("inval")) {
                			  
                			  System.out.println("Insert a valid email (email@email.com or email@email.com.pt)!");
                			  
                		  }
                		  else if (serv2.equals("inval2")) {
                			  
                			  System.out.println("Email already registed!");
                			  
                		  }
            			  
            		  }
            		  else if (serv1.equals("added")) {
            			  
            			  System.out.println("User already added!");
            			  
            		  }
            		  
            	  }
            	  else if (op == 3) {
            		  
            		  out.println("exit1");
            		  log = false;
            		  on = false;
            	  }
            	  else {
                	  
                	  System.out.println("Select a valid option!");
                  }
              }
              else {
            	  
            	  System.out.println("Select a valid option!");
            	  sc.nextLine();
              }    	  
          }
          
          while (adm) {
        	  
        	  menu2();
        	  
        	  if (sc.hasNextInt()) {
            	  
            	  int op = sc.nextInt();
            	  sc.nextLine();
            	  
            	  if (op == 1) {
            		  
            		  out.println("list");
            		  
            		  ArrayList<DataBase.Contact> contacts = (ArrayList<DataBase.Contact>) inl.readObject();
            		  
            		  if (!contacts.isEmpty()) {
                		  
                		  for (DataBase.Contact c : contacts) {
                			  
                			  System.out.println(c.toString2());
                		  }
            		  }
            		  else {
            			  
            			  System.out.println("Empty contact list!");
            			  
            		  }
            		  
            	  }
            	  else if (op == 2) {
            		  
            		  out.println("add");
            		  insert = true;
            		  
            		  System.out.println(br.readLine());
            		  rep = sc.nextLine();
            		  out.println(rep);
            		  
            		  while (insert) {
                		  
                		  System.out.println(br.readLine());
                		  rep = sc.nextLine();
                		  
                		  if (rep.length() == 9 && rep.chars().allMatch(Character::isDigit)) {
                			  
                			  out.println(rep);
                			  
                			  String serv = br.readLine();
                			  
                			  if (serv.equals("exist")) {
                				  
                				  System.out.println("Contact already exist!");
                				  insert = false;
                				  
                			  }
                			  else {
                				  
                				  System.out.println(serv);
                				  insert = false;
                				  
                			  }
                			  
                		  }
                		  else {
                			  
                			  System.out.println("Invalid phone number!");
                			  out.println("wrong");
                			  continue;
                			  
                		  }
            			  
            			  
            			  
            		  }        		  
            	  }
            	  else if (op == 3) {
            		  
            		  out.println("remove");
            		  
            		  System.out.println(br.readLine());
            		  rep = sc.nextLine();
            		  out.println(rep);
            		  
            		  rep = br.readLine();
            		  
            		  if (rep.equals("nexist")) {
            			  
            			  System.out.println("Non-existent number!");
            			  
            		  }
            		  else {
            			  
            			  System.out.println(rep);
            			  
            		  }
            		  
            	  }
            	  else if (op == 4) {
            		  
            		  out.println("exit2");
            		  log = true;
            		  adm = false;
            		  
            	  }
            	  else {
            		  
            		  System.out.println("Chose a valid option!");
            		  
            	  }
        		  
        	  }
        	  else {
        		  
        		  System.out.println("Chose a valid option!");
        		  
        	  }
          }
      }
    }
    catch (Exception e)
    {
    	System.out.println("Connection Problem...");
    }
  }
	
	public static void menu() {
		
	    int width = 33;
	    
	    String[] lines = {
	    	"  Contact Admin  ",
	        "",
	        "  [1] Login    ",
	        "  [2] Register ",
	        "  [3] Exit     ",
	        "",
	    }; 
	    
	    String uBorder = "╔" + "═".repeat(width - 2) + "╗";	    
	    String bBorder = "╚" + "═".repeat(width - 2) + "╝";
	    
	    System.out.println(uBorder);
	    for (String line : lines) {
	    	
	    	int padd = (width - 2 - line.length()) / 2;
	    		
		        String fLine = "║" + " ".repeat(Math.max(0, padd)) + line + " ".repeat(Math.max(0, width - 2 - padd - line.length())) + "║";
		        System.out.println(fLine);	    		
	    }
	    System.out.println(bBorder);
	}
	
	public static void menu2() {
		
	    int width = 33;
	    
	    String[] lines = {
	    	"  Contact Admin  ",
	        "",
	        "  [1] List of Contacts ",
	        "  [2] Insert Contact   ",
	        "  [3] Remove Contact   ",
	        "  [4] Exit             ",
	        "",
	    }; 
	    
	    String uBorder = "╔" + "═".repeat(width - 2) + "╗";	    
	    String bBorder = "╚" + "═".repeat(width - 2) + "╝";
	    
	    System.out.println(uBorder);
	    for (String line : lines) {
	    	
	    	int padd = (width - 2 - line.length()) / 2;
	    		
		        String fLine = "║" + " ".repeat(Math.max(0, padd)) + line + " ".repeat(Math.max(0, width - 2 - padd - line.length())) + "║";
		        System.out.println(fLine);	    		
	    }
	    System.out.println(bBorder);
	}
}
