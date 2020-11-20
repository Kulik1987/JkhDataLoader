package ru.kulikovskiy.jkh.jkhdataloader.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kulikovskiy.jkh.jkhdataloader.entity.House;

@Repository
public interface HouseRepository extends CrudRepository<House, String> {

}
