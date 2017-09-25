package main.java.dao;

import java.util.List;

import main.java.entity.File;


public interface FileDao {
	static File save(File f) {
		// TODO Auto-generated method stub
		return null;
	}
	File getFile(Integer id);
	boolean checkExist(String path);
	List<File> getAll();
}
