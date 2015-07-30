package searchengine.manager;

public interface SpiderManager {
	
	public boolean climb(String url,Integer deep);
	public boolean lagouClimb(String url);

}
