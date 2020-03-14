package org.example;

import org.example.Exceptions.*;
import org.example.Repository.XMLFileRepository.NotaXMLRepo;
import org.example.Repository.XMLFileRepository.StudentXMLRepo;
import org.example.Repository.XMLFileRepository.TemaLabXMLRepo;
import org.example.Service.XMLFileService.NotaXMLService;
import org.example.Service.XMLFileService.StudentXMLService;
import org.example.Service.XMLFileService.TemaLabXMLService;
import org.example.Validator.*;
import org.example.UI.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ValidatorException {
        //System.out.println("Hello World!");
        StudentValidator vs=new StudentValidator();
        TemaLabValidator vt=new TemaLabValidator();
        NotaValidator vn=new NotaValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        TemaLabXMLRepo tmrepo=new TemaLabXMLRepo(vt,"TemaLaboratorXML.xml");
        NotaXMLRepo ntrepo=new NotaXMLRepo(vn,"NotaXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        TemaLabXMLService tmsrv=new TemaLabXMLService(tmrepo);
        NotaXMLService ntsrv=new NotaXMLService(ntrepo);
        ui ui=new ui(stsrv,tmsrv,ntsrv);
        ui.run();
    }
}