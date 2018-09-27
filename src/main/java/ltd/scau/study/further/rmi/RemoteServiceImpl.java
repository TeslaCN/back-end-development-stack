package ltd.scau.study.further.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

/**
 * @author Weijie Wu
 */
public class RemoteServiceImpl extends UnicastRemoteObject implements RemoteService {

    protected RemoteServiceImpl() throws RemoteException {
    }

    @Override
    public String aRemoteMethod(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0, j = chars.length - 1; i < j; i++, j--) {
            char c = chars[i];
            chars[i] = chars[j];
            chars[j] = c;
        }
        return new String(chars);
    }
}
