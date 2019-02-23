package jdbc.jdbctools;

//��ϰ��
//�����������������˼������������ӽ����ݿ��һ������¼��������һ��������

public class Student{
	//��������ҪСд�ģ�����д�Ĳ��淶��
	private int FlowID;
	private int Type;
	private String IDCard;
	private String ExamCard;
	private String StudentName;
	private String Location;
	private int Grade;
	
	public Student(int flowID, int type, String iDCard, String examCard, String studentName, String location, int grade) {

		FlowID = flowID;
		Type = type;
		IDCard = iDCard;
		ExamCard = examCard;
		StudentName = studentName;
		Location = location;
		Grade = grade;
	}
	public Student(){
		super();
	}
	
	public int getFlowID() {
		return FlowID;
	}
	public void setFlowID(int flowID) {
		FlowID = flowID;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	public String getIDCard() {
		return IDCard;
	}
	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}
	public String getExamCard() {
		return ExamCard;
	}
	public void setExamCard(String examCard) {
		ExamCard = examCard;
	}
	public String getStudentName() {
		return StudentName;
	}
	public void setStudentName(String studentName) {
		StudentName = studentName;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public int getGrade() {
		return Grade;
	}
	public void setGrade(int grade) {
		Grade = grade;
	}
	@Override
	public String toString() {
		return "Student [FlowID=" + FlowID + ", Type=" + Type + ", IDCard=" + IDCard + ", ExamCard=" + ExamCard
				+ ", StudentName=" + StudentName + ", Location=" + Location + ", Grade=" + Grade + "]\n";
	}
}
