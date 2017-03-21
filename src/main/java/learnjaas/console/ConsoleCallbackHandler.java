package learnjaas.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

public class ConsoleCallbackHandler implements CallbackHandler{

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		
		for(int i=0;i<callbacks.length;i++){
			if(callbacks[i] instanceof NameCallback){
				NameCallback nc = (NameCallback)callbacks[i];
				System.out.println(nc.getPrompt());
				System.err.flush();
				String name = (new BufferedReader(new InputStreamReader(System.in))).readLine();
				nc.setName(name);
			}else if(callbacks[i] instanceof PasswordCallback){
				PasswordCallback pc = (PasswordCallback)callbacks[i];
				System.out.println(pc.getPrompt());
				System.err.flush();
				String password = (new BufferedReader(new InputStreamReader(System.in))).readLine();
				pc.setPassword(password.toCharArray());
			}else{
				throw new UnsupportedCallbackException(callbacks[i],"Callback handler not support");
			}
		}
		
	}

}
