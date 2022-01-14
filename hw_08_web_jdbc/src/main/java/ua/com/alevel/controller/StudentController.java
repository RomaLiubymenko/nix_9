package ua.com.alevel.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.dto.request.StudentRequestDto;
import ua.com.alevel.dto.response.PageData;
import ua.com.alevel.dto.response.StudentResponseDto;
import ua.com.alevel.facade.StudentFacade;
import ua.com.alevel.util.PaginationUtil;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class StudentController {

    private final StudentFacade studentFacade;

    public StudentController(StudentFacade studentFacade) {
        this.studentFacade = studentFacade;
    }

    @PostMapping("/students")
    public ResponseEntity<HttpStatus> create(@RequestBody StudentRequestDto studentRequestDto) {
        try {
            studentFacade.create(studentRequestDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/students/{uuid}")
    public ResponseEntity<HttpStatus> update(@RequestBody StudentRequestDto studentRequestDto, @PathVariable UUID uuid) {
        try {
            studentFacade.update(studentRequestDto, uuid);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/students/{uuid}")
    public ResponseEntity<HttpStatus> delete(@PathVariable UUID uuid) {
        try {
            studentFacade.delete(uuid);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/students/{uuid}")
    public ResponseEntity<StudentResponseDto> findById(@PathVariable UUID uuid) {
        try {
            return ResponseEntity.ok(studentFacade.findByUuid(uuid));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentResponseDto>> findAll(WebRequest webRequest, @RequestParam(required = false, defaultValue = "false") Boolean isExistRequest) {
        try {
            if (isExistRequest != null && isExistRequest) {
                return ResponseEntity.ok(studentFacade.findAll());
            }
            PageData<StudentResponseDto> studentPage = studentFacade.findAll(webRequest);
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(studentPage, "/api/students");
            return ResponseEntity.ok().headers(headers).body(studentPage.getItems());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
