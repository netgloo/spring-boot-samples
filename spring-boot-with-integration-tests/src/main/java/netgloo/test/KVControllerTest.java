package netgloo.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.val;
import netgloo.controllers.KVController;
import netgloo.models.KVEntity;
import netgloo.repository.KVDatabase;
import netgloo.service.KVService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class KVControllerTest {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final SecureRandom random = new SecureRandom();

    @Autowired
    MockMvc mockMvc;
    @InjectMocks
    private KVController controller;

    @MockBean
    KVDatabase kvDatabase;

    @MockBean
    KVService kvService;

    private static final KVEntity TEST_FOUND_KV_ENTITY =
            new KVEntity(1L, "TEST", "TEST");

    @BeforeEach
    void setUp() {
        assertNotNull(controller);
        assertNotNull(mockMvc);

        doReturn(Optional.of(TEST_FOUND_KV_ENTITY))
                .when(kvDatabase)
                .findById(any(Long.class));
    }

    @AfterEach
    void tearDown() {
        mockMvc = null;
        controller = null;
        kvDatabase = null;
        kvService = null;
    }

    @Test
    void contextLoads() throws Exception {
        assertNotNull(controller);
        assertNotNull(mockMvc);
    }

    @Test
    void getTest() throws Exception {
        when(kvService.get(any(Long.class))).thenReturn(TEST_FOUND_KV_ENTITY);

        val result = mockMvc.perform(get("/kv/1"))
               .andDo(print())
               .andExpect(status().isOk())
               .andReturn();

        assertNotNull(result);
        assertEquals(TEST_FOUND_KV_ENTITY, mapper.readValue(result.getResponse().getContentAsString(), KVEntity.class));
    }

    @Test
    void getAllTest() throws Exception {
        when(kvService.findAll()).thenReturn(Arrays.asList(TEST_FOUND_KV_ENTITY));

        val result = mockMvc.perform(
                get("/kv"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertNotNull(result);
        assertEquals(mapper.readValue(mapper.writeValueAsString(Arrays.asList(TEST_FOUND_KV_ENTITY)), List.class),
                mapper.readValue(result.getResponse().getContentAsString(), List.class));
        verify(kvService, times(1)).findAll();
    }

    @Test
    void postTest() throws Exception {
        doReturn(1L)
                .when(kvService)
                .post(anyString(), anyString());

        val result = mockMvc.perform(
                post("/kv")
                        .param("key", "TEST")
                        .param("value", "TEST"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        assertNotNull(result);
        assertEquals("1", result.getResponse().getContentAsString());
        verify(kvService, times(1)).post(TEST_FOUND_KV_ENTITY.getKVEntityKey(), TEST_FOUND_KV_ENTITY.getKVEntityValue());
    }

    @Test
    void putTest() throws Exception {
        doReturn(TEST_FOUND_KV_ENTITY)
                .when(kvService)
                .put(any(KVEntity.class));

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(TEST_FOUND_KV_ENTITY);

        val result = mockMvc.perform(
                        put("/kv")
                                .content(requestJson)
                                .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();

        assertNotNull(result);
        assertEquals("", result.getResponse().getContentAsString());
        verify(kvService, times(1)).put(TEST_FOUND_KV_ENTITY);
    }

    @Test
    void deleteTest() throws Exception {
        doNothing().when(kvService).delete(any(Long.class));

        val result = mockMvc.perform(
                        delete("/kv/1"))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();

        assertNotNull(result);
        verify(kvService, times(1)).delete(any(Long.class));
    }
}