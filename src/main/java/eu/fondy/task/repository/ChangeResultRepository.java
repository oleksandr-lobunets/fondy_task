package eu.fondy.task.repository;

import eu.fondy.task.entity.ChangeResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChangeResultRepository extends JpaRepository<ChangeResult, UUID> {
}
