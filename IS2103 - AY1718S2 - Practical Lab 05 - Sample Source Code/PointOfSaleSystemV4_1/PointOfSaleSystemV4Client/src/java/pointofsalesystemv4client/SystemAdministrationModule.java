package pointofsalesystemv4client;

import ejb.session.stateless.ProductEntityControllerRemote;
import ejb.session.stateless.StaffEntityControllerRemote;
import entity.ProductEntity;
import entity.StaffEntity;
import java.text.NumberFormat;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.enumeration.AccessRightEnum;
import util.exception.InvalidAccessRightException;
import util.exception.StaffNotFoundException;



public class SystemAdministrationModule
{
    private StaffEntityControllerRemote staffEntityControllerRemote;
    private ProductEntityControllerRemote productEntityControllerRemote;
    
    private StaffEntity currentStaffEntity;

    
    
    public SystemAdministrationModule()
    {
    }

    
    
    public SystemAdministrationModule(StaffEntityControllerRemote staffEntityControllerRemote, ProductEntityControllerRemote productEntityControllerRemote, StaffEntity currentStaffEntity) 
    {
        this();
        this.staffEntityControllerRemote = staffEntityControllerRemote;
        this.productEntityControllerRemote = productEntityControllerRemote;
        this.currentStaffEntity = currentStaffEntity;
    }
    
    
    
    public void menuSystemAdministration() throws InvalidAccessRightException
    {
        if(currentStaffEntity.getAccessRightEnum() != AccessRightEnum.MANAGER)
        {
            throw new InvalidAccessRightException("You don't have MANAGER rights to access the system administration module.");
        }
        
        Scanner scanner = new Scanner(System.in);
        Integer response = 0;
        
        while(true)
        {
            System.out.println("*** POS System :: System Administration ***\n");
            System.out.println("1: Create New Staff");
            System.out.println("2: View Staff Details");
            System.out.println("3: View All Staffs");
            System.out.println("-----------------------");
            System.out.println("4: Create New Product");
            System.out.println("5: View Product Details");
            System.out.println("6: View All Products");
            System.out.println("-----------------------");
            System.out.println("7: Back\n");
            response = 0;
            
            while(response < 1 || response > 7)
            {
                System.out.print("> ");

                response = scanner.nextInt();

                if(response == 1)
                {
                    doCreateNewStaff();
                }
                else if(response == 2)
                {
                    doViewStaffDetails();
                }
                else if(response == 3)
                {
                    doViewAllStaffs();
                }
                else if(response == 4)
                {
                    doCreateNewProduct();
                }
                else if(response == 5)
                {
                    doViewProductDetails();
                }
                else if(response == 6)
                {
                    doViewAllProducts();
                }
                else if (response == 7)
                {
                    break;
                }
                else
                {
                    System.out.println("Invalid option, please try again!\n");                
                }
            }
            
            if(response == 7)
            {
                break;
            }
        }
    }
    
    
    
    private void doCreateNewStaff()
    {
        Scanner scanner = new Scanner(System.in);
        StaffEntity newStaffEntity = new StaffEntity();
        
        System.out.println("*** POS System :: System Administration :: Create New Staff ***\n");
        System.out.print("Enter First Name> ");
        newStaffEntity.setFirstName(scanner.nextLine().trim());
        System.out.print("Enter Last Name> ");
        newStaffEntity.setLastName(scanner.nextLine().trim());
        
        while(true)
        {
            System.out.print("Select Access Right (1: Cashier, 2: Manager)> ");
            Integer accessRightInt = scanner.nextInt();
            
            if(accessRightInt >= 1 && accessRightInt <= 2)
            {
                newStaffEntity.setAccessRightEnum(AccessRightEnum.values()[accessRightInt-1]);
                break;
            }
            else
            {
                System.out.println("Invalid option, please try again!\n");
            }
        }
        
        scanner.nextLine();
        System.out.print("Enter Username> ");
        newStaffEntity.setUsername(scanner.nextLine().trim());
        System.out.print("Enter Password> ");
        newStaffEntity.setPassword(scanner.nextLine().trim());
        
        newStaffEntity = staffEntityControllerRemote.createNewStaff(newStaffEntity);
        System.out.println("New staff created successfully!: " + newStaffEntity.getStaffId() + "\n");
    }
    
    
    
