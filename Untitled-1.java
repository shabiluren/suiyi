//Java Group Project_StudentManagement源码
//NetBeans IDE 6.5 环境
package studentmanager;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
class Student implements java.io.Serializable{
String number,name,specialty,grade,borth,sex;
public Student(){};
public void setNumber(String number){ this.number=number;}
public String getNumber(){ return number;}
public void setName(String name){ this.name=name;}
public String getName(){ return name;}
public void setSex(String sex){ this.sex=sex;}
public String getSex(){ return sex;}
public void setSpecialty(String specialty){ this.specialty=specialty;}
public String getSpecialty(){ return specialty;}
public void setGrade(String grade){ this.grade=grade;}
public String getGrade(){ return grade;}
public void setBorth(String borth){ this.borth=borth;}
public String getBorth(){ return borth;}
}
public class StudentManager extends JFrame{
JLabel lb=new JLabel("录入请先输入记录，查询、删除请先输入学号，修改是对查询" +
"内容改后的保存！");
JTextField 学号,姓名,专业,年级,出生;
JRadioButton 男,女;
ButtonGroup group=null;
JButton 录入,查询,删除,修改,显示;
JPanel p1,p2,p3,p4,p5,p6,pv,ph;
Student 学生=null;
Hashtable 学生散列表=null;
File file=null;
FileInputStream inOne=null;
ObjectInputStream inTwo=null;
FileOutputStream outOne=null;
ObjectOutputStream outTwo=null;
public StudentManager(){
super("学生基本信息管理系统");
学号=new JTextField(10);
姓名=new JTextField(10);
专业=new JTextField(10);
年级=new JTextField(10);
出生=new JTextField(10);
group=new ButtonGroup();
男=new JRadioButton("男",true);
女=new JRadioButton("女",false);
group.add(男);
group.add(女);
录入=new JButton("录入");
查询=new JButton("查询");
删除=new JButton("删除");
修改=new JButton("修改");
显示=new JButton("显示");
录入.addActionListener(new InputAct());
查询.addActionListener(new InquestAct());
修改.addActionListener(new ModifyAct());
删除.addActionListener(new DeleteAct());
显示.addActionListener(new ShowAct());
修改.setEnabled(false);
p1=new JPanel();
p1.add(new JLabel("学号:",JLabel.CENTER));
p1.add(学号);
p2=new JPanel();
p2.add(new JLabel("姓名:",JLabel.CENTER));
p2.add(姓名);
p3=new JPanel();
p3.add(new JLabel("性别:",JLabel.CENTER));
p3.add(男);
p3.add(女);
p4=new JPanel();
p4.add(new JLabel("专业:",JLabel.CENTER));
p4.add(专业);
p5=new JPanel();
p5.add(new JLabel("年级:",JLabel.CENTER));
p5.add(年级);
p6=new JPanel();
p6.add(new JLabel("出生:",JLabel.CENTER));
p6.add(出生);
pv=new JPanel();
pv.setLayout(new GridLayout(6,1));
pv.add(p1);
pv.add(p2);
pv.add(p3);
pv.add(p4);
pv.add(p5);
pv.add(p6);
ph=new JPanel();
ph.add(录入);
ph.add(查询);
ph.add(修改);
ph.add(删除);
ph.add(显示);
file=new File("学生信息.txt");
学生散列表=new Hashtable();
if(!file.exists()){
try{
FileOutputStream out=new FileOutputStream(file);
ObjectOutputStream objectOut=new ObjectOutputStream(out);
objectOut.writeObject(学生散列表);
objectOut.close();
out.close();
}
catch(IOException e){}
}
Container con=getContentPane();
con.setLayout(new BorderLayout());
con.add(lb, BorderLayout.NORTH);
con.add(pv, BorderLayout.CENTER);
con.add(ph, BorderLayout.SOUTH);
setDefaultCloseOperation(EXIT_ON_CLOSE);
setBounds(100,100,600,300);
setVisible(true);
}
public static void main(String[] args) {new StudentManager();}
class InputAct implements ActionListener{
public void actionPerformed(ActionEvent e){
修改.setEnabled(false);
String number="";
number=学号.getText();
if(number.length()>0){
try{
inOne=new FileInputStream(file);
inTwo=new ObjectInputStream(inOne);
学生散列表=(Hashtable)inTwo.readObject();
inOne.close();
inTwo.close();
}
catch(Exception ee){System.out.println("创建散列表出现问题！");}
if(学生散列表.containsKey(number)){
String warning="该生信息已存在，请到修改页面修改！";
JOptionPane.showMessageDialog(null,warning,"警告",
JOptionPane.WARNING_MESSAGE);
}//end if1
else{
String m="该生信息将被录入！";
int ok=JOptionPane.showConfirmDialog(null,m,"确认",
JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
if(ok==JOptionPane.YES_OPTION){
String name=姓名.getText();
String specialty=专业.getText();
String grade=年级.getText();
String borth=出生.getText();
String sex=null;
if(男.isSelected()){sex=男.getText();}
else{sex=女.getText();}
学生=new Student();
学生.setNumber(number);
学生.setName(name);
学生.setSpecialty(specialty);
学生.setGrade(grade);
学生.setBorth(borth);
学生.setSex(sex);
try{
outOne=new FileOutputStream(file);
outTwo=new ObjectOutputStream(outOne);
学生散列表.put(number,学生);
outTwo.writeObject(学生散列表);
outTwo.close();
outOne.close();
}
catch(Exception ee){System.out.println("输出散列表出现问题！");}
学号.setText(null);
姓名.setText(null);
专业.setText(null);
年级.setText(null);
出生.setText(null);
}
}//end else1
}//end if0
else{
String warning="必须输入学号！";
JOptionPane.showMessageDialog(null,warning,
"警告",JOptionPane.WARNING_MESSAGE);
}//end else0
}//end actionPerformed
}//end class
class InquestAct implements ActionListener{
public void actionPerformed(ActionEvent e){
String number="";
number=学号.getText();
if(number.length()>0){
try{
inOne=new FileInputStream(file);
inTwo=new ObjectInputStream(inOne);
学生散列表=(Hashtable)inTwo.readObject();
inOne.close();
inTwo.close();
}
catch(Exception ee){System.out.println("散列表有问题！");}
if(学生散列表.containsKey(number)){
修改.setEnabled(true);
Student stu=(Student)学生散列表.get(number);
姓名.setText(stu.getName());
专业.setText(stu.getSpecialty());
年级.setText(stu.getGrade());
出生.setText(stu.getBorth());
if(stu.getSex().equals("男")){男.setSelected(true);}
else{女.setSelected(true);}
}
else{
修改.setEnabled(false);
String warning="该学号不存在！";
JOptionPane.showMessageDialog(null,warning,
"警告",JOptionPane.WARNING_MESSAGE);
}
}
else{
修改.setEnabled(false);
String warning="必须输入学号！";
JOptionPane.showMessageDialog(null,warning,
"警告",JOptionPane.WARNING_MESSAGE);
}
}
}
class ModifyAct implements ActionListener{
public void actionPerformed(ActionEvent e){
String number=学号.getText();
String name=姓名.getText();
String specialty=专业.getText();
String grade=年级.getText();
String borth=出生.getText();
String sex=null;
if(男.isSelected()){sex=男.getText();}
else{sex=女.getText();}
Student 学生=new Student();
学生.setNumber(number);
学生.setName(name);
学生.setSpecialty(specialty);
学生.setGrade(grade);
学生.setBorth(borth);
学生.setSex(sex);
try{
outOne=new FileOutputStream(file);
outTwo=new ObjectOutputStream(outOne);
学生散列表.put(number, 学生);
outTwo.writeObject(学生散列表);
outTwo.close();
outOne.close();
学号.setText(null);
姓名.setText(null);
专业.setText(null);
年级.setText(null);
出生.setText(null);
}
catch(Exception ee){
System.out.println("录入修改出现异常!");
修改.setEnabled(false);
}
}
}
class DeleteAct implements ActionListener{
public void actionPerformed(ActionEvent e){
修改.setEnabled(false);
String number=学号.getText();
if(number.length()>0){
try{
inOne=new FileInputStream(file);
inTwo=new ObjectInputStream(inOne);
学生散列表=(Hashtable)inTwo.readObject();
inOne.close();
inTwo.close();
}
catch(Exception ee){}
if(学生散列表.containsKey(number)){
Student stu=(Student)学生散列表.get(number);
姓名.setText(stu.getName());
专业.setText(stu.getSpecialty());
年级.setText(stu.getGrade());
出生.setText(stu.getBorth());
if(stu.getSex().equals("男")){男.setSelected(true);}
else{女.setSelected(true);}
}
String m="确定要删除该学生的记录吗？";
int ok=JOptionPane.showConfirmDialog(null,m,"确认",
JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
if(ok==JOptionPane.YES_OPTION){
学生散列表.remove(number);
try{
outOne=new FileOutputStream(file);
outTwo=new ObjectOutputStream(outOne);
outTwo.writeObject(学生散列表);
outTwo.close();
outOne.close();
学号.setText(null);
姓名.setText(null);
专业.setText(null);
年级.setText(null);
出生.setText(null);
}
catch(Exception ee){System.out.println(ee);}
}
else if(ok==JOptionPane.NO_OPTION){
学号.setText(null);
姓名.setText(null);
专业.setText(null);
年级.setText(null);
出生.setText(null);
}
else{
String warning="该学号不存在！";
JOptionPane.showMessageDialog(null,warning,
"警告",JOptionPane.WARNING_MESSAGE);
}
}
else{
String warning="必须输入学号！";
JOptionPane.showMessageDialog(null,warning,
"警告",JOptionPane.WARNING_MESSAGE);
}
}
}
class ShowAct implements ActionListener{
public void actionPerformed(ActionEvent e){
new StudentShow(file);
}
}
class StudentShow extends JDialog{
Hashtable 学生散列表= null;
JTextArea 显示=null;
FileInputStream inOne=null;
ObjectInputStream inTwo=null;
File file=null;
public StudentShow(File file){
super(new JFrame(),"显示对话框");
this.file=file;
显示=new JTextArea(16,30);
try{
inOne=new FileInputStream(file);
inTwo=new ObjectInputStream(inOne);
学生散列表=(Hashtable)inTwo.readObject();
inOne.close();
inTwo.close();
}
catch(Exception ee){}
if(学生散列表.isEmpty())显示.append("目前还没有学生的信息记录！\n");
else{
显示.setText("学号 姓名 性别 专业 年级 出生\n");
for(Enumeration enm=学生散列表.elements();enm.hasMoreElements();){
Student stu=(Student)enm.nextElement();
String sex="";
if(stu.getSex().equals("男"))sex="男";
else sex="女";
String str=stu.getNumber()+","+stu.getName()+","+sex+","
+stu.getSpecialty()+","+stu.getGrade()+","+stu.getBorth()+"\n";
显示.append(str);
}
}
JScrollPane scroll=new JScrollPane(显示);
Container con=getContentPane();
con.add("Center",scroll);
con.validate();
setVisible(true);
setBounds(200,200,400,300);
addWindowListener(new WindowAdapter(){
public void windowClosing(WindowEvent e){setVisible(false);}
}
);
}
}
}