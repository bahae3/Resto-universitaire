package objects;

public class MenuObject {
    public int idMenu;
    public String nom, description, nomPhoto;
    public double prix;

    public MenuObject(int idMenu, String nom, String description, String nomPhoto, double prix) {
        this.idMenu = idMenu;
        this.nom = nom;
        this.description = description;
        this.nomPhoto = nomPhoto;
        this.prix = prix;
    }
}
