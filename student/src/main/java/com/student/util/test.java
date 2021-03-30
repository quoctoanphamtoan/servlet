package com.student.util;

import org.hibernate.SessionFactory;

public class test {
public static void main(String[] args) {
	SessionFactory sessionFactory= new HibernateUtil().getSessionFactory();
}
}
