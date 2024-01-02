package characters;

public interface TowerLoader { // ce qui est propre a chaque tour
    public void evolution(); // Evolue ou non a un niveau

    public void primarySkill(); // Apprends les capacites a certains niveau

    public void unlock(); // Deblocable selon le niveau du compte et achetable selon le prix (quantité illimité)
    // public void innerFocus(); //talent
}
