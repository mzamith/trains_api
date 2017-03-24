package org.feup.trains.repository;

import org.feup.trains.model.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {

	Configuration findByKey(String key);
}
