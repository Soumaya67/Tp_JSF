/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.domaine;


import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import ma.projet.beans.Employe;
import ma.projet.beans.Service;
import ma.projet.service.EmployeService;
import ma.projet.service.ServiceService;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.chart.ChartModel;


/**
 *
 * @author soumaya
 */
@ManagedBean(name="employeBean")
@RequestScoped
public class EmployeBean{

    private Employe employe;
    private Service service;
    private List<Employe> employes;
    
    private EmployeService employeService;
    private ServiceService serviceService;
    private static ChartModel barModel;
    private String file;
    private String fileName;
    private UploadedFile uploadedFile;
    private UploadedFile Ufile;
    private Employe superviseur;

    
   public EmployeBean() {
        employe = new Employe();
        superviseur = new Employe();
        Ufile = null;
        employeService = new EmployeService();
        serviceService = new ServiceService();

    }
   public UploadedFile getUFile() {
        return Ufile;
    }

    public void setUFile(UploadedFile ufile) {
        this.Ufile = ufile;
    }
   
   public List<Employe> getEmployes() {
        if (employes == null) {
            employes = employeService.getAll();
        }
        return employes;
    }
   
    public Employe getEmploye() {
        return employe;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }
     
    public void setEmployeService(EmployeService employeService) {
        this.employeService = employeService;
    }
    
    public EmployeService getEmployeService() {
        return employeService;
    }
    
    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
    
    public ServiceService getServiceService() {
        return serviceService;
    }
    
    public void setServiceService(ServiceService serviceService) {
        this.serviceService = serviceService;
    }
    
    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
    
    public String onCreateAction() {
        if(superviseur != null){
            superviseur = employeService.getById((int) superviseur.getId());
            employe.setChef(superviseur);
        }
        if(Ufile != null){
            employe.setPhoto(Ufile.getFileName());
        }
        employeService.create(employe);
        employe = new Employe();
        superviseur = new Employe();
        return null;
    }
    
   
    
   
    
}
