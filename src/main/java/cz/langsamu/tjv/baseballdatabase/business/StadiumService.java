package cz.langsamu.tjv.baseballdatabase.business;

import cz.langsamu.tjv.baseballdatabase.dao.StadiumRepository;
import cz.langsamu.tjv.baseballdatabase.domain.Stadium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StadiumService {

    private final StadiumRepository stadiumRepository;

    //@Autowired
    public StadiumService(StadiumRepository stadiumRepository) {
        this.stadiumRepository = stadiumRepository;
    }

    public List<Stadium> getStadiums() {
        return stadiumRepository.findAll();
    }

    public void AddNewStadium(Stadium stadium) {
        Optional<Stadium> stadiumById = stadiumRepository.findById(stadium.getStadiumID());
        if(stadiumById.isPresent()){
            throw new IllegalStateException("The stadium is already registered!");
        }
        stadiumRepository.save(stadium);
    }

    public void deleteStadium() {
    }

    @Transactional
    public void updateStadium(long id, String name, int capacity, String city) {

    }


}
