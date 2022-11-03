package com.springRestApi.EmployeeManagementRestApi.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class Error {
	private int code;
	private String message;
}
