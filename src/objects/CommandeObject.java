package objects;

public class CommandeObject {
    public int idCommande, idMenu, idUser, quantite, numCommande;
    public String nomMenu, etatLivraison;
    public double prix;
    public CommandeObject(int idCommande, int idMenu, String nomMenu, double prix, int idUser, int quantite, String etatLivraison, int numCommande) {
        this.idCommande = idCommande;
        this.idMenu = idMenu;
        this.nomMenu = nomMenu;
        this.prix = prix;
        this.idUser = idUser;
        this.quantite = quantite;
        this.etatLivraison = etatLivraison;
        this.numCommande = numCommande;
    }
}
