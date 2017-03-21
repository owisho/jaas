package samples;

import java.security.Principal;
import java.security.PrivilegedAction;
import java.util.Iterator;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;

public class MyAction implements PrivilegedAction{

	public static void main(String[] args) {
		LoginContext ctx = new LoginContext("SimpleLogin", new ConsoleCallbackHandler());
		ctx.login();
		Subject subj = ctx.getSubject();
		System.out.println("Login assigned these principals:");
		Iterator it = subj.getPrincipals().iterator();
		while(it.hasNext()){
			Principal pl = (Principal)it.next();
			System.out.println("\t"+pl.getName());
		}
		Subject.doAsPrivileged(subj, new MyAction(), null);
		ctx.logout();
	}

	@Override
	public Object run() {
		return null;
	}
	
}
