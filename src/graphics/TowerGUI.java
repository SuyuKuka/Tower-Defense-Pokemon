package graphics;

import control.Control;
import control.Control.SkillButton;
import model.Skill;
import model.Tower;
import model.Types;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.BoxLayout;

public class TowerGUI extends JPanel {

    private boolean opened = false;
    private Tower pkm;
    private GraphicsMaker maker = new GraphicsMaker();
    private Control control = new Control();

    private JPanel info;

    private JLabel types = maker.oneLineTextMaker();
    private JLabel pkmName = maker.oneLineTextMaker();
    private JLabel phys = maker.oneLineTextMaker();
    private JLabel spe = maker.oneLineTextMaker();
    private JLabel portee = maker.oneLineTextMaker();
    private JLabel vitesse = maker.oneLineTextMaker();

    private SkillButton[] skillSlot = new SkillButton[4];

    private JLabel skillName = maker.oneLineTextMaker();
    private JLabel skillDmg = maker.oneLineTextMaker();
    private JLabel skillPattern = maker.oneLineTextMaker();
    private JLabel skillType = maker.oneLineTextMaker();

    public TowerGUI(Tower pkm) {
        pkm.setGUI(this);
        this.pkm = pkm;

        JPanel skillInfoPanel = new JPanel();
        skillInfoPanel.setLayout(new BoxLayout(skillInfoPanel, BoxLayout.Y_AXIS));
        skillInfoPanel.setBorder(new LineBorder(Color.BLUE));
        skillInfoPanel.setOpaque(false);
        skillInfoPanel.add(skillName);
        skillInfoPanel.add(skillType);
        skillInfoPanel.add(skillDmg);
        skillInfoPanel.add(skillPattern);

        JPanel skills = new JPanel();
        skills.setLayout(new GridLayout(2, 2));
        for (int i = 0; i < skillSlot.length; i++) {
            skillSlot[i] = control.new SkillButton();
            skillSlot[i].setBorder(new EmptyBorder(1, 1, 1, 1));
            skills.add(skillSlot[i]);
        }
        JPanel stats = new JPanel();
        stats.setLayout(new GridLayout(2, 2));
        stats.setOpaque(false);
        stats.add(phys);
        stats.add(portee);
        stats.add(spe);
        stats.add(vitesse);

        info = new JPanel();
        info.setLayout(new GridLayout(5, 1));
        info.setBackground(Color.BLACK);
        info.add(pkmName);
        info.add(types);
        info.add(stats);
        info.add(skills);
        info.add(skillInfoPanel);

        setPreferredSize(new Dimension(0, 600));
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());
        add(info, BorderLayout.CENTER);
    }

    public void updatePkm() {

        pkmName.setText(pkm.getName() + " lvl." + pkm.getLevel());
        updateTypePanel();
        updateStatsPanel();
        updateSkillPanel();

        info.revalidate();
        info.repaint();
    }

    private void updateStatsPanel() {

        phys.setText("AtkPhys: " + pkm.getPhysical() + "");
        spe.setText("AtkSpe: " + pkm.getSpecial() + "");
        portee.setText("Portee: " + pkm.getRayon() + "");
        vitesse.setText("Vitesse: " + pkm.getAtkCd() + "");
    }

    private void updateTypePanel() {

        if (pkm.getType()[1] == Types.NONE) {
            types.setText("Type: " + pkm.getType()[0] + "");
        } else {
            types.setText("Type: " + pkm.getType()[0] + " et " + pkm.getType()[1]);
        }
    }

    private void updateSkillPanel() {
        int incre = 0;
        for (SkillButton skill : skillSlot) {

            if (pkm.getSkills()[incre] != null) {
                skill.setText(pkm.getSkills()[incre].getName());
                skill.setSkill(pkm.getSkills()[incre]);
                skill.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent evt) {
                        control.SkillManager(skill, pkm);

                    }
                });

            } else {
                skill.setText("");
            }

            if (pkm.getCurrentSkill(pkm.getSelect()) == pkm.getSkills()[incre]) {
                skill.setBorder(new LineBorder(Color.RED));
            } else {
                skill.setBorder(new EmptyBorder(1, 1, 1, 1));
            }

            incre++;
        }

        skillName.setText(pkm.getCurrentSkill(pkm.getSelect()).getName());
        skillType.setText("Attaque de type: " + pkm.getCurrentSkill(pkm.getSelect()).getType() + "");
        if (pkm.getCurrentSkill(pkm.getSelect()).getPhysicalDamage() == 0) {
            skillDmg.setText("Degat Special: " + pkm.getCurrentSkill(pkm.getSelect()).getSpecialDamage() + "");
        } else {
            skillDmg.setText("Degat Physique: " + pkm.getCurrentSkill(pkm.getSelect()).getPhysicalDamage() + "");
        }
        if (pkm.getCurrentSkill(pkm.getSelect()).getPatern() == Skill.Pattern.AURA) {
            skillPattern.setText("Attaque sur une zone autour.");
        }
        if (pkm.getCurrentSkill(pkm.getSelect()).getPatern() == Skill.Pattern.UNIQUE) {
            skillPattern.setText("Attaque un seul ennemi.");
        }
    }

    public boolean isOpened() {
        return this.opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

}
