package com.lx.myclass;

public class Student {

	private int score;

	public Student(int score) {
		this.score = score;
	}
	public Student() {
	}
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "Student [score=" + score + "]";
	}
	
	
}
