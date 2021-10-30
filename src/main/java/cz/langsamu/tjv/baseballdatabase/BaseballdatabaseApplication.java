package cz.langsamu.tjv.baseballdatabase;

import cz.langsamu.tjv.baseballdatabase.dao.PlayerRepository;
import cz.langsamu.tjv.baseballdatabase.domain.BaseballPositions;
import cz.langsamu.tjv.baseballdatabase.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BaseballdatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseballdatabaseApplication.class, args);
	}

}
