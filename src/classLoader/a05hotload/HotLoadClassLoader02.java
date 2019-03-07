package classLoader.a05hotload;

import java.net.URL;
import java.net.URLClassLoader;

public class HotLoadClassLoader02 extends URLClassLoader{
	
	public HotLoadClassLoader02(URL[] urls) {
		super(urls); 
	}
}
