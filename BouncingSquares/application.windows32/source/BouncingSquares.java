import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class BouncingSquares extends PApplet {

BouncingSquare[] squares = new BouncingSquare[0];
int NUM_Squares = 25;

public void setup(){
  size(640, 360);
  smooth();
}

public void draw(){
  background(255);
  frameRate(5);
  squares = (BouncingSquare[])append(squares, new BouncingSquare());
  frameRate(30);
    if (squares != null) {
      for (int i = 0; i < squares.length; i++) {
        if(!squares[i].BounceFull()){
          squares[i].move();
          squares[i].display();
        }
      }
  }
}

public void mousePressed(){
  for (int i = 0; i < NUM_Squares; i++) {
    squares = (BouncingSquare[])append(squares, new BouncingSquare());
  }
}

//-----------------------------------//

public class BouncingSquare
{
  int size, rsize;    // width and height of sq
  int xpos, ypos;         // initial x and y position of sq
  int newx, newy;         // current position of sq
  int jumpx, jumpy;       // initial loc of inner bounds
  int newjumpx, newjumpy; // current shift of inner bounds
  int idx, idy;           // initial random direction of sq
  int movex, movey;       // current direction of sq
  int speedx, speedy;     // speed of x and y movement
  int dirx, diry;         // current direction of inner bounds
  int boox, booy;         // boolean
  int bounces;
  int xdir, ydir;
  PImage[] imgs;

  public BouncingSquare () {
    bounces = 3;
    rsize = 75;    size = 15;
    xpos = mouseX;    ypos = mouseY;
    newx = xpos;    newy = ypos;
    speedx = PApplet.parseInt(random(1,5));    speedy = PApplet.parseInt(random(1,5));
    movex = PApplet.parseInt(random(2));    movey = PApplet.parseInt(random(2));
    if(movex==0) movex = -1;
    else movex = 1;
    if(movey==0) movey = -1;
    else movey = 1;
    boox = PApplet.parseInt(random(2));    booy = PApplet.parseInt(random(2));
    if(movex==1){      jumpx = 10;      dirx = -1;    }
    else{       jumpx = 5;      dirx = 1;    }
    if(movey==1){      jumpy = 10;      diry = -1;    }
    else{       jumpy = 5;      diry = 1;    }
    //this.setImages("http://apod.nasa.gov/apod.rss");
  }
  
  public boolean BounceFull(){
    if(bounces==0)      return true;
    else    return false;
  }

  public void move() {
    //if not full sized, increase size
    if(size<rsize){
      size++;
    }
    //once full sized, track movement
      if(newx<0){         movex = 1;      newx+=(movex*speedx);        bounces--;  }
      else if(newx>width-size){         movex = -1;      newx+=(movex*speedx);        bounces--;  }
      else newx+=(movex*speedx);
      
      if(newy<0){         movey = 1;      newy+=(movey*speedy);        bounces--;  }
      else if(newy>height-size){         movey = -1;      newy+=(movey*speedy);        bounces--;  }
      else newy+=(movey*speedy);
      
      /*
      if(jumpx<newx-(size/2)+5){
        xdir=1;
        jumpx+=(xdir);
      }
      else if(jumpx>newx+(size/2)-5){
        xdir=-1;
        jumpx+=(xdir);
      }
      //else jumpx+=(xdir);
      
      if(jumpy<newy-(size/2)+5){
        ydir=1;
        jumpy+=(ydir);
      }
      else if(jumpy>newy+(size/2)-5){
        ydir=-1;
        jumpy+=(ydir);
      }
      else jumpy+=(ydir);*/
  }

  public void display() {
      //beginShape();
        stroke(0,128,128);
        fill(0,128,128);
        rect(newx,newy,size,size,12);
        //vertex(newx-(size/2), newy+(size/2));
        //vertex(newx+(size/2), newy+(size/2));
        //vertex(newx+(size/2), newy-(size/2));
        //vertex(newx-(size/2), newy-(size/2));
      //endShape(CLOSE);
      //breakShape();
      //beginShape();
        fill(0);
        //image(imgs[int(random(imgs.length))],newx+jumpx,newy+jumpy);
        rect(newx+jumpx,newy+jumpy,size-15,size-15,6);
        //vertex((newx+newjumpx)-(size/2), (newy+newjumpy)+(size/2));   //tl
        //vertex((newx-newjumpx)+(size/2), (newy+newjumpy)+(size/2));   //tr
        //vertex((newx-newjumpx)+(size/2), (newy-newjumpy)-(size/2));   //br
        //vertex((newx+newjumpx)-(size/2), (newy-newjumpy)-(size/2));   //bl
      //endShape(CLOSE);
  }
  
  /*public void setImages(String urlz){
    String url = urlz;  
    XML rss = loadXML(url);
    XML[] titleXMLElements = rss.getChildren("channel/item/description");
    imgs = new PImage[titleXMLElements.length];
    for (int i = 0; i < titleXMLElements.length; i++) {  
        String description = titleXMLElements[i].getContent();
        String src = "";
        for(int j=70; j<124; j++){
          src += description.charAt(j);
        }
        println(src);
        imgs[i] = loadImage(src);
        imgs[i].resize(75,0);
    }
  }*/
  
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "BouncingSquares" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
