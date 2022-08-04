package org.softwareengine.utils.ui;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.softwareengine.core.model.Amount;
import org.softwareengine.core.model.Item;
import org.softwareengine.core.model.Transaction;
import org.softwareengine.core.model.banks;
import org.softwareengine.utils.service.DatabaseService;


import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class report {


    public static JasperPrint getPrint() throws JRException {

        String path = report.class.getResource("/report/item.jrxml") .toString();




        JasperReport report = null ;
        JasperPrint jp = null ;
        report = JasperCompileManager.compileReport("item.jrxml");
        jp = JasperFillManager.fillReport(report,null, DatabaseService.connection);

        return jp ;
    }


    public JasperPrint getItemReport () throws JRException, FileNotFoundException, SQLException {


        JasperReport reports = null ;
        JasperPrint jp = null ;
//        report = JasperCompileManager.compileReport(new FileInputStream(new File("newnew.jrxml")));

//        report = JasperCompileManager.compileReport(new FileInputStream(new File("Cherry_2.jrxml")));


        reports = JasperCompileManager.compileReport(
                getClass().getResourceAsStream("/report/itemReport.jrxml")
        );

        jp = JasperFillManager.fillReport(reports,null, DatabaseService.connection);

        List<Item> list = new ArrayList<Item>();

        Item model = new Item();


        int size = model.getInfo().size();

        for (int i=0 ; i < size ; i++) {
            Item temp = new Item();
            temp.setName(model.getInfo().get(i).getName());
            temp.setCode(model.getInfo().get(i).getCode());
            temp.setTypeName(model.getInfo().get(i).getTypeName());

            list.add(temp) ;

        }

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);

        JasperPrint print = JasperFillManager.fillReport(reports,null,dataSource);

//        JasperViewer.viewReport(print,false);
        return print ;

    }
    public JasperPrint getBankBranchs () throws JRException, FileNotFoundException, SQLException {


        JasperReport reports = null ;
        JasperPrint jp = null ;

        reports = JasperCompileManager.compileReport(
                getClass().getResourceAsStream("/report/branchs.jrxml")
        );

        jp = JasperFillManager.fillReport(reports,null, DatabaseService.connection);

        List<banks> list = new ArrayList<banks>();

        banks model = new banks();


        int size = model.getInfo().size();

        for (int i=0 ; i < size ; i++) {
            banks temp = new banks();
            temp.setName(model.getInfo().get(i).getName());
            temp.setReferenceNumber(model.getInfo().get(i).getReferenceNumber());


            list.add(temp) ;

        }

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);

        JasperPrint print = JasperFillManager.fillReport(reports,null,dataSource);

//        JasperViewer.viewReport(print,false);
        return print ;

    }


    public JasperPrint getAmountReport () throws JRException, FileNotFoundException, SQLException {


        JasperReport reports = null ;
        JasperPrint jp = null ;

        reports = JasperCompileManager.compileReport(
                getClass().getResourceAsStream("/report/amountReport.jrxml")
        );

        jp = JasperFillManager.fillReport(reports,null, DatabaseService.connection);

        List<Amount> list = new ArrayList<Amount>();

        Amount model = new Amount();


        int size = model.getInfo().size();

        for (int i=0 ; i < size ; i++) {
            Amount temp = new Amount();

            temp.setId(model.getInfo().get(i).getId());
            temp.setItem(model.getInfo().get(i).getItem());
            temp.setStore(model.getInfo().get(i).getStore());
            temp.setNum(model.getInfo().get(i).getNum());

            list.add(temp) ;

        }

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);

        JasperPrint print = JasperFillManager.fillReport(reports,null,dataSource);

//        JasperViewer.viewReport(print,false);
        return print ;

    }

    public JasperPrint getDistrubumentReport () throws JRException, FileNotFoundException, SQLException {


        JasperReport reports = null ;
        JasperPrint jp = null ;

        reports = JasperCompileManager.compileReport(
                getClass().getResourceAsStream("/report/disbursementReport.jrxml")
        );

        jp = JasperFillManager.fillReport(reports,null, DatabaseService.connection);

        List<Transaction> list = new ArrayList<Transaction>();

        Transaction model = new Transaction();


        int size = model.getInfo().size();

        for (int i=0 ; i < size ; i++) {
            Transaction temp = new Transaction();

            temp.setId(model.getInfoTransactions().get(i).getId());
            temp.setItem(model.getInfoTransactions().get(i).getItem());
            temp.setStore(model.getInfoTransactions().get(i).getStore());
            temp.setBank(model.getInfoTransactions().get(i).getBank());
            temp.setNumber(model.getInfoTransactions().get(i).getNumber());
            temp.setDate(model.getInfoTransactions().get(i).getDate());


            list.add(temp) ;

        }

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);

        JasperPrint print = JasperFillManager.fillReport(reports,null,dataSource);

//        JasperViewer.viewReport(print,false);
        return print ;

    }


}