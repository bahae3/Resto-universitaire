package objects;

public class CommandeObject {
    public int idMenu, idUser, quantite;
    public String nomMenu, etatLivraison;
    public double prix;
    public CommandeObject(int idMenu, String nomMenu, double prix, int idUser, int quantite, String etatLivraison) {
        this.idMenu = idMenu;
        this.nomMenu = nomMenu;
        this.prix = prix;
        this.idUser = idUser;
        this.quantite = quantite;
        this.etatLivraison = etatLivraison;
    }
}
