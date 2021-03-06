package tech.financial.cloud.codingexercise.domain.api;

import java.util.List;
import java.util.UUID;

public interface Repository<T> {

    T update(UUID id, T t);

    T save(T t);

    void remove(UUID id);

    T get(UUID id);

    List<T> list();
}
