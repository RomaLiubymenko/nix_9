package ua.com.alevel.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ua.com.alevel.persistence.entity.AbstractEntity;

@NoRepositoryBean
public interface AbstractRepository<ENTITY extends AbstractEntity> extends JpaRepository<ENTITY, Long> {
}
