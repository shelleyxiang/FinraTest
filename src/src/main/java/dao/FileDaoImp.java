package main.java.dao;

import java.util.List;
import javax.persistence.*;
import org.springframework.stereotype.Repository;

import main.java.entity.File;


@Repository
public class FileDaoImp implements FileDao {
	
	@PersistenceContext
	EntityManager em;
	
	public File save(File f){
		em.persist(f);
		return f;
	}
	
	public File getFile(Integer id){
		return em.find(File.class, id);
	}
	
	public boolean checkExist(String path){
		Query q = em.createQuery("SELECT file from File file where file.path=:path");
		q.setParameter("path", path);
		try{
			q.getSingleResult();
			return true;
		}catch(NoResultException nre){
			return false;
		}
	}
	
	public List<File> getAll(){
		Query q = em.createQuery("SELECT file FROM File file");
		return q.getResultList();
	}
}
