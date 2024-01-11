package model;

public class Effect {
    //stat baisse def phys/spe, speed (cumulable?)
    //buff atk physique et speciale, atk speed, augmenter la portee (non cumulable)
    //statut stun => immobile pdt x sec, paralyse => + lent, brulure => inflige x degat/sec diminue sa def physique de -10%, poison => inflige x degat mais progressive? (cumulable?)
    //reveal spectre
    //Debuff
    //Ennemi e qui se prend l'effet, int i la reduction de defense physique
    public void reducesPhysicalDefense(Ennemi e, int defense, int duration){
        //jusqu'a duration
        e.setPhysical(e.getPhysical()-defense);
    }
    
    //Ennemi e qui se prend l'effet, int i la reduction de defense speciale
    public void reducesSpecialDefense(Ennemi e, int defense, int duration){
        e.setSpecial(e.getSpecial()-defense);
    }

    //Ennemi e qui se prend l'effet, int i la reduction de la vitesse
    public void reducesSpeed(Ennemi e, int speed, int duration){
        //faire qlq modif a speed
    }

    //Buff
    //Tower t qui se prend l'effet, int i l'augmentation de l'attaque physique
    public void increasesPhysicalAttack(Tower t, int damage, int duration){
        t.setPhysical(t.getPhysical()+damage);
    }

    //Tower t qui se prend l'effet, int i l'augmentation de l'attaque speciale
    public void increasesSpecialAttack(Tower t, int damage, int duration){
        t.setSpecial(t.getSpecial()+damage);
    }

    //Tower t qui se prend l'effet, int i l'augmentation de la vitesse d'attaque
    public void increasesSpeedAttack(Tower t, int speed, int duration){
        
    }

    //Tower t qui se prend l'effet, int i l'augmentation du rayon d'attaque
    public void increasesRayon(Tower t, int range, int duration){
        //faire qlq modif a rayon
    }

    //Statut
    //Ennemi e qui se prend l'effet, int duration la duree du stun
    public void statutStun(Ennemi e, int duration){
        
    }

    //Ennemi e qui se prend l'effet, int i la diminution de vitesse
    public void statutParalyse(Ennemi e, int slowness, int duration){

    }

    //Ennemi e qui se prend l'effet, int i represente les degats et diminue sa def physique de -10% sur x secondes
    public void statutBurn(Ennemi e, int damage, int duration){

    }

    //Ennemi e qui se prend l'effet, int i represente les degats, qui augmente petit a petit sur x secondes
    public void statutPoison(Ennemi e, int damage, int duration){

    }

    public void nothing(){
        
    }
}
