package ua.com.alevel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dto.profile.StudentProfileDto;
import ua.com.alevel.dto.table.StudentTableDto;
import ua.com.alevel.facade.StudentFacade;
import ua.com.alevel.util.PaginationUtil;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/students", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentController {

    private static final String NEW_LOG = "Request to create student: {}";
    private static final String UPDATED_LOG = "Request to update student: {}";
    private static final String DELETED_LOG = "Request to update student: {}";
    private static final String GET_LOG = "Request to get student by uuid {}";
    private static final String GET_ALL_LOG = "Request to get all students";
    private static final String GET_ALL_PAGE_LOG = "Request to get page students based on params {}";
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    private final StudentFacade studentFacade;

    public StudentController(StudentFacade studentFacade) {
        this.studentFacade = studentFacade;
    }

    @Operation(summary = "Create a new student")
    @ApiResponse(responseCode = "201", description = "Student is created", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentProfileDto.class))
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentProfileDto> create(@Valid @RequestBody StudentProfileDto studentProfileDto) {
        logger.info(NEW_LOG, studentProfileDto.toString());
        StudentProfileDto savedStudent = studentFacade.create(studentProfileDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);

    }

    @Operation(summary = "Update a student by own uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student was updated", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentProfileDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Student not found", content = @Content)
    })
    @PutMapping(path = "/{uuid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentProfileDto> update(@Valid @RequestBody StudentProfileDto studentProfileDto, @PathVariable UUID uuid) {
        logger.info(UPDATED_LOG, studentProfileDto.toString());
        Optional<StudentProfileDto> student = studentFacade.update(studentProfileDto, uuid);
        if (student.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student.get());
    }

    @Operation(summary = "Delete a student")
    @ApiResponse(responseCode = "204", description = "Student was deleted")
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        logger.info(DELETED_LOG, uuid);
        studentFacade.delete(uuid);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get a student by its uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the student", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentProfileDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Student not found", content = @Content)
    })
    @GetMapping("/{uuid}")
    public ResponseEntity<StudentProfileDto> findByUuid(@PathVariable UUID uuid) {
        logger.info(GET_LOG, uuid);
        Optional<StudentProfileDto> student = studentFacade.findByUuid(uuid);
        if (student.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student.get());
    }

    @Operation(summary = "Returns a list of students with pagination based on the query parameters")
    @ApiResponse(responseCode = "200", description = "Students are existing", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentTableDto.class))
    })
    @GetMapping
    public ResponseEntity<List<StudentTableDto>> findAll(Pageable pageable, @RequestParam(required = false, defaultValue = "false") Boolean isExistRequest) {
        if (isExistRequest != null && isExistRequest) {
            logger.info(GET_ALL_LOG);
            return ResponseEntity.ok(studentFacade.findAll());
        }
        logger.info(GET_ALL_PAGE_LOG, pageable);
        Page<StudentTableDto> studentPage = studentFacade.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(studentPage, "/api/students");
        return ResponseEntity.ok().headers(headers).body(studentPage.getContent());
    }


}
