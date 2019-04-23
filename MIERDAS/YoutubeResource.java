package aiss.model.resources;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.youtube.SearchVideos;

public class YoutubeResource {
	private static final String YOUTUBE_API_KEY = "AIzaSyAJLyQQWL4i1UDX6A9xajWBaHCSLpLQexg"; 
	private static final Logger log = Logger.getLogger(YoutubeResource.class.getName());
	private static final String URL_YOUTUBE = "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=%maxResult&q=%QUERY+music&key=%APIKEY";
	
	public SearchVideos getTrailer(String query) throws UnsupportedEncodingException {
		String title = URLEncoder.encode(query, "UTF-8");
		ClientResource cr = null;
		SearchVideos res = null;
		CharSequence maxResults = "1";
	//	String uri = "https://www.googleapis.com/youtube/v3/search?q=" +title + "&order=relevance&part=snippet&type=video&maxResults=" + maxResults + "&key=" + YOUTUBE_API_KEY;

		try {
			cr = new ClientResource(URL_YOUTUBE.replace("%maxResult",  maxResults).replace("%QUERY", title).replace("%APIKEY", YOUTUBE_API_KEY));
			res = cr.get(SearchVideos.class);
			log.log(Level.FINE, "Búsqueda de vídeos de " + query + " realizada correctamente.");
		} catch (ResourceException e) {
			log.log(Level.WARNING, "Error al obtener los vídeos: " + cr.getResponse().getStatus());
			throw e;
		}
		return res;
	}
}
