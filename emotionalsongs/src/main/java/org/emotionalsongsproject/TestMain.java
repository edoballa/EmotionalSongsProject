package org.emotionalsongsproject;

import java.io.IOException;
import java.util.HashMap;

import objects.Emotion;
import objects.EmotionFelt;
import objects.Emotions;
import objects.Playlist;
import objects.Song;
import objects.User;
import persistence.EmotionFelt_Factory;
import persistence.Playlist_Factory;
import persistence.Song_Factory;
import persistence.User_Factory;

public class TestMain {
	private Song_Factory songFactory;
	private EmotionFelt_Factory emotionFeltFactory;
	private User_Factory userFactory;
	private Playlist_Factory playlistFactory;
	
	public TestMain() throws Exception {
		songFactory = Song_Factory.getIstance();
		emotionFeltFactory = EmotionFelt_Factory.getIstance();
		userFactory = User_Factory.getIstance();
		playlistFactory = Playlist_Factory.getIstance();
	}
	
	public void TestSong() throws Exception {
		Song song = new Song(null, "Test inserimento3", "Io", "Io","2022", new HashMap<>());
		songFactory.create(song);
		
		song.setMusicalGenre("Pop - Rock");
		songFactory.update(song);
		
		//song = songFactory.getById(3L);
		//songFactory.delete(song);
		
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
	
	public void TestEmotion() throws Exception {
		User user = userFactory.getById(Long.valueOf(100));
		Song song = songFactory.getById(Long.valueOf(2));
		Emotion emotion = Emotions.getEmotionById(3L);
		
		EmotionFelt ef = new EmotionFelt(emotion.getEmotionId(), "", song.getSongId(), user.getUserId(), 5);
		
		emotionFeltFactory.delete(ef);
		ef.setUserId(Long.valueOf(101));
		emotionFeltFactory.create(ef);
		ef.setNote("ggggggggggggggggggggg");
		ef.calculateEmotionFeltId();
		emotionFeltFactory.update(ef);
		emotionFeltFactory.getById(ef.getEmotionFeltId());
		
		for(EmotionFelt e : emotionFeltFactory.listAll().values()) {
			System.out.println("Id : " + e.getEmotionFeltId());
			System.out.println("Song : " + e.getSongId());
			System.out.println("User : " + e.getUserId());
			System.out.println("Score : " + e.getScore());
			System.out.println("Note : " + e.getNote());
			
		}
	}
	
	public void TestPlaylist() throws Exception {
		Playlist playlist = new Playlist();
		playlist.setName("playlist test 1");
		playlist.setPublic(false);
		playlist.setUserId(Long.valueOf(105));
		playlistFactory.create(playlist);
		Song song = songFactory.getById(Long.valueOf(5));
		playlist.getSongs().add(song);
		playlistFactory.update(playlist);
		playlist = playlistFactory.getById(Long.valueOf(0));
		playlistFactory.delete(playlist);
		
		for(Playlist p : playlistFactory.listAll().values()) {
			System.out.println("Id : " + p.getPlaylistId());
			System.out.println("Name : " + p.getName());
			System.out.println("UserId : " + p.getUserId());
			
			for(Song s : p.getSongs()) {
				System.out.println("SongId : " + s.getSongId());
				System.out.println("Title : " + s.getTitle());
			}
		}
	}
	
}
