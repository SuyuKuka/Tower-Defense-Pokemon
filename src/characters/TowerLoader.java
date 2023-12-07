package characters;

import model.Skill;

public interface TowerLoader { //ce qui est propre a chaque tour
    public void evolution(); //Evolue ou non a un niveau
    public void primarySkill(); //Apprends les capacités a certains niveau
    public void unlock(); //Déblocable selon le niveau du compte et prix de la boutique
    public void learnSkill(Skill newSkill);
    public void innerFocus(); //talent
}
