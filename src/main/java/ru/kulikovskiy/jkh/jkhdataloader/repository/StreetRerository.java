package ru.kulikovskiy.jkh.jkhdataloader.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kulikovskiy.jkh.jkhdataloader.entity.Street;

@Repository
public interface StreetRerository extends CrudRepository<Street, String> {
}
