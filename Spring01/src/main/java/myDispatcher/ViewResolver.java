package myDispatcher;

public class ViewResolver {
	
	private String prefix;
	private String suffix;
	
	// setter만 생성
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	
	public String getViewName(String viewName) {
		
		viewName = prefix + viewName + suffix;
		System.out.println("** viewName => " + viewName);
		
		return viewName;
		
	} // getViewName

} // class
