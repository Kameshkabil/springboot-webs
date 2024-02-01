package com.example.springbootwebs.service;

import com.example.springbootwebs.Exception.UserNotFoundException;
import com.example.springbootwebs.model.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class EmployeeService {
    @Autowired
    EntityManager entityManager;

    @Autowired
    JavaMailSender javaMailSender;

    @Transactional
    public String createNewAccount(Employee employee){
//        Employee employee1 = entityManager.find(Employee.class,employee.getEmail());
//        if (employee1==null){
            employee.setFirstName(employee.getFirstName());
            employee.setLastName(employee.getLastName());
            employee.setEmail(employee.getEmail());
            employee.setPhone(employee.getPhone());
            employee.setLocation(employee.getLocation());
            employee.setActivationToken(UUID.randomUUID().toString());
            entityManager.persist(employee);

            String activationLink = "http://localhost:8080/api/employees/activate/" + employee.getActivationToken();
            sendRegistrationMailToEmployee(employee.getEmail(),
                    "Activate Your Account","Click the following link to activate your account: "+activationLink);
            return "Account Created Successfully ✅. Check Your Email✉️ For Account Activation.";
//        }else {
//            throw new UserNotFoundException("Already Exist Account");
//        }
    }


    @Transactional
    public String activateEmployee(String activationToken){
        TypedQuery<Employee> query = entityManager.createQuery("SELECT e FROM Employee e WHERE e.active = false AND e.activationToken = :activationToken",
                Employee.class);
        query.setParameter("activationToken",activationToken);
        Employee employee =  query.getSingleResult();
        if (employee!=null){
            employee.setActive(true);
            entityManager.persist(employee);
            return "Your Account Activated ✅";
        }else {
            return "Invalid Activation Token";
        }
    }


    public void sendRegistrationMailToEmployee(String toEmail,String subject,String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("kameshkabil9@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }
}
