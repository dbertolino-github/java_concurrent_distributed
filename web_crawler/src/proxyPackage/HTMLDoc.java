package proxyPackage;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HTMLDoc {
	
    private String htmlDoc;
    private URI host;

    public HTMLDoc(String html, URI uri) throws URISyntaxException {
        htmlDoc = html;
        this.host = new URI(uri.getScheme() + "://" +  uri.getAuthority() + "/");
    }

    public String toString() {
        return htmlDoc;
    }

    public ArrayList<String> getEmailAddresses() {
        // regular expression to find email address
        String regex = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(htmlDoc);

        ArrayList<String> addresses = new ArrayList<String>();
        while (matcher.find()) {
            addresses.add(matcher.group());
        }
        return addresses;
    }

    public ArrayList<URI> getURLs() {
        // regular expression to find TAG href
        Pattern pattern = Pattern.compile("href=\"(.*?)\"");
        Matcher matcher = pattern.matcher(htmlDoc);

        ArrayList<URI> urls = new ArrayList<URI>();
        while (matcher.find()) {
            try {
                String href = matcher.group();

                URI absoluteLink = replaceRelativeLinks(href);
                if (absoluteLink==null)
                    continue;
              //MODIFICA CON CONTROLLI SUL HTTP
                if(absoluteLink.getScheme().equals("http"))
                	urls.add(absoluteLink);
            } catch (URISyntaxException e) {
                System.err.println("URI Error: " + e.getMessage());
            }
        }
        return urls;
    }

    private URI replaceRelativeLinks(String content) throws URISyntaxException {
        Pattern pattern = Pattern.compile("\"(.*?)\"");
        Matcher matcher = pattern.matcher(content);

        //determines if the link is already absolute
        Pattern absoluteLinkPattern = Pattern.compile("[a-z]+://.+");

        if (matcher.find()) {
            String addr = matcher.group();

            //position of link in quotes
            String link = addr.substring(1, addr.length() - 1);
            Matcher absoluteMatcher = absoluteLinkPattern.matcher(link);
            //is the link relative?
            if (!absoluteMatcher.find()) {
                //create relative URL
                try {
                    URI tmpUri = host.resolve(link);
                    return tmpUri;
                }catch (IllegalArgumentException e){
                    return null;
                }
            }
            return new URI(link);
        }
        return null;
    }
}
