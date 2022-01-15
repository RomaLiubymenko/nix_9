package ua.com.alevel.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.dto.request.StudentGroupRequestDto;
import ua.com.alevel.dto.response.PageData;
import ua.com.alevel.dto.response.StudentGroupResponseDto;
import ua.com.alevel.facade.StudentGroupFacade;
import ua.com.alevel.util.PaginationUtil;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class StudentGroupController {

    private final StudentGroupFacade studentGroupFacade;

    public StudentGroupController(StudentGroupFacade studentGroupFacade) {
        this.studentGroupFacade = studentGroupFacade;
    }

    @PostMapping("/student-groups")
    public ResponseEntity<HttpStatus> create(@RequestBody StudentGroupRequestDto studentGroupRequestDto) {
        try {
            studentGroupFacade.create(studentGroupRequestDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/student-groups/{uuid}")
    public ResponseEntity<HttpStatus> update(@RequestBody StudentGroupRequestDto studentGroupRequestDto, @PathVariable UUID uuid) {
        try {
            studentGroupFacade.update(studentGroupRequestDto, uuid);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/student-groups/{uuid}")
    public ResponseEntity<HttpStatus> delete(@PathVariable UUID uuid) {
        try {
            studentGroupFacade.delete(uuid);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/student-groups/{uuid}")
    public ResponseEntity<StudentGroupResponseDto> findById(@PathVariable UUID uuid) {
        try {
            return ResponseEntity.ok(studentGroupFacade.findByUuid(uuid));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/student-groups")
    public ResponseEntity<List<StudentGroupResponseDto>> findAll(WebRequest webRequest, @RequestParam(required = false) Boolean isExistRequest) {
        try {
            if (isExistRequest != null && isExistRequest) {
                return ResponseEntity.ok(studentGroupFacade.findAll());
            }
            PageData<StudentGroupResponseDto> studentGroupPage = studentGroupFacade.findAll(webRequest);
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(studentGroupPage, "/api/student-groups");
            return ResponseEntity.ok().headers(headers).body(studentGroupPage.getItems());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
