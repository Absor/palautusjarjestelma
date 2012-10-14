package wad.palautusjarjestelma.service;

import java.util.Collection;

public interface ServiceInterface<T> {

    T create(T object);

    T findById(Long id);

    void update(T object);

    void delete(T object);

    void delete(Long id);

    Collection<T> findAll();
}
