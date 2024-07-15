package com.example.demo.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(FileUploadController.class)
public class FileUploadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFileUpload() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", MediaType.TEXT_PLAIN_VALUE, "Test content".getBytes());

        mockMvc.perform(multipart("/upload")
                .file(file))
                .andExpect(status().isOk())
                .andExpect(content().string("File uploaded successfully: test.txt"));
    }

    @Test
    public void testEmptyFileUpload() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "", MediaType.TEXT_PLAIN_VALUE, new byte[0]);

        mockMvc.perform(multipart("/upload")
                .file(file))
                .andExpect(status().isOk())
                .andExpect(content().string("File is empty"));
    }
}
