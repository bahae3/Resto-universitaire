package objects;

public class MenuObject {
    public int idMenu, quantite;
    public String nom, nomPhoto, description, jourMenu, etatLivraison;
    public double prix;

    public MenuObject(int idMenu, int qtt, String nom, String nomPhoto, String description, String jourMenu, String etatLivraison, double prix) {
        this.idMenu = idMenu;
        this.quantite = qtt;
        this.nom = nom;
        this.description = description;
        this.nomPhoto = nomPhoto;
        this.prix = prix;
        this.jourMenu = jourMenu;
        this.etatLivraison = etatLivraison;
    }
}
