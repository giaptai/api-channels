package com.iat.momentsqa.service;

import com.iat.momentsqa.model.Programs;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface IPrgsService {
    Slice<Programs> getAllPrograms(int page);

    Programs addProgram(Programs program);

    List<Programs> addMultiplePrograms(List<Programs> programs);

    long annihilateProgram(Long id);

    Slice<Programs> getProgramsByName(String name, int page);

    Programs findById(Long id);

    boolean renewPrograms(Programs programs);

}
