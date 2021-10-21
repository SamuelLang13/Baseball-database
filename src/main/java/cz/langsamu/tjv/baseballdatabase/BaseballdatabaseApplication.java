package cz.langsamu.tjv.baseballdatabase;

import cz.langsamu.tjv.baseballdatabase.dao.PlayersRepository;
import cz.langsamu.tjv.baseballdatabase.domain.BaseballPositions;
import cz.langsamu.tjv.baseballdatabase.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BaseballdatabaseApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BaseballdatabaseApplication.class, args);
	}
	@Autowired
	private PlayersRepository playersRepository;
	@Override
	public void run(String... args) throws Exception {
		this.playersRepository.save(new Player("Bo","Bichette", BaseballPositions.ShortStop));
		this.playersRepository.save(new Player("Vladdy","Guerero", BaseballPositions.FirstBase));
		this.playersRepository.save(new Player("Marcus","Semien", BaseballPositions.SecondBase));
	}
}
