package ru.urfu;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
@SpringBootTest
@WithMockUser(roles = "USER")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MessageStorageTests {

    @Inject private WebApplicationContext context;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void test1EmptyMessageStorrage() throws Exception {
        mockMvc.perform(get("/messages").contentType(MediaType.ALL))
                .andExpect(model().attribute("messages", Arrays.asList()));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void test2AddMessage() throws Exception {
        mockMvc.perform(post("/add_message").param("msg", "aaa"));
        mockMvc.perform(get("/messages").accept(MediaType.ALL))
                .andExpect(model().attribute("messages", Arrays.asList(Pair.of("aaa", 1))));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void test3AddEmptyMessage() throws Exception {
        mockMvc.perform(post("/add_message").param("msg", ""));
        mockMvc.perform(get("/messages").accept(MediaType.ALL))
                .andExpect(model().attribute("messages", Arrays.asList(Pair.of("aaa", 1))));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void test4DeleteMessage() throws Exception {
        mockMvc.perform(post("/delete_message/1"));
        mockMvc.perform(get("/messages").accept(MediaType.ALL))
                .andExpect(model().attribute("messages", Arrays.asList()));
    }
}
