import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import javax.imageio.ImageIO;

public class Game extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener {
    private BufferedImage back;
    private int selectedIndex, hoveredIndex = -1, selectedStageIndex, hoveredStageIndex = -1;
    private String selectionStatus = "Choose your champion";
    private boolean abilityButtonHovered;
    private long abilityActivatedAt;
    private static final long ABILITY_ANIMATION_DURATION = 900;
    private final Image gremlinImage = loadImage("Gremlin.png"), goblinImage = loadImage("Goblin.png");
    private boolean encounterStarted, stageSelectionStarted;
    private final CharacterOption[] characters = {
        new CharacterOption("Swordsman", "Frontline fighter", "Unyielding Charge", "Unyielding Charge.png", "sprites/Swordsman.png", 500, 250, 100, 50),
        new CharacterOption("Medic", "Support specialist", "Field Recovery", "Field Recovery.png", "sprites/Medic.png", 300, 500, 60, 80),
        new CharacterOption("Beast", "Heavy tank", "Savage Roar", "SavageRoar.png", "sprites/Beast.png", 700, 150, 150, 35),
        new CharacterOption("Longsword", "Versatile fighter", "Longsword Strike", "Longsword Strike.png", "sprites/Longsword.png", 500, 250, 100, 50)
    };
    private final StageOption[] stages = {
        new StageOption("Bastion Courtyard", "BackgroundB.png"),
        new StageOption("Dungeon Hall", "BackgroundD.png"),
        new StageOption("Heart of the Keep", "BackgroundH.png"),
        new StageOption("Ruined Citadel", "BackgroundS.png")
    };

    public Game() {
        new Player().playmusic("track02.wav");
        setFocusable(true); setLayout(null);
        addKeyListener(this); addMouseListener(this); addMouseMotionListener(this);
        requestFocusInWindow(); new Thread(this).start();
    }

    public void run() {
        while (true) {
            try { Thread.sleep(16); repaint(); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); return; }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (back == null || back.getWidth() != getWidth() || back.getHeight() != getHeight()) back = (BufferedImage) createImage(getWidth(), getHeight());
        Graphics2D b = back.createGraphics();
        b.setColor(new Color(18, 24, 38)); b.fillRect(0, 0, getWidth(), getHeight());
        if (encounterStarted) drawEncounterScreen(b);
        else if (stageSelectionStarted) drawStageSelectionScreen(b);
        else drawSelectionScreen(b);
        ((Graphics2D) g).drawImage(back, null, 0, 0); b.dispose();
    }

