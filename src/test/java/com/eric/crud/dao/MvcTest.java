package com.eric.crud.dao;

import com.github.pagehelper.PageInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "file:src/main/web/WEB-INF/dispatcherServlet-servlet.xml"})
public class MvcTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void initMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testPageInfo() throws Exception {
        // get the result
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/emps").param("pn", "1"))
                .andReturn();
        PageInfo pi = (PageInfo) result.getRequest().getAttribute("pageInfo");
        System.out.println("=======================The current pageNumber: " + pi.getPageNum());
        System.out.println("=======================The current pageInfo: " + pi.toString());
    }
}
