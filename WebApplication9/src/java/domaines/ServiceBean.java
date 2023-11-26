/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaines;

import entities.Service;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import service.ServiceService;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author Lachgar
 */
@ManagedBean
@SessionScoped

public class ServiceBean {
    
    private Service service;
    private List<Service> services;
    private ServiceService serviceService;
    private Service selectedService;
    private Object employeService;
  

    /**
     * Creates a new instance of ServiceBean
     */
   public ServiceBean() {
        serviceService = new ServiceService();
        services = serviceService.getAll(); // Initialiser la liste des services
        service = new Service();
       
    }

    public void onCreateAction (){
        
        System.out.println(service.getNom());
        serviceService.create(service);
        // Mettez à jour la liste des services
        services = serviceService.getAll();

        System.out.println(services);
        service = new Service();
    }
    public void onEditAction(Service selectedService) {
    // Mettez à jour le service avec celui sélectionné pour l'édition
    service = selectedService;
}
    public void onUpdateAction() {
   
    serviceService.update(service);
    services = serviceService.getAll(); // Mettez à jour la liste des services
    service = new Service(); // Réinitialisez le service après la mise à jour

    // Mettez à jour le service sélectionné si c'est le même service
    if (selectedService != null && selectedService.getId() == service.getId()) {
        selectedService = serviceService.getById((int) service.getId());
    }

    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "La modification a été effectuée avec succès.");
    FacesContext.getCurrentInstance().addMessage(null, message);
}

    public void onDeleteAction(Service service) {
    // Implémentez la logique de suppression en utilisant le service approprié
    serviceService.delete(service);

    // Ajoutez un message pour indiquer que la suppression a réussi
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "La suppression a été effectuée avec succès.");
    FacesContext.getCurrentInstance().addMessage(null, message);

    // Rafraîchissez la liste des services
    services = serviceService.getAll();
}
    
    


    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public Service getSelectedService() {
        return selectedService;
    }

    public void setSelectedService(Service selectedService) {
        this.selectedService = selectedService;
    }
    
    


    

   
}