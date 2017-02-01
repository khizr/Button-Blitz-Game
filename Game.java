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
import java.awt.event.*;
import java.awt.Font;




public class Game extends Applet implements Runnable, KeyListener
{

    Image LeftArrow;            //declaration of the various pictures used in the program
    Image RightArrow;
    Image DownArrow;
    Image LeftArrowOutline;
    Image RightArrowOutline;
    Image DownArrowOutline;
    Image pictureGood;
    Image picturePerfect;
    Image pictureMiss;
    Image Background;
    Image Red;
    Image Heart;
    Image Instructions;
    Image Highscores;
    private final String PICTURE_PATH = "blueLeft.png";         //declaration of the various picture paths used in the program
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
    private final String PICTURE_PATH_Red = "Menu.jpg";
    private final String PICTURE_PATH_Instructions = "Instructions.jpg";
    private final String PICTURE_PATH_Highscores = "Highscores.jpg";


    int mode = 1;           //switches between menu, game, instructions, highscores and difficulty select.
    int y1 = -70;           //the y coordinate of the first arrow that will always be on the screen.
    int y2 = -70;           //the y coordinate of the second arrow that will always be on the screen.
    int yy1 = -70;          //the y coordinate of the first left arrow.
    int yy2 = -70;          //the y coordinate of the second left arrow.
    int yy3 = -70;          //the y coordinate of the first right arrow.
    int yy4 = -70;          //the y coordinate of the second right arrow.
    int yy5 = -70;          //the y coordinate of the first down arrow.
    int yy6 = -70;          //the y coordinate of the second down arrow.
    int speed = 5;          //speed of arrows
    int randomInt2 = 5;     //random integer for corresponding column for the second arrow.
    int randomInt;          //random integer for corresponding column for the second arrow.
    int score = 0;          //points given to the user
    int highscore = 0;      //highest score the user got in that session
    int lives = 5;          //how many times the user can miss without ending the game
    int LeftSize = 60;      //Size of left arrow
    int DownSize = 60;      //Size of down arrow
    int RightSize = 60;     //Size of right arrow
    private Image dbImage;  //Required for double buffering
    private Graphics dbg;   //Required for double buffering

    boolean hit = false;        //status on whether the user pressed a key while the arrow went from start to finish
    boolean perfect1 = false;   //status on whether the first arrow got a perfect hit
    boolean good1 = false;      //status on whether the first arrow got a good hit
    boolean miss1 = false;      //status on whether the first arrow was missed
    boolean perfect2 = false;   //status on whether the second arrow got a perfect hit
    boolean good2 = false;      //status on whether the second arrow got a good hit
    boolean miss2 = false;      //status on whether the second arrow was missed
    boolean perfect3 = false;   //status on whether the third arrow got a perfect hit
    boolean good3 = false;      //status on whether the third arrow got a good hit
    boolean miss3 = false;      //status on whether the third arrow was missed

