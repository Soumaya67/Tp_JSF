/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.domaine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.bean.ManagedBean;
import ma.projet.beans.Employe;
import ma.projet.beans.Service;
import ma.projet.service.EmployeService;
import ma.projet.service.ServiceService;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author soumaya
 */

@ManagedBean(name = "serviceBean")
public class ServiceBean{

    private Employe employe;
    private Service service;
    private ServiceService serviceService;
    private List<Service> services;
    private List<Employe> employes;
    private EmployeService employeService;
    private static ChartModel barModel;
    private Service selectedService;
    private List<Employe> collaborators;
    private List<Employe> subordonnes;

    public Service getSelectedService() {
        return selectedService;
    }

    public void setSelectedService(Service selectedService) {
        this.selectedService = selectedService;
    }

    public Employe getEmploye() {
        return employe;
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

    public EmployeService getEmployeService() {
        return employeService;
    }

    public void setEmployeService(EmployeService employeService) {
        this.employeService = employeService;
    }

    public List<Employe> getSubordonnes() {
        return subordonnes;
    }

    public void setSubordonnes(List<Employe> subordonnes) {
        this.subordonnes = subordonnes;
    }
    
    
    
    public ServiceBean() {
        service = new Service();
        serviceService = new ServiceService();
        employe = new Employe();
        employeService = new EmployeService();
    }
    
    public List<Employe> getEmployes() {
        if (employes == null) {
            employes = employeService.getAll();
        }
        return employes;
    }
    
    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }
    

    public List<Service> getservices() {
        if (services == null) {
            services = serviceService.getAll();
            
        }
        return services;
    }
    
    public void setServices(List<Service> services) {
        this.services = services;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String onCreateAction() {
        serviceService.create(service);
        service  = new Service();
        return null;
    }

    public void onDeleteAction() {
        service.setEmployees(null);
        serviceService.delete(service);
    }
    
    public void onEdit(RowEditEvent event) {
        service = (Service) event.getObject();
        service.setEmployees(null);
        serviceService.update(service);
    }

    public void load() {
        System.out.println(service.getNom());
        service = serviceService.getById((int) service.getId());
        getEmployes();
    }
    
    public void onCancel(RowEditEvent event) {
    }

    public void onEditm(RowEditEvent event) {
        employe = (Employe) event.getObject();
        service = serviceService.getById((int) this.employe.getService().getId());
        employe.setService(service);
        employe.getService().setNom(service.getNom());
        employeService.update(employe);
    }

    public String onDeleteActionm() {
        employeService.delete(employeService.getById((int) employe.getId()));
        return null;
    }
    
    public List<Employe> serviceLoad() {
        for (Employe m : employeService.getAll()) {
            if (m.getService().equals(service)) {
                employes.add(m);
            }
        }
        return employes;

    }
    
    
}
