import java.awt.*;
import javax.swing.*;
import java.util.*;

class Stoplicht {
  private int positie;
  private boolean isZichtbaar;
  private boolean rood   = true;
  private boolean oranje = false;
  private boolean groen  = false;

  private static TekenVenster venster = null;
  
  private final static int blinkOnTime = 500; 
  private final static int blinkOffTime = 1000;
  private Thread blinkThread;
  
  /**
    * Constructor voor objecten van klasse Stoplicht
    * Maakt een stoplicht op positie 100
  */
  public Stoplicht() {
    this( 100 );
  }
  
  /**
    * Constructor voor objecten van klasse Stoplicht
    * Maakt een stoplicht op de plaats aangegeven door positie
  */
  public Stoplicht( int positie ) {
    isZichtbaar = false;
    this.positie = positie;
  }

  // Methoden
  
  public void startBlinking()
  {
      blinkThread = new Thread(){
          public void run(){
              blink();
            }
        };
    blinkThread.start();
  }
  
    public void stopBlinking()
  {
    blinkThread.stop();
    
    reset();
  }
  
  private void blink(){
      setOranje();
      
      slaap(blinkOnTime);
      
      reset();
      
      slaap(blinkOffTime);
      
      blink();
  }
  
  private void slaap(int milliseconds) {
    try { Thread.sleep( milliseconds ); }
    catch( InterruptedException e ) { }
  }
  
  
  /**
    * Zet alle lampen van het stoplicht uit
  */
  public void reset() {
    rood   = false;
    oranje = false;
    groen  = false;
    teken();
  }

  /**
    * Zet de rode lamp aan, de andere uit
  */
  public void setRood() {
    rood = true;
    oranje = false;
    groen = false;
    teken();
  }

  /**
    * Zet de oranje lamp aan, de andere uit
  */
  public void setOranje() {
    rood = false;
    oranje = true;
    groen = false;
    teken();
  }

  /**
    * Zet de groene lamp aan, de andere uit
  */
  public void setGroen() {
    rood = false;
    oranje = false;
    groen = true;
    teken();
  }
  
  /** 
    * Levert true als rood aan is, anders false
  */
  public boolean isRood() {
    return rood;
  }
  
  /** 
    * Levert true als oranje aan is, anders false
  */
  public boolean isOranje() {
    return oranje;
  }

  /** 
    * Levert true als groen aan is, anders false
  */
  public boolean isGroen() {
    return groen;
  }
   

  /** 
    * Maak stoplicht zichtbaar in het uitvoervenster
  */
  public void maakZichtbaar() {
    isZichtbaar = true;
    teken();
  }
  

  // private methoden
  private void teken() {
    if( isZichtbaar ) {
      if( venster == null ) 
        venster = new TekenVenster( "Venster voor stoplichten" );
      venster.teken( this );
    }
  }
  
  private int getPositie() {
    return positie;
  }
    
  // Inwendige klasse
  private class TekenVenster {

    private JFrame frame;
    private TekenPaneel paneel;
    private Graphics gB;
    private Image buffer;
    
    private ArrayList<Stoplicht> lijst; 
    
    // private Constructor ivbm singleton Venster
    private TekenVenster( String titel ) {
      frame = new JFrame( titel );
      paneel = new TekenPaneel();
      frame.setContentPane( paneel );
      paneel.setPreferredSize( new Dimension( 500, 350 ) );
      frame.pack();
      lijst = new ArrayList<Stoplicht>();
      maakZichtbaar( true );
    }  
    
    
   
    // Overige methoden
    public void maakZichtbaar( boolean zichtbaar ) {
      if( gB == null ) {
        Dimension dim = paneel.getSize();
        buffer = frame.createImage( dim.width, dim.height );
        gB = buffer.getGraphics();
      }
      frame.setVisible( zichtbaar );
    }
    
    public void teken( Stoplicht s ) {
      frame.setVisible( true );
      lijst.remove( s );
      lijst.add( s );
      herschilder();
    }
  
  
    public void verwijder( Stoplicht s ) {
      lijst.remove( s );
      herschilder();
    }
      
   
    // Private methoden
    private void herschilder() {
      maakBufferSchoon();
      Iterator<Stoplicht> iter = lijst.iterator();
      while( iter.hasNext() ) {
        tekenStoplicht( iter.next() );
      }
      paneel.repaint();
    }
    
    private void maakBufferSchoon() {
      gB.setColor( Color.white );
      Dimension dim = paneel.getSize();
      gB.fillRect( 0, 0, dim.width, dim.height );
    }
    
    private void tekenStoplicht( Stoplicht s ) {
      int positie = s.getPositie();
      gB.setColor( Color.gray );
  
      gB.fillRect( positie - 30, 50, 60, 160 ); // Het stoplicht
      gB.fillRect( positie - 5, 210, 10, 120 ); // De paal
      if( s.isRood() ) {            // Kijk welke lamp aan moet
        gB.setColor( Color.red );
        gB.fillOval( positie - 20, 60, 40, 40 );
      }
      else if( s.isOranje() ) {
        gB.setColor( Color.orange );
        gB.fillOval( positie - 20, 110, 40, 40 );
      }
      else if( s.isGroen() ) {
        gB.setColor( Color.green );
        gB.fillOval( positie - 20, 160, 40, 40 );
      }

      gB.setColor( Color.black );
      gB.drawOval( positie - 20, 60, 40, 40 );
      gB.drawOval( positie - 20, 110, 40, 40 );
      gB.drawOval( positie - 20, 160, 40, 40 );
    }
  
       
    // Inwendige klasse
    private class TekenPaneel extends JPanel {
      public void paint( Graphics g ) {
        g.drawImage( buffer, 0, 0, null );
      }
    }
  }
}
    