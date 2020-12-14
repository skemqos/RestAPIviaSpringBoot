package demo;

public class StudentData {

	private int studentId;
	private String studentName;
	private String monthOfBirth;
	private String bloodGroup;

	public StudentData() {
		super();
	}

	public StudentData(int studentId, String studentName, String monthOfBirth, String bloodGroup) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.monthOfBirth = monthOfBirth;
		this.bloodGroup = bloodGroup;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getMonthOfBirth() {
		return monthOfBirth;
	}

	public void setMonthOfBirth(String monthOfBirth) {
		this.monthOfBirth = monthOfBirth;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

}
