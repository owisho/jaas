package learnjaas.console;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.security.PrivilegedAction;
import java.util.Iterator;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import com.sun.security.auth.login.ConfigFile;

@SuppressWarnings("rawtypes")
public class MyAction implements PrivilegedAction{

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws LoginException, URISyntaxException {
		//文件位置使用了绝对路径需要改善
		LoginContext ctx = new LoginContext("SimpleLogin", null,new ConsoleCallbackHandler(),new ConfigFile(new URI("file:E:/learn/learnjaas/src/main/java/learnjaas/console/simple.conf")));
		ctx.login();
		Subject subj = ctx.getSubject();
		System.out.println("Login assigned these principals:");
		Iterator<?> it = subj.getPrincipals().iterator();
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
