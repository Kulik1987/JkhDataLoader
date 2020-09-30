package ru.kulikovskiy.jkh.jkhdataloader.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kulikovskiy.jkh.jkhdataloader.entity.Street;

import java.util.List;

@Repository
public interface StreetRerository extends CrudRepository<Street, String> {
}
