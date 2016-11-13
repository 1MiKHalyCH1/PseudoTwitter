package ru.urfu;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MessageStorageTests {

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new MessageStorage()).build();
    }

    @Test
    public void test1EmptyMessageStorrage() throws Exception {
        mockMvc.perform(get("/messages").contentType(MediaType.ALL))
                .andExpect(model().attribute("messages", Arrays.asList()));
    }

    @Test
    public void test2AddMessage() throws Exception {
        mockMvc.perform(post("/add_message").param("msg", "aaa"));
        mockMvc.perform(get("/messages").accept(MediaType.ALL))
                .andExpect(model().attribute("messages", Arrays.asList("aaa")));
    }

    @Test
    public void test3AddEmptyMessage() throws Exception {
        mockMvc.perform(post("/add_message").param("msg", ""));
        mockMvc.perform(get("/messages").accept(MediaType.ALL))
                .andExpect(model().attribute("messages", Arrays.asList("aaa")));
    }

    @Test
    public void test4DeleteMessage() throws Exception {
        mockMvc.perform(post("/delete_message/0"));
        mockMvc.perform(get("/messages").accept(MediaType.ALL))
                .andExpect(model().attribute("messages", Arrays.asList()));
    }
}
