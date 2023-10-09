package com.iat.momentsqa.controller;

import com.iat.momentsqa.dto.ProgramDto;
import com.iat.momentsqa.dtomapper.EntityToDto.ProgramsToDto;
import com.iat.momentsqa.model.Channels;
import com.iat.momentsqa.model.Programs;
import com.iat.momentsqa.service.IPrgsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/programs")
public class ProgramsCtrl {
    @Autowired
    IPrgsService programsService;

    @Autowired
    ProgramsToDto programsToDto;

    @GetMapping("/all")
    public List<ProgramDto> getAllPrograms(@RequestParam(defaultValue = "0") int page) {
        return programsService.getAllPrograms(page).stream().map(programsToDto).collect(Collectors.toList());
    }

    // SEARCH BY TITLE
    @GetMapping("/search")
    public List<ProgramDto> getProgramsByTitle(@RequestParam(name = "q") Optional<String> name,
            @RequestParam(defaultValue = "0") int page) {
        return programsService.getProgramsByName(name.orElse(""), page).stream()
                .map(programsToDto)
                .collect(Collectors.toList());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ProgramDto getProgramById(@PathVariable(name = "id") Long id) {
        return programsToDto.apply(programsService.findById(id));
    }

    // DELETE BY ID
    @DeleteMapping("/{id}")
    public Long annihilateProgram(@PathVariable(name = "id") Long id) {
        return programsService.annihilateProgram(id);
    }

    // ADD PROGRAM
    @PostMapping(path = "/add-program")
    public ProgramDto addProgram(@RequestBody ProgramDto programDto) {
        Programs program = new Programs(programDto.programId(),
                programDto.name(), programDto.title(),
                programDto.contentType(), programDto.genre(),
                programDto.duration(), programDto.releaseDate(), programDto.prodCountry(), programDto.description(),
                new Channels(programDto.channelId(), null, null, null, null, null),
                null);
        return programsToDto.apply(programsService.addProgram(program));
    }

    // ADD MANY PROGRAM
    @PostMapping("/add-programs")
    public List<Programs> addMultiplePrograms(@RequestBody List<Programs> programs) {
        return programsService.addMultiplePrograms(programs);
    }

    // UPDATE PROGRAM
    @PatchMapping("/{id}")
    public boolean renewProgram(@RequestBody Programs program) {
        return programsService.renewPrograms(program);
    }
}
