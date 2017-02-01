// The "Game" class.
import java.applet.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Image;
import java.util.Random;
import java.awt.MediaTracker;
import java.awt.Event;
import java.awt.Font;




public class Game extends Applet implements Runnable
{

    Image LeftArrow;
    Image RightArrow;
    Image DownArrow;
    Image LeftArrowOutline;
    Image RightArrowOutline;
    Image DownArrowOutline;
    Image pictureGood;
    Image picturePerfect;
    Image pictureMiss;
    Image Background;
    Image Heart;
    private final String PICTURE_PATH = "blueLeft.png";
    private final String PICTURE_PATH2 = "blueRight.png";
    private final String PICTURE_PATH3 = "blueDown.png";
    private final String PICTURE_PATH2O = "DownO.png";
    private final String PICTURE_PATH3O = "RightO.png";
    private final String PICTURE_PATHO = "LeftO.png";
    private final String PICTURE_PATH_Good = "Good.png";
    private final String PICTURE_PATH_Perfect = "Perfect.png";
    private final String PICTURE_PATH_Miss = "Miss.png";
    private final String PICTURE_PATH_Background = "background.png";
    private final String PICTURE_PATH_Heart = "Heart.png";

    int mode = 2;
    int y1 = -70;
    int y2 = -70;
    int yy1 = -70;
    int yy2 = -70;
    int yy3 = -70;
    int yy4 = -70;
    int yy5 = -70;
    int yy6 = -70;
    int radius = 70;
    int dy = 2;
    int randomInt2 = 5;
    int randomInt;
    int score = 0;
    int lives = 5;
    int LeftSize = 60;
    int DownSize = 60;
    int RightSize = 60;
    private Image dbImage;
    private Graphics dbg;
    boolean perfect1 = false;
    boolean good1 = false;
    boolean miss1 = false;
    boolean perfect2 = false;
    boolean good2 = false;
    boolean miss2 = false;
    boolean perfect3 = false;
    boolean good3 = false;
    boolean miss3 = false;



    public void init ()
    {
	resize (500, 400);
	LeftArrow = getToolkit ().getImage (PICTURE_PATH);
	RightArrow = getToolkit ().getImage (PICTURE_PATH2);
	DownArrow = getToolkit ().getImage (PICTURE_PATH3);
	RightArrowOutline = getToolkit ().getImage (PICTURE_PATH2O);
	DownArrowOutline = getToolkit ().getImage (PICTURE_PATH3O);
	LeftArrowOutline = getToolkit ().getImage (PICTURE_PATHO);
	pictureGood = getToolkit ().getImage (PICTURE_PATH_Good);
	picturePerfect = getToolkit ().getImage (PICTURE_PATH_Perfect);
	pictureMiss = getToolkit ().getImage (PICTURE_PATH_Miss);
	Background = getToolkit ().getImage (PICTURE_PATH_Background);
	Heart = getToolkit ().getImage (PICTURE_PATH_Heart);
	MediaTracker tracker = new MediaTracker (this);
	prepareImage (LeftArrow, this);
	prepareImage (RightArrow, this);
	prepareImage (DownArrow, this);
	prepareImage (LeftArrowOutline, this);
	prepareImage (RightArrowOutline, this);
	prepareImage (DownArrowOutline, this);
	prepareImage (pictureGood, this);
	prepareImage (picturePerfect, this);
	prepareImage (pictureMiss, this);
	prepareImage (Background, this);
	prepareImage (Heart, this);

	tracker.addImage (LeftArrow, 0);
	tracker.addImage (RightArrow, 1);
	tracker.addImage (DownArrow, 2);
	tracker.addImage (LeftArrowOutline, 3);
	tracker.addImage (RightArrowOutline, 4);
	tracker.addImage (DownArrowOutline, 5);
	tracker.addImage (pictureGood, 6);
	tracker.addImage (picturePerfect, 7);
	tracker.addImage (pictureMiss, 8);
	tracker.addImage (Background, 9);
	tracker.addImage (Heart, 10);
	try
	{
	    tracker.waitForAll ();
	}
	catch (InterruptedException e)
	{
	}

    }


    // Ryans high score was 43800 :D
    public void start ()
    { 
	Thread thread = new Thread (this);
	thread.start ();

    }
    



    public void run ()
    {
	while (lives != 0)
	{

	    if (y1 == -70)
	    {
		Random generator = new Random ();
		randomInt = generator.nextInt (3) + 1;
	    }
	    if (y1 == 210)
	    {
		Random generator = new Random ();
		randomInt2 = generator.nextInt (3) + 1;
	    }

	    if (randomInt == 1)
	    {
		yy1 = y1;
	    }


	    if (randomInt2 == 1)
	    {
		yy2 = y2;
	    }


	    if (randomInt == 2)
	    {
		yy3 = y1;
	    }


	    if (randomInt2 == 2)
	    {
		yy4 = y2;
	    }


	    if (randomInt == 3)
	    {
		yy5 = y1;
	    }


	    if (randomInt2 == 3)
	    {
		yy6 = y2;
	    }
	    repaint ();

	    if (y1 == 420)
	    {
		y1 = -70;
	    }
	    if (y2 == 420)
	    {
		y2 = -70;
	    }
	    y1 += dy;
	    if (randomInt2 != 5)
	    {
		y2 += dy;
	    }
	    try
	    {
		Thread.sleep (8);
	    }
	    catch (InterruptedException e)
	    {
		e.printStackTrace ();
	    }
	}
    }


