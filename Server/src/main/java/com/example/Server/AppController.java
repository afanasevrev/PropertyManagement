package com.example.Server;

import com.example.Server.hibernate.Admins;
import com.example.Server.hibernate.Estate;
import com.example.Server.hibernate.HibernateUtil;
import com.example.Server.hibernate.Suggestion;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AppController {

    @GetMapping("/")
    private String getInfo(){
        return "Разработка комплекса программных приложений для агентства недвижимости";
    }
    @GetMapping("/suggestions")
    private List<Suggestion> suggestions() {
        return getSuggestions();
    }
    @GetMapping("/authorized/{login}&{password}")
    private String getAuthorized(@PathVariable String login, @PathVariable String password) {
        List<Admins> admins = new ArrayList<>();
        admins = getAdmins();

        boolean authorized = false;

        for (Admins admin: admins) {
            if (admin.getLogin().equals(login) && admin.getPassword().equals(password)) {
                authorized = true;
            } else {}
        }

        if (authorized) {
            return "AUTHORIZED";
        } else {
            return "UNAUTHORIZED";
        }
    }

    /**
     * Метод возвращает список админов из БД
     * @return
     */
    public synchronized List<Admins> getAdmins() {
        Transaction transaction = null;
        List<Admins> admins = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            admins = session.createQuery("from Admins", Admins.class).list();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return admins;
    }

    /**
     * Метод возвращает список сделок из БД
     * @return
     */
    public synchronized List<Suggestion> getSuggestions() {
        Transaction transaction = null;
        List<Suggestion> suggestions = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            suggestions = session.createQuery("from Suggestion", Suggestion.class).list();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return suggestions;
    }
}
