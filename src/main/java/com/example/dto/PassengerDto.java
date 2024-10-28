package com.example.dto;

import lombok.Data;

@Data
public class PassengerDto {
    private String name;
    private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "PassengerDto [name=" + name + ", age=" + age + "]";
	}
    
}