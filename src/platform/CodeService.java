package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CodeService {
    private final CodesRepository codesRepository;

    @Autowired
    public CodeService(CodesRepository codeRepository) {
        this.codesRepository = codeRepository;
    }

    public Optional<Code> findCodeById(Long id) {
        return codesRepository.findById(id);
    }

    public Code saveCode(Code code) {
        return codesRepository.save(code);
    }

    public List<Code> latest() {
        return codesRepository.findTop10ByOrderByDateDesc();
    }
}
