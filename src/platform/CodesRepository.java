package platform;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CodesRepository extends CrudRepository<Code, Long> {
//    List<Code> findAllByOrderByDateDesc();
    List<Code> findTop10ByTimeBFalseAndViewsBFalseOrderByDateDesc();

    Optional<Code> findByUuid(String uuid);
}
