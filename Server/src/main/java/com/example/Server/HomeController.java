package com.example.Server;

import com.example.Server.hibernate.Estate;
import com.example.Server.hibernate.HibernateUtil;
import com.example.Server.hibernate.Suggestion;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/estates")
    private String getListEstates(Model model) {
        List<Estate> allEstates = getEstates();
        model.addAttribute("estates", allEstates);
        return "estates";
    }
    @GetMapping("/details/{id}")
    private String getDetails(Model model, @PathVariable("id") int id) {
        Estate estate = getEstates().get(id - 1);
        model.addAttribute("selectedEstate", estate);
        byte[] imageData = estate.getPhoto();

        String base64Image = Base64.getEncoder().encodeToString(imageData);
        model.addAttribute("imageData", base64Image);
        model.addAttribute("imageWidth", "300px");
        model.addAttribute("imageHeight", "300px");

        model.addAttribute("subscriber", new Subscriber());

        return "estate_details";
    }
    /**
     * Метод возвращает список квартир из БД
     * @return
     */
    public synchronized List<Estate> getEstates() {
        Transaction transaction = null;
        List<Estate> estates = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            estates = session.createQuery("from Estate", Estate.class).list();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return estates;
    }

    /**
     * Метод записывает в таблицу БД новую заявку от покупателя
     */
    public synchronized void writeSession(Suggestion suggestion) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            // Добавим в БД сессию
            session.persist(suggestion);
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
