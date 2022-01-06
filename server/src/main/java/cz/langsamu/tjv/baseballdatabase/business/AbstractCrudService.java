package cz.langsamu.tjv.baseballdatabase.business;

import cz.langsamu.tjv.baseballdatabase.api.exception.NoEntityFoundException;
import cz.langsamu.tjv.baseballdatabase.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;
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
            throw new NoEntityFoundException();
        }
        return entity.get();
    }


    public Optional<E> readById(K id) {
        return repository.findById(id);
    }



    @Transactional
    public abstract E update(Long id,E entity);

    public void deleteById(K id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
        }
        throw new NoEntityFoundException("Does not exist!");
    }

    @Transactional
    public E create(E entity) throws EntityStateException {
        if (exists(entity))
            throw new EntityStateException(entity);
        E e = repository.save(entity);
        return e;
    }

    public Collection<E> readAll() {
        return repository.findAll();
    }

}
