package org.emotionalsongsproject;

import object.Song;
import persistence.Song_Factory;

public class EmotionalSongsProject {
	public static void main(String[] args) throws Exception{
		System.out.println("Starting...");
		
		Song_Factory song_Factory = Song_Factory.getIstance();
		for(Song s : song_Factory.listAll().values()) {
			System.out.println("Id : " + s.getSongId());
			System.out.println("Title : " + s.getTitle());
			System.out.println("Author : " + s.getAuthor());
			System.out.println("Musical Genre : " + s.getMusicalGenre());
			System.out.println("Year : " + s.getYear());
		}
	}
}
