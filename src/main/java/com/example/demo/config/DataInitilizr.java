package com.example.demo.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Component
public class DataInitilizr implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		List<User> users = userRepository.findAll();
		if(users.isEmpty()) {
			createUser("Brunno", "bruno_owned@hotmail.com");
			createUser("Rhanna", "rhanna@hotmail.com");
			createUser("Teste", "teste@hotmail.com");			
		}

		User user = userRepository.findByEmailLike("bruno_owned@");
		System.out.println(user.getName());
		
	}
	
	public void createUser(String name, String email) {
		User user = new User(name, email);
		userRepository.save(user);
	}

}
