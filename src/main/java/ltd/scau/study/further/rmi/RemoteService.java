package ltd.scau.study.further.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Weijie Wu
 */
public interface RemoteService extends Remote {

    String aRemoteMethod(String s) throws RemoteException;

}