    /** Update - Method, implements double buffering */
    public void update (Graphics g)
    {

	// initialize buffer
	if (dbImage == null)
	{
	    dbImage = createImage (this.getSize ().width, this.getSize ().height);
	    dbg = dbImage.getGraphics ();
	}

	// clear screen in background
	dbg.setColor (getBackground ());
	dbg.fillRect (0, 0, this.getSize ().width, this.getSize ().height);

	// draw elements in background
	dbg.setColor (getForeground ());
	paint (dbg);

	// draw image on the screen
	g.drawImage (dbImage, 0, 0, this);

    }


    public void stop ()
    {
    }


    public void destroy ()
    {
    }


    public void paint (Graphics g)
    {
    
	Graphics2D g2d = (Graphics2D)g;
	g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	//g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	
	g.setColor (Color.BLACK);
	g.fillRect (0, 0, 750, 600);
	
	Font font = new Font ("Arial", 0, 20);
	g.setFont (font);


	if (mode == 1)
	{

	}

	if (mode == 2)
	{

	    g.setColor (Color.CYAN);
	    g.fillRect (150, 0, 5, 400);
	    g.fillRect (265, 0, 5, 400);
	    g.fillRect (380, 0, 5, 400);
	    g.fillRect (495, 0, 5, 400);
	    g.drawImage (Background, 155, 0, 110, 400, null);
	    g.drawImage (Background, 270, 0, 110, 400, null);
	    g.drawImage (Background, 385, 0, 110, 400, null);

	    g.drawImage (LeftArrowOutline, 175, 320, 70, 70, null);
	    g.drawImage (RightArrowOutline, 285, 314, 85, 85, null);
	    g.drawImage (DownArrowOutline, 405, 320, 70, 70, null);
	    
	    for (int i = 0 ; i < lives ; i++)
	    {                
	    g.drawImage (Heart, ((i * 25) + 5), 30, 23, 23, null);
	    }
	    

	    g.drawString (("Your score: " +(String.valueOf (score))), 10, 20);







	    if (randomInt == 1)
	    {
		g.drawImage (LeftArrow, 180, yy1, LeftSize, LeftSize, null);
	    }

	    if (randomInt2 == 1)
	    {
		g.drawImage (LeftArrow, 180, yy2, LeftSize, LeftSize, null);
	    }


	    if (randomInt == 2)
	    {
		g.drawImage (RightArrow, 410, yy3, RightSize, RightSize, null);
	    }


	    if (randomInt2 == 2)
	    {
		g.drawImage (RightArrow, 410, yy4, RightSize, RightSize, null);
	    }


	    if (randomInt == 3)
	    {
		g.drawImage (DownArrow, 295, yy5, DownSize, DownSize, null);
	    }


	    if (randomInt2 == 3)
	    {
		g.drawImage (DownArrow, 295, yy6, DownSize, DownSize, null);
	    }
	    
	    if (perfect1 == true || perfect2 == true || perfect3 == true)
	    {
	    g.drawImage (picturePerfect, 10, 330, 130, 50, null);
	    }
	    
	    if (good1 == true || good2 == true || good3 == true)
	    {
	    g.drawImage (pictureGood, 10, 330, 130, 50, null);
	    }
	    
	    if (miss1 == true || miss2 == true || miss3 == true)
	    {
	    g.drawImage (pictureMiss, 10, 330, 130, 50, null);
	    }

	}
    }


    public boolean keyDown (Event e, int key)
    {

	// user presses left cursor f
	if (key == Event.LEFT)
	{
	
	    if ((341 > yy1 && 309 < yy1) || (341 > yy2 && 309 < yy2))
	    {
		score += 500;
		perfect1 = true;
	    }

	    else if ((340 < yy1 && 370 > yy1) || (280 < yy1 && 310 > yy1) || (340 < yy2 && 370 > yy2) || (280 < yy2 && 310 > yy2))
	    {
		score += 100;
		good1 = true;
	    }
	    else
	    {
		score -= 50;
		lives -= 1;
		miss1 = true;
	    }
	}

	else
	{
	    perfect1 = false;
	    good1 = false;
	    miss1 = false;
	}
	
	if (key == Event.DOWN)
	{
	    if ((341 > yy5 && 309 < yy5) || (341 > yy6 && 309 < yy6))
	    {
		score += 500;
		perfect2 = true;

	    }

	    else if ((340 < yy5 && 370 > yy5) || (280 < yy5 && 310 > yy5) || (340 < yy6 && 370 > yy6) || (280 < yy6 && 310 > yy6))
	    {
		score += 100;
		good2 = true;
	    }
	    else
	    {
		score -= 50;
		lives -= 1;
		miss2 = true;
	    }
	}
	else
	{
	    perfect2 = false;
	    good2 = false;
	    miss2 = false;
	}

	if (key == Event.RIGHT)
	{
	    if ((341 > yy3 && 309 < yy3) || (341 > yy4 && 309 < yy4))
	    {
		score += 500;
		perfect3 = true;

	    }

	    else if ((340 < yy3 && 370 > yy3) || (280 < yy3 && 310 > yy3) || (340 < yy4 && 370 > yy4) || (280 < yy4 && 310 > yy4))
	    {
		score += 100;
		good3 = true;
	    }
	    else
	    {
		score -= 50;
		lives -= 1;
		miss3 = true;
	    }
	}

	else
	{
	    perfect3 = false;
	    good3 = false;
	    miss3 = false;
	}
   


	return true;

    }
} // Game class


