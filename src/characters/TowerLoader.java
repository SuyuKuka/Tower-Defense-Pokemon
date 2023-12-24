package characters;

public interface TowerLoader { // ce qui est propre a chaque tour
    public void evolution(); // Evolue ou non a un niveau

    public void primarySkill(); // Apprends les capacites a certains niveau

    public void unlock(); // Deblocable selon le niveau du compte et prix de la boutique
    // public void innerFocus(); //talent
}
