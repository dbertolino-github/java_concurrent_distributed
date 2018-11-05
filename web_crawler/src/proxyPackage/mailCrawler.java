package proxyPackage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Hashtable;

import javax.swing.text.BadLocationException;

import java.net.URI;

public class mailCrawler {
	
	private static IServer server = null;
	private static final String frstURL = "http://informatica.dista.uninsubria.it/";
	private static Hashtable<URI,Boolean> webPages;
	private static HashSet<String> emails;
	
	public static void main( String[] args ) throws IOException, URISyntaxException, BadLocationException {
		
		try{
			System.out.println("SPIDER IS FREE");
			
			server = new Stub();
			webPages = new Hashtable<URI,Boolean>();
			emails = new HashSet<String>();
			URI uri = new URI(frstURL);
			
			addToWebPages(uri);

			while(webPages.containsValue(false)){
				URI uriToScan = chooseURI();
				System.out.println("Analyzing: " + uriToScan);
				scanUri(uriToScan);	
				printMails();
			}
			
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
	
	public static URI chooseURI() {
		URI urlNotChecked = null;
		for (URI key: webPages.keySet()) {
			if(webPages.get(key)==false){
				urlNotChecked = key;
				break;
			}	
		}
		return urlNotChecked;
	}	
	
	private static void addToWebPages(URI url) {
		if(!webPages.containsKey(url)){
			webPages.put(url, false);
		}
	}
	
	private static void scanUri(URI url) throws IOException, BadLocationException, URISyntaxException {
		webPages.replace(url, false, true);
		HTMLDoc doc = server.sendGet(url);
		
		searchURLs(doc);
		searchMails(doc);
	}
	
	private static void searchURLs(HTMLDoc doc){
		for(URI uri: doc.getURLs()){
			addToWebPages(uri);
		}
	}
	
	private static void searchMails(HTMLDoc doc){
		for(String mail : doc.getEmailAddresses()){
			if(!emails.contains(mail)){
				emails.add(mail);
			}
		}
	}
	
	private static void printMails(){
		System.out.println("Mail founded till now:\n");
		for(String mail : emails){
			System.out.println(mail + "\n");
		}
	}
}
