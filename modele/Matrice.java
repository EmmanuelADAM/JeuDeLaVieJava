package modele;

import java.util.Random;


/**
 * matrice des cellules du jeu de la vie
 *
 * @author Emmanuel Adam
 */
public class Matrice {
    /**
     * grille a l'instant t
     */
    Cellule[][] grille;

    /**
     * taille de la grille
     */
    int taille;
    /**
     * pourcentage de cellules vivantes initialement
     */
    double densite = 0.1;

    public Matrice() {
        grille = new Cellule[20][20];
        taille = 20;
        init();
    }

    /**
     * constructeur par defaut, initialise la taille, le nombre de cellules initiales,
     * ainsi que les grilles a l'instnat t et t-1
     */
    public Matrice(int taille, double densite) {
        this.taille = taille;
        grille = new Cellule[taille][taille];
        this.densite = densite;
        init();
    }


    /**
     * initialise les grilles a l'instant t et t-1
     * Une cellule n'est liée qu'à la grille au temps t-1
     * un clone de chaque cellule est placée dans la grille au temps t (la grille qui est affichée)
     * L'ajout de cellules  actives se fait par appel de la fonction initHasard
     */
    void init() {
        for (int i = 0; i < taille; i++)
            for (int j = 0; j < taille; j++) {
                grille[i][j] = new Cellule(grille, i, j, false);
            }
        initHasard();
    }

    /**
     * place au hasard des cellulles vivantes dans les grilles a l'instant t et t-1
     * pour chaque cellule, on tire un réel au hasard entre 0 et 1.
     * si le réel < densité, alors la cellule est activée
     * (il y a donc densité% de chance que la cellule en (i,j) soit activée)
     */
    void initHasard() {
        Random r = new Random();
        for (Cellule[] ligne:grille)
            for (Cellule c:ligne)
                if (r.nextDouble() < densite)
                    c.vivante = c.etatSuivant = true;
    }

    /**change l'etat de la cellule en i,j*/
    public void change(int i, int j)
    {
        grille[i][j].vivante = !grille[i][j].vivante;
        grille[i][j].switchColor();
    }

    /**
     * recopie l'etat de la grille dans l'ancienne
     */
    public void avancer() {
        for (Cellule[] ligne:grille)
            for (Cellule c:ligne)
                c.avancer();
    }


    /**
     * demande a toutes les cellules de la grille de calculer l'etat suivant 
     */
    public void calculer() {
        for (Cellule[] ligne:grille)
            for (Cellule c:ligne)
                c.evoluer();
    }

    /**
     * @return the grille
     */
    public Cellule[][] getGrille() {
        return grille;
    }

}
