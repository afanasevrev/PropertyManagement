package com.example.Server;

import com.example.Server.hibernate.Estate;
import com.example.Server.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/estates")
    private String getListEstates(Model model) {
        List<Estate> allEstates = getEstates();
        model.addAttribute("estates", allEstates);
        return "estates";
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
}
