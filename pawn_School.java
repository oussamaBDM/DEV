import extensions.File;
import extensions.CSVFile;
class pawn_School extends Program {
    pawn newPion(type_couleur couleur,type_pion pion){
        pawn p = new pawn();
        p.couleur=couleur;
        p.caracteristique=pion;
        return p;
    }
    joueur newjoueur(String Nom, type_couleur couleur){
        joueur p=new joueur();
        p.nom=Nom;
        p.couleur=couleur;
        return p;
    }
    void testdeterminer_case(){
    assertEquals(toString(newPion(type_couleur.Blanche,type_pion.Vide)),toString(determiner_case(1,1)));
    assertEquals(toString(newPion(type_couleur.Noir,type_pion.Vide)),toString(determiner_case(2,1)));
    }
    pawn determiner_case(int idxLigne,int idxColumn){
        if((idxLigne+idxColumn)%2==0){
                return newPion(type_couleur.Blanche,type_pion.Vide);
        }
        return newPion(type_couleur.Noir,type_pion.Vide);
    }
    //verifie si la fonction tout String qui prend une seul valeur renvoie bien le caractere voulue en fonction du type_couleur et de caracteristique
    void testTostring(){
        pawn test_pion= newPion(type_couleur.Noir,type_pion.Pion);  
        assertEquals("♙ ",toString(test_pion));
        test_pion= newPion(type_couleur.Blanche,type_pion.Pion);  
        assertEquals("♟ ",toString(test_pion));
        test_pion= newPion(type_couleur.Noir,type_pion.Reine);   
        assertEquals("⛁ ",toString(test_pion));
        test_pion= newPion(type_couleur.Blanche,type_pion.Reine);   
        assertEquals("⛃ ",toString(test_pion));
        test_pion= newPion(type_couleur.Noir,type_pion.Vide);   
        assertEquals("⬜",toString(test_pion));
        test_pion= newPion(type_couleur.Blanche,type_pion.Vide);   
        assertEquals("⬛",toString(test_pion));
    }    
    //fonction qui permet de selectionner quelle caractere afficher en fonction du type de couleur et du type de pion
    String toString(pawn nouveau_pion){
        String c="";
        if(nouveau_pion.caracteristique==type_pion.Pion){
            if(nouveau_pion.couleur==type_couleur.Blanche){
                c="♟ ";
            }
            else if(nouveau_pion.couleur==type_couleur.Noir){
                c="♙ ";
            }
        }
        else if(nouveau_pion.caracteristique==type_pion.Reine){
             if(nouveau_pion.couleur==type_couleur.Blanche){
                c="⛃ ";
            }
            else if(nouveau_pion.couleur==type_couleur.Noir){
                c="⛁ ";
            }
        }
        else if(nouveau_pion.caracteristique== type_pion.Vide){
            if(nouveau_pion.couleur==type_couleur.Blanche){
                c="⬛";
            }
            else if(nouveau_pion.couleur==type_couleur.Noir){
                c="⬜";
            }
        }
        return c;
    }
       void testInitialiserPion(){
        pawn[][] tab=initialiser_pion();
        for(int i=0;i<length(tab);i++){
            for(int y=0;y<length(tab);y++){
                if(tab[i][y]==null){
                    println("probleme" +i+' '+y);
                }
            }
        }
   }
    //fonction qui permet d'inititaliser le tableau de pion au debut du jeux
    pawn[][] initialiser_pion(){
        pawn[][] tableau_a_initialiser=new pawn[8][8];
        for(int idxLigne=0;idxLigne<4;idxLigne++){
            for(int idxColumn=0;idxColumn<8;idxColumn++){
                if(idxLigne<2){
                    tableau_a_initialiser[idxLigne][idxColumn]=newPion(type_couleur.Noir,type_pion.Pion);
                    tableau_a_initialiser[7-idxLigne][7-idxColumn]=newPion(type_couleur.Blanche,type_pion.Pion);
                }
                else{
                    tableau_a_initialiser[idxLigne][idxColumn]=determiner_case(idxLigne,idxColumn);
                    tableau_a_initialiser[7-idxLigne][7-idxColumn]=determiner_case(7-idxLigne,7-idxColumn);
                }

            }
        }
        return tableau_a_initialiser;
    }
    void testtoString(){
        assertEquals("0♙ ♙ ♙ ♙ ♙ ♙ ♙ ♙ \n1♙ ♙ ♙ ♙ ♙ ♙ ♙ ♙ \n2⬛⬜⬛⬜⬛⬜⬛⬜\n3⬜⬛⬜⬛⬜⬛⬜⬛\n4⬛⬜⬛⬜⬛⬜⬛⬜\n5⬜⬛⬜⬛⬜⬛⬜⬛\n6♟ ♟ ♟ ♟ ♟ ♟ ♟ ♟ \n7♟ ♟ ♟ ♟ ♟ ♟ ♟ ♟ \n A B C D E F G H\n",toString(initialiser_pion()));
    }
    //fonction qui permet d'initialiser le tableau sous forme de chaine de caractere
    String toString(pawn[][]tab){
        String s="";
        for(int idxLigne=0; idxLigne<length(tab,1);idxLigne++){
            s+=idxLigne;
            for(int idxColumn=0; idxColumn<length(tab,2);idxColumn++){
                s=s+toString(tab[idxLigne][idxColumn]);
            }
            s+="\n";
        }
        s+=" A B C D E F G H\n";
        return s;
    }
    boolean est_possible_coordonnee(int idxLigne_debut,int idxColumn_debut,int idxLigne_fin,int idxColumn_fin){
        if((idxColumn_debut>0)&&(idxLigne_debut>0)&&(idxLigne_fin>0)&&(idxColumn_fin>0)){
            if((idxColumn_debut<8)&&(idxLigne_debut<8)&&(idxLigne_fin<8)&&(idxColumn_fin<8)){
                return true;
            }
        }
        return false;
    }
    void testest_bon_pion(){
        assertTrue(est_bon_pion(newjoueur("Aubin",type_couleur.Noir),initialiser_pion(),0,1));
        assertFalse(est_bon_pion(newjoueur("Aubin",type_couleur.Blanche),initialiser_pion(),0,1));
    }
    //fonction qui verifie si le joueur a saisie une coordonnée valide en fonction du camp du joueur
    boolean est_bon_pion(joueur pnj,pawn[][]tab,int idxLigne,int idxColumn){
        boolean test=false;
        if(idxLigne>0&&idxColumn>0&&idxLigne<8&&idxColumn<8){
            if(tab[idxLigne][idxColumn].caracteristique!=type_pion.Vide){
                if(pnj.couleur==type_couleur.Blanche){
                if(tab[idxLigne][idxColumn].couleur==type_couleur.Blanche){
                    println("retourne true pour la condition joueur=Blanc");
                    test=true;
                }
                }
                else if(pnj.couleur==type_couleur.Noir){
                    if(tab[idxLigne][idxColumn].couleur==type_couleur.Noir){
                        println("retourne true pour la condition joueur=Noir");
                        test=true;
                    }
                }
            }
        }
        println("retourne false");
        return test;
    }
    /*void testcoordonnée_possible_pion(){
        assertTrue(coordonnée_possible_deplacement(initialiser_pion(),2,2,3,3));
    }*/
   void testpion_adverse_diagonal_gauche(){
    pawn[][] tab=new pawn[][]{
                            {newPion(type_couleur.Blanche,type_pion.Vide) , newPion(type_couleur.Noir,type_pion.Pion) , newPion(type_couleur.Noir,type_pion.Pion)},
                            {newPion(type_couleur.Blanche,type_pion.Pion) , newPion(type_couleur.Blanche,type_pion.Pion) , newPion(type_couleur.Blanche,type_pion.Pion)},
                            {newPion(type_couleur.Blanche,type_pion.Vide) , newPion(type_couleur.Blanche,type_pion.Vide) , newPion(type_couleur.Blanche,type_pion.Vide)}};
        
        assertTrue(pion_adverse_diagonal_gauche(tab,0,2,1,1));
        pawn[][] tab2=new pawn[][]{
                            {newPion(type_couleur.Blanche,type_pion.Vide) , newPion(type_couleur.Noir,type_pion.Pion) , newPion(type_couleur.Noir,type_pion.Pion)},
                            {newPion(type_couleur.Blanche,type_pion.Pion) , newPion(type_couleur.Noir,type_pion.Pion) , newPion(type_couleur.Blanche,type_pion.Pion)},
                            {newPion(type_couleur.Blanche,type_pion.Vide) , newPion(type_couleur.Blanche,type_pion.Vide) , newPion(type_couleur.Blanche,type_pion.Pion)}};
        assertTrue(pion_adverse_diagonal_gauche(tab2,2,2,1,1));
        pawn[][] tab3=new pawn[][]{
                            {newPion(type_couleur.Blanche,type_pion.Vide) , newPion(type_couleur.Noir,type_pion.Pion) , newPion(type_couleur.Noir,type_pion.Pion)},
                            {newPion(type_couleur.Blanche,type_pion.Pion) , newPion(type_couleur.Noir,type_pion.Vide) , newPion(type_couleur.Blanche,type_pion.Pion)},
                            {newPion(type_couleur.Blanche,type_pion.Vide) , newPion(type_couleur.Blanche,type_pion.Vide) , newPion(type_couleur.Blanche,type_pion.Pion)}};
        assertFalse(pion_adverse_diagonal_gauche(tab3,2,2,1,1));
         //commande de debugage pour comprendre erreur
        /*println();
        for(int i=0;i<length(tab2,1);i++){
            for(int y=0;y<length(tab2,2);y++){
                print(toString(tab2[i][y]));
            }
            println();
        }*/
        
   }
    //fonction qui verifie si l'on peut manger un pion adverse du coté gauche par apport a l'affichage (utile pour savoir si l'on doi manger un pion par la gauche)
    boolean pion_adverse_diagonal_gauche(pawn[][]tab,int idxLigne_debut,int idxColumn_debut,int idxLigne_fin,int idxColumn_fin){
        //println("Vérification des indices : (" + idxLigne_debut + "," + idxColumn_debut + ") à (" + idxLigne_fin + "," + idxColumn_fin + ")");
        if((tab[idxLigne_debut][idxColumn_debut].couleur==type_couleur.Noir)&&(tab[idxLigne_debut][idxColumn_debut].caracteristique!=type_pion.Vide)){
            if((idxColumn_debut>1)){
                //println("arrive a passer la condition (idxColumn_debut>0) &&(idxColumn_fin>0))");
                //verifie si un pion du camp adverse est en diagonale de 1 par 1 maximum
                if((tab[idxLigne_debut+1][idxColumn_debut-1].couleur==type_couleur.Blanche)&&(tab[idxLigne_debut+1][idxColumn_debut-1]==tab[idxLigne_fin][idxColumn_fin])&&(tab[idxLigne_debut][idxColumn_debut].caracteristique!=type_pion.Vide)){
                    //println("Pion trouvé en diagonale avant gauche : Couleur - " + tab[idxLigne_debut + 1][idxColumn_debut - 1].couleur + ", Caractéristique - " + tab[idxLigne_debut + 1][idxColumn_debut - 1]);
                    //println("arrive a passer la condition (tab[idxLigne_debut+1][idxColumn_debut-1]==tab[idxLigne_fin][idxColumn_fin])");
                    if(tab[idxLigne_debut+2][idxColumn_debut-2].caracteristique==type_pion.Vide){
                        //println("La case derrière le pion adverse est vide");
                        return true;
                    }
                }
            }
        }
        else if((tab[idxLigne_debut][idxColumn_debut].couleur==type_couleur.Blanche)&&(tab[idxLigne_debut][idxColumn_debut].caracteristique!=type_pion.Vide)){
            if(((idxLigne_debut>1)&&(idxColumn_debut>1))){
                //println("test condition >1 reussi");
                if((tab[idxLigne_debut-1][idxColumn_debut-1]==tab[idxLigne_fin][idxColumn_fin])&&(tab[idxLigne_debut-1][idxColumn_debut-1].caracteristique!=type_pion.Vide)&&(tab[idxLigne_debut-1][idxColumn_debut-1].couleur==type_couleur.Noir)){
                    //verifie si un pion du camp adverse est en diagonale de 1 par 1 maximum
                    //println("Pion adverse en diagonale arrière gauche trouvé");
                    //verifie si la deuxieme diagonal est vide
                    if(tab[idxLigne_debut-2][idxColumn_debut-2].caracteristique==type_pion.Vide){
                        ///println("La case derrière le pion adverse est vide");
                        return true;
                    }
                }
            } 
        }
        return false;   
    }
    void testpion_adverse_diagonal_droite(){
        pawn[][] tab1=new pawn[][]{
                            {newPion(type_couleur.Blanche,type_pion.Vide) , newPion(type_couleur.Noir,type_pion.Pion) , newPion(type_couleur.Noir,type_pion.Vide)},
                            {newPion(type_couleur.Blanche,type_pion.Pion) , newPion(type_couleur.Noir,type_pion.Pion) , newPion(type_couleur.Blanche,type_pion.Pion)},
                            {newPion(type_couleur.Blanche,type_pion.Pion) , newPion(type_couleur.Blanche,type_pion.Vide) , newPion(type_couleur.Blanche,type_pion.Pion)}};
                            
        println();
        for(int i=0;i<length(tab1,1);i++){
            for(int y=0;y<length(tab1,2);y++){
                print(toString(tab1[i][y]));
            }
            println();
        }
        assertTrue(pion_adverse_diagonal_droite(tab1,2,0,1,1));
    }
    boolean pion_adverse_diagonal_droite(pawn[][]tab,int idxLigne_debut,int idxColumn_debut,int idxLigne_fin,int idxColumn_fin){
        //println("est rentré dans la fonction");
        if(tab[idxLigne_debut][idxColumn_debut].couleur==type_couleur.Noir){
            //println("Vérification des indices : (" + idxLigne_debut + "," + idxColumn_debut + ") à (" + idxLigne_fin + "," + idxColumn_fin + ")");
            //regarde si la position du pion permet de le deplacer== ne peut pas le deplacer en dehors du tableau et faire planter le jeux
            if((idxLigne_debut<(length(tab,1)-1))&&(idxColumn_debut<(length(tab,2)-1))&&(tab[idxLigne_debut][idxColumn_debut].caracteristique!=type_pion.Vide))
                //println("arrive a passer la condition (idxColumn_debut>0) &&(idxColumn_fin>0))");
                //verifie si un pion du camp adverse est en diagonale de 1 par 1 maximum
                if((tab[idxLigne_debut+1][idxColumn_debut+1]==tab[idxLigne_fin][idxColumn_fin])&&((tab[idxLigne_fin][idxColumn_fin].couleur==type_couleur.Blanche))&&(tab[idxLigne_debut][idxColumn_debut].caracteristique!=type_pion.Vide)){
                    println("avant derniere etape passer pour les pions Noir");
                    //verifie si la case de en diagonale du tableau est vide pour pouvoir depasser le pion adverse et prendre la place de la case vide 
                    if(tab[idxLigne_debut+2][idxColumn_debut+2].caracteristique==type_pion.Vide){
                        println("valeur retourné a true");
                        return true;
                    }
                }
        }
        else if((tab[idxLigne_debut][idxColumn_debut].couleur==type_couleur.Blanche)&&(tab[idxLigne_debut][idxColumn_debut].caracteristique!=type_pion.Vide)){
            //println("Vérification des indices : (" + idxLigne_debut + "," + idxColumn_debut + ") à (" + idxLigne_fin + "," + idxColumn_fin + ")");
            if((idxLigne_debut>1)&&(idxColumn_debut<(length(tab,2)-1)))
                //println("test taille pion noir");
                if((tab[idxLigne_debut-1][idxColumn_debut+1]==tab[idxLigne_fin][idxColumn_fin])&&(tab[idxLigne_debut-1][idxColumn_debut+1].couleur==type_couleur.Noir)&&(tab[idxLigne_fin][idxColumn_fin].caracteristique!=type_pion.Vide)){
                    println("avant derniere etape passer pour les pions Blanc");
                    if(tab[idxLigne_debut-2][idxColumn_debut+2].caracteristique==type_pion.Vide){
                        println("valeur retourné a true");
                        return true;
                    }
                }
        }
        return false;   
    }
    void testest_possible_deplacement(){
        pawn[][] tab1=new pawn[][]{
                            {newPion(type_couleur.Blanche,type_pion.Vide) , newPion(type_couleur.Noir,type_pion.Pion) , newPion(type_couleur.Noir,type_pion.Vide)},
                            {newPion(type_couleur.Blanche,type_pion.Pion) , newPion(type_couleur.Noir,type_pion.Vide) , newPion(type_couleur.Blanche,type_pion.Pion)},
                            {newPion(type_couleur.Blanche,type_pion.Pion) , newPion(type_couleur.Blanche,type_pion.Vide) , newPion(type_couleur.Blanche,type_pion.Pion)}};
        println();
        for(int i=0;i<length(tab1,1);i++){
            for(int y=0;y<length(tab1,2);y++){
                print(toString(tab1[i][y]));
            }
            println();
        }
        assertTrue(est_possible_deplacement(tab1,0,1,1,1));
    }
    //fonction qui verifie si la coordonnée où il veut mettre le pion est dans les coordonnée possible
    boolean est_possible_deplacement(pawn[][] tab,int idxLigne_debut,int idxColumn_debut,int idxLigne_fin,int idxColumn_fin){
        println("est rentré dans la fonction");
        //verifie si la case est une case du plateau 
        int i=-1;
        //verifie que la valeur de la column du pion selectionner est égale à 0 si se c'est pas le cas on ne calcule pas la valeur d'indice de diagonal gauche
        if(idxColumn_debut==0){
            i=0;
        }
        if(est_possible_coordonnee(idxLigne_debut,idxColumn_debut,idxLigne_fin,idxColumn_fin)){
            if(tab[idxLigne_debut][idxColumn_debut].caracteristique!=type_pion.Vide){
                println("reussi etape pion non vide");
                if(tab[idxLigne_debut][idxColumn_debut].couleur==type_couleur.Noir){
                    println("reussi etape pion Noir");
                    while(i<2){
                        if(tab[idxLigne_debut+1][idxColumn_debut+i]==tab[idxLigne_fin][idxColumn_fin]){
                            println("est entrée dans la condition de tab selectionner==tab deplacer");
                            if(tab[idxLigne_fin][idxColumn_fin].caracteristique==type_pion.Vide){
                                println("retourne true");
                                return true;
                            }
                            if((tab[idxLigne_fin][idxColumn_fin].caracteristique==type_pion.Pion)&&(tab[idxLigne_fin][idxColumn_fin].couleur==type_couleur.Blanche)){
                                if((pion_adverse_diagonal_gauche(tab,idxLigne_debut,idxColumn_debut,idxLigne_fin,idxColumn_fin))||((pion_adverse_diagonal_droite(tab,idxLigne_debut,idxColumn_debut,idxLigne_fin,idxColumn_fin)))){
                                    println("retourne true pour les diagonal");
                                    return true;
                                }
                            }
                            //force la bloucle a s'aretter avec un false vu qu'on a deja trouver le pion mais on a vu que ce n'est pas possible de le placer
                            println("retourne false");
                            return false;
                            }
                        i++;
                    }
                }   
                else if(tab[idxLigne_debut][idxColumn_debut].couleur==type_couleur.Blanche){
                    while(i<2){
                        if(tab[idxLigne_debut-1][idxColumn_debut+i]==tab[idxLigne_fin][idxColumn_fin]){
                            println("est entrée dans la condition de tab selectionner==tab deplacer");
                            if(tab[idxLigne_fin][idxColumn_fin].caracteristique==type_pion.Vide){
                                println("retourne true");
                                return true;
                            }
                            if((tab[idxLigne_fin][idxColumn_fin].caracteristique==type_pion.Pion)&&(tab[idxLigne_fin][idxColumn_fin].couleur==type_couleur.Noir)){
                                if((pion_adverse_diagonal_gauche(tab,idxLigne_debut,idxColumn_debut,idxLigne_fin,idxColumn_fin))||((pion_adverse_diagonal_droite(tab,idxLigne_debut,idxColumn_debut,idxLigne_fin,idxColumn_fin)))){
                                    println("retourne true pour les diagonal");
                                    return true;
                                }
                            }
                            //force la bloucle a s'aretter avec un false vu qu'on a deja trouver le pion mais on a vu que ce n'est pas possible de le placer
                            return false;
                        }
                        i++;
                    }
                }
            }
        }
                
        return false; 
    }
    void testdeplacement_plateau(){
        pawn[][] tab1=new pawn[][]{
                            {newPion(type_couleur.Blanche,type_pion.Vide) , newPion(type_couleur.Noir,type_pion.Pion) , newPion(type_couleur.Noir,type_pion.Vide)},
                            {newPion(type_couleur.Blanche,type_pion.Pion) , newPion(type_couleur.Noir,type_pion.Vide) , newPion(type_couleur.Blanche,type_pion.Pion)},
                            {newPion(type_couleur.Blanche,type_pion.Pion) , newPion(type_couleur.Blanche,type_pion.Vide) , newPion(type_couleur.Blanche,type_pion.Pion)}};
        tab1=deplacement_plateau(tab1,0,1,1,1);
        assertEquals(toString(new pawn[][]{
                            {newPion(type_couleur.Blanche,type_pion.Vide) , newPion(type_couleur.Noir,type_pion.Vide) , newPion(type_couleur.Noir,type_pion.Vide)},{newPion(type_couleur.Blanche,type_pion.Pion) , newPion(type_couleur.Noir,type_pion.Pion) , newPion(type_couleur.Blanche,type_pion.Pion)},{newPion(type_couleur.Blanche,type_pion.Pion) , newPion(type_couleur.Blanche,type_pion.Vide) , newPion(type_couleur.Blanche,type_pion.Pion)}}),toString(tab1));

        pawn[][] tab2=new pawn[][]{
                            {newPion(type_couleur.Blanche,type_pion.Vide) , newPion(type_couleur.Noir,type_pion.Pion) , newPion(type_couleur.Noir,type_pion.Vide)},
                            {newPion(type_couleur.Blanche,type_pion.Pion) , newPion(type_couleur.Noir,type_pion.Pion) , newPion(type_couleur.Blanche,type_pion.Pion)},
                            {newPion(type_couleur.Blanche,type_pion.Pion) , newPion(type_couleur.Blanche,type_pion.Vide) , newPion(type_couleur.Blanche,type_pion.Pion)}};
        //fontion pour debuger
        println();
        for(int i=0;i<length(tab2,1);i++){
            for(int y=0;y<length(tab2,2);y++){
                print(toString(tab2[i][y]));
            }
            println();
        }
        tab2=deplacement_plateau(tab2,2,2,1,1);
        /*println();
        for(int i=0;i<length(tab2,1);i++){
            for(int y=0;y<length(tab2,2);y++){
                print(toString(tab2[i][y]));
            }
            println();
        }*/
        assertEquals(toString(new pawn[][]{
                            {newPion(type_couleur.Blanche,type_pion.Pion) , newPion(type_couleur.Noir,type_pion.Pion) , newPion(type_couleur.Noir,type_pion.Vide)},{newPion(type_couleur.Blanche,type_pion.Pion) , newPion(type_couleur.Blanche,type_pion.Vide) , newPion(type_couleur.Blanche,type_pion.Pion)},{newPion(type_couleur.Blanche,type_pion.Pion) , newPion(type_couleur.Blanche,type_pion.Vide) , newPion(type_couleur.Blanche,type_pion.Vide)}}),toString(tab2));
        
        pawn[][] tab3=new pawn[][]{
                            {newPion(type_couleur.Noir,type_pion.Pion) , newPion(type_couleur.Blanche,type_pion.Pion) , newPion(type_couleur.Blanche,type_pion.Pion)},
                            {newPion(type_couleur.Blanche,type_pion.Vide) , newPion(type_couleur.Blanche,type_pion.Pion) , newPion(type_couleur.Blanche,type_pion.Pion)},
                            {newPion(type_couleur.Blanche,type_pion.Pion) , newPion(type_couleur.Blanche,type_pion.Vide) , newPion(type_couleur.Blanche,type_pion.Vide)}};
        println("tableau 3");
        for(int i=0;i<length(tab3,1);i++){
            for(int y=0;y<length(tab2,2);y++){
                print(toString(tab3[i][y]));
            }
            println();
        }
        tab3=deplacement_plateau(tab2,0,0,1,1);
        println();
        for(int i=0;i<length(tab2,1);i++){
            for(int y=0;y<length(tab2,2);y++){
                print(toString(tab2[i][y]));
            }
            println();
        }
    }
    //erreur cette fonction
    pawn[][] deplacement_plateau(pawn[][] tab,int idxLigne_debut,int idxColumn_debut,int idxLigne_fin,int idxColumn_fin){
        //println("est entrer dans variable");
        if(est_comprise_tableau(tab,idxLigne_debut,idxColumn_debut)&&(est_comprise_tableau(tab,idxLigne_fin,idxColumn_fin))){
            //println("passer étape tableau");
            if(tab[idxLigne_debut][idxColumn_debut].couleur==type_couleur.Blanche){
                //println("passer étape pion blanc");
                if(pion_adverse_diagonal_gauche(tab,idxLigne_debut,idxColumn_debut,idxLigne_fin,idxColumn_fin)){
                    //println("passer étape diagonal gauche");
                    tab[idxLigne_fin][idxColumn_fin]=determiner_case(idxLigne_fin,idxColumn_fin);
                    tab[idxLigne_debut-2][idxColumn_debut-2]=tab[idxLigne_debut][idxColumn_debut];
                    tab[idxLigne_debut][idxColumn_debut]=determiner_case(idxLigne_debut,idxColumn_debut);
                }
                else if(pion_adverse_diagonal_droite(tab,idxLigne_debut,idxColumn_debut,idxLigne_fin,idxColumn_fin)){
                    //println("passer étape diagonal droite");
                    tab[idxLigne_fin][idxColumn_fin]=determiner_case(idxLigne_fin,idxColumn_fin);
                    tab[idxLigne_debut-2][idxColumn_debut+2]=tab[idxLigne_debut][idxColumn_debut];
                    tab[idxLigne_debut][idxColumn_debut]=determiner_case(idxLigne_debut,idxColumn_debut);
                }
                else if(tab[idxLigne_fin][idxColumn_fin].caracteristique==type_pion.Vide){
                    //println("passer étape sans diagonal");
                    tab[idxLigne_fin][idxColumn_fin]=tab[idxLigne_debut][idxColumn_debut];
                    tab[idxLigne_debut][idxColumn_debut]=determiner_case(idxLigne_debut,idxColumn_debut);
                }
            }
            else if(tab[idxLigne_debut][idxColumn_debut].couleur==type_couleur.Noir){
                //println("passer étape pion Noir");
                if(pion_adverse_diagonal_gauche(tab,idxLigne_debut,idxColumn_debut,idxLigne_fin,idxColumn_fin)){
                    //println("passer étape diagonal gauche");
                    tab[idxLigne_fin][idxColumn_fin]=determiner_case(idxLigne_fin,idxColumn_fin);
                    tab[idxLigne_debut+2][idxColumn_fin-2]=tab[idxLigne_debut][idxColumn_debut];
                    tab[idxLigne_debut][idxColumn_debut]=determiner_case(idxLigne_debut,idxColumn_debut);
                }
                else if(pion_adverse_diagonal_droite(tab,idxLigne_debut,idxColumn_debut,idxLigne_fin,idxColumn_fin)){
                    //println("passer étape diagonal droite");
                    tab[idxLigne_fin][idxColumn_fin]=determiner_case(idxLigne_fin,idxColumn_fin);
                    tab[idxLigne_debut+2][idxColumn_debut+2]=tab[idxLigne_debut][idxColumn_debut];
                    tab[idxLigne_debut][idxColumn_debut]=determiner_case(idxLigne_debut,idxColumn_debut);
                }
                else if(tab[idxLigne_fin][idxColumn_fin].caracteristique==type_pion.Vide){
                     //println("passer étape sans diagonal");
                    tab[idxLigne_fin][idxColumn_debut]=tab[idxLigne_debut][idxColumn_debut];
                    tab[idxLigne_debut][idxColumn_debut]=determiner_case(idxLigne_debut,idxColumn_debut);
                }
            }
        }
        return tab;
    }
    void testBonne_Coordonnee(){
        assertTrue(saisie_coordonnee_valide("E4"));
        assertTrue(saisie_coordonnee_valide("H6"));
        assertFalse(saisie_coordonnee_valide("H9"));
        assertFalse(saisie_coordonnee_valide("J8"));
        
    }
    boolean saisie_coordonnee_valide(String mot){
        if(length(mot)==2){
            if((charAt(mot,0)>='A')&&(charAt(mot,0)<='H')){
                if((charAt(mot,1)>='0')&&(charAt(mot,1)<='7')){
                    return true;
                }
            }
        }
        return false;
    }
    void testest_comprise_tableau(){
         pawn[][] tab1=new pawn[][]{
                            {newPion(type_couleur.Blanche,type_pion.Vide) , newPion(type_couleur.Noir,type_pion.Pion) , newPion(type_couleur.Noir,type_pion.Vide)},
                            {newPion(type_couleur.Blanche,type_pion.Pion) , newPion(type_couleur.Noir,type_pion.Vide) , newPion(type_couleur.Blanche,type_pion.Pion)},
                            {newPion(type_couleur.Blanche,type_pion.Pion) , newPion(type_couleur.Blanche,type_pion.Vide) , newPion(type_couleur.Blanche,type_pion.Pion)}};
        assertTrue(est_comprise_tableau(tab1,0,0));
        assertFalse(est_comprise_tableau(tab1,-1,-1));
        assertFalse(est_comprise_tableau(tab1,5,5));
    }
    boolean est_comprise_tableau(pawn[][] tab,int idxLigne,int idxColumn){
        if((length(tab,1)>=idxLigne)&&(length(tab,2)>=idxColumn)){
            if((idxLigne>=0)&&(idxColumn>=0)){
                return true;
            }
        }
        return false;
    }
    void testselection_pion_depart(){
        pawn[][] tab=initialiser_pion();
        assertEquals(new int []{1,1},selectionner_pion_depart(tab,newjoueur("aun",type_couleur.Noir)));
    }
    int[] selectionner_pion_depart(pawn[][] tab,joueur nom_joueur){
        String mot="";
        int coordonnée_1;
        int coordonnée_2;
        boolean premiere_utilisation=true;
        println(nom_joueur.nom +"  Choisis le pion que tu veux utiliser en entrant ses coordonnées !");
        do{ 
            do{
                if(!premiere_utilisation){
                    println(nom_joueur.nom + " Oups, cette coordonnée n'est pas valide. Essaie encore une fois !");
                }
                premiere_utilisation=false;
                mot=readString();
                coordonnée_1=(int)(charAt(mot,1)-'0');
                coordonnée_2=(int)(charAt(mot,0)-'A');
            }while((!est_comprise_tableau(tab,coordonnée_1,coordonnée_2))&&(!saisie_coordonnee_valide(mot))&&(!est_bon_pion(nom_joueur,tab,coordonnée_1,coordonnée_2)));
        }while(tab[coordonnée_1][coordonnée_2].caracteristique==type_pion.Vide);
        return new int[] {coordonnée_1,coordonnée_2};
    }
    int[] selectionner_pion_arriver(pawn[][] tab,joueur nom_joueur,int idxLigne_debut,int idxColumn_debut){
        String mot="";
        //utile pour transformé le char en int
        int idxLigne_fin;
        int idxColumn_fin;
        boolean premiere_utilisation=true;
        println(nom_joueur.nom +" Où veux-tu déplacer ton pion ? Entre les coordonnées de la case où tu veux aller !");
        do{
            //test si ce n'est pas la premiere utilisation pour afficher la phrase d'erreur
            if(!premiere_utilisation){
                println(nom_joueur.nom + " Oups, erreur ! Essaie encore avec une coordonnée valide !");
            }
            premiere_utilisation=false;
            mot=readString();
            idxColumn_fin=(int)(charAt(mot,0)-'A');
            idxLigne_fin=(int)(charAt(mot,1)-'0');
        }while((!saisie_coordonnee_valide(mot))&&(!est_possible_deplacement(tab,idxLigne_debut,idxColumn_debut,idxLigne_fin,idxColumn_fin)));
        return new int[] {idxLigne_fin,idxColumn_fin};
    }
    boolean fin_du_jeu(pawn[][] tab){
        int cpt_blanc=0;
        int cpt_noir=0;
        for(int idxLigne=0;idxLigne<length(tab,1);idxLigne++){
            for(int idxColumn=0;idxColumn<length(tab,2);idxColumn++){
                if(tab[idxLigne][idxColumn].caracteristique!=type_pion.Vide){
                    if(tab[idxLigne][idxColumn].couleur==type_couleur.Blanche){
                        cpt_blanc++;
                    }
                    else if(tab[idxLigne][idxColumn].couleur==type_couleur.Blanche){
                        cpt_noir++;
                    }
                }
            }
        }
        if(cpt_blanc==0){
            return true;
        }
        if(cpt_noir==0){
            return true;
        }
        return false;
    }
      question newquestion(String Question,String Reponse1, String Reponse2,String Reponse3,String resulta){
            question q =new question();
            q.question=Question;
            q.reponse1=Reponse1;
            q.reponse2=Reponse2;
            q.reponse3=Reponse3;
            q.resultat=resulta;
            return q;
        }
        question[] load(String nom_fichier){
            CSVFile file= loadCSV(nom_fichier,',');
            question[] tab= new question[rowCount(file)];
            for(int i=0;i<19;i++){
                tab[i].question=getCell(file,i,0);
                tab[i].reponse1=getCell(file,i,1);
                tab[i].reponse2=getCell(file,i,2);
                tab[i].reponse3=getCell(file,i,3);
                tab[i].resultat=getCell(file,i,4);
            }
            return tab;
        }
        String toString_Fichier(String nom_fichier){
            int Ligne=(int)((random()*20)-1);
            question[] fichier= load(nom_fichier);
            String phrase=fichier[Ligne].question+","+fichier[Ligne].reponse1+","+fichier[Ligne].reponse2+","+fichier[Ligne].reponse3;
            return phrase;
        }

