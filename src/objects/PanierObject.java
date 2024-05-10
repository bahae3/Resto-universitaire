package objects;

public class PanierObject {
    public int idMenu, quantite;
    public String nomPhoto, nomMenu;
    public double prix;

    public PanierObject(int idMenu, int quantite, String nomPhoto, String nomMenu, double prix) {
        this.idMenu = idMenu;
        this.quantite = quantite;
        this.nomPhoto = nomPhoto;
        this.nomMenu = nomMenu;
        this.prix = prix;
    }
}
