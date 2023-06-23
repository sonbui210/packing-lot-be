package com.sobu.parkinglot.api.classApi;

import com.sobu.parkinglot.app.entity.Response;
import com.sobu.parkinglot.data.model.ClassModel;
import com.sobu.parkinglot.data.repository.ClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/class")
@RequiredArgsConstructor
public class ClassController {

    private final ClassRepository classRepository;

    @GetMapping
    public ResponseEntity getAllClass() {
        return Response.data(classRepository.findAll(Sort.by("createAt")));
    }

    @PostMapping("/createOrUpdate")
    public ResponseEntity createOrUpdateClass(@RequestBody ClassModel request) {
        ClassModel classModel = classRepository.save(request);

        return Response.data(classModel);
    }
}
