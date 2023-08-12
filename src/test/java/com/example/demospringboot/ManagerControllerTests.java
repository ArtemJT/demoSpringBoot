package com.example.demospringboot;

import com.example.demospringboot.domain.Manager;
import com.example.demospringboot.dto.ManagerDto;
import com.example.demospringboot.dto.ManagerReadDto;
import com.example.demospringboot.service.interfaces.ManagerService;
import com.example.demospringboot.util.mapper.ManagerMapper;
import com.example.demospringboot.util.mapper.BookingMapper;
import com.example.demospringboot.web.ManagerController;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Artem Kovalov on 12.08.2023
 */
@WebMvcTest(controllers = ManagerController.class)
@DisplayName("Manager Controller Tests")
class ManagerControllerTests {

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ManagerService service;

    @MockBean
    private ManagerMapper managerMapper;

    @MockBean
    private BookingMapper bookingMapper;

    @Autowired
    private MockMvc mockMvc;

    private ManagerDto managerDto;
    private Manager manager;

    @BeforeEach
    void setUp() {
        managerDto = ManagerDto.builder()
                .id(1)
                .name("Manager")
                .email("manager@mail.com")
                .build();
        manager = Manager.builder()
                .id(1)
                .name("Manager")
                .email("manager@mail.com")
                .build();

    }

    @Test
    @DisplayName("POST /app/manager")
    void createManagerTest() throws Exception {
        when(managerMapper.toDto(any(Manager.class))).thenReturn(managerDto);
        when(managerMapper.toEntity(any(ManagerDto.class))).thenReturn(manager);
        when(service.create(any(Manager.class))).thenReturn(manager);

        MockHttpServletRequestBuilder mockRequest = post("/app/manager")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(manager));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andReturn();

        verify(service).create(any());
    }

    @Test
    @DisplayName("PATCH /app/manager")
    void deleteManagerTest() throws Exception {
        when(service.deleteById(1)).thenReturn(manager);
        when(service.deleteById(2)).thenThrow(EntityNotFoundException.class);

        mockMvc.perform(patch("/app/manager?id=1"))
                .andExpect(status().isOk());

        verify(service).deleteById(1);
    }

    @Test
    @DisplayName("GET /app/manager/all")
    void getAllManagersTest() throws Exception {
        List<ManagerReadDto> list = List.of(
                ManagerReadDto.builder()
                .id(1)
                .name("Manager")
                .email("manager@mail.com")
                .build());

        doReturn(list).when(managerMapper).toReadDtoCol(anyList());

        mockMvc.perform(get("/app/manager/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is("Manager")))
                .andReturn();

        verify(service).getAll();
    }
}
