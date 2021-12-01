package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.*;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void add(User user) {
        sessionFactory.openSession().save(user);
    }

    @Override
    @Transactional
    public void add(Car car) {
        sessionFactory.openSession().save(car);
    }


    @Override
//    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.openSession().createQuery("from User", User.class);
        return query.getResultList();
    }


    @Override
    public User getUserByCar(String model, int series) {
        TypedQuery<User> query = sessionFactory.openSession().createQuery("FROM User AS u INNER JOIN FETCH u.car AS c " +
                "where c.model = :model and c.series = :series");
        query.setParameter("model", model).
                setParameter("series", series);

        return query.setMaxResults(1).getSingleResult();
    }
}
