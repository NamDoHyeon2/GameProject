package background;


public class item {
	private int itemNum;
	private int grade;
	private String name;
	private String ability;
	
	public item(int itemNum, int grade, String name, String ability) {
		this.itemNum = itemNum;
		this.grade = grade;
		this.name = name;
		this.ability = ability;
	}

	public int getItemNum() {
		return itemNum;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbility() {
		return ability;
	}

	public void setAbility(String ability) {
		this.ability = ability;
	}
	
	
}