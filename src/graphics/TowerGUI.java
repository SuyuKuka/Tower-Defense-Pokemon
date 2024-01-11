package graphics;

import control.Control;
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
    private Tower pkm;
    private GraphicsMaker maker = new GraphicsMaker();
    private Control control = Control.INSTANCE;

    private JPanel info;
    private JLabel types = maker.oneLineTextMaker();
    private JLabel pkmName = maker.oneLineTextMaker();
    private JLabel phys = maker.oneLineTextMaker();
    private JLabel spe = maker.oneLineTextMaker();
    private JLabel portee = maker.oneLineTextMaker();
    private JLabel vitesse = maker.oneLineTextMaker();
    private JLabel skillName = maker.oneLineTextMaker();
    private JLabel skillDmg = maker.oneLineTextMaker();
    private JLabel skillPattern = maker.oneLineTextMaker();
    private JLabel skillType = maker.oneLineTextMaker();
    private JPanel pkmInfoPanel;

    private SkillButton[] skillSlot = new SkillButton[4];

    private boolean alert = false;

    public TowerGUI(Tower pkm) {
        this.pkm = pkm;
        pkm.setGUI(this);

        setVisible(false);
        setPreferredSize(new Dimension(0, 600));
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());

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
            skillSlot[i] = new SkillButton();
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

        pkmInfoPanel = new JPanel();
        pkmInfoPanel.setOpaque(false);
        pkmInfoPanel.setBorder(new LineBorder(Color.GREEN));
        pkmInfoPanel.add(pkmName);
        pkmInfoPanel.add(types);
        pkmInfoPanel.add(stats);

        info = new JPanel();
        info.setLayout(new GridLayout(3, 1));
        info.setOpaque(false);
        info.add(skills);
        info.add(skillInfoPanel);
        info.add(pkmInfoPanel);

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
        phys.setText("AtkPhys: " + pkm.getPhysical());
        spe.setText("AtkSpe: " + pkm.getSpecial());
        portee.setText("Portee: " + pkm.getRayon());
        vitesse.setText("Vitesse: " + pkm.getAtkCd());
    }

    private void updateTypePanel() {
        if (pkm.getType()[1] == Types.NONE) {
            types.setText("Type: " + pkm.getType()[0]);
        } else {
            types.setText("Type: " + pkm.getType()[0] + " et " + pkm.getType()[1]);
        }
    }

    private void updateSkillInfoPanel(Skill skill) {
        skillName.setText(skill.getName());
        skillType.setText("Attaque de type: " + skill.getType());
        if (skill.getPhysicalDamage() == 0) {
            skillDmg.setText("Degat Special: " + skill.getSpecialDamage());
        } else {
            skillDmg.setText("Degat Physique: " + skill.getPhysicalDamage());
        }
        if (skill.getPatern() == Skill.Pattern.AURA) {
            skillPattern.setText("Attaque sur une zone autour.");
        }
        if (skill.getPatern() == Skill.Pattern.UNIQUE) {
            skillPattern.setText("Attaque un seul ennemi.");
        }
    }

    private void updateSkillPanel() {
        int incre = 0;
        for (SkillButton skill : skillSlot) {
            if (pkm.getSkills()[incre] != null) {
                skill.setFont(maker.getCustomFont().deriveFont(20f));
                skill.setText(pkm.getSkills()[incre].getName());
                skill.setSkill(pkm.getSkills()[incre]);
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
    }

    public void opendLearnSkill(Skill newSkill) {
        alert = true;
        info.remove(info.getComponent(2));

        JLabel title = maker.oneLineTextMaker();
        title.setFont(maker.getCustomFont().deriveFont(15f));
        title.setText(pkm.getName() + " veut apprendre " + newSkill.getName());

        JLabel question = maker.oneLineTextMaker();
        question.setAlignmentX(CENTER_ALIGNMENT);
        question.setFont(maker.getCustomFont().deriveFont(12f));
        question.setText("Remplacer l'attaque actuelle par " + newSkill.getName() + " ?");

        SkillButton newSkillButton = new SkillButton(newSkill);
        newSkillButton.setFont(maker.getCustomFont().deriveFont(20f));
        newSkillButton.setText("Prix: " + newSkill.getPrice());
        newSkillButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (control.skillReplace(pkm, newSkill)) {
                    alert = false;
                    info.remove(info.getComponent(2));
                    info.add(pkmInfoPanel);
                }
            }
            @Override
            public void mouseEntered(MouseEvent evt) {
                newSkillButton.setText(newSkill.getName());
                updateSkillInfoPanel(newSkillButton.getSkill());
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                newSkillButton.setText("Prix: " + newSkill.getPrice());
                updateSkillInfoPanel(newSkillButton.getSkill());
            }
        });

        JButton no = new JButton();
        no.setFont(maker.getCustomFont().deriveFont(20f));
        no.setText("Non");
        no.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                alert = false;
                info.remove(info.getComponent(2));
                info.add(pkmInfoPanel);
            }
        });

        JPanel learnSkill = new JPanel();
        learnSkill.setOpaque(false);
        learnSkill.setBorder(new LineBorder(Color.PINK));
        learnSkill.add(title);
        learnSkill.add(question);
        learnSkill.add(newSkillButton);
        learnSkill.add(no);

        info.add(learnSkill);
    }

    public boolean getAlert() {
        return this.alert;
    }

    public class SkillButton extends JButton {
        private Skill skill;

        public SkillButton() {
           addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent evt) {
                    control.chooseSkill(SkillButton.this, pkm);
                }

                @Override
                public void mouseEntered(MouseEvent evt) {
                    if (skill != null) {
                        updateSkillInfoPanel(skill);
                    }
                }
            });
        }

        public SkillButton(Skill skill) {
            this.skill = skill;
        }

        public void setSkill(Skill skill) {
            this.skill = skill;
        }

        public Skill getSkill() {
            return skill;
        }
    }
}
