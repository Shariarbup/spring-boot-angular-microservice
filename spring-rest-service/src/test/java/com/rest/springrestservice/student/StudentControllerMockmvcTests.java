package com.rest.springrestservice.student;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerMockmvcTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testCreateRetrieveWithMockMVC() throws Exception {

//		this.mockMvc.perform(post("/api/v1/students")
//				.header(HttpHeaders.AUTHORIZATION,
//						"Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzaGFyaWFyIiwicm9sZXMiOlsiUk9MRV9BRE1JTiJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvbG9naW4iLCJleHAiOjE2NTY0MzQ0ODR9.mLS4n7Bk03U8DqipZQHLLEkgM-O5H_WF_RypOH6gVBc")
//				)
//		.andExpect(status().isOk()); // is2xxSuccessful()

//		this.mockMvc.perform(get("/api/v1/students/16")
//				.header(HttpHeaders.AUTHORIZATION,
//						"Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiYWRzaGEiLCJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2xvZ2luIiwiZXhwIjoxNjU2NDI5NTgxfQ.wb9mWdksGz89KQz3f38nzUDzXFtFvsyllPDl717pVjM")
//				).andDo(print()).andExpect(status().isOk())
//				.andExpect(content().string(containsString("shariar")));
		

	}
	@Test
	public void getStudentByIdTest() throws Exception{
		this.mockMvc.perform(get("/api/v1/students/17").header(HttpHeaders.AUTHORIZATION, 
				"Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzaGFyaWFyIiwicm9sZXMiOlsiUk9MRV9BRE1JTiJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvbG9naW4iLCJleHAiOjE2NTY0Nzc1OTZ9.7aKg-tfym5_ZpW3KCmZnULOfL-9m8Puvfc2TNJz_r08"))
				.andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void deleteStudentByIdTest() throws Exception{
		this.mockMvc.perform(delete("/api/v1/students/25").header(HttpHeaders.AUTHORIZATION, 
				"Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzaGFyaWFyIiwicm9sZXMiOlsiUk9MRV9BRE1JTiJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvbG9naW4iLCJleHAiOjE2NTY0Nzc1OTZ9.7aKg-tfym5_ZpW3KCmZnULOfL-9m8Puvfc2TNJz_r08"))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void getAllStudentsTest() throws Exception {
		this.mockMvc.perform(get("/api/v1/students").header(HttpHeaders.AUTHORIZATION,
				"Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzaGFyaWFyIiwicm9sZXMiOlsiUk9MRV9BRE1JTiJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvbG9naW4iLCJleHAiOjE2NTY0Nzc1OTZ9.7aKg-tfym5_ZpW3KCmZnULOfL-9m8Puvfc2TNJz_r08"))
				.andExpect(status().isOk());
	}
}
