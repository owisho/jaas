package learnjaas.web;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.Iterator;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.security.auth.login.ConfigFile;

import learnjaas.console.MyAction;

public class LoginServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		System.out.println("userName="+userName);
		System.out.println("password="+password);
		LoginContext ctx = null;
		try {
			ctx = new LoginContext("SimpleLogin", null, new PassiveCallbackHandler(userName,password),new ConfigFile(new URI("file:E:/learn/learnjaas/src/main/java/learnjaas/console/simple.conf")));
			ctx.login();
		} catch (LoginException e) {
			//TODO 需要特殊处理
			e.printStackTrace();
		} catch (Exception e) {
			//记日志即可
			e.printStackTrace();
		}
		Subject subj = ctx.getSubject();
		System.out.println("Login assigned these principals:");
		Iterator it = subj.getPrincipals().iterator();
		while (it.hasNext()) {
			Principal pl = (Principal)it.next();
			System.out.println("\t"+pl.getName());
		}
		Subject.doAsPrivileged(subj, new MyAction(), null);
		try {
			ctx.logout();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
