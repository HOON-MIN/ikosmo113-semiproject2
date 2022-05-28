package model;

import dao.Adao;

import dto.Chat;

public class model {

	public void addChat(Chat c) {
		Adao.getDao().addChat(c);
	}
}
