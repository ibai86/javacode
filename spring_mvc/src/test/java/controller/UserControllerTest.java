package controller;

import com.task.store.controller.UserController;
import com.task.store.dto.UserDto;
import com.task.store.mapper.UserMapper;
import com.task.store.model.User;
import com.task.store.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;
    private User user;
    private UserDto userDto;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        user = User.builder()
                .id(1L)
                .username("Bobby")
                .email("example@mail.org")
                .orders(List.of())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        userDto = UserDto.builder()
                .id(1L)
                .username("Bobby")
                .email("example@mail.org")
                .orders(null)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Test
    public void testGetAllUsersSummary_shouldReturnSummaryView() throws Exception {
        when(userService.getAllUsers()).thenReturn(Collections.singletonList(user));
        when(userMapper.toDtoList(anyList())).thenReturn(Collections.singletonList(userDto));

        mockMvc.perform(get("/api/v1/user/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(userDto.id()))
                .andExpect(jsonPath("$[0].username").value(userDto.username()))
                .andExpect(jsonPath("$[0].email").value(userDto.email()))
                .andExpect(jsonPath("$[0].orders").doesNotExist())
                .andExpect(jsonPath("$[0].createdAt").doesNotExist())
                .andExpect(jsonPath("$[0].updatedAt").doesNotExist());


    }

    @Test
    public void testGetUserDetails_shouldReturnDetailsView() throws Exception {
        when(userService.getUser(1L)).thenReturn(user);
        when(userMapper.toDto(any(User.class))).thenReturn(userDto);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'#'HH:mm:ss");

        String expectedCreatedDate = userDto.createdAt().format(formatter);
        String expectedUpdatedDate = userDto.updatedAt().format(formatter);

    mockMvc.perform(get("/api/v1/user/1")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(userDto.id()))
            .andExpect(jsonPath("$.username").value(userDto.username()))
            .andExpect(jsonPath("$.email").value(userDto.email()))
            .andExpect(jsonPath("$.orders").value(userDto.orders()))
            .andExpect(jsonPath("$.createdAt").value(expectedCreatedDate))
            .andExpect(jsonPath("$.updatedAt").value(expectedUpdatedDate));
    }
}
