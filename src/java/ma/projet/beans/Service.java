package ma.projet.beans;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Service {
    @Id
    @GeneratedValue
    private long id;
    
    private String nom;
   
    @OneToMany(mappedBy = "service", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Employe> employees;

    // Constructors, getters, and setters...

    // Make sure to add a default constructor (required by Hibernate)
    public Service() {
    }

    public Service(String nom, List<Employe> employees) {
        this.nom = nom;
        this.employees = employees;
    }

    // Other methods...

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Employe> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employe> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Service{" + "id=" + id + ", nom=" + nom + ", employees=" + employees + '}';
    }
    
    

}
