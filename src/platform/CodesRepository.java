package platform;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodesRepository extends CrudRepository<Code, Long> {
//    List<Code> findAllByOrderByDateDesc();
    List<Code> findTop10ByOrderByDateDesc();

}
