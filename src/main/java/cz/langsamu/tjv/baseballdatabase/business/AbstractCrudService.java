package cz.langsamu.tjv.baseballdatabase.business;

import org.springframework.data.jpa.repository.JpaRepository;

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

}
