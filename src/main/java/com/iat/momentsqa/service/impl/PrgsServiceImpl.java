package com.iat.momentsqa.service.impl;

import com.iat.momentsqa.model.Programs;
import com.iat.momentsqa.repository.PrgsRepository;
import com.iat.momentsqa.service.IPrgsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrgsServiceImpl implements IPrgsService {
    @Autowired
    PrgsRepository repositoryPro;

    @Override
    public Slice<Programs> getAllPrograms(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return repositoryPro.findAll(pageable);
    }

    @Override
    public Programs addProgram(Programs program) {
        return repositoryPro.save(program);
    }

    @Override
    public List<Programs> addMultiplePrograms(List<Programs> programs) {
        return repositoryPro.saveAll(programs);
    }

    @Override
    public long annihilateProgram(Long id) {
        return repositoryPro.deleteProgramById(id);
    }

    @Override
    public Slice<Programs> getProgramsByName(String name, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return repositoryPro.findByNameContainingIgnoreCase(name, pageable);
    }

    @Override
    public Programs findById(Long id) {
        return repositoryPro.findById(id).orElse(new Programs());
    }

    @Override
    public boolean renewPrograms(Programs programs) {
        if (repositoryPro.existsById(programs.getProgramId())) {
            addProgram(programs);
        }
        return repositoryPro.existsById(programs.getProgramId());
    }
}
