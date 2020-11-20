package ru.kulikovskiy.jkh.jkhdataloader.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kulikovskiy.jkh.jkhdataloader.entity.HouseOrganization;
import ru.kulikovskiy.jkh.jkhdataloader.entity.HouseOrganizationIdentity;

@Repository
public interface HouseOrganizationRepository extends CrudRepository<HouseOrganization, HouseOrganizationIdentity> {
}
