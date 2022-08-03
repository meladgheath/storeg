package org.softwareengine.utils.ui;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.softwareengine.core.model.Item;
import org.softwareengine.utils.service.DatabaseService;

import java.io.File;
import java.io.FileInputStream;
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

            list.add(temp) ;

        }

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);

        JasperPrint print = JasperFillManager.fillReport(reports,null,dataSource);

//        JasperViewer.viewReport(print,false);
        return print ;

    }
}