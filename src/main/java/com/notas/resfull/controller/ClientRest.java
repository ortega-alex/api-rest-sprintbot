package com.notas.resfull.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.notas.resfull.entity.Nota;

@Controller
@RequestMapping("/notas")
public class ClientRest {
	
	private final String token = "BearereyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJvcnRlZ2FAb3V0bG9vay5lcyJ9.gVqQ3uMjslUxFZz0GhgBEiBSG_Jzr8f20xuZNXrgi-cLR7wltbpqjqNHnW4nUoe2TGRdVw3y2srB4UtO8UGWUA";

	@GetMapping("/all")
	public ModelAndView devolverTodos() {
		
		ModelAndView mav = new ModelAndView("template");
		
		RestTemplate rest = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.add("Authorization" , token);
		
		HttpEntity entity = new HttpEntity(headers);
		
		ResponseEntity<Nota[]> notasEntity = rest.exchange("http://localhost:8090/v1/notas?page=0&size=5" , HttpMethod.GET , entity , Nota[].class);
		
		Nota[] notas = notasEntity.getBody();
		
		mav.addObject("notas" , notas);
		
		return mav;
	}
	
}
