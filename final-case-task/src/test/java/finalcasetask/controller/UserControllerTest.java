package finalcasetask.controller;


import finalcasetask.payload.dto.RestaurantInfoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import finalcasetask.FinalCaseTaskApplication;
import finalcasetask.client.RestaurantClient;
import finalcasetask.payload.request.UserUpdateRequest;
import finalcasetask.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

;


@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {FinalCaseTaskApplication.class})
@AutoConfigureMockMvc
class UserControllerTest extends BaseControllerTest{

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private RestaurantClient restaurantClient;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    public void saveUserTest() throws Exception {
        String requestAsString = "{\n"
                + "  \"name\": \"John\",\n"
                + "  \"lastName\": \"Doe\",\n"
                + "  \"latitude\": \"40.0\",\n"
                + "  \"longitude\": \"12.2\",\n"
                + "  \"phoneNumber\": \"123-456-7890\",\n"
                + "  \"email\": \"john.doe@gmail.com\",\n"
                + "  \"gender\": \"MALE\"\n"
                + "}";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                        .content(requestAsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void userUpdateTest() throws Exception {

        UserUpdateRequest request  = new UserUpdateRequest(1L, "John", "Doe","john.doe@gmail.com","151483654782",25.5,12.3);
        String requestAsString = objectMapper.writeValueAsString(request);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/update/1")
                        .content(requestAsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        boolean success = isSuccess(mvcResult);

        assertTrue(success);
    }

    @Test
    void deleteUserTest() throws Exception {

        Long userId = 1L;

        doNothing().when(userService).deleteUser(userId);


        mockMvc.perform(delete("/api/v1/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        verify(userService, times(1)).deleteUser(userId);
    }

    @Test
    void recommendRestaurantsTest() throws Exception {
        // Hazırlık
        List<RestaurantInfoDTO> allRestaurants = new ArrayList<>();
        allRestaurants.add(new RestaurantInfoDTO("1", "Restaurant A", 40.7128, -74.0060, 4.5));
        allRestaurants.add(new RestaurantInfoDTO("2", "Restaurant B", 40.7127, -74.0059, 4.2));
        allRestaurants.add(new RestaurantInfoDTO("3", "Restaurant C", 40.7126, -74.0058, 4.0));

        // Mock verilerini ayarla
        when(restaurantClient.getAllRestaurantSolr()).thenReturn(allRestaurants);

        // Eylem ve Doğrulama
        mockMvc.perform(get("/api/v1/users/recommend-restaurants")
                        .param("latitude", "40.7128")
                        .param("longitude", "-74.0060"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Restaurant A")))
                .andExpect(jsonPath("$[0].weightedScore").isNumber())
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Restaurant B")))
                .andExpect(jsonPath("$[1].weightedScore").isNumber())
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].name", is("Restaurant C")))
                .andExpect(jsonPath("$[2].weightedScore").isNumber());
    }
}