package aiss.model.resources;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import aiss.model.spotify.Playlist;

public class SpotifyTest {
	@Test
	public void getSpotifyTest() throws UnsupportedEncodingException {
	
		String title="Shrek 2001";
		
		SpotifyResource track = new SpotifyResource(title);
		Playlist result = track.SearchPlaylists(title);
		

		System.out.println("nombre Playlist: "+result.getName());
		
	}

}