    public void init ()         // initialization method
    {
	addKeyListener (this);  //declaration of keylistener that will incorporate keyboard inputs

	resize (500, 400);      //resize the window to the correct size
	LeftArrow = getToolkit ().getImage (PICTURE_PATH);                  //various paths for the corresponding pictures
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
	Red = getToolkit ().getImage (PICTURE_PATH_Red);
	Instructions = getToolkit ().getImage (PICTURE_PATH_Instructions);
	Highscores = getToolkit ().getImage (PICTURE_PATH_Highscores);
	MediaTracker tracker = new MediaTracker (this);         //declaration od mediatracker to avoid picture stutter
	prepareImage (LeftArrow, this);                         //preparing all images
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
	prepareImage (Red, this);
	prepareImage (Instructions, this);
	prepareImage (Highscores, this);

	tracker.addImage (LeftArrow, 0);                        //applying mediatracker to each image
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
	tracker.addImage (Red, 11);
	tracker.addImage (Instructions, 12);
	tracker.addImage (Highscores, 13);
	try
	{
	    tracker.waitForAll ();                              //will attempt to wait for all the pictures to load before continuing
	}
	catch (InterruptedException e)
	{
	}

	addMouseListener (new MouseAdapter ()                   //declaration of mouselistener will allow us to incorporate mouse inputs
	{
	    public void mouseClicked (MouseEvent e)             //method that is used if mouse is clicked
	    {
		int mouse_x = e.getX ();
		int mouse_y = e.getY ();
		if (mode == 1)
		{

		    if (mouse_x > 220 && mouse_x < 310 && mouse_y > 140 && mouse_y < 190)       //if "play" is clicked on menu, go to the difficulty select. mode is changed to 5
		    {
			repaint ();
			lives = 5;
			score = 0;
			mode = 5;
			repaint ();
		    }

		    if (mouse_x > 160 && mouse_x < 380 && mouse_y > 220 && mouse_y < 270)       //if "Instructions" is clicked on menu, go to instructions page. mode is changed to 4
		    {
			repaint ();
			mode = 4;
			repaint ();
		    }

		    if (mouse_x > 170 && mouse_x < 380 && mouse_y > 300 && mouse_y < 350)       //if "Highscores" is clicked on menu, go to instructions page. mode is changed to 3
		    {
			repaint ();
			mode = 3;
			repaint ();
		    }

		}

		if (mode == 4 || mode == 3 || mode == 5)
		{

		    if (mouse_x > 420 && mouse_x < 500 && mouse_y > 360 && mouse_y < 480)       //if "back" is clicked on instructions page, difficulty select page or highscores page, go back to the menu.
		    {
			repaint ();
			mode = 1;
			repaint ();
		    }
		}

		if (mode == 5)
		{


		    if (mouse_x > 120 && mouse_x < 210 && mouse_y > 140 && mouse_y < 190)       //if "easy" is clicked on difficulty select page, start the game with the speed of 5.
		    {
			lives = 5;
			score = 0;
			speed = 5;
			mode = 2;

		    }

		    if (mouse_x > 60 && mouse_x < 280 && mouse_y > 220 && mouse_y < 270)        //if "medium" is clicked on difficulty select page, start the game with the speed of 7.
		    {
			lives = 5;
			score = 0;
			speed = 7;
			mode = 2;
		    }

		    if (mouse_x > 70 && mouse_x < 280 && mouse_y > 300 && mouse_y < 350)        //if "hard" is clicked on difficulty select page, start the game with the speed of 10.
		    {
			lives = 5;
			score = 0;
			speed = 10;
			mode = 2;
		    }

		}

	    }
	}
	);
    }
    
    public void start ()
    {
	Thread thread = new Thread (this);      //declaration of thread. allows program to do multiple things at once.
	thread.start ();

    }


    public void mouseDragged (MouseEvent ex)    //method that is used if mouse is dragged
    {
    }


    public void mouseMoved (MouseEvent ex)      //method that is used if mouse is moved
    {
    }


