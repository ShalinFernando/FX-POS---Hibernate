package lk.ijse.dep.app.util;

import lk.ijse.dep.app.entity.Customer;
import lk.ijse.dep.app.entity.Item;
import lk.ijse.dep.app.entity.Order;
import lk.ijse.dep.app.entity.OrderDetail;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;

public class HibernateUtil {

    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory(){

        File propFile = new File("resources/application.properties");

        StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder().loadProperties(propFile).build();

        Metadata metadata = new MetadataSources(registry)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(OrderDetail.class)
                .buildMetadata();

        return metadata.buildSessionFactory();

    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

}
