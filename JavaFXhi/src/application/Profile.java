package application;

public class Profile extends User{
	private String image;
	private String name;
	private String Username;
	private String password;
	private int age;
	private String gender;
	private String instagram;
	private String facebook;
	private String department;
	private int grade;
	private String MBTI;
	private String movies;
	private String music;
	private String book;
	private String celebrity;
	private int drinkinghabit;
	private int smokinghabit;
	private String sexualperference;
	private String purpose;
	private int ID;
	
	public Profile(String username, String password) {
		this.Username = username;
		this.password = password;
		this.image = "file:/Users/winnie/Downloads/icon/avatar.png";
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getMBTI() {
		return MBTI;
	}

	public void setMBTI(String mBTI) {
		MBTI = mBTI;
	}

	public String getMovies() {
		return movies;
	}

	public void setMovies(String movies) {
		this.movies = movies;
	}

	public String getMusic() {
		return music;
	}

	public void setMusic(String music) {
		this.music = music;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getCelebrity() {
		return celebrity;
	}

	public void setCelebrity(String celebrity) {
		this.celebrity = celebrity;
	}

	public int getDrinkinghabit() {
		return drinkinghabit;
	}

	public void setDrinkinghabit(int drinkinghabit) {
		if( drinkinghabit == 1) {
		 this.drinkinghabit = 1;
		}
		else {
			this.drinkinghabit=0;
		}
	}

	public int getSmokinghabit() {
		return smokinghabit;
	}

	public void setSmokinghabit(int smokinghabit) {
		if( smokinghabit == 1) {
			 this.smokinghabit = 1;
			}
			else {
				this.smokinghabit=0;
			}
	}

	public String getSexualperference() {
		return sexualperference;
	}

	public void setSexualperference(String sexualperference) {
		this.sexualperference = sexualperference;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	
	

}
