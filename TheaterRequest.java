package com.project.BTS.dto;
import com.project.BTS.entity.Admin;

//import Lombook.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TheaterRequest {

	private String name;
	private String address;
	private Long citId;
	private Admin admin;
}
