package characters;

public interface TowerLoader { //ce qui est propre a chaque tour
    public void evolution(); //Evolue ou non a un niveau
    public void skill(); //Apprends les capacités a certains niveau
    public void unlock(); //Déblocable selon le niveau du compte et prix de la boutique
    public void innerFocus(); //talent
    public void getCurrentSkill(); //Avoir son attaque qu'il utilise actuellement
    public void loadSprite(); //Genere le sprite
}
