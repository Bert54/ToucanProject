package toucan.modele.animation;

public enum AttributAnimation {

    COMPARAISON, // Compare 2 cases
    COMPARAISONVALCASE, // Compare une case avec la variable temporaire
    AFFECTATION, // Echange de la valeur de 2 cases
    AFFECTATIONVCASE, // Affectation d'une case par la valeur temporaire
    AFFECTATIONCVAL, // Affectation de la variable temporaire par une case
    AFFECTATIONECRASEMENTCASECASE; // Affectaction d'une case du tableau par une autre case
}

/**
    Enumeration permettant de designer une IAnimation par un attribut defini ici
 */