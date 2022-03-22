package dao;

import connection.HibernateUtil;
import model.entity.WeatherEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;

public class WeatherDao {

    private final Logger logger = LogManager.getLogger(WeatherDao.class);

    //Data Access Object - DAO, CRUD operations on database objects
    //-----Create-----

    public void save(WeatherEntity weatherEntity) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(weatherEntity);

            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        }
    }

    //-----Read-----

    public WeatherEntity findById(Integer id) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            WeatherEntity weatherEntity = session.get(WeatherEntity.class, id);

            transaction.commit();

            return weatherEntity;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        }

        return null;
    }

    public List<WeatherEntity> findByCity(String cityName) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            List<WeatherEntity> weatherEntityList = session.createQuery("SELECT w FROM WeatherEntity AS w WHERE " +
                            "w.cityName = :city_name", WeatherEntity.class)
                    .setParameter("city_name", cityName)
                    .getResultList();

            transaction.commit();

            return weatherEntityList;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        }

        return null;
    }

    public List<WeatherEntity> findAllWeathers() {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            List<WeatherEntity> weatherEntityList = session.createQuery("SELECT w FROM WeatherEntity AS w",
                            WeatherEntity.class)
                    .getResultList();

            transaction.commit();

            return weatherEntityList;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        }

        return Collections.emptyList();
    }

    public List<WeatherEntity> findByCityAndDate(String cityName, String date) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            List<WeatherEntity> weatherEntityList = session.createQuery("SELECT w FROM WeatherEntity AS w WHERE " +
                            "w.cityName = :city_name AND to_char(w.date, 'YYYY/MM/DD') = :date", WeatherEntity.class)
                    .setParameter("city_name", cityName)
                    .setParameter("date", date)
                    .getResultList();

            transaction.commit();

            return weatherEntityList;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public List<WeatherEntity> findByDate(String date) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            List<WeatherEntity> weatherEntityList = session.createQuery("SELECT w FROM WeatherEntity AS w WHERE " +
                            "to_char(w.date, 'YYYY/MM/DD') = :date", WeatherEntity.class)
                    .setParameter("date", date)
                    .getResultList();

            transaction.commit();

            return weatherEntityList;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public List<WeatherEntity> findByCoordinatesAndDate(String longitude, String latitude, String date) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            List<WeatherEntity> weatherEntityList = session.createQuery("SELECT w FROM WeatherEntity AS w WHERE " +
                            "to_char(w.lon) = :lon AND to_char(w.lat) = :lat AND to_char(w.date, 'YYYY/MM/DD') = :date",
                            WeatherEntity.class)
                    .setParameter("lon", longitude)
                    .setParameter("lat", latitude)
                    .setParameter("date", date)
                    .getResultList();

            transaction.commit();

            return weatherEntityList;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        }
        return null;
    }

    //-----Update-----

    public void update(WeatherEntity weatherEntity) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.update(weatherEntity);

            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        }
    }

    //-----Delete-----

    public void delete(WeatherEntity weatherEntity) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.delete(weatherEntity);

            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        }
    }
}