        String dificulte(int nb_fois_reussi){
            if(nb_fois_reussi<3){
                return "question_facile.csv";
            }
            if(nb_fois_reussi<5){
                return "question_moyen.csv";
            }
            return "question_dificile.csv";
        }
        boolean question(int nb_fois_reussi){
            String file= dificulte(nb_fois_reussi);
            CSVFile contenue= loadCSV(file);
            int x= (int) (random()*rowCount(contenue));
            for(int y=0;y<columnCount(contenue)-1;y++){
                println(getCell(contenue,x,y));
            }
            String s=readString();
            if(equals(s,getCell(contenue,x,4))){
                println();
                println("Bravo, tu as trouvé la bonne reponse !");
                return true;
            }
            println();
            println("Oups, tu t'es trompé ! La bonne réponse était la réponse : " + getCell(contenue,x,4));
            return false;
        }

    void algorithm(){
        println();
        println("Bonjour a vous et bienvenue dans Pawn School notre jeu ! Voici les règles :");
        println();
        println("2 equipes s'affrontent sur un plateau, les pions blancs et les pions noir.");
        println("Le but est très simple : avancer sur le plateau et éliminer tous vos adversaires jusqu'à ce qu'il ne reste plus aucun pion adverse en jeu.");
        println("Le pion peut avancer d'une case soit en ligne droite, soit en diagonale, selon la direction choisie. À toi de bien choisir ta trajectoire pour avancer et surprendre tes adversaires !");
        println("Pour faire avancer ton pion, commence par entrer la lettre (en majuscule) de sa position, puis le chiffre qui lui correspond !");
        println();
        println("Seulement avancer ne sera pas aussi facile que vous le pensez :)");
        println();
        println("À chaque tour, tu devras répondre à une question. Tu auras trois réponses possibles, mais une seule est correcte !");
        println("Si tu choisis la bonne réponse alors tu pourras avancer.");
        println("Si tu choisis la mauvaise réponse, pas de chance ! Tu resteras sur ta case pour ce tour.");
        println("Plus tu avances, plus les questions deviennent difficiles. Prépare-toi à réfléchir davantage à chaque étape !");
        println();
        println("Si ton pion atteint la dernière case de l'autre côté du plateau, bravo ! Il se transforme en reine, la pièce la plus forte du jeu. Avec une reine, tu pourras être encore plus puissant pour éliminer les autres pions !");
        println();
        println("Bravo, tu sais maintenant tout ce qu'il faut pour jouer ! Amuse-toi bien, et surtout, n'oublie pas d'apprendre en t'amusant !");
        println();
        println();
        String prenom_joueur="";
        int cpt=0;
        int[] coordonnée_selectionner;
        int[] coordonnée_deplacer;
        pawn[][] tableau_jeu=initialiser_pion();
        String afficher_jeu=toString(tableau_jeu);
        pawn[][] tab=new pawn[length(tableau_jeu,1)][length(tableau_jeu,2)];

        println("Veuillez entrez votre prenom !");
        print("Pion Blanc : ");
        prenom_joueur=readString();
        joueur perssonnage_1=newjoueur(prenom_joueur,type_couleur.Blanche);
        print("Pion Noir : ");
        prenom_joueur=readString();
        joueur perssonnage_2=newjoueur(prenom_joueur,type_couleur.Noir);
        while(fin_du_jeu(tableau_jeu)){
            println(toString(tableau_jeu));
            if(cpt%2==0){
                coordonnée_selectionner=selectionner_pion_depart(tableau_jeu,perssonnage_1);
                coordonnée_deplacer=selectionner_pion_arriver(tableau_jeu,perssonnage_1,coordonnée_selectionner[0],coordonnée_selectionner[1]);
            }
            else{
                coordonnée_selectionner=selectionner_pion_depart(tableau_jeu,perssonnage_2);
                coordonnée_deplacer=selectionner_pion_arriver(tableau_jeu,perssonnage_2,coordonnée_selectionner[0],coordonnée_selectionner[1]);
            }
            tab=deplacement_plateau(tableau_jeu,coordonnée_selectionner[0],coordonnée_selectionner[1],coordonnée_deplacer[0],coordonnée_deplacer[1]);
            cpt++;
        }

    }
} 


