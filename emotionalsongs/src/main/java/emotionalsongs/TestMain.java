package emotionalsongs;

import java.io.IOException;

import persistence.Song_Factory;
import persistence.User_Factory;

public class TestMain {
	private Song_Factory songFactory;
	private User_Factory userFactory;
	
	public TestMain() throws Exception {
		songFactory = Song_Factory.getIstance();
		userFactory = User_Factory.getIstance();
	}
	
	public void TestSong() throws Exception {
		/*Song song = new Song(null, "Test inserimento3", "Io", "Io","2022", new HashMap<>());
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
		}*/
	}
	
	public void TestUser() throws IOException, Exception {
		/*Address address = new Address("via Santa Caterina", "36", "22066", "Mariano Comense", "CO", "Lombardia", "Italia"); 
		User user = new User(null, "edoballa", "pippo", "edo.balla01@gmail.com", "Edoardo", "Ballabio", "BLLDRD01T31B639K", address);
		Address address2 = new Address("via Cavour", "28", "20900", "Monza", "MB", "Lombardia", "Italia"); 
		User user2 = new User(null, "mariorossi", "qwerty", "mario.rossi@gmail.com", "Mario", "Rossi", "MRLDRD91F21B447C", address2);
		Address address3 = new Address("via Sempione", "4", "22063", "Cantu", "CO", "Lombardia", "Italia"); 
		User user3 = new User(null, "lucaverdi", "password", "luca.verdi@gmail.com", "Luca", "Verdi", "LCVDRD69B66B609D", address3);
		userFactory.create(user);
		userFactory.create(user2);
		userFactory.create(user3);

		user.setLastName("Rossi");
		address.setAddressNumber("45");
		user.setAddress(address);
		userFactory.update(user);
		
		user = userFactory.getById(1L);
		userFactory.delete(user);*/
	}
	
	public void TestAuthenticator() throws Exception {
		/*Authenticator authenticator = new Authenticator();
		authenticator.actionRegisterUser();
		authenticator.actionLogin();*/
	}
}
