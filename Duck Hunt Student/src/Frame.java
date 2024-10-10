import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	Font bigFont = new Font("Serif", Font.BOLD, 75);
	
	JellyFish fish = new JellyFish();
	Background ground = new Background();
	ForGround kelp = new ForGround();
	ForGround kelp2 = new ForGround();
	ForGround kelp3 = new ForGround();
	ForGround kelp4 = new ForGround();
	ForGround kelp5 = new ForGround();
	ForGround kelp6 = new ForGround();
	ForGround kelp7 = new ForGround();
	ForGround kelp8 = new ForGround();
	
	bubble bubble = new bubble();

	SpongeBobHouse house = new SpongeBobHouse();
	spongeBob spongeBobPlain = new spongeBob();
	
//	jellFishFall jellFall = new jellFishFall();//won't be painted
//	
//	explosion explosion = new explosion();//won't be painted
	
	int score;
	int roundTimer;
	long time;
	int stage;
	boolean start;
	boolean bubbleOn;
	boolean fishFly;
	boolean timer;
	boolean lose;
	boolean fishHit;
	boolean nextStage;
	String spaceToPlay;
	String spaceNextStage;
	
	public void init() {//init variables for the "start" of the game
		roundTimer = 30;
		score = 0;
		time = 0;
		stage = 1;
		start = true;
		fishFly = false;
		bubbleOn = false;
		timer = false;
		lose = false;
		fishHit = true;
		nextStage = false;
		spaceToPlay = "Press Space To start";
		
		bubble.setY(800);
		
		fish.setScale(1, 1);
		fish.setVx(0);
		fish.setY(799);
		fish.setX(400);
		
		house.setY(400);
		house.setX(100);
		house.setScale(5,5);//house (don't change)
		
		ground.setScale(2, 2);//don't change backgroudn resizing
		
		kelp.setY(800-200);//kelp (don't change)
		kelp.setScale(3, 3);
		
		kelp2.setScale(3,3);
		kelp2.setXY(kelp.getWidth()*3, 600);
		
		kelp3.setScale(3,3);
		kelp3.setXY(kelp.getWidth()*3*2, 600);
		
		kelp4.setScale(3,3);
		kelp4.setXY(kelp.getWidth()*3*3, 600);
		
		kelp5.setScale(3,3);
		kelp5.setXY(kelp.getWidth()*3*4, 600);
		
		kelp6.setScale(3,3);
		kelp6.setXY(kelp.getWidth()*3*5, 600);

		kelp7.setScale(3,3);
		kelp7.setXY(kelp.getWidth()*3*6, 600);

		kelp8.setScale(3,3);
		kelp8.setXY(kelp.getWidth()*3*7, 600);

		spongeBobPlain.setScale(2, 2);//change variables later
		spongeBobPlain.setX(400);
		spongeBobPlain.setY(800);
		spongeBobPlain.setVx(-5);
		spongeBobPlain.setVy(0);
		
	}
	
	public void reset() {//reseting for muliply rounds
		stage +=1;
		spaceToPlay = "Stage "+stage;
		nextStage = true;
		fishFly = false;
		timer = false;
		fish.setVy(0);
		fish.setY(799);
		fish.changePicture("imgs/jellFish.gif");
	}
	
	
	
	public void paint(Graphics g) {
		super.paintComponent(g);


		//layer obecjts as yoru want them to layer visualy, backroudn fist
		ground.paint(g);
		
		house.paint(g);
		
		spongeBobPlain.paint(g);
		
		fish.paint(g);//this is the fish making lots of changes later
		
		bubble.paint(g);
		
		kelp.paint(g);
		kelp2.paint(g);
		kelp3.paint(g);
		kelp4.paint(g);
		kelp5.paint(g);
		kelp6.paint(g);
		kelp7.paint(g);
		kelp8.paint(g);
		

		
		g.setFont(bigFont);
		g.setColor(Color.black);
		g.drawString("Time "+this.roundTimer, 400, 65);
		g.drawString("Score " +this.score,50,65);
		
		g.drawString(""+spaceToPlay, 200, 400);
		g.drawString("Stage " +stage, 800, 65);
			
		/*logic statement for fish
		 * change later
		 */
		
		if (fish.getY()>=800) {
			spongeBobPlain.setX(fish.getX()+200);
			spongeBobPlain.setY(800);
			spongeBobPlain.setVy(-5);
			fish.setY(799);
			fish.setVx(0);
			fish.setVy(0);
		}
		
		
		if (fish.getY()<=0) {
			lose = true;
			spaceToPlay = "Press Space To Play Again";
			fishFly = false;
			timer = false;
			start = true;
		}
		

		
		if (spongeBobPlain.getY()<=500) {//makes sponge bob go up then down
			spongeBobPlain.setVy(0);
			if(spongeBobPlain.timer>0) {
				spongeBobPlain.timer -=200;
			}else {
				spongeBobPlain.setVy(5);
				spongeBobPlain.timer = 1000;
				fishFly = true;
				fishHit = true;
				fish.setX((int)(Math.random()*1150));
				fish.setVx((int)((Math.random()-0.5)*20));
				fish.changePicture("imgs/jellFish.gif");
			}
			
		}
		
		if (spongeBobPlain.getY()>=800) {
			spongeBobPlain.setVx(-5);
		}
		
		if (spongeBobPlain.getX()<=0) {
			spongeBobPlain.setVx(spongeBobPlain.getVx()*-1);
		}
		
		if (fish.getX()<=0||fish.getX()>=1200-fish.getWidth()) {
			fish.setVx(fish.getVx()*-1);
		}
		
		if (fishFly && timer) {
			fish.setVy(-(stage*2));
		}
		
		if (!bubbleOn) {
			bubbleOn = true;
			bubble.setScale(1,1);
			bubble.setY(150);
			bubble.setX(-50-bubble.getWidth());
			bubble.setVx((int)Math.random()*(5)+1);
		}
		
		if (bubble.getX()>=1200) {
			bubbleOn = false;
		}
		
		time+=20;//timer
		
		if (time%600==0&&timer) {//checks and timer for when round ends
			roundTimer-=1;
			if (roundTimer == 0 ) {
				reset();
			}
		}
		
		
		
	}

	public static void main(String[] arg) {
		Frame f = new Frame();
	}
	
	public Frame() {
		JFrame f = new JFrame("Duck Hunt");
		f.setSize(new Dimension(1200, 800));
		f.setBackground(Color.blue);
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1,2));
		f.addMouseListener(this);
		f.addKeyListener(this);
		
		init();//call init method to give proterties to objects and varables
		
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
	}
	
	Timer t = new Timer(16, this);//make timer visible to other methods
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent mouse) {
		// TODO Auto-generated method stub
	//performe a rectangle conlision wth mouse and the object
		Rectangle rMouse = new Rectangle(mouse.getX()-fish.getWidth(),mouse.getY()-fish.getHeight(),fish.getWidth(),fish.getHeight());
		//2nd rect for the object
		Rectangle rMain = new Rectangle(fish.getX(),fish.getY(),fish.getWidth(),fish.getHeight());
		
		//chekc if colliding
		if (rMouse.intersects(rMain)&&fishHit) {
			System.out.println("hit the fish with mouse");
			fishFly = false;
			fish.setVy(7);
			fish.setVx(0);
			fish.changePicture("imgs/jellFishFall.gif");
			score += 50;
			fishHit = false;
		}
		
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (!timer&&arg0.getKeyCode()==32) {
			fish.setY(799);
			fish.setX((int)(Math.random()*1150));
			timer = true;
			roundTimer = 30;
			fishFly = true;
			spaceToPlay = "";
			fish.setVx((int)((Math.random()-0.5)*20));
		}
		
		if (lose&&arg0.getKeyCode()==32) {
			score = 0;
			stage = 1;
		}
		
		if(nextStage&&arg0.getKeyCode()==32) {
			nextStage = false;
			fishHit=true;
			roundTimer = 30;
			spaceToPlay = "";
			fishFly = true;
			timer = true;
			fish.setX((int)(Math.random()*1150));
			fish.setVx((int)((Math.random()-0.5)*20));
		}
		
		
		System.out.println(arg0.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
