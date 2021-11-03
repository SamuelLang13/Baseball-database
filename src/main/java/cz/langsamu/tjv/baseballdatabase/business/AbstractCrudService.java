package cz.langsamu.tjv.baseballdatabase.business;

import cz.langsamu.tjv.baseballdatabase.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

public abstract class AbstractCrudService<K,E,REPOSITORY extends JpaRepository<E,K>> {

    protected final REPOSITORY repository;

    public AbstractCrudService(REPOSITORY repository) {
        this.repository = repository;
    }

   public abstract boolean exists(E entity);

    public E getEntityById(K id) {
        Optional<E> entity = repository.findById(id);
        if(entity.isEmpty()) {
            throw new RuntimeException();
        }
        return entity.get();
    }


    public Optional<E> readById(K id) {
        return repository.findById(id);
    }

    @Transactional
    public abstract E update(Long id,E entity);

    public void deleteById(K id) {
        repository.deleteById(id);
    }

    @Transactional
    public E create(E entity) throws EntityStateException {
        if (exists(entity))
            throw new EntityStateException(entity);
        return repository.save(entity);
    }

    public Collection<E> readAll() {
        return repository.findAll();
    }

}
