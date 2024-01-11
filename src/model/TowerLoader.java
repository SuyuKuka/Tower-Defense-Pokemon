package model;

public interface TowerLoader { // ce qui est propre a chaque tour
    public void evolution(); // Evolue ou non a un niveau

    public void primarySkill(); // Capacite qu'il apprend des le debut

    public void unlock(); // Capacite qu'il apprend par niveau
    // Deblocable selon le niveau du compte et achetable selon le prix (juste 1 fois)
}
