package finalcasetask.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import finalcasetask.FinalCaseTaskApplication;
import finalcasetask.payload.entity.enums.Score;
import finalcasetask.payload.request.UserReviewUpdateRequest;
import finalcasetask.service.UserReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {FinalCaseTaskApplication.class})
public class UserReviewControllerTest extends BaseControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @MockBean
    private UserReviewService userReviewService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    public void saveUserReviewTest() throws Exception {
        String requestAsString = "{\n"
                + "  \"restaurantId\": \"1\",\n"
                + "  \"userId\": \"2\",\n"
                + "  \"comment\": \"Thank you\",\n"
                + "  \"score\": \"TWO\"\n"
                + "}";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/userReviews")
                        .content(requestAsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void deleteUserReviewTest() throws Exception {

        Long id = 1L;

        doNothing().when(userReviewService).deleteUserReview(id);


        mockMvc.perform(delete("/api/v1/userReviews/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        verify(userReviewService, times(1)).deleteUserReview(id);
    }
    @Test
    void userReviewUpdateTest() throws Exception {

        UserReviewUpdateRequest request  = new UserReviewUpdateRequest(10L, "Thank you", Score.THREE);
        String requestAsString = objectMapper.writeValueAsString(request);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/userReviews/10")
                        .content(requestAsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        boolean success = isSuccess(mvcResult);

        assertTrue(success);
    }
}
