package com.hanhui.stumanage.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户持久化类
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class User implements Serializable {

	private static final long serialVersionUID = -3488782624705018398L;

	private Long userId;

	private String userCode;

	private String userName;

	private Integer status;
}
