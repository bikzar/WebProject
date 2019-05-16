package by.epam.webproject.voitenkov.model.entity;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 14, 2019
 */
public class User {

	private long userId;
	private String name;
	private String secondName;
	private LocalDate birthDate;
	private boolean isAdmin;
	private String login;
	private String password;
	private List<BankAccount> bankAccountList;

	public User(long userId, String name, String secondName,
			LocalDate birthDate, boolean isAdmin, String login, String password,
			List<BankAccount> bankAccountList) {
		this.userId = userId;
		this.name = name;
		this.secondName = secondName;
		this.birthDate = birthDate;
		this.isAdmin = isAdmin;
		this.login = login;
		this.password = password;
		this.bankAccountList = bankAccountList;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		if (userId > 0) {
			this.userId = userId;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != null) {
			this.name = name;
		}
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		if (secondName != null) {
			this.secondName = secondName;
		}
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		if (birthDate != null) {
			this.birthDate = birthDate;
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		if (login != null) {
			this.login = login;
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (password != null) {
			this.password = password;
		}
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public List<BankAccount> getBankAccountList() {
		return bankAccountList;
	}

	public void setBankAccountList(List<BankAccount> bankAccountList) {
		if (bankAccountList != null) {
			this.bankAccountList = bankAccountList;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bankAccountList == null) ? 0 : bankAccountList.hashCode());
		result = prime * result
				+ ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + (isAdmin ? 1231 : 1237);
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((secondName == null) ? 0 : secondName.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		User other = (User) obj;
		if (bankAccountList == null) {
			if (other.bankAccountList != null) {
				return false;
			}
		} else if (!bankAccountList.equals(other.bankAccountList)) {
			return false;
		}
		if (birthDate == null) {
			if (other.birthDate != null) {
				return false;
			}
		} else if (!birthDate.equals(other.birthDate)) {
			return false;
		}
		if (isAdmin != other.isAdmin) {
			return false;
		}
		if (login == null) {
			if (other.login != null) {
				return false;
			}
		} else if (!login.equals(other.login)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (secondName == null) {
			if (other.secondName != null) {
				return false;
			}
		} else if (!secondName.equals(other.secondName)) {
			return false;
		}
		if (userId != other.userId) {
			return false;
		}
		return true;
	}

	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", secondName="
				+ secondName + ", birthDate=" + birthDate + ", isAdmin="
				+ isAdmin + ", login=" + login + ", password=" + password
				+ ", bankAccountList=" + bankAccountList + "]";
	}
}