    public void run ()              //thread that was set up earlier
    {
	while (true)
	{
	    while (mode == 5)       //numerous "repaint" commands to keep the game running at all outcomes.
	    {

		repaint ();
	    }
	    while (mode == 1)
	    {
		repaint ();
	    }

	    while (mode == 3)
	    {
		repaint ();
	    }

	    while (mode == 4)
	    {
		repaint ();
	    }

	    repaint ();
	    if (mode == 2)          // mode number 2 is the actual game
	    {

		if (y1 == -70)      //-70 is the coordinate when a new arrow will be chosen
		{
		    Random generator = new Random ();
		    randomInt = generator.nextInt (3) + 1;      //random generator between 1 to 3.
		}
		if (y1 == 210)          //There will always be 2 arrows on the screen so 210 is the halfway point. when the first arrow is at 210, the second arrow will spawn.
		{
		    Random generator = new Random ();
		    randomInt2 = generator.nextInt (3) + 1;     //random generator between 1 to 3.
		}

		if (randomInt == 1)             //There are only 2 possible places where an arrow can be at a time. There are 6 possible arrows. These if statements change the y value of the arrows.
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


		if (y1 == 420)
		{
		    if (hit == false)           
		    {
			lives -= 1;             //if the user didnt touch the correct key at all, one life is taken away.
		    }
		    y1 = -70;                   //once the first arrow crosses the window and reaches y value of 420, it is reset back to -70
		    hit = false;
		}
		if (y2 == 420)
		{
		    if (hit == false)
		    {
			lives -= 1;             //if the user didnt touch the correct key at all, one life is taken away.
		    }
		    y2 = -70;                   //once the second arrow crosses the window and reaches y value of 420, it is reset back to -70
		    hit = false;
		}
		y1 += speed;                    //y value of first arrow increasing by speed value every time
		if (randomInt2 != 5)
		{
		    y2 += speed;                //y value of second arrow increasing by speed value every time
		}
		try
		{
		    Thread.sleep (17);          //how often the thread will repeat itself. The number 17 is chosen because it would create roughly 60 frames per second which is the refresh rate of most monitors
		}
		catch (InterruptedException e)
		{
		    e.printStackTrace ();
		}

	    }
	    if (lives == 0)                     //once the user loses the game, go to the main menu.
	    {
		mode = 1;
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

	Graphics2D g2d = (Graphics2D) g;
	g2d.setRenderingHint (RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);            //Hints incorporated to make texts and shape look better through anti aliasing. They look less pixelized from the edges.
	g2d.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	g.setColor (Color.BLACK);
	g.fillRect (0, 0, 750, 600);            //black background 

	Font title = new Font ("Franklin Gothic Demi Cond", 0, 90);             //Font for title
	Font options = new Font ("Franklin Gothic Demi Cond", 0, 45);           //Font for the options
	Font scoreFont = new Font ("Franklin Gothic Demi Cond", 0, 20);         //Font for the score
	Font Back = new Font ("Franklin Gothic Demi Cond", 0, 30);              //Font for back button
	Font SelectDifficulty = new Font ("Franklin Gothic Demi Cond", 0, 70);  //Font for Difficulty Select title
	g.setColor (Color.WHITE);                                               //set colour to white for white text                                        

	if (mode == 5)                                                          //If program is on difficulty select page
	{
	    g.drawImage (Red, 0, 0, 500, 400, null);                            //draw background image
	    g.setFont (SelectDifficulty);                                       //set the Font
	    g.drawString ("Select Difficulty", 35, 85);                         //write the title
	    g.setFont (options);                                                //set the font
	    g.drawString ("Easy", 110, 180);                                    //write "Easy"
	    g.drawString ("Medium", 90, 260);                                   //write "Medium"
	    g.drawString ("Hard", 110, 340);                                    //write "Hard"
	}

	if (mode == 1)                                                          //If program is on menu page
	{
	    g.drawImage (Red, 0, 0, 500, 400, null);                            //draw background image
	    g.setFont (title);                                                  //set the font
	    g.drawString ("Button Blitz", 55, 85);                              //Write the title "Button Blitz"
	    g.setFont (options);                                                //Set the font
	    g.drawString ("Play", 230, 180);                                    //write "play"
	    g.drawString ("Instructions", 170, 260);                            //write "Instructions"
	    g.drawString ("Highscores", 180, 340);                              //write "Highscores"

	}

	if (mode == 4)                                                          //if program is on Instructions page
	{
	    g.drawImage (Instructions, 0, 0, 500, 400, null);                   //draw background image
	    g.setFont (Back);                                                   //Set the font
	    g.drawString ("BACK", 425, 385);                                    //create the back button
	}

	if (mode == 3)                                                          //If program is on highscores page
	{
	    g.drawImage (Highscores, 0, 0, 500, 400, null);                     //draw background image
	    g.setFont (Back);                                                   //Set the font
	    g.drawString ("BACK", 425, 385);                                    //create the back button
	    g.setFont (options);                                                //Set the font
	    g.drawString ((String.valueOf (score)), 330, 300);                  //Write the score
	    if (score > highscore)                                              //if score is bigger than highscore, make highscore equal score
	    {
		highscore = score;
	    }
	    g.drawString ((String.valueOf (highscore)), 285, 210);              //Write the highscore

	}

	if (mode == 2)                                                          //If program is on the game page
	{

	    g.drawImage (Background, 155, 0, 110, 400, null);                   //draw background image
	    g.drawImage (Background, 270, 0, 110, 400, null);                   //draw background image
	    g.drawImage (Background, 385, 0, 110, 400, null);                   //draw background image

	    g.drawImage (LeftArrowOutline, 175, 320, 70, 70, null);             //draw left arrow outline image
	    g.drawImage (RightArrowOutline, 285, 314, 85, 85, null);            //draw right arrow outline image
	    g.drawImage (DownArrowOutline, 405, 320, 70, 70, null);             //draw down arrow outline image

	    for (int i = 0 ; i < lives ; i++)                                   // for loop that will draw as many hearts as there are lives
	    {
		g.drawImage (Heart, ((i * 25) + 5), 30, 23, 23, null);
	    }

	    g.setFont (scoreFont);                                              //Sets the font
	    g.drawString (("Score: " + (String.valueOf (score))), 10, 20);      //Writes the score

	    if (randomInt == 1)                                                 //draws the arrow in its location if this arrow was chosen from the generator
	    {
		g.drawImage (LeftArrow, 180, yy1, LeftSize, LeftSize, null);
	    }

	    if (randomInt2 == 1)                                                //draws the arrow in its location if this arrow was chosen from the generator
	    {
		g.drawImage (LeftArrow, 180, yy2, LeftSize, LeftSize, null);
	    }


	    if (randomInt == 2)                                                 //draws the arrow in its location if this arrow was chosen from the generator
	    {
		g.drawImage (RightArrow, 410, yy3, RightSize, RightSize, null);
	    }


	    if (randomInt2 == 2)                                                //draws the arrow in its location if this arrow was chosen from the generator
	    {
		g.drawImage (RightArrow, 410, yy4, RightSize, RightSize, null);
	    }


	    if (randomInt == 3)                                                 //draws the arrow in its location if this arrow was chosen from the generator
	    {
		g.drawImage (DownArrow, 295, yy5, DownSize, DownSize, null);
	    }


	    if (randomInt2 == 3)                                                //draws the arrow in its location if this arrow was chosen from the generator
	    {
		g.drawImage (DownArrow, 295, yy6, DownSize, DownSize, null);
	    }

	    if (perfect1 == true || perfect2 == true || perfect3 == true)       //if the user pressed key at the perfect time, draw the perfect image to prompt them
	    {
		g.drawImage (picturePerfect, 10, 330, 130, 50, null);
	    }

	    if (good1 == true || good2 == true || good3 == true)                //if the user pressed key at a good time, draw the good image to prompt them
	    {
		g.drawImage (pictureGood, 10, 330, 130, 50, null);
	    }

	    if (miss1 == true || miss2 == true || miss3 == true)                //if the user missed, draw the miss image to prompt them.
	    {
		g.drawImage (pictureMiss, 10, 330, 130, 50, null);
	    }

	}
    }


    public void keyPressed (KeyEvent e)                 // method for key inputs
    {
	int key = e.getKeyCode ();                      //gets the number of the key that is pressed

	if (mode == 2)                                  //if the program is on game page
	{

	    if (key == 37)                              //if left key is pressed, the program will determine whether it is a perfect, good or a miss hit and give points on each option.
	    {

		if ((341 > yy1 && 309 < yy1) || (341 > yy2 && 309 < yy2))   //if its a perfect hit
		{
		    score += 500;                                           //add 500 points to score
		    perfect1 = true;                                        //will display the perfect image now that the variable is true
		    hit = true;                                             //user DID hit a key so a life will not be taken away.
		}

		else if ((340 < yy1 && 370 > yy1) || (280 < yy1 && 310 > yy1) || (340 < yy2 && 370 > yy2) || (280 < yy2 && 310 > yy2))  //if its a good hit
		{
		    score += 100;                                           //add 100 points to score
		    good1 = true;                                           //will display the good image now that the variable is true
		    hit = true;                                             //user DID hit a key so a life will not be taken away
		}
		else                                                        //would mean a miss hit
		{
		    score -= 50;                                            //will take away 50 points from because of a miss hot
		    lives -= 1;                                             //1 life is taken away
		    miss1 = true;                                           //user missed so the miss image will appear now that the variable is true
		}
	    }

	    else
	    {
		perfect1 = false;                                           //variable becomes false is key is not pressed
		good1 = false;                                              //variable becomes false is key is not pressed
		miss1 = false;                                              //variable becomes false is key is not pressed
	    }

	    if (key == 40)                                                  //if right key is pressed, the program will determine whether it is a perfect, good or a miss hit and give points on each option.
	    {
		if ((341 > yy5 && 309 < yy5) || (341 > yy6 && 309 < yy6))   //if its a perfect hit
		{
		    score += 500;                                           //add 500 points to score
		    perfect2 = true;                                        //will display the perfect image now that the variable is true
		    hit = true;                                             //user DID hit a key so a life will not be taken away.

		}

		else if ((340 < yy5 && 370 > yy5) || (280 < yy5 && 310 > yy5) || (340 < yy6 && 370 > yy6) || (280 < yy6 && 310 > yy6))  //if its a good hit
		{
		    score += 100;                                           //add 100 points to score
		    good2 = true;                                           //will display the good image now that the variable is true
		    hit = true;                                             //user DID hit a key so a life will not be taken away
		}
		else                                                        //would mean a miss hit
		{
		    score -= 50;                                            //will take away 50 points from because of a miss hot
		    lives -= 1;                                             //1 life is taken away
		    miss2 = true;                                           //user missed so the miss image will appear now that the variable is true
		}
	    }
	    else
	    {
		perfect2 = false;                                           //variable becomes false is key is not pressed
		good2 = false;                                              //variable becomes false is key is not pressed
		miss2 = false;                                              //variable becomes false is key is not pressed
	    }

	    if (key == 39)
	    {
		if ((341 > yy3 && 309 < yy3) || (341 > yy4 && 309 < yy4))   //if down key is pressed, the program will determine whether it is a perfect, good or a miss hit and give points on each option.
		{
		    score += 500;                                           //add 500 points to score
		    perfect3 = true;                                        //will display the perfect image now that the variable is true
		    hit = true;                                             //user DID hit a key so a life will not be taken away.

		}

		else if ((340 < yy3 && 370 > yy3) || (280 < yy3 && 310 > yy3) || (340 < yy4 && 370 > yy4) || (280 < yy4 && 310 > yy4))  //if its a good hit
		{
		    score += 100;                                           //add 100 points to score
		    good3 = true;                                           //will display the good image now that the variable is true
		    hit = true;                                             //user DID hit a key so a life will not be taken away
		}
		else                                                        //would mean a miss hit
		{
		    score -= 50;                                            //will take away 50 points from because of a miss hot
		    lives -= 1;                                             //1 life is taken away
		    miss3 = true;                                           //user missed so the miss image will appear now that the variable is true
		}
	    }

	    else
	    {
		perfect3 = false;                                           //variable becomes false is key is not pressed
		good3 = false;                                              //variable becomes false is key is not pressed
		miss3 = false;                                              //variable becomes false is key is not pressed
	    }
	}
    }

    
    public void keyReleased (KeyEvent e)
    {
    }


    public void keyTyped (KeyEvent e)
    {
    }
} // Game class


