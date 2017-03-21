package learnjaas.console;

import java.io.IOException;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import com.sun.security.auth.UserPrincipal;

public class RdbmsLoginModule implements LoginModule{

	private Subject subject ;
	
	private CallbackHandler callbackHandler;
	
	private Map<String,?> sharedState;
	
	private Map<String,?> options;
	
	private String url ;
	
	private String driverClass;
	
	private boolean debug;
	
	private Callback[] callbacks;
	
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
			Map<String, ?> options) {
		this.subject = subject;
		this.callbackHandler = callbackHandler;
		this.sharedState = sharedState;
		this.options = options;
		
		url = (String)options.get("url");
		driverClass = (String)options.get("driver");
		debug = "true".equalsIgnoreCase((String)options.get("debug"));
	}

	@Override
	public boolean login() throws LoginException {
		if(callbackHandler==null)
			throw new LoginException("no handler");
		NameCallback nameCb = new NameCallback("user:");
		PasswordCallback passCb = new PasswordCallback("password:", true);
		callbacks = new Callback[]{nameCb,passCb};
		try {
			callbackHandler.handle(callbacks);
		} catch (IOException | UnsupportedCallbackException e) {
			e.printStackTrace();
		}
		String username = nameCb.getName();
		String password = new String(passCb.getPassword());
		if(rdbmsValidate(username,password)){
			UserPrincipal p = new UserPrincipal(username);
			subject.getPrincipals().add(p);
			return true;
		}else{
			return false;
		}
	}

	private boolean rdbmsValidate(String username, String password) {
		//TODO 使用jdbc进行用户名密码校验
		System.out.println(url);
		System.out.println(driverClass);
		System.out.println(debug);
		return true;
	}

	@Override
	public boolean commit() throws LoginException {
		return true;
	}

	@Override
	public boolean abort() throws LoginException {
		return true;
	}

	@Override
	public boolean logout() throws LoginException {
		return true;
	}

}
