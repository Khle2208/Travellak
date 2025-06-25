package com.anhkhoa.travellak.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.anhkhoa.travellak.Service.UsersService;
import com.anhkhoa.travellak.dto.Request.Users.UsersCreationRequest;
import com.anhkhoa.travellak.dto.Request.Users.UsersUpdateRequest;
import com.anhkhoa.travellak.dto.Response.UsersResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersService usersService;

    private UsersCreationRequest creationRrequest;
    private UsersUpdateRequest updateRequest;
    private UsersResponse responseCreation;
    private UsersResponse responseUpdate;

    @BeforeEach
    void initData() {
        creationRrequest = UsersCreationRequest.builder()
                .email("abc@gmail.com")
                .password("Anhkhoa1@")
                .roles(Set.of("USER"))
                .build();
        updateRequest = UsersUpdateRequest.builder()
                .password("Anhkhoa2@")
                .roles(Set.of("USER"))
                .name("Anhkhoa")
                .phoneNumber("0865018731")
                .build();
        responseCreation = UsersResponse.builder()
                .userId(UUID.randomUUID())
                .email("abc@gmail.com")
                .password("Anhkhoa1@")
                .phoneNumber(null)
                .createTime(LocalDateTime.now())
                .name(null)
                .updateTime(null)
                .build();

        responseUpdate = UsersResponse.builder()
                .userId(UUID.randomUUID())
                .email("abc@gmail.com")
                .password("Anhkhoa2@")
                .phoneNumber("0865018731")
                .createTime(LocalDateTime.now())
                .name("Anhkhoa")
                .updateTime(LocalDateTime.now())
                .build();
    }

    @Test
    void createUser_validRequest_success() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(creationRrequest);

        Mockito.when(usersService.createUser(ArgumentMatchers.any())).thenReturn(responseCreation);
        //        When
        mockMvc.perform(MockMvcRequestBuilders.post("/Users/SignUp")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value("1000"));
    }

    @WithMockUser(roles = "USER")
    @Test
    void updateUser_validRequest_success() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(updateRequest);

        Mockito.when(usersService.updateUser(ArgumentMatchers.any())).thenReturn(responseUpdate);
        //        When
        mockMvc.perform(MockMvcRequestBuilders.put("/Users/editProfile")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value("1000"));
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    void getUserById_success() throws Exception {
        UUID userId = UUID.randomUUID();
        Mockito.when(usersService.getUserById(userId)).thenReturn(responseCreation);

        mockMvc.perform(MockMvcRequestBuilders.get("/Users/" + userId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("email").value("abc@gmail.com"));
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    void myInfo_success() throws Exception {
        Mockito.when(usersService.myInfo()).thenReturn(responseCreation);
        mockMvc.perform(MockMvcRequestBuilders.get("/Users/myInfo"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("result.email").value("abc@gmail.com"));
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    void deleteUser_success() throws Exception {
        UUID userId = UUID.randomUUID();
        Mockito.when(usersService.deleteUser(userId)).thenReturn("user deleted");
        mockMvc.perform(MockMvcRequestBuilders.delete("/Users/" + userId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    void getAllUsers_success() throws Exception {

        Mockito.when(usersService.getAllUser()).thenReturn(List.of(responseUpdate, responseCreation));
        mockMvc.perform(MockMvcRequestBuilders.get("/Users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("result").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("result.length()").value(2));
        ;
    }
}
