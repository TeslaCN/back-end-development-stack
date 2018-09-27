package ltd.scau.study.further.rmi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @author Weijie Wu
 */
public class RemoteServer {

    public static void main(String[] args) throws RemoteException, NamingException {
        LocateRegistry.createRegistry(1099);
        RemoteService service = new RemoteServiceImpl();
        Context context = new InitialContext();
        context.rebind("rmi://localhost:1099/service", service);
        System.out.println("Remote Server started");
    }
}
