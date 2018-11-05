package webCrawler;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.ForkJoinPool;

public class WebCrawler implements LinkHandler {
	
	private final Collection<String> visitedLinks = Collections.synchronizedSet(new HashSet<String>());
	private String url;
	private ForkJoinPool mainPool;
	
	public WebCrawler(String startingURL, int maxThreads){
		this.url = startingURL;
		mainPool = new ForkJoinPool(maxThreads);
	}
	
	private void startCrawling(){
		mainPool.invoke(new LinkFinderAction(this.url, this));
	}
	
	@Override
	public int size() {
		return visitedLinks.size();
	}
	
	@Override
	public boolean visited(String link) {
		return visitedLinks.contains(link);
	}
	
	@Override
	public void addVisited(String link) {
		visitedLinks.add(link);
	}
	
	public static void main(String[] args ) throws Exception {
		new WebCrawler("http://www.javaworld.com", 64).startCrawling();
	}

}
