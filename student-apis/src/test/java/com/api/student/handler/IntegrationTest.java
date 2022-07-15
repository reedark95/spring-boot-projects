package com.api.student.handler;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class IntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	public void testGetListOfStudents() throws Exception {
		mockMvc.perform(get("/students")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$", hasSize(5)))
				.andExpect(jsonPath("$[*].firstName", hasItems("Felice", "Emmerich", "Isidoro", "Dulsea", "Felicle")))
				.andExpect(jsonPath("$[0].lastName", equalTo("Rosnau"))).andExpect(jsonPath("$[0].age", equalTo(20)));
	}

	@Test
	public void testGetStudentById() throws Exception {
		mockMvc.perform(get("/students/2")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.id", is(2)))
				.andExpect(jsonPath("$.firstName", is("Emmerich"))).andExpect(jsonPath("$.lastName", is("Dury")))
				.andExpect(jsonPath("$.age", is(12)));
	}

	@Test
	public void createnewStudent() throws JsonProcessingException, Exception {
		Student a = Student.builder().firstName("Sand").lastName("Man").age(25).build();

		mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(a)))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.id", is(Long.valueOf(6).intValue())));

		mockMvc.perform(get("/students")).andExpect(jsonPath("$", hasSize(6)));
	}

	@Test
	public void testUpdatingStudent() throws JsonProcessingException, Exception {
		Student a = Student.builder().id(2).firstName("Akash").lastName("Patiala").age(18).build();

		mockMvc.perform(put("/students/2").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(a)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message", is("Student details successfully updated")));

		mockMvc.perform(get("/students/2")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", is(Long.valueOf(a.getId()).intValue())))
				.andExpect(jsonPath("$.firstName", is(a.getFirstName())))
				.andExpect(jsonPath("$.lastName", is(a.getLastName()))).andExpect(jsonPath("$.age", is(a.getAge())));
	}

	@Test
	public void testDeleteStudent() throws Exception {
		mockMvc.perform(delete("/students/4")).andExpect(status().isOk())
				.andExpect(jsonPath("$.message", equalTo("Student details successfully deleted")));
	}

}
