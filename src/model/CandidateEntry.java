package model;

import java.sql.Date;

public class CandidateEntry extends UserEntry {
	public CandidateEntry(String id) {
		super(id);
	}

	public CandidateEntry(String id, String name) {
		super(id, name);
	}

	public CandidateEntry(String id, String name, boolean sex, String address, Date birthday, String phone) {
		super(id, name, sex, address, birthday, phone, null);
	}

	public CandidateEntry(String name, boolean sex, String address, Date birthday, String phone) {
		super(name, sex, address, birthday, phone, null);
	}
}
