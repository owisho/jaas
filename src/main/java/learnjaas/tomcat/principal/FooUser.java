package learnjaas.tomcat.principal;

import java.security.Principal;

public class FooUser implements Principal{

	private String name ;
	
	@Override
	public String getName() {
		return this.name;
	}
	
}
