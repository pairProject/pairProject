package is2103practicallab05client;

import ejb.session.stateless.CheckProcessingSessionBeanRemote;
import javax.ejb.EJB;



public class Main
{
    @EJB
    private static CheckProcessingSessionBeanRemote checkProcessingSessionBeanRemote;
    
    
    
    public static void main(String[] args)
    {
        MainApp mainApp = new MainApp(checkProcessingSessionBeanRemote);
        mainApp.runApp();   
    }
}