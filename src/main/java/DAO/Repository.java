package DAO;


import Entity.DateTime;
import Entity.NodeBase;
import org.hibernate.Query;
import org.hibernate.Session;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Repository<T,V> {

private String ip;
private String date;
private String dateTime;
private String hash;
private T obT;
private V obV;

    public T getObT() {
        return obT;
    }

    public void setObT(T obT) {
        this.obT = obT;
    }


    public V getObV() {
        return obV;
    }

    public void setObV(V obV) {
        this.obV = obV;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public List<NodeBase> findByIpNode() {
        Session session = SessionFactory.getSessionFactory();
        session.beginTransaction();
        Query query1 = session.createQuery("FROM NodeBase where ipNode=:paramName");
        query1.setParameter("paramName",ip);
        List<NodeBase>nodeBaseList=query1.list();
        session.getTransaction().commit();
        session.clear();
        session.close();
        session.getSessionFactory().close();
        return nodeBaseList;
    }


    public List<NodeBase> selectDate() {
        Session session = SessionFactory.getSessionFactory();
        session.beginTransaction();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(getDate(), formatter);
        Query query1 = session.createQuery("FROM NodeBase where localDate=:paramName");
        query1.setParameter("paramName",date);
        List<NodeBase>nodeBaseList=query1.list();
        session.getTransaction().commit();
        session.clear();
        session.close();
        session.getSessionFactory().close();
        return nodeBaseList;
    }



    public List<NodeBase> localDateTimeReport() {
        Session session = SessionFactory.getSessionFactory();
        session.beginTransaction();
       /* DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(getHash(), formatter);*/
        Query query1 = session.createQuery("FROM  NodeBase nb where nb.hash =:paramName");
        query1.setParameter("paramName", hash);
        List<NodeBase>nodeList=query1.list();
        session.getTransaction().commit();
        session.clear();
        session.close();
        session.getSessionFactory().close();
        return nodeList;
    }

    public void setTimeZona() {
        Session session = SessionFactory.getSessionFactory();
       // session.beginTransaction();
        Query query1 = session.createNamedQuery("SET LOBAL time_zone = '+4:00'");
       // session.getTransaction().commit();
        session.clear();
        session.close();
        session.getSessionFactory().close();

    }




    public void save(){
        Session session = SessionFactory.getSessionFactory();
        session.beginTransaction();
        session.save(getObT());
        session.getTransaction().commit();
        session.clear();
        session.close();
        session.getSessionFactory().close();

    }


}

