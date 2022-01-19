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
import ua.com.alevel.dto.profile.StudentGroupProfileDto;
import ua.com.alevel.dto.table.StudentGroupTableDto;
import ua.com.alevel.facade.StudentGroupFacade;
import ua.com.alevel.util.PaginationUtil;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/student-groups", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentGroupController {

    private static final String NEW_LOG = "Request to create group: {}";
    private static final String UPDATED_LOG = "Request to update group: {}";
    private static final String DELETED_LOG = "Request to update group: {}";
    private static final String GET_LOG = "Request to get group by uuid {}";
    private static final String GET_ALL_LOG = "Request to get all groups";
    private static final String GET_ALL_PAGE_LOG = "Request to get page groups based on params {}";
    private static final Logger logger = LoggerFactory.getLogger(StudentGroupController.class);
    private final StudentGroupFacade studentGroupFacade;

    public StudentGroupController(StudentGroupFacade studentGroupFacade) {
        this.studentGroupFacade = studentGroupFacade;
    }


    @Operation(summary = "Create a new student group")
    @ApiResponse(responseCode = "201", description = "Student group is created", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentGroupProfileDto.class))
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentGroupProfileDto> create(@Valid @RequestBody StudentGroupProfileDto studentGroupProfileDto) {
        logger.info(NEW_LOG, studentGroupProfileDto.toString());
        StudentGroupProfileDto savedGroup = studentGroupFacade.create(studentGroupProfileDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGroup);
    }

    @Operation(summary = "Update a student group by own uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student group was updated", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentGroupProfileDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Student group not found", content = @Content)
    })
    @PutMapping(path = "/{uuid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentGroupProfileDto> update(@Valid @RequestBody StudentGroupProfileDto studentGroupProfileDto, @PathVariable UUID uuid) {
        logger.info(UPDATED_LOG, studentGroupProfileDto.toString());
        Optional<StudentGroupProfileDto> group = studentGroupFacade.update(studentGroupProfileDto, uuid);
        if (group.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(group.get());
    }

    @Operation(summary = "Delete a student group")
    @ApiResponse(responseCode = "204", description = "Student group was deleted")
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        logger.info(DELETED_LOG, uuid);
        studentGroupFacade.delete(uuid);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get a student group by its uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the student group", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentGroupProfileDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Student group not found", content = @Content)
    })
    @GetMapping("/{uuid}")
    public ResponseEntity<StudentGroupProfileDto> findByUuid(@PathVariable UUID uuid) {
        logger.info(GET_LOG, uuid);
        Optional<StudentGroupProfileDto> group = studentGroupFacade.findByUuid(uuid);
        if (group.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(group.get());
    }

    @Operation(summary = "Returns a list of student groups with pagination based on the query parameters")
    @ApiResponse(responseCode = "200", description = "Student groups are existing", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentGroupTableDto.class))
    })
    @GetMapping
    public ResponseEntity<List<StudentGroupTableDto>> findAll(Pageable pageable, @RequestParam(required = false, defaultValue = "false") Boolean isExistRequest) {
        if (isExistRequest != null && isExistRequest) {
            logger.info(GET_ALL_LOG);
            return ResponseEntity.ok(studentGroupFacade.findAll());
        }
        logger.info(GET_ALL_PAGE_LOG, pageable);
        Page<StudentGroupTableDto> studentGroupPage = studentGroupFacade.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(studentGroupPage, "/api/student-groups");
        return ResponseEntity.ok().headers(headers).body(studentGroupPage.getContent());
    }
}
