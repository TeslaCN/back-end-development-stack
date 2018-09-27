package ltd.scau.study.further.rmi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;

/**
 * @author Weijie Wu
 */
public class RemoteClient {

    public static void main(String[] args) throws NamingException, RemoteException {
        String uri = "rmi://localhost:1099";
        Context context = new InitialContext();
        RemoteService service = (RemoteService) context.lookup(uri + "/service");
        System.out.println(service.aRemoteMethod("This is RMI client"));
    }
}
