package fr.ing.interview.repository;

import fr.ing.interview.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

    List<Operation> findByAccountIdOrderByDateDesc(Long accountId);
}
