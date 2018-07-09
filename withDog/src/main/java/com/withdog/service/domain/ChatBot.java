package com.withdog.service.domain;

public class ChatBot {
	private User user;
	private String questionNo;
	private String question;
	private String answer;
	private String questionCount;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getQuestionNo() {
		return questionNo;
	}

	public void setQuestionNo(String questionNo) {
		this.questionNo = questionNo;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(String questionCount) {
		this.questionCount = questionCount;
	}

	@Override
	public String toString() {
		return "ChatBot [questionNo=" + questionNo + ", question=" + question + ", answer=" + answer
				+ ", questionCount=" + questionCount + "]";
	}

}
