/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanliquancaphe;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import quanliquancaphe.hibernate.HibernateUtils;
import quanliquancaphe.models.Ban;
import quanliquancaphe.models.Mon;
import quanliquancaphe.models.NhanVien;

/**
 *
 * @author admin
 */
public class Ultils {

    private static final SessionFactory factory = HibernateUtils.getSessionFactory();

    public static void addOrUpdateBan(Ban b) {
        Session session = factory.openSession();

        Transaction trans = session.beginTransaction();

        session.saveOrUpdate(b);

        trans.commit();

        session.close();
    }
    
    public static void deleteBans(Ban b){
        Session session = factory.openSession();

        Transaction trans = session.beginTransaction();

        session.delete(b);

        trans.commit();

        session.close();
    }
    public static List<NhanVien> getLogin() {
        Session session = factory.openSession();

        //Criteria cr = session.createCriteria(NhanVien.class);
        //Transaction r = session.beginTransaction();
        Query q = session.createQuery("from NhanVien");
        List<NhanVien> rs = q.list();     
        //r.commit();
        session.close();

        return rs;
    }

    public static void addOrUpdateMon(Mon m) {
        Session session = factory.openSession();

        Transaction trans = session.beginTransaction();

        session.saveOrUpdate(m);

        trans.commit();

        session.close();
    }
    
    public static void deleteMons(Mon m){
        Session session = factory.openSession();

        Transaction trans = session.beginTransaction();

        session.delete(m);

        trans.commit();

        session.close();
    }
    
}
