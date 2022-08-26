package org.emotionalsongsproject;

import java.util.HashMap;

import objects.Emotions;
import objects.Song;
import persistence.Song_Factory;

public class EmotionalSongsProject {
	public static void main(String[] args) throws Exception{
		System.out.println("Starting...");
		
		Song_Factory songFactory = Song_Factory.getIstance();
		
		Song song = new Song(null, "Test inserimento3", "Io", "Io","2022", new HashMap<>());
		songFactory.create(song);
		
		song.setMusicalGenre("Pop - Rock");
		songFactory.update(song);
		
		song = songFactory.getById(3L);
		songFactory.delete(song);
		
		for(Song s : songFactory.listAll().values()) {
			System.out.println("Id : " + s.getSongId());
			System.out.println("Title : " + s.getTitle());
			System.out.println("Author : " + s.getAuthor());
			System.out.println("Musical Genre : " + s.getMusicalGenre());
			System.out.println("Year : " + s.getYear());
			
			for(int i = 0; i < s.getEmotionList().size(); i++) {
				System.out.println(Emotions.getNameById(Long.valueOf(i+1)) +" : " 
						+ s.getEmotionList().get(Emotions.getEmotionById(Long.valueOf(i+1))));
			}
		}
		
		
	}
}
