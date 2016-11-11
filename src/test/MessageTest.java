package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

import bean.Message;
import bean.User;

public class MessageTest {
	private SessionFactory sessionfactory;
	private org.hibernate.Session session;
	private Transaction transaction;
	

	@Test
	public void test() throws IOException {
		//创建配置对象
		Configuration configuration = new Configuration().configure();
		//创建注册对象
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		//创建会话工厂
		sessionfactory = configuration.buildSessionFactory(serviceRegistry);
		//创建会话对象
		session = sessionfactory.openSession();
		//开启事务
		transaction = session.beginTransaction();
		
		Message message = new Message();
		message.setId(1);
		message.setCommand("我是命令");
		message.setContent("呢荣");
		message.setDescription("我是描述");
		
		//获得blob类型数据
		File file = new File("h:"+File.separator+"Download"+File.separator+"Others"+File.separator+"test.jpg");
		InputStream iStream = new FileInputStream(file);
		java.sql.Blob image =  Hibernate.getLobCreator(session).createBlob(iStream, iStream.available());
		message.setPicture(image);
		User user = new User("xiaoling","nan",11);
		message.setUser(user);
		session.save(message);
		session.getTransaction().commit();
		session.close();
	}
	@Test
	public void testGetMessage(){
		if (session!=null) {
			Message message = (Message) session.get(Message.class, 0);
			System.out.println(message);
		}else {
			System.out.println("session对象为空");
			//创建配置对象
			Configuration configuration = new Configuration().configure();
			//创建注册对象
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			//创建会话工厂
			sessionfactory = configuration.buildSessionFactory(serviceRegistry);
			//创建会话对象
			session = sessionfactory.openSession();
			//开启事务
			transaction = session.beginTransaction();
		}
		Message message = (Message) session.get(Message.class, 0);
		System.out.println(message);
	}
	@Test
	public void testLoadMessage(){
		Message message = (Message) session.load(Message.class, 1);
		System.out.println(message);
	}
	@Test
	public void testUpdateMessage(){
		Message message = (Message) session.get(Message.class, 1);
		message.setCommand("原来是你");
		session.update(message);		
	}
	@Test
	public void testDeleteMessage(){
		Message message = (Message) session.get(Message.class, 1);
		session.delete(message);
	}


}
