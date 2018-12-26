package com.demo.model;

/**
 * Users 实体类
 * 
 * @author
 * @date 2018-12-12
 */
public class Users implements java.io.Serializable {

	/**
	 * 序列化版本号
	 */

	/**
	 * id
	 */
	private Long id;

	/**
	 * name
	 */
	private String name;

	/**
	 * phone
	 */
	private String telephone;

	/**
	 * sex
	 */
	private Integer sex;

	/**
	 * age
	 */
	private Integer age;

	/**
	 * address
	 */
	private String address;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return this.address;
	}

}
