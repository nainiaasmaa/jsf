package domaines;

import entities.Employe;
import entities.Service;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.ChartSeries;
import service.EmployeService;
import service.ServiceService;
import javax.annotation.PostConstruct;

/**
 *
 * @author Lachgar
 */
@ManagedBean
@SessionScoped

public class EmployeBean {
    
    
    
    
    private List<Employe> employes;
    private EmployeService employeService;
    private Employe selectedEmploye;
     private List<ChartSeries> employeeChartData;
     private Employe employe ;
     private Service service;
     private ServiceService serviceService;
     
    
     
    
  

    /**
     * Creates a new instance of EmployeBean
     */
   public EmployeBean() {
        employeService = new EmployeService();
        employes = employeService.getAll(); // Initialiser la liste des employes
        employe = new Employe();
     
       
        
                
        }
   
@PostConstruct
public void init() {
    employeService = new EmployeService();
    employes = employeService.getAll(); // Initialiser la liste des employes
    employe = new Employe();
    employe.setService(new Service()); // Initialiser le service
    employe.setChefDe(new Employe());
    serviceService = new ServiceService();
}

   
  
   
  

  public void onCreateAction() {
    // Avant de créer un nouvel employé, assurez-vous que le service et le chefDe sont sélectionnés
    if (employe.getService() == null || employe.getChefDe() == null) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Veuillez sélectionner un service et un chef.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        return; // Arrêtez le traitement si l'une des conditions n'est pas remplie
    }

    employeService.create(employe);
    employes = employeService.getAll();

    employe = new Employe();
    employe.setService(new Service()); // Réinitialiser le service après la création
    employe.setChefDe(new Employe()); // Réinitialiser le chefDe après la création
}
    public void onEditAction(Employe selectedEmploye) {
    // Mettez à jour le employe avec celui sélectionné pour l'édition
    employe = selectedEmploye;
}
    public void onUpdateAction() {
        if (employe.getService() == null || employe.getChefDe() == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Veuillez sélectionner un service et un chef.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return; // Arrêtez le traitement si l'une des conditions n'est pas remplie
        }

        employeService.update(employe);
        employes = employeService.getAll();
        employe = new Employe();
        employe.setService(new Service()); // Réinitialiser le service après la création
    employe.setChefDe(new Employe());

        if (selectedEmploye != null && selectedEmploye.getId() == employe.getId()) {
            selectedEmploye = employeService.getById((int) employe.getId());
        }

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "La modification a été effectuée avec succès.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public void onDeleteAction(Employe employe) {
    // Implémentez la logique de suppression en utilisant le employe approprié
    employeService.delete(employe);

    // Ajoutez un message pour indiquer que la suppression a réussi
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "La suppression a été effectuée avec succès.");
    FacesContext.getCurrentInstance().addMessage(null, message);

    // Rafraîchissez la liste des employes
    employes = employeService.getAll();
}


    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public List<Employe> getEmployes() {
        return employes;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }
    
      public ChartModel initBarModel() {
    CartesianChartModel model = new CartesianChartModel();
    ChartSeries employes = new ChartSeries();
    employes.setLabel("employes");
    model.setAnimate(true);

    for (Object[] e : employeService.nbemployes()) {
        Service service = (Service) e[1];
        employes.set(service.getNom(), Integer.parseInt(e[0].toString()));
    }


    model.addSeries(employes);

    return model;
}


   
     
    
        
              
    

    

   
}
