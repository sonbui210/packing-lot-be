package com.sobu.parkinglot.api.auth.request;

import com.google.gson.Gson;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class LoginRequest implements Serializable {
	@NotBlank
  private String username;

	@NotBlank
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
