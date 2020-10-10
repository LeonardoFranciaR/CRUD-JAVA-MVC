package Modelo;

/**
 *
 * @author LeonardoFrancia
 */
public class ClassEstudiante {
    int id;
    String nom;
    String ape;
    String corr;
    
    public ClassEstudiante(){}

    public ClassEstudiante(int id, String nom, String ape, String corr) {
        this.id = id;
        this.nom = nom;
        this.ape = ape;
        this.corr = corr;
    }
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getApe() {
        return ape;
    }

    public void setApe(String ape) {
        this.ape = ape;
    }

    public String getCorr() {
        return corr;
    }

    public void setCorr(String corr) {
        this.corr = corr;
    }
    
    
}
