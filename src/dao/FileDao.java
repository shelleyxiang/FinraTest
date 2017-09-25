package dao;

import java.util.List;

import entity.File;

public interface FileDao {
	File save(File f);
	File getFile(Integer id);
	boolean checkExist(String path);
	List<File> getAll();
}
