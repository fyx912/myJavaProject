package com.ding;

import com.alibaba.fastjson2.JSON;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author ding
 * @create 28 1:27
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//测试环境使用，用来表示测试环境使用的ApplicationContext将是WebApplicationContext类型的
@AutoConfigureMockMvc
public class BaseTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * mock 测试方法
     * @param method　{@link HttpMethod}
     * @param uriPath uri
     * @param data  {@link  Object json}
     * @throws Exception
     */
    public void mockTest(HttpMethod method, String uriPath, Object  data) throws Exception {
        MvcResult result;
        MockHttpServletRequestBuilder mockMvcRequestBuilders = null;
        if (method == HttpMethod.GET){
            mockMvcRequestBuilders = MockMvcRequestBuilders.get(uriPath);
        }
        if (method== HttpMethod.PATCH ){
            mockMvcRequestBuilders = MockMvcRequestBuilders.patch(uriPath)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(JSON.toJSONString(data));
        }
        if (method==HttpMethod.PUT ){
            mockMvcRequestBuilders=MockMvcRequestBuilders.put(uriPath)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(JSON.toJSONString(data))
            ;
        }
        if (method==HttpMethod.POST ){
            mockMvcRequestBuilders= MockMvcRequestBuilders.post(uriPath)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(JSON.toJSONString(data))
            ;
        }
        if (method==HttpMethod.DELETE ){
            mockMvcRequestBuilders = MockMvcRequestBuilders.delete(uriPath).contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(JSON.toJSONString(data));
        }
        result = mockMvc.perform(mockMvcRequestBuilders).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        System.out.println("response status:"+response.getStatus());
        System.out.println("result:"+response.getContentAsString());
    }


}
