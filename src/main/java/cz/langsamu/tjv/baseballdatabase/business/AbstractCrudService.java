package cz.langsamu.tjv.baseballdatabase.business;

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

    public Optional<E> readById(K id) {
        return repository.findById(id);
    }

    @Transactional
    public void update(E entity) throws EntityStateException {
        if (exists(entity))
            repository.save(entity);
        else
            throw new EntityStateException(entity);
    }

    public void deleteById(K id) {
        repository.deleteById(id);
    }

    @Transactional
    public void create(E entity) throws EntityStateException {
        if (exists(entity))
            throw new EntityStateException(entity);
        repository.save(entity);
    }

    public Collection<E> readAll() {
        return repository.findAll();
    }


}
