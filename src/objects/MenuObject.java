package objects;

public class MenuObject {
    public int idMenu;
    public String nom, nomPhoto, jourMenu, etatLivraison;
    public double prix;

    public MenuObject(int idMenu, String nom, String nomPhoto, String jourMenu,String etatLivraison, double prix) {
        this.idMenu = idMenu;
        this.nom = nom;
        this.nomPhoto = nomPhoto;
        this.prix = prix;
        this.jourMenu = jourMenu;
        this.etatLivraison = etatLivraison;
    }
}
