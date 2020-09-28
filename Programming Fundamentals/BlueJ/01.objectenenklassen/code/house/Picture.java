/**
 * This class represents a simple picture. You can draw the picture using
 * the draw method. But wait, there's more: being an electronic picture, it
 * can be changed. You can set it to black-and-white display and back to
 * colors (only after it's been drawn, of course).
 *
 * This class was written as an early example for teaching Java with BlueJ.
 * 
 * @author  Michael Kšlling and David J. Barnes
 * @version 2011.07.31
 * 
 */
public class Picture
{
    private Square wall;
    private Square window;
    private Triangle roof;
    private Circle sun;
    private Circle moon;
    private Person person;

    /**
     * Constructor for objects of class Picture
     */
    public Picture()
    {
        // nothing to do... instance variables are automatically set to null
    }

    /**
     * Draw this picture.
     */
    public void draw()
    {
        wall = new Square();
        wall.moveHorizontal(-140);
        wall.moveVertical(20);
        wall.changeSize(120);
        wall.makeVisible();
        
        window = new Square();
        window.changeColor("black");
        window.moveHorizontal(-120);
        window.moveVertical(40);
        window.changeSize(40);
        window.makeVisible();

        roof = new Triangle();  
        roof.changeSize(60, 180);
        roof.moveHorizontal(20);
        roof.moveVertical(-60);
        roof.makeVisible();

        sun = new Circle();
        sun.changeColor("blue");
        sun.moveHorizontal(100);
        sun.moveVertical(-40);
        sun.changeSize(80);
        sun.makeVisible();
        
        moon = new Circle();
        moon.changeColor("yellow");
        moon.moveHorizontal(-200);
        moon.moveVertical(-20);
        moon.changeSize(40);
        moon.makeVisible();
        
        person = new Person();
        person.makeVisible();
        person.moveHorizontal(300);
        
        MoonSet();
        
        MovePerson();
    }

    /**
     * Change this picture to black/white display
     */
    public void setBlackAndWhite()
    {
        if (wall != null)   // only if it's painted already...
        {
            wall.changeColor("black");
            window.changeColor("white");
            roof.changeColor("black");
            sun.changeColor("black");
            moon.changeColor("black");
        }
    }

    /**
     * Change this picture to use color display
     */
    public void setColor()
    {
        if (wall != null)   // only if it's painted already...
        {
            wall.changeColor("red");
            window.changeColor("black");
            roof.changeColor("green");
            sun.changeColor("blue");
        }
    }
    /**
     * Make the moon set on draw.
     */
    private void MoonSet()
    {
        moon.slowMoveVertical(250);
    }
    
    /**
     * Move the person towards the house.
     */
    private void MovePerson()
    {
        person.slowMoveHorizontal(-300);
    }
}
