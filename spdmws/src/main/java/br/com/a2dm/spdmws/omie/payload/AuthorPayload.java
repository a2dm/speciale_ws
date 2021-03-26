package br.com.a2dm.spdmws.omie.payload;

public class AuthorPayload {

	private String email;
	private String name;
	private String userId;

	public AuthorPayload() {
		super();
	}

	public AuthorPayload(String email, String name, String userId) {
		super();
		this.email = email;
		this.name = name;
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "AuthorPayload [email=" + email + ", name=" + name + ", userId=" + userId + "]";
	}

}
