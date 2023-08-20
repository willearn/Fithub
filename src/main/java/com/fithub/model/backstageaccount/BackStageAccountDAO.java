package com.fithub.model.backstageaccount;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BackStageAccountDAO implements IBackStageAccountDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public BackStageAccount insert(BackStageAccount bBean) {
		Session session = sessionFactory.openSession();
		BackStageAccount  resultBean = session.get(BackStageAccount.class, bBean.getEmployeeaccount());
		System.out.println(resultBean);
		if(resultBean==null) {
			System.out.println("is null");
			session.persist(bBean);
			session.flush();
			session.close();
			return bBean;
		}
		session.close();
		return null;
	}
	
	@Override
	public BackStageAccount selectByAccount(String employeeaccount) {
		Session session = sessionFactory.openSession();
		Query<BackStageAccount> query = session.createQuery("from BackStageAccount where employeeaccount='" + employeeaccount + "'",BackStageAccount.class);
		BackStageAccount resultBean = query.uniqueResult();
		session.close();
		return resultBean;
	}
	
	@Override
	public List<BackStageAccount> selectAll(){
		Session session = sessionFactory.openSession();
		Query<BackStageAccount> query = session.createQuery("from BackStageAccount",BackStageAccount.class);
		List<BackStageAccount> lists = query.list();
		session.close();
		return lists;
	}
	
	@Override
	public BackStageAccount update(String employeeaccount,String employeepassword,int loa) {
		Session session = sessionFactory.openSession();
		BackStageAccount resultBean = session.get(BackStageAccount.class, employeeaccount);
		if(resultBean!=null) {
			resultBean.setEmployeepassword(employeepassword);
			resultBean.setLoa(loa);
		}
		session.close();
		return resultBean;
	}
	
	@Override
	public boolean deleteByAccount(String employeeaccount) {
		Session session = sessionFactory.openSession();
		BackStageAccount resultBean = session.get(BackStageAccount.class, employeeaccount);
		if(resultBean!=null) {
			session.remove(resultBean);
			session.flush();
			session.close();
			return true;
		}
		session.close();
		return false;
	}
	
	@Override
	public boolean checkLogin(BackStageAccount backstageaccount) {
		Session session = sessionFactory.openSession();
		String hql = "from BackStageAccount where employeeaccount=:account and employeepassword=:pwd";
		Query<BackStageAccount>  query = session.createQuery(hql,BackStageAccount.class);
		query.setParameter("account", backstageaccount.getEmployeeaccount());
		query.setParameter("pwd", backstageaccount.getEmployeepassword());
		
		BackStageAccount resultBean = query.uniqueResult();
		
		session.close();
		
		if(resultBean!=null) {
			return true;
		}
		
		return false;
	}
}
