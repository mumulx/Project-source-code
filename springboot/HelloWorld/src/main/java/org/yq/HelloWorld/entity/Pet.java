package org.yq.HelloWorld.entity;

public class Pet {
	private String nickName ;//松散写法nick-name
	private String strain ;
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getStrain() {
		return strain;
	}
	public void setStrain(String strain) {
		this.strain = strain;
	}
	@Override
	public String toString() {
		return "Pet [nickName=" + nickName + ", strain=" + strain + "]";
	}
	
}