    private void drawEncounterScreen(Graphics2D g) {
        drawCoverImage(g, stages[selectedStageIndex].image, new Rectangle(0, 0, getWidth(), getHeight()));
        g.setColor(new Color(10, 14, 24, 155)); g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE); g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString(stages[selectedStageIndex].name + " - " + characters[selectedIndex].name + " is ready!", 40, 55);
        drawParty(g);
        drawAbilityControls(g);
    }

    private void drawParty(Graphics2D g) {
        CharacterOption c = characters[selectedIndex];
        int px = Math.max(100, getWidth() / 4), cy = getHeight() / 2;
        drawCharacterImage(g, c.image, px, cy, 160, 160); drawEncounterName(g, c.name, px, cy + 100);
        int ex = Math.min(getWidth() - 100, getWidth() * 3 / 4), y1 = getHeight() * 2 / 5, y2 = getHeight() * 3 / 5;
        drawCharacterImage(g, gremlinImage, ex, y1, 140, 140); drawCharacterImage(g, gremlinImage, ex, y2, 140, 140);
        drawEncounterName(g, "Gremlin", ex, y1 + 85); drawEncounterName(g, "Gremlin", ex, y2 + 85);
        drawAbilityEffect(g, c, px, cy, ex);
    }

    private void drawAbilityEffect(Graphics2D g, CharacterOption character, int playerX, int centerY, int enemyX) {
        long elapsed = System.currentTimeMillis() - abilityActivatedAt;
        if (elapsed < 0 || elapsed >= ABILITY_ANIMATION_DURATION) return;

        float progress = (float) elapsed / ABILITY_ANIMATION_DURATION;
        int startX = playerX + 80;
        int endX = enemyX - 70;
        int imageX = startX + Math.round((endX - startX) * progress);
        Composite originalComposite = g.getComposite();
        g.setComposite(AlphaComposite.SrcOver.derive(1.0f - progress * 0.25f));
        drawCharacterImage(g, character.abilityImage, imageX, centerY, 128, 128);
        g.setComposite(originalComposite);
    }

    private void drawAbilityControls(Graphics2D g) {
        Rectangle button = abilityButtonBounds();
        g.setColor(abilityButtonHovered ? new Color(235, 197, 112) : new Color(185, 143, 67));
        g.fillRoundRect(button.x, button.y, button.width, button.height, 16, 16);
        g.setColor(new Color(25, 32, 44));
        g.fillRoundRect(button.x + 3, button.y + 3, button.width - 6, button.height - 6, 13, 13);
        g.setColor(abilityButtonHovered ? new Color(255, 235, 190) : new Color(245, 221, 164));
        g.setFont(new Font("Arial", Font.BOLD, 18));
        String label = "USE ABILITY: " + characters[selectedIndex].ability;
        g.drawString(label, button.x + (button.width - g.getFontMetrics().stringWidth(label)) / 2, button.y + 27);
        g.setColor(new Color(220, 226, 236));
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        String shortcut = "Click here or press SPACE";
        g.drawString(shortcut, button.x + (button.width - g.getFontMetrics().stringWidth(shortcut)) / 2, button.y + 48);
    }

    private void drawEncounterName(Graphics2D g, String n, int x, int y) {
        g.setColor(Color.WHITE); g.setFont(new Font("Arial", Font.BOLD, 16));
        int w = g.getFontMetrics().stringWidth(n); g.drawString(n, x - w / 2, y);
    }

    private void drawCharacterImage(Graphics2D g, Image image, int x, int y, int w, int h) {
        double s = Math.min((double) w / image.getWidth(this), (double) h / image.getHeight(this));
        int iw = Math.max(1, (int) Math.round(image.getWidth(this) * s));
        int ih = Math.max(1, (int) Math.round(image.getHeight(this) * s));
        g.drawImage(image, x - iw / 2, y - ih / 2, iw, ih, this);
    }

    private void drawSelectionScreen(Graphics2D g) {
        g.setColor(Color.WHITE); g.setFont(new Font("Arial", Font.BOLD, 30)); g.drawString("Choose a Character", 415, 60);
        g.setFont(new Font("Arial", Font.PLAIN, 16)); g.drawString(selectionStatus, 455, 90);
        for (int i = 0; i < characters.length; i++) {
            CharacterOption c = characters[i]; Rectangle b = cardBounds(i); int left = b.x, w = b.width, top = b.y; boolean active = i == selectedIndex, hover = i == hoveredIndex;
            g.setColor(active ? new Color(220, 235, 255) : new Color(240, 240, 240)); g.fillRect(b.x, b.y, b.width, b.height);
            g.setColor(active ? Color.BLUE : hover ? Color.GRAY : Color.BLACK); g.setStroke(new BasicStroke(active ? 3 : 1)); g.drawRect(b.x, b.y, b.width, b.height);
            drawCharacterImage(g, c.image, left + w / 2, top + 80, 120, 120); g.setColor(Color.BLACK); g.setFont(new Font("Arial", Font.BOLD, 22));
            g.drawString(c.name, left + (w - g.getFontMetrics().stringWidth(c.name)) / 2, top + 175); g.setFont(new Font("Arial", Font.PLAIN, 16));
            g.drawString(c.role, left + 20, top + 210); g.drawString("Health: " + c.health, left + 20, top + 255); g.drawString("Mana: " + c.mana, left + 20, top + 285);
            g.drawString("Damage: " + c.damage, left + 20, top + 315); g.drawString("Speed: " + c.speed, left + 20, top + 345); g.setFont(new Font("Arial", Font.BOLD, 14));
            g.drawString("Ability: " + c.ability, left + 20, top + 370); g.setFont(new Font("Arial", Font.PLAIN, 16));
            String label = active ? "Selected" : "Click to select"; g.drawString(label, left + (w - g.getFontMetrics().stringWidth(label)) / 2, top + 395);
        }
    }

    private void drawStageSelectionScreen(Graphics2D g) {
        g.setColor(new Color(10, 14, 24)); g.fillRect(0, 0, getWidth(), getHeight()); g.setColor(new Color(30, 43, 61)); g.fillRect(0, 0, getWidth(), 150);
        g.setColor(new Color(245, 221, 164)); g.setFont(new Font("Arial", Font.BOLD, 34));
        String title = "Choose Your Stage"; g.drawString(title, (getWidth() - g.getFontMetrics().stringWidth(title)) / 2, 58);
        g.setColor(new Color(215, 222, 232)); g.setFont(new Font("Arial", Font.PLAIN, 17));
        String subtitle = "Select a destination for " + characters[selectedIndex].name; g.drawString(subtitle, (getWidth() - g.getFontMetrics().stringWidth(subtitle)) / 2, 91);
        drawStageBackButton(g);
        for (int i = 0; i < stages.length; i++) {
            Rectangle b = stageCardBounds(i); StageOption s = stages[i]; boolean hover = i == hoveredStageIndex;
            g.setColor(hover ? new Color(235, 197, 112) : new Color(116, 132, 153)); g.fillRoundRect(b.x - 4, b.y - 4, b.width + 8, b.height + 8, 18, 18);
            g.setColor(new Color(25, 32, 44)); g.fillRoundRect(b.x, b.y, b.width, b.height, 14, 14);
            Rectangle img = new Rectangle(b.x + 8, b.y + 8, b.width - 16, Math.min(190, b.height / 2)); drawCoverImage(g, s.image, img);
            g.setColor(new Color(245, 221, 164)); g.setFont(new Font("Arial", Font.BOLD, 19)); g.drawString(s.name, b.x + 16, img.y + img.height + 38);
            g.setColor(hover ? new Color(255, 235, 190) : new Color(178, 190, 208)); g.setFont(new Font("Arial", Font.BOLD, 14));
            String action = "CLICK TO ENTER"; g.drawString(action, b.x + (b.width - g.getFontMetrics().stringWidth(action)) / 2, b.y + b.height - 18);
        }
    }

    private void drawStageBackButton(Graphics2D g) { Rectangle b = stageBackBounds(); g.setColor(new Color(55, 68, 85)); g.fillRoundRect(b.x, b.y, b.width, b.height, 12, 12); g.setColor(Color.WHITE); g.setFont(new Font("Arial", Font.BOLD, 14)); g.drawString("<  BACK", b.x + 15, b.y + 24); }

    private void drawWrappedText(Graphics2D g, String text, int x, int y, int maxWidth) {
        FontMetrics m = g.getFontMetrics(); StringBuilder line = new StringBuilder();
        for (String word : text.split(" ")) {
            String cand = line.length() == 0 ? word : line + " " + word;
            if (m.stringWidth(cand) > maxWidth && line.length() > 0) { g.drawString(line.toString(), x, y); y += m.getHeight(); line.setLength(0); line.append(word); }
            else { if (line.length() > 0) line.append(' '); line.append(word); }
        }
        if (line.length() > 0) g.drawString(line.toString(), x, y);
    }

    private void drawCoverImage(Graphics2D g, Image image, Rectangle b) {
        int iw = image.getWidth(this), ih = image.getHeight(this); if (iw <= 0 || ih <= 0) return;
        double s = Math.max((double) b.width / iw, (double) b.height / ih); int w = (int) Math.ceil(iw * s), h = (int) Math.ceil(ih * s);
        int x = b.x + (b.width - w) / 2, y = b.y + (b.height - h) / 2; Shape clip = g.getClip(); g.clip(b); g.drawImage(image, x, y, w, h, this); g.setClip(clip);
    }

    private Rectangle cardBounds(int i) { int gap = 20, w = Math.min(300, Math.max(1, (getWidth() - 80 - gap * (characters.length - 1)) / characters.length)); int total = w * characters.length + gap * (characters.length - 1); int left = (getWidth() - total) / 2 + i * (w + gap); return new Rectangle(left, 125, w, 410); }
    private Rectangle stageCardBounds(int i) { int gap = 18, w = Math.min(270, Math.max(1, (getWidth() - 80 - gap * (stages.length - 1)) / stages.length)); int h = Math.min(390, Math.max(240, getHeight() - 220)); int total = w * stages.length + gap * (stages.length - 1); int left = (getWidth() - total) / 2 + i * (w + gap); int top = 165 + Math.max(0, (getHeight() - 165 - h - 35) / 2); return new Rectangle(left, top, w, h); }
    private Rectangle stageBackBounds() { return new Rectangle(24, 24, 92, 34); }
    private Rectangle abilityButtonBounds() { return new Rectangle((getWidth() - 360) / 2, getHeight() - 90, 360, 64); }
    private int stageAt(int x, int y) { for (int i = 0; i < stages.length; i++) if (stageCardBounds(i).contains(x, y)) return i; return -1; }
    private void enterSelectedStage() { encounterStarted = true; repaint(); }
    private void activateAbility() {
        abilityActivatedAt = System.currentTimeMillis();
        repaint();
    }
    private int cardAt(int x, int y) { for (int i = 0; i < characters.length; i++) if (cardBounds(i).contains(x, y)) return i; return -1; }
    private void selectCharacter(int i) { selectedIndex = i; selectionStatus = characters[i].name + " selected - press Enter to confirm"; repaint(); }

    @Override public void mouseDragged(MouseEvent e) { mouseMoved(e); }
    @Override public void mouseMoved(MouseEvent e) {
        if (encounterStarted) {
            boolean hovered = abilityButtonBounds().contains(e.getPoint());
            if (hovered != abilityButtonHovered) { abilityButtonHovered = hovered; repaint(); }
            return;
        }
        int n = stageSelectionStarted ? stageAt(e.getX(), e.getY()) : cardAt(e.getX(), e.getY());
        if (stageSelectionStarted) { if (n != hoveredStageIndex) { hoveredStageIndex = n; repaint(); } return; }
        if (n != hoveredIndex) { hoveredIndex = n; repaint(); }
    }

    @Override public void mouseClicked(MouseEvent e) {
        if (encounterStarted) {
            if (abilityButtonBounds().contains(e.getPoint())) activateAbility();
            return;
        }
        if (stageSelectionStarted) {
            if (stageBackBounds().contains(e.getPoint())) { stageSelectionStarted = false; hoveredStageIndex = -1; repaint(); return; }
            int s = stageAt(e.getX(), e.getY()); if (s >= 0) { selectedStageIndex = s; enterSelectedStage(); }
            return;
        }
        int i = cardAt(e.getX(), e.getY()); if (i >= 0) selectCharacter(i);
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) { requestFocusInWindow(); }
    @Override public void mouseExited(MouseEvent e) { hoveredIndex = -1; hoveredStageIndex = -1; abilityButtonHovered = false; repaint(); }
    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyPressed(KeyEvent e) {
        if (encounterStarted) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) activateAbility();
            return;
        }
        if (stageSelectionStarted) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) { stageSelectionStarted = false; repaint(); }
            else if (e.getKeyCode() == KeyEvent.VK_LEFT) { selectedStageIndex = (selectedStageIndex + stages.length - 1) % stages.length; hoveredStageIndex = selectedStageIndex; repaint(); }
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT) { selectedStageIndex = (selectedStageIndex + 1) % stages.length; hoveredStageIndex = selectedStageIndex; repaint(); }
            else if (e.getKeyCode() == KeyEvent.VK_ENTER) enterSelectedStage();
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) selectCharacter((selectedIndex + characters.length - 1) % characters.length);
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) selectCharacter((selectedIndex + 1) % characters.length);
        else if (e.getKeyCode() == KeyEvent.VK_ENTER) { selectionStatus = characters[selectedIndex].name + " locked in!"; stageSelectionStarted = true; repaint(); }
    }
    @Override public void keyReleased(KeyEvent e) {}

    private static class CharacterOption { final String name, role, ability; final BufferedImage image, abilityImage; final int health, mana, damage, speed;
        CharacterOption(String name, String role, String ability, String abilityImageName, String imageName, int health, int mana, int damage, int speed) {
            this.name = name; this.role = role; this.ability = ability; this.image = loadImage(imageName); this.abilityImage = loadImage(abilityImageName); this.health = health; this.mana = mana; this.damage = damage; this.speed = speed;
        }
    }

    private static class StageOption { final String name; final BufferedImage image;
        StageOption(String name, String imageName) { this.name = name; this.image = loadImage(imageName); }
    }

    private static BufferedImage loadImage(String imageName) {
        java.net.URL resource = Game.class.getResource("/" + imageName);
        if (resource != null) { try { BufferedImage img = ImageIO.read(resource); if (img == null) throw new IllegalStateException("Unsupported image format: " + imageName); return img; } catch (IOException e) { throw new IllegalStateException("Could not read image resource: " + imageName, e); } }
        File wd = new File(System.getProperty("user.dir")); File classDir; try { classDir = new File(Game.class.getProtectionDomain().getCodeSource().getLocation().toURI()); } catch (URISyntaxException e) { throw new IllegalStateException("Could not locate game classes to load " + imageName, e); }
        java.util.List<File> files = new ArrayList<>(); files.add(new File(wd, imageName)); files.add(new File(new File(wd, "src"), imageName)); files.add(new File(new File(wd, "SoundDemo/src"), imageName)); files.add(new File(classDir, imageName)); files.add(new File(new File(classDir, "src"), imageName)); File parent = classDir.getParentFile(); if (parent != null) files.add(new File(new File(parent, "src"), imageName));
        for (File f : files) if (f.isFile()) { try { BufferedImage img = ImageIO.read(f); if (img == null) throw new IllegalStateException("Unsupported image format: " + f.getAbsolutePath()); return img; } catch (IOException e) { throw new IllegalStateException("Could not read image: " + f.getAbsolutePath(), e); } }
        throw new IllegalStateException("Image not found: " + imageName + ". Searched: " + files);
    }
}