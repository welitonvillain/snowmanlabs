package com.snowmanlabs.challenge.rental;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.snowmanlabs.challenge.rental.application.dto.CreateRentalDTO;
import com.snowmanlabs.challenge.rental.application.dto.RentalDTO;
import com.snowmanlabs.challenge.rental.application.usecase.CreateRentalUseCase;
import com.snowmanlabs.challenge.rental.domain.type.ItemType;
import com.snowmanlabs.challenge.rental.infrastructure.in.web.CreateRentalController;
import com.snowmanlabs.challenge.shared.domain.type.Format;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CreateRentalControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private CreateRentalController createRentalController;

    @Mock
    private CreateRentalUseCase createRentalUseCase;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(createRentalController).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void testExecute() throws Exception {
        Long itemId = 1L;
        ItemType itemType = ItemType.MOVIE;
        String username = "weliton.villain";
        Format format = Format.DIGITAL;
        LocalDateTime rentalDate = LocalDateTime.now();

        CreateRentalDTO createRentalDTO = new CreateRentalDTO(itemId, itemType, username, format, rentalDate);

        RentalDTO rentalDTO = new RentalDTO(
                100L,
                itemId,
                itemType,
                username,
                rentalDate,
                null
        );

        when(createRentalUseCase.execute(any(CreateRentalDTO.class))).thenReturn(rentalDTO);

        mockMvc.perform(post("/api/v1/rentals/rent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRentalDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(100))
                .andExpect(jsonPath("$.itemId").value(1))
                .andExpect(jsonPath("$.itemType").value("MOVIE"))
                .andExpect(jsonPath("$.username").value("weliton.villain"))
                .andExpect(jsonPath("$.format").doesNotExist())
                .andExpect(jsonPath("$.rentalDate").isNotEmpty())
                .andExpect(jsonPath("$.returnDate").doesNotExist());

        verify(createRentalUseCase, times(1)).execute(any(CreateRentalDTO.class));
    }
}
