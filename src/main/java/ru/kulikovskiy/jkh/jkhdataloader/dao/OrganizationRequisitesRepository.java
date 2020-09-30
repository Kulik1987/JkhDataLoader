package ru.kulikovskiy.jkh.jkhdataloader.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kulikovskiy.jkh.jkhdataloader.entity.OrganizationRequisites;

@Repository
public interface OrganizationRequisitesRepository extends CrudRepository<OrganizationRequisites, String> {
}
