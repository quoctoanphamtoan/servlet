/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student.dao;
 
import java.io.InputStream;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.student.model.Student;
import com.student.util.HibernateUtil;

/**
 *
 * @author TriPham
 */
public class StudentDao {
    public void saveStudent(Student student, InputStream inputStream) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

//    /**
//     * Update Customer
//     * @param user
//     */
//    public void updateCustomer(Customer user, InputStream inputStream) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            // start a transaction
//            transaction = session.beginTransaction();
//             if(inputStream!=null)
//            {
//                byte[] bytes = IOUtils.toByteArray(inputStream);
//                Blob blob = Hibernate.getLobCreator(session).createBlob(bytes);
//                user.setImage(blob);
//            }
//            // save the student object 
//            session.saveOrUpdate(user);
//            // commit transaction
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Delete Customer
//     * @param id
//     */
//    public void deleteCustomer(int id) {
//
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            // start a transaction
//            transaction = session.beginTransaction();
//
//            // Delete a user object
//            Customer user = session.get(Customer.class, id);
//            if (user != null) {
//                session.delete(user);
//                System.out.println("user is deleted");
//            }
//
//            // commit transaction
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Get Customer By ID
//     * @param id
//     * @return
//     */
    public Student getCustomer(int id) {

        Transaction transaction = null;
        Student student = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            student = session.get(Student.class, id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return student;
    }
//public CustomerDTO getCustomerDTO(int id) {
//
//        Transaction transaction = null;
//        Customer user = null;
//        CustomerDTO customerDTO=new CustomerDTO();
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            // start a transaction
//            transaction = session.beginTransaction();
//            // get an user object
//            user = session.get(Customer.class, id);
//            // commit transaction
//            transaction.commit();
//            customerDTO.setName(user.getName());
//            customerDTO.setEmail(user.getEmail());
//            customerDTO.setCountry(user.getCountry());
//            Blob blob= user.getImage();
//            if(blob!=null)
//            {
//            InputStream inputStream = blob.getBinaryStream();
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//             byte[] buffer = new byte[4096];
//             int bytesRead = -1;
//                 
//                while ((bytesRead = inputStream.read(buffer)) != -1) {
//                    outputStream.write(buffer, 0, bytesRead);                  
//                }
//                byte[] imageBytes = outputStream.toByteArray();
//                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
//                customerDTO.setBase64Image(base64Image);
//            }
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            
//            e.printStackTrace();
//        }
//        }
//        return customerDTO;
//    }
//
//    /**
//     * Get all Customers
//     * @return
//     */
    @SuppressWarnings("unchecked")
    public List <Student> getAllStudent() {

        Transaction transaction = null;
        List< Student >  listOfStudent = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            listOfStudent = session.createQuery("from Student").getResultList();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfStudent;
    }
//

}
