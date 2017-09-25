package main.java.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class File {
	private int id;
	private Date createDate;
	private String fileName;
	private long size;
	private String path;
	
	public File(){
		
	}
	
	public File(String path){
		this.path = path;
	}
	
	public File(String fileName, Date createDate, long size, String path){
		this.fileName = fileName;
		this.createDate = createDate;
		this.size = size;
		this.path = path;
	}
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getFileName(){
		return fileName;
	}
	
	public void setFileName(String fileName){
		this.fileName = fileName;
	}
	
	public Date getCreateDate(){
		return createDate;
	}
	
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	
	public long getSize(){
		return size;
	}
	
	public void setSize(long size){
		this.size = size;
	}
	
	public String getPath(){
		return path;
	}
	
	public void setPath(String path){
		this.path = path;
	}
	
	
	@Override
	public String toString(){
		return id+"|"+fileName+"|"+createDate+"|"+size+"|"+path;
	}
}
