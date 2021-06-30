package com.domiworld.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(force = true)
public class DomiWorldVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long userId;
	private String userName;
	private String password;
	private String email;
	private String lastLogin;

}
