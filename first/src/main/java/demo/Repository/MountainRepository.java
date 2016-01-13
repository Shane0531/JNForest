package demo.Repository;


import java.util.Collection;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.jboss.jandex.Main;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import demo.models.Forest;
import demo.models.Mountain;

@Repository
@EnableAutoConfiguration
@EnableJpaRepositories
public interface MountainRepository extends CrudRepository<Mountain,String>{
	Collection<Mountain> findByNameLike(String name);
	Collection<Mountain> findByCityLike(String city);
	Collection<Mountain> findByAddrLike(String addr);
}

