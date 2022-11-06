package org.softwareengine.utils.ui;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import javafx.collections.ObservableList;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import org.softwareengine.config.languages;
import org.softwareengine.core.model.*;
import org.softwareengine.utils.service.DatabaseService;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public JasperPrint getBankBranchs (ObservableList<banks> bank) throws JRException, IOException, SQLException, WriterException {


        JasperReport reports = null ;
        JasperPrint jp = null ;

        reports = JasperCompileManager.compileReport(
                getClass().getResourceAsStream("/report/branchs.jrxml")
        );

        jp = JasperFillManager.fillReport(reports,null, DatabaseService.connection);

        List<banks> list = new ArrayList<banks>();
        List<banks> list2 = bank ;

        /*banks model = new banks();


        int size = model.getInfo().size();

        for (int i=0 ; i < size ; i++) {
            banks temp = new banks();
            temp.setName(model.getInfo().get(i).getName());
            temp.setReferenceNumber(model.getInfo().get(i).getReferenceNumber());
            list.add(temp) ;
        }
*/
        languages lang = new languages();

        String data = user.getId()+","+user.getName();
//        ByteArrayInputStream in = new ByteArrayInputStream(createQR(data).toByteArray());

//        Image img = ImageIO.read(in) ;

        HashMap params = new HashMap();
        params.put("REPORT_RESOURCE_BUNDLE", lang.get());
//        params.put("QR",img);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list2);

        JasperPrint print = JasperFillManager.fillReport(reports,params,dataSource);

//        in.close();

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


        int size = model.getInfoTransactions().size();

        for (int i=0 ; i < size ; i++) {
            Transaction temp = new Transaction();

            temp.setId(model.getInfoTransactions().get(i).getId());
            temp.setItem(model.getInfoTransactions().get(i).getItem());
//            temp.setStore(model.getInfoTransactions().get(i).getStore());
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

    public JasperPrint getDistrubumentReport (List<Transaction> lists,String report) throws JRException, IOException, WriterException {


        JasperReport reports = null ;
        JasperPrint jp = null ;

        reports = JasperCompileManager.compileReport(
                getClass().getResourceAsStream("/report/"+report)
        );

        jp = JasperFillManager.fillReport(reports,null, DatabaseService.connection);

        List<Transaction> list = new ArrayList<Transaction>();




        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lists);

        String data = user.getId()+","+user.getName();
//        ByteArrayInputStream in = new ByteArrayInputStream(createQR(data).top);
        File f = createQR(data) ;
//        InputStream in = new FileInputStream(f);

//        Image img = ImageIO.read(in) ;

//        in.close();
//        f.delete();

        languages lang = new languages();

        HashMap params = new HashMap();
        params.put("REPORT_RESOURCE_BUNDLE", lang.get());
//        params.put("QR",img);
        params.put("user",user.getName());

        JasperPrint print = JasperFillManager.fillReport(reports,params,dataSource);

        return print ;
    }
    public JasperPrint getCoffee (Transaction model) throws JRException, IOException, SQLException {


        JasperReport reports = null ;
        JasperPrint jp = null ;

        reports = JasperCompileManager.compileReport(
                getClass().getResourceAsStream("/report/Coffee.jrxml")
        );

        jp = JasperFillManager.fillReport(reports,null, DatabaseService.connection);


        reports.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
        Map<String,Object> para = new HashMap<>();

        Image i = ImageIO.read(new File("add.png"));

        para.put("name","name");
        para.put("Pimg",i) ;



        JasperPrint print = JasperFillManager.fillReport(reports,para);
        return print ;

    }

//    public ByteArrayOutputStream createQR(String data) throws IOException, WriterException {
    public File createQR(String data) throws IOException, WriterException {
//        String path = "demo.png";
        File f = new File("C:\\Users\\melad\\Desktop\\19\\qr.png");
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap
                = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        BitMatrix matrix = new MultiFormatWriter().encode(
                new String(data.getBytes("UTF-8"), "UTF-8"),
                BarcodeFormat.QR_CODE, 200, 200);

        ByteArrayOutputStream b = new ByteArrayOutputStream();
//        MatrixToImageWriter.writeToStream(matrix,"png",b);
        MatrixToImageWriter.writeToFile(matrix,"png",f);
        b.close();
        return f ;
    }
}