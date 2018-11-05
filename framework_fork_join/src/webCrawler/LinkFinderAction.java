package webCrawler;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

@SuppressWarnings("serial")
public class LinkFinderAction extends RecursiveAction {
	
	private String url;
	private LinkHandler cr;
	
	public LinkFinderAction(String url, LinkHandler cr) {
		this.url = url;
		this.cr = cr;
	}
	
	@SuppressWarnings("unused")
	@Override
	protected void compute() {
		
		if(!cr.visited(url)){
			try{
				List<RecursiveAction> actions = new ArrayList<RecursiveAction>();
				URL uriLink = new URL(url);
				/*
				 * Parser parser = new Parser(uriLink.openConnection());
				 * NodeList list = parser.extractAllNodesThatMatch(new NodeClassFilter(LinkTag.class));
				 * 
				 * for(int i=0; i<list.size(); i++){
				 * 		LinkTag extracted = (LinkTag) list.elementAt(i);
				 * 		if(!extracted.extractLink().isEmpty() && !cr.visited(extracted.extractLink())){
				 * 			actions.add(new LinkFinderAction(extracted.extractLink()));
				 * 		}
				 * }
				 * cr.addVisited(url);
				 * invokeAll(actions);
				 */
			}catch(Exception e){
				//ignore 404, unknown protocol or other server errors
			}
		}
	}

}