    private void doViewStaffDetails()
    {
        Scanner scanner = new Scanner(System.in);
        Integer response = 0;
        
        System.out.println("*** POS System :: System Administration :: View Staff Details ***\n");
        System.out.print("Enter Staff ID> ");
        Long staffId = scanner.nextLong();
        
        try
        {
            StaffEntity staffEntity = staffEntityControllerRemote.retrieveStaffByStaffId(staffId);
            System.out.printf("%8s%20s%20s%15s%20s%20s\n", "Staff ID", "First Name", "Last Name", "Access Right", "Username", "Password");
            System.out.printf("%8s%20s%20s%15s%20s%20s\n", staffEntity.getStaffId().toString(), staffEntity.getFirstName(), staffEntity.getLastName(), staffEntity.getAccessRightEnum().toString(), staffEntity.getUsername(), staffEntity.getPassword());         
            System.out.println("------------------------");
            System.out.println("1: Update Staff");
            System.out.println("2: Delete Staff");
            System.out.println("3: Back\n");
            System.out.print("> ");
            response = scanner.nextInt();

            if(response == 1)
            {
                doUpdateStaff(staffEntity);
            }
            else if(response == 2)
            {
                doDeleteStaff(staffEntity);
            }
        }
        catch(StaffNotFoundException ex)
        {
            System.out.println("An error has occurred while retrieving staff: " + ex.getMessage() + "\n");
        }
    }
    
    
    private void doUpdateStaff(StaffEntity staffEntity)
    {
        Scanner scanner = new Scanner(System.in);        
        String input;
        
        System.out.println("*** POS System :: System Administration :: View Staff Details :: Update Staff ***\n");
        System.out.print("Enter First Name (blank if no change)> ");
        input = scanner.nextLine().trim();
        if(input.length() > 0)
        {
            staffEntity.setFirstName(input);
        }
                
        System.out.print("Enter Last Name (blank if no change)> ");
        input = scanner.nextLine().trim();
        if(input.length() > 0)
        {
            staffEntity.setLastName(input);
        }
        
        while(true)
        {
            System.out.print("Select Access Right (0: No Change, 1: Cashier, 2: Manager)> ");
            Integer accessRightInt = scanner.nextInt();
            
            if(accessRightInt >= 1 && accessRightInt <= 2)
            {
                staffEntity.setAccessRightEnum(AccessRightEnum.values()[accessRightInt-1]);
                break;
            }
            else if (accessRightInt == 0)
            {
                break;
            }
            else
            {
                System.out.println("Invalid option, please try again!\n");
            }
        }
        
        scanner.nextLine();
        System.out.print("Enter Username (blank if no change)> ");
        input = scanner.nextLine().trim();
        if(input.length() > 0)
        {
            staffEntity.setUsername(input);
        }
        
        System.out.print("Enter Password (blank if no change)> ");
        input = scanner.nextLine().trim();
        if(input.length() > 0)
        {
            staffEntity.setPassword(input);
        }
        
        staffEntityControllerRemote.updateStaff(staffEntity);
        System.out.println("Staff updated successfully!\n");
    }
    
    
    
    private void doDeleteStaff(StaffEntity staffEntity)
    {
        Scanner scanner = new Scanner(System.in);        
        String input;
        
        System.out.println("*** POS System :: System Administration :: View Staff Details :: Delete Staff ***\n");
        System.out.printf("Confirm Delete Staff %s %s (Staff ID: %d) (Enter 'Y' to Delete)> ", staffEntity.getFirstName(), staffEntity.getLastName(), staffEntity.getStaffId());
        input = scanner.nextLine().trim();
        
        if(input.equals("Y"))
        {
            try 
            {
                staffEntityControllerRemote.deleteStaff(staffEntity.getStaffId());
                System.out.println("Staff deleted successfully!\n");
            } 
            catch (StaffNotFoundException ex) 
            {
                System.out.println("An error has occurred while deleting staff: " + ex.getMessage() + "\n");
            }            
        }
        else
        {
            System.out.println("Staff NOT deleted!\n");
        }
    }
    
    
    
    private void doViewAllStaffs()
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("*** POS System :: System Administration :: View All Staffs ***\n");
        
        List<StaffEntity> staffEntities = staffEntityControllerRemote.retrieveAllStaffs();
        System.out.printf("%8s%20s%20s%15s%20s%20s\n", "Staff ID", "First Name", "Last Name", "Access Right", "Username", "Password");

        for(StaffEntity staffEntity:staffEntities)
        {
            System.out.printf("%8s%20s%20s%15s%20s%20s\n", staffEntity.getStaffId().toString(), staffEntity.getFirstName(), staffEntity.getLastName(), staffEntity.getAccessRightEnum().toString(), staffEntity.getUsername(), staffEntity.getPassword());
        }
        
        System.out.print("Press any key to continue...> ");
        scanner.nextLine();
    }
    
    
    
    private void doCreateNewProduct()
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("*** POS System :: System Administration :: Create New Product ***\n");
        System.out.println("Unsupported operation!\n");
        System.out.print("Press any key to continue...> ");
        scanner.nextLine();
    }
    
    
    
    private void doViewProductDetails()
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("*** POS System :: System Administration :: View Product Details ***\n");
        System.out.println("Unsupported operation!\n");
        System.out.print("Press any key to continue...> ");
        scanner.nextLine();
    }
    
    
    
    private void doViewAllProducts()
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("*** POS System :: System Administration :: View All Products ***\n");
        
        List<ProductEntity> productEntities = productEntityControllerRemote.retrieveAllProducts();
        System.out.printf("%10s%20s%20s%20s%13s%20s\n", "SKU Code", "Name", "Description", "Quantity On Hand", "Unit Price", "Category");

        for(ProductEntity productEntity:productEntities)
        {
            System.out.printf("%10s%20s%20s%20d%13s%20s\n", productEntity.getSkuCode(), productEntity.getName(), productEntity.getDescription(), productEntity.getQuantityOnHand(), NumberFormat.getCurrencyInstance().format(productEntity.getUnitPrice()), productEntity.getCategory());
        }
        
        System.out.print("Press any key to continue...> ");
        scanner.nextLine();
    }
}