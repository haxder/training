package thinhtv.training.entity;

import java.util.Date;

public class History {
	private Date 日付;
	private String イベント;
	private String 実施者;
	private String 部署;
	private String email;
	private String message;
	public Date get日付() {
		return 日付;
	}
	public void set日付(Date 日付) {
		this.日付 = 日付;
	}
	public String getイベント() {
		return イベント;
	}
	public void setイベント(String イベント) {
		this.イベント = イベント;
	}
	public String get実施者() {
		return 実施者;
	}
	public void set実施者(String 実施者) {
		this.実施者 = 実施者;
	}
	public String get部署() {
		return 部署;
	}
	public void set部署(String 部署) {
		this.部署 = 部署;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public History(Date 日付, String イベント, String 実施者, String 部署, String email, String message) {
		this.日付 = 日付;
		this.イベント = イベント;
		this.実施者 = 実施者;
		this.部署 = 部署;
		this.email = email;
		this.message = message;
	}
}
