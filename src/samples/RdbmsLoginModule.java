package samples;

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
		return rdbmsValidate(username,password);
	}

	private boolean rdbmsValidate(String username, String password) {
		return true;
	}

	@Override
	public boolean commit() throws LoginException {
		return false;
	}

	@Override
	public boolean abort() throws LoginException {
		return false;
	}

	@Override
	public boolean logout() throws LoginException {
		return false;
	}

}